import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by Yang on 2017/2/27.
 */
public class Judge_Flag extends HttpServlet{

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException,ServletException{
        response.setContentType("text/gson;charset=utf-8");

        Gson gson =new Gson();
        String data = request.getParameter("data");//接收用户SessionID 字符串定义data=SessionID

        //用SessionID获取用户的ID
        HttpSession session = MySessionContext.getSession(data);
        String id = session.getAttribute("id").toString();


        //提取用户的flag标志位  flag 0（未收到好友请求）|1 （收到好友请求）
        UserDAO userDAO = new UserDAOImp();
        int flag = userDAO.getUser(id).getFlag();


        //将结果输入到result中 转化为gson返回给客户端
        Result result = new Result();
        result.setStatus("Success");
        result.setContent(flag);
        String jsonString = gson.toJson(result);
        response.getWriter().print(jsonString);
        response.getWriter().close();


    }

    public void doGet(HttpServletRequest request,HttpServletResponse response)
            throws IOException, ServletException {
            doPost(request,response);
    }
}
