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
 * Created by Yang on 2017/2/23.
 */
public class AddFriend extends HttpServlet{

    public void doPost(HttpServletRequest request,HttpServletResponse response)
            throws IOException,ServletException
    {
        response.setContentType("text/json;charset=utf-8");

        int isdeal = 0;
        Result result = new Result();//初始化返回结果类
        Gson gson=new Gson();
        String data = request.getParameter("data");
        addFriendInfo addfriendInfo = gson.fromJson(data,addFriendInfo.class);//接收客户端传来的数据 将gson转化为类


        //输出data内容
        System.out.println("data:"+data);


        //建立DAO层连接
        RequesterDAO requesterDAO = new RequesterDAOImp();
        UserDAO userDAO = new UserDAOImp();

        //从addfriendInfo对象中提取出请求加好友的ID，以及本用户的SeesionID
        String addFriendID = addfriendInfo.getAddFriendID();
        HttpSession UserSessionID = MySessionContext.getSession(addfriendInfo.getUserSessionID());

        //获取本用户ID
        String UserID = UserSessionID.getAttribute("id").toString();

        User addUser = userDAO.getUser(addFriendID);//获取被请求好友对象
        int flag = addUser.getFlag();//得到被请求好友的flag位
        //判断该好友是否在改用户的好友列表中
        //读取该用户的好友列表
        FriendDAO friendDAO = new FriendDAOImp();

        List<Friend> friends = new ArrayList<Friend>();
        friends = friendDAO.getAllFriend(UserID);
        Iterator<Friend> iterator = friends.iterator();
        int syn = friends.size();

        String state = null;
        while(syn>0){

            Friend friendtemp = iterator.next();
            String tempID = friendtemp.getFriendID();
            if (tempID.equals(addFriendID)){
                state = "exist";
                break;
            }
            syn--;
        }

        state = "NULL";
        if(state.equals("exist")){
            response.getWriter().print(state);
            response.getWriter().close();
        }else{
            flag = 1;//设置被加好友的flag标志位为1
            //将申请者的ID和sessionID 及被申请者的ID存入到requestfriend表中
            Requester requester = new  Requester(UserID,UserSessionID.getId(),isdeal,addFriendID);
            requesterDAO.addRequester(requester);


            addUser.setFlag(flag);
            userDAO.updateFlag(addUser);
            //输出是否处理标志位
            System.out.println("是否处理标志位："+requester.getIsdeal());
            while(true){
                requester = requesterDAO.getRequester(UserID,addFriendID);
                isdeal = requester.getIsdeal();
                if(1==isdeal||-1==isdeal){
                    System.out.println("跳出循环");
                    break;
                }
            }


            if(1==isdeal){
                state = "agree";
                User user = userDAO.getUser(UserID);
                Friend frienduser =new Friend(UserID,user.getUserName(),addFriendID,addUser.getUserName());
                Friend friendadduser = new Friend(addFriendID,addUser.getUserName(),UserID,user.getUserName());
                friendDAO.addFriend(frienduser);
                friendDAO.addFriend(friendadduser);
                System.out.println(frienduser.toString()+"执行成功");
            }else {
                state = "disagree";
            }

            response.getWriter().print(state);
            response.getWriter().close();


            //删除数据库添加内容并将标志位复位
            System.out.println("删除数据库中纪录");
            requesterDAO.deleteRequester(requester.getRequesterID(),requester.getUserID());
            flag=0;
            addUser.setFlag(flag);
            userDAO.updateFlag(addUser);
        }

    }

    public void doGet(HttpServletRequest request,HttpServletResponse response)
            throws IOException,ServletException
    {
        doPost(request,response);
    }
}
