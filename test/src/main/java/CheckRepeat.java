import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Yang on 2017/3/8.
 */
public class CheckRepeat extends HttpServlet {
    public  void  doPost(HttpServletRequest request, HttpServletResponse response)
        throws IOException,ServletException{

        request.setCharacterEncoding("utf-8");
        response.setContentType("text/gson;utf-8");

        String data = request.getParameter("data");

        UserAuthDAO userAuthDAO = new UserAuthDAOImp();

        List<UserAuth> userAuths = userAuthDAO.getAllSameKey(data);
        Iterator<UserAuth> iterator = userAuths.iterator();
        int count = 0 ;
        while (iterator.hasNext()){
            UserAuth userAuth = iterator.next();
            if(userAuth.getIsHandle()==1){
                response.getWriter().print("NO");
                break;
            }
            count++;
        }
        if (count == userAuths.size()){
            response.getWriter().print("YES");
        }
        response.getWriter().close();

    }

    public void doGet(HttpServletRequest request,HttpServletResponse response)
        throws IOException,ServletException{
        doPost(request,response);
    }
}
