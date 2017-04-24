import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;

/**
 * Created by Yang on 2017/3/3.
 */
public class Ask_keyid extends HttpServlet{
    public void doPost(HttpServletRequest request, HttpServletResponse response)
        throws IOException,ServletException{
        Gson gson = new Gson();
        Result result = new Result();
        String data = request.getParameter("data");
        int identity = Integer.valueOf(data);


        //建立U_A_T表的数据库连接
        UserAuthDAO userAuthDAO = new UserAuthDAOImp();

        //通过identity获取userAuth对象
        UserAuth userAuth = userAuthDAO.getUserAuth(identity);

        if(userAuth.getKey_id()!=null) {

            //获取生成token的字段值
            String special_field = "10.18636,194.41002";
            Timestamp Sys_Time = new Timestamp(System.currentTimeMillis());

            System.out.println(Sys_Time);

            String tokenSting[] = {userAuth.getUser_id(), userAuth.getFriend_id(), userAuth.getFile_name(), Sys_Time.toString(), special_field};
            String token = MD5.getMD5(tokenSting[0] + tokenSting[1] + tokenSting[2] + tokenSting[3] + tokenSting[4]);

            System.out.println("token:" + token);

            TokenInfo tokenInfo = new TokenInfo();
            tokenInfo.setUser_id(userAuth.getUser_id());
            tokenInfo.setFriend_id(userAuth.getFriend_id());
            tokenInfo.setFile_name(userAuth.getFile_name());
            tokenInfo.setKey_id(userAuth.getKey_id());
            tokenInfo.setSys_time(Sys_Time.toString());
            tokenInfo.setToken(token);
            result.setContent(tokenInfo);
        }
        else {
            result.setContent("null");
        }

        result.setStatus("Success");
        String gsonString = gson.toJson(result);
        System.out.println(gsonString);
        response.getWriter().print(gsonString);
        response.getWriter().close();



    }

    public void doGet(HttpServletRequest request, HttpServletResponse response)
        throws IOException,ServletException{
        doPost(request,response);
    }
}
