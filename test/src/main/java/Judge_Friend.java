import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Yang on 2017/2/23.
 */
public class Judge_Friend extends HttpServlet{
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException,ServletException
    {
        response.setContentType("text/json;charset=utf-8");
        String friendID=request.getParameter("data");

        System.out.println(friendID);//输出好友ID

        UserDAO userDAO=new UserDAOImp();
        Gson gson=new Gson();
        Result result=new Result();
        if(userDAO.isExist(friendID))
        {
            result.setContent(friendID+"_"+userDAO.getUser(friendID).getUserName());
        }
        else {
            result.setContent("unavailable");
        }
        result.setStatus("Success");
        String jsonString=gson.toJson(result);

        System.out.println(jsonString);//输出返回结果

        response.getWriter().print(jsonString);
        response.getWriter().close();

    }
    public void doGet(HttpServletRequest request,HttpServletResponse response)
        throws IOException,ServletException
    {
        doPost(request,response);
    }
}
