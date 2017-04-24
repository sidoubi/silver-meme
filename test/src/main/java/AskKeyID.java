import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Yang on 2017/3/7.
 */
public class AskKeyID extends HttpServlet{

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException,ServletException {

        String data = request.getParameter("data");
        int identity = Integer.valueOf(data);

        //建立U_A_T表的数据库连接
        UserAuthDAO userAuthDAO = new UserAuthDAOImp();

        //通过identity获取userAuth对象
        UserAuth userAuth = userAuthDAO.getUserAuth(identity);
        String key_id = "NULL";
        if(userAuth.getKey_id()!=null){
            key_id = userAuth.getKey_id();
        }

        response.getWriter().print(key_id);
        response.getWriter().close();
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException,ServletException{
        doPost(request,response);
    }

}
