import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Yang on 2017/2/20.
 */
public class Judge_EmpPhone extends HttpServlet{
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException,ServletException
    {
        response.setContentType("text/json;charset=UTF-8");
        String data=request.getParameter("data");
        System.out.println(data);
        UserDAO userDAO=new UserDAOImp();
        Result result=new Result();
        Gson gson=new Gson();
        try {
            if (userDAO.isExist(data)) {
                result.setContent("unavailable");
                System.out.println("不可以注册");
            }
            else {
                result.setContent("available");
                System.out.println("能注册");
            }
            result.setStatus("Success");
        }catch (Exception e){
            result.setStatus("Fail");
            e.printStackTrace();
        }
        String jsonString=gson.toJson(result);
        System.out.println(result);//测试序列化
        response.getWriter().print(jsonString);
        response.getWriter().close();
    }
    public void doGet(HttpServletRequest request,HttpServletResponse response)
            throws ServletException,IOException
    {
        doPost(request,response);
    }
}
