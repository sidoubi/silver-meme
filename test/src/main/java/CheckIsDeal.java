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
public class CheckIsDeal extends HttpServlet{

    public class DealFlag{
            private  String UserSession;
            private  String RequesterID;
            private  int isdeal;


        public String getUserSession() {
            return UserSession;
        }

        public void setUserSession(String userSession) {
            UserSession = userSession;
        }

        public int getIsdeal() {
            return isdeal;
        }

        public void setIsdeal(int isdeal) {
            this.isdeal = isdeal;
        }

        public String getRequesterID() {
            return RequesterID;
        }

        public void setRequesterID(String requesterID) {
            RequesterID = requesterID;
        }
    }


    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException,ServletException{

        response.setContentType("text/json;charset=utf-8");

        Gson gson = new Gson();
        String data = request.getParameter("data");
        DealFlag dealFlag = gson.fromJson(data,DealFlag.class);

        //通过用户SessionID获取用户ID
        HttpSession session = MySessionContext.getSession(dealFlag.getUserSession());
        String UserID = session.getAttribute("id").toString();

        //获取请求者ID
        String requsterID = dealFlag.getRequesterID();

        //获取isdeal
        int isdeal = dealFlag.getIsdeal();

        //建立DAO层访问
        RequesterDAO requesterDAO = new RequesterDAOImp();
        Requester requester = new Requester();
        requester.setIsdeal(isdeal);

        //输出isdeal值
        System.out.println(isdeal);



        requester.setRequesterID(requsterID);
        requester.setUserID(UserID);

        System.out.println(requester.toString());

        requesterDAO.updateIsDeal(requester);


        response.getWriter().print("yes");
        response.getWriter().close();

    }

    public void doGet(HttpServletRequest request, HttpServletResponse response)
        throws IOException,ServletException{
        doPost(request,response);
    }
}

