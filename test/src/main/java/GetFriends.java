import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Yang on 2017/2/21.
 */
public class GetFriends extends HttpServlet{
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException,ServletException
    {
        String data=request.getParameter("data");

        response.setContentType("text/json;charset=utf-8");
        FriendDAO friendDAO=new FriendDAOImp();
        Gson gson=new Gson();
        Result result=new Result();

        HttpSession session=MySessionContext.getSession(data);
        String id=session.getAttribute("id").toString();

//      System.out.println(request.getSession().getId());
        System.out.println(id);
        //从session获取用户id
        List<Friend> list=friendDAO.getAllFriend(id);
        List<FriendsListResult> friendsList=new ArrayList<FriendsListResult>();
        Iterator<Friend> iterator=list.iterator();
        while (iterator.hasNext())
        {
            Friend friend=iterator.next();
            FriendsListResult friendsListResult=new FriendsListResult();
            friendsListResult.setFriendID(friend.getFriendID());
            friendsListResult.setFriendName(friend.getFriendname());
            friendsList.add(friendsListResult);
        }
//        String jsonContent=gson.toJson(friendsList);
//        System.out.println(jsonContent);

        result.setStatus("Success");
        result.setContent(friendsList);
        String jsonString=gson.toJson(result);
        System.out.println(jsonString);

        response.getWriter().print(jsonString);
        response.getWriter().close();

    }
    public void doGet(HttpServletRequest request,HttpServletResponse response)
            throws IOException,ServletException
    {
        doPost(request,response);
    }

}
