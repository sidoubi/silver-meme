import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Yang on 2017/2/20.
 */
public class Register extends HttpServlet{
    public void doPost(HttpServletRequest request, HttpServletResponse response)
        throws IOException,ServletException
    {
        Result result=new Result();
        //返回类型
        UserDAO userDAO=new UserDAOImp();
        //数据库接口
        response.setContentType("text/json;charset=UTF-8");
        request.setCharacterEncoding("utf-8");
        String data=request.getParameter("data");

        System.out.println(data);//输出接收

        Gson gson=new Gson();
        User user=gson.fromJson(data,User.class);
        System.out.println(user.getEmp_phone()+user.getEmp_password()+user.getUserName()+user.getIpAddress());
        //接收的json转用户对象

        try {
//            if (!userDAO.isExist(user.getEmp_phone())) {
//                userDAO.addUser(user);
//                result.setContent("register success");
//            }
//            else {
//
//            }
            if(user.getUserName()==null)
            {
                user.setUserName(user.getEmp_phone());
            }
            userDAO.addUser(user);
            result.setContent("register success");
            result.setStatus("Success");
        } catch (Exception e)
        {
            result.setStatus("Fail");
            e.printStackTrace();
        }
        String jsonString=gson.toJson(result);
        System.out.println(jsonString);//输出result测试序列化
        response.getWriter().print(jsonString);
        response.getWriter().close();
    }
    public void doGet(HttpServletRequest request,HttpServletResponse response)
            throws ServletException,IOException
    {
        doPost(request,response);
    }

}
