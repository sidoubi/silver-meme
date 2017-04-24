import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;




/**
 * Created by Yang on 2017/2/17.
 */
public class Login extends HttpServlet{
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        response.setContentType("text/json;charset=UTF-8");
        Result result = new Result();
        Gson gson = new Gson();
        HttpSession session = request.getSession();

        String sessionID=session.getId();
        System.out.println(sessionID);


        try {
            String data = request.getParameter("data");

            System.out.println(data);//输出接收的data

            User user = gson.fromJson(data,User.class);


            UserDAO userDAO = new UserDAOImp();
            String id = user.getEmp_phone();//key参数
            session.setAttribute("id",id);
            String userid = session.getAttribute("id").toString();

            System.out.println(userid);

            String password = user.getEmp_password(); //password参数
            String ip=request.getRemoteAddr();

            user.setIpAddress(ip);
            user.setSessionID(sessionID);
            //修改IP和SessionID

            if (userDAO.isExist(id)) {
                if (password.equals(userDAO.getUser(id).getEmp_password())) {

                    result.setContent("32442134_"+sessionID);
                    user.setUserName(userDAO.getUser(id).getUserName());
                    userDAO.updateUser(user);

                    System.out.println("登陆成功");
                    System.out.println(id);
                    System.out.println(password);
                    //输出结果
                } else {
                    result.setContent("passworderror");

                    System.out.println("登陆失败");
                    //输出结果
                }
            } else {
                result.setContent("phonenull");

                System.out.println("错误");
                //输出id不存在
            }

            System.out.print("ok\n");
            //测试程序到达
            result.setStatus("Success");
//            String jsonString=gson.toJson(result);
//            System.out.println(jsonString);
//            response.getWriter().print(jsonString);
//            response.getWriter().close();
        } catch (Exception e) {
            result.setStatus("服务器异常");
            e.printStackTrace();
        }
        String jsonString=gson.toJson(result);
        System.out.println(jsonString);
        response.getWriter().print(jsonString);
        response.getWriter().close();
    }




    public void doPost(HttpServletRequest request,HttpServletResponse response)
        throws ServletException,IOException
    {
        doGet(request,response);
    }
}
