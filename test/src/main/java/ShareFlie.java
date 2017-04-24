import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Timestamp;

/**
 * Created by Yang on 2017/3/2.
 */
public class ShareFlie extends HttpServlet{

    public void doPost(HttpServletRequest request, HttpServletResponse response)
        throws IOException,ServletException{

        UserAuthDAO userAuthDAO = new UserAuthDAOImp();
        response.setContentType("text/json;charset=UTF-8");
        request.setCharacterEncoding("utf-8");
        String data = request.getParameter("data");

        Gson gson = new Gson();
        System.out.println("---->"+data);
        UserAuth userAuth = gson.fromJson(data,UserAuth.class);//Session?|User_ID?

        //输出sessionID
        System.out.println(data);

        HttpSession UserSessionID = MySessionContext.getSession(userAuth.getUser_id());
        String UserID = (String)UserSessionID.getAttribute("id");



        String[] FriendID = userAuth.getFriend_id().split("_");
        userAuth.setUser_id(UserID);
        userAuth.setIsHandle(0);
        userAuth.setShare_time(new Timestamp(System.currentTimeMillis()));
        int count = Integer.valueOf(FriendID[0]);




        for (int i = 1 ;count>0;count--) {
            userAuth.setFriend_id(FriendID[i]);
            userAuthDAO.addUserAuth(userAuth);
            i++;
        }
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response)
        throws IOException,ServletException{
        doPost(request,response);

    }
}
