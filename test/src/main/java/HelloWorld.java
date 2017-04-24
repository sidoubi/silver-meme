/**
 * Created by Yang on 2017/1/12.
 */

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class HelloWorld extends HttpServlet{
//    private String message;
//    public void init() throws ServletException
//    {
//        message = "HelloWorld";
//    }
    public void doGet(HttpServletRequest request,HttpServletResponse response)
        throws ServletException, IOException
    {
        response.setContentType("text/html;charset=utf-8");

//        PrintWriter out = response.getWriter();
//        out.println("<h1>"+message+"</h1>");
//        response.getWriter().write("zhongguo");


        String name =new String(request.getParameter("name").getBytes("ISO8859-1"),"UTf-8");
        String password =new String(request.getParameter("password"));
        String docType ="<!DOCTYPE html> \n";
        UserDAO userDAO=new UserDAOImp();
        if (userDAO.isExist(name))
        {

            if (password.equals(userDAO.getUser("root").getEmp_password()))
            {

            response.getWriter().write("SUCCESS");
//            response.setIntHeader("Refresh",3);
            Calendar calendar=Calendar.getInstance();
            Date gettime=calendar.getTime();
            SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String nowtime=dateFormat.format(gettime);
            PrintWriter out=response.getWriter();
            out.println(docType
                    +"<h1 align=\"center\">当前时间是："+nowtime+"</h1>\n"
            );
            }
            else
                response.getWriter().write("password error");
        }
        else
        {
            response.getWriter().write("Phone NULL，3秒后返回主页");
            response.setHeader("refresh","3;url=/index.jsp");

        }
        System.out.println(name);
        System.out.println(password);
//        UserDAO userDAO=new UserDAOImp();
//        User li=new User("1","10086","李","***");
//        User liu=new User("2","10086","刘","***");
//        User niu=new User("2","10088","牛","***");
//        userDAO.addUser(li);
//        userDAO.addUser(liu);
//
//        List<User>list=userDAO.getAllUser();
//        UserDAOImp.printUsers(list);
//
//        userDAO.updateUser(niu);
//
//        User user=userDAO.getUser("2");
//        System.out.println("用户ID："+user.getUserID()+"  "+"用户密码： "+user.getPassword()
//                +"  "+"用户名： "+user.getUserName()+"  "+"IP地址:  "+user.getIpAddress()+"\n");
//
//        userDAO.deleteUser("1");
        FriendDAO friendDAO=new FriendDAOImp();
//        Friend friend=new Friend("root","",
//                "15002789760","");
//        friendDAO.addFriend(friend);
//        Friend friend1=new Friend("root","李白",
//                "15002789760","李白");
//        friendDAO.updateFriend(friend1);
//        Friend friend2=friendDAO.getFriend("root","15002789760");
//        System.out.println(friend2.getUsername()+friend2.getFriendname());

//        List<Friend> list= friendDAO.getAllFriend("18727765918");
//        FriendDAOImp.printFriend(list);
//        friendDAO.deleteFriend("root","15002789760");


    }
    public void destroy()
    {

    }

}
