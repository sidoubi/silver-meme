import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Yang on 2017/3/2.
 */
public class Judge_User extends HttpServlet{

    public void  doPost(HttpServletRequest request, HttpServletResponse response)
        throws IOException,ServletException{
        response.setContentType("text/json;charset=utf-8");
        request.setCharacterEncoding("utf-8");
        Result result = new Result();
        result.setStatus("Success");
        //获取客户端json数据
        Gson gson = new Gson();
        String data = request.getParameter("data");

        //通过SessionID获取用户ID
        HttpSession session = MySessionContext.getSession(data);
        String id = session.getAttribute("id").toString();

        //建立数据库连接
        UserAuthDAO userAuthDAO = new UserAuthDAOImp();


        //获取所有发送给该id的文件传输纪录
        List<UserAuth> userAuthList = userAuthDAO.getAllSender(id);
        List<FileResult> fileResults = new ArrayList<FileResult>();
        Iterator<UserAuth> iterator = userAuthList.iterator();
        int flag = 0 ;
        if(userAuthList.size()!=0){
            while(iterator.hasNext()){
                UserAuth userAuth = iterator.next();
                if(0==userAuth.getIsHandle()) {
                    FileResult fileResult = new FileResult();
                    fileResult.setUser_id(userAuth.getUser_id());
                    fileResult.setFile_name(userAuth.getFile_name());
                    String Identity = String.valueOf(userAuth.getIdentity());
                    fileResult.setIdentity(Identity);
                    String shareTime = userAuth.getShare_time().toString();
                    fileResult.setShare_time(shareTime);
                    fileResults.add(fileResult);
                }
            }
            if (fileResults.size()>0){
                flag = 1;
                ArrayList<Object> list = new ArrayList<Object>(2);
                list.add(flag);
                list.add(fileResults);
                result.setContent(list);
            }
            else{
                result.setContent(flag+"_");
            }
        }else{
            result.setContent(flag+"_");
        }

        String gsonString = gson.toJson(result);
        //System.out.println(gsonString);//输出发送文件的用户ID和发送的相应的文件名


        //返回发送者 文件名列表给接收端
        response.getWriter().print(gsonString);
        response.getWriter().close();



    }

    public void doGet(HttpServletRequest request, HttpServletResponse response)
        throws IOException,ServletException{
        doPost(request,response);
    }
}
