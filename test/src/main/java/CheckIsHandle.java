import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Yang on 2017/3/2.
 */
public class CheckIsHandle extends HttpServlet {

    public void doPost(HttpServletRequest request, HttpServletResponse response)
        throws IOException,ServletException{
        response.setContentType("text/json;charset=utf-8");


        UserAuth userAuth =new UserAuth();
        int ishandle;
        int identity;
        String User_ip = null;


        String data = request.getParameter("data");//接收用户传输的identity+ishandel状态位
        String[] temp = data.split("_");
        identity = Integer.valueOf(temp[0]);
        //建立数据库连接
        UserAuthDAO userAuthDAO = new UserAuthDAOImp();
        UserDAO userDAO = new UserDAOImp();

        //通过获取的identity+ishandle值进行判断
        if (temp[1].equals("1")){
            ishandle = 1;
            userAuth = userAuthDAO.getUserAuth(identity);
            userAuth.setIsHandle(ishandle);
            userAuthDAO.updateIsHandle(userAuth);

            //获取发送者IP,identity给接收端 数据格式为identity_IP
            String user_id = userAuth.getUser_id();
            User user = userDAO.getUser(user_id);
            User_ip = user.getIpAddress();

        }

        //发送结果给B端
        response.getWriter().print(User_ip);
        response.getWriter().close();

    }

    public void doGet(HttpServletRequest request,HttpServletResponse response)
        throws IOException,ServletException{
        doPost(request,response);
    }


}
