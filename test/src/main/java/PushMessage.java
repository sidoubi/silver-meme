import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Yang on 2017/2/23.
 */
public class PushMessage extends HttpServlet{
    public void doPost (HttpServletRequest request, HttpServletResponse response)
            throws IOException,ServletException{

        response.setContentType("text/json;charset=utf-8");
        Gson gson =new Gson();
        String data = request.getParameter("data");
        HttpSession session = MySessionContext.getSession(data);
        String id = session.getAttribute("id").toString();
        RequesterDAO requesterDAO = new RequesterDAOImp();
        Result result = new Result();
        result.setStatus("Success");

        //获取所有请求自己好友的ID
        List<Requester> requesterList = requesterDAO.getAllResponse(id);
        //int len = requesterList.size();//获取list表中的用户总数  便于客户端进行判断输出结果的循环次数
        Iterator<Requester> iterator = requesterList.iterator();
        while(iterator.hasNext()){
            Requester requester = iterator.next();
            if(0==requester.getIsdeal()) {
                result.setContent(requester.getRequesterID());
                String jsonString = gson.toJson(result);
                response.getWriter().print(jsonString);
            }

            //每次客户端提取len对其进行判断
            //若len不小于等于0 则继续进行循环输出提示框
            //若len小于或等于0 则停止输出
            //len--;
        }
        response.getWriter().close();
    }

    public void doGet(HttpServletRequest request,HttpServletResponse response)
        throws IOException,ServletException{
        doPost(request,response);
    }


}
