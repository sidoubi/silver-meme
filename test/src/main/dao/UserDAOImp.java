import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Yang on 2017/2/13.
 */
public class UserDAOImp implements UserDAO {
    Connection connection;
    PreparedStatement preparedStatement;
    ResultSet resultSet;


    public void addUser(User user) {
        String sql="insert into userinformation value(?,?,?,?,?,?)";
        UserDBConnection userDBConnection=new UserDBConnection();
        try {
            connection=userDBConnection.getConnection();
            preparedStatement=connection.prepareStatement(sql);
            preparedStatement.setString(1,user.getEmp_phone());
            preparedStatement.setString(2,user.getEmp_password());
            preparedStatement.setString(3,user.getUserName());
            preparedStatement.setString(4,user.getIpAddress());
            preparedStatement.setString(5,user.getSessionID());
            preparedStatement.setInt(6,user.getFlag());
            preparedStatement.executeUpdate();
            System.out.println("添加用户成功");
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            userDBConnection.close();
        }
    }

    public void deleteUser(String id) {
        String sql="delete from userinformation where UserID=?";
        UserDBConnection userDBConnection=new UserDBConnection();
        try{
            connection=userDBConnection.getConnection();
            preparedStatement=connection.prepareStatement(sql);
            preparedStatement.setObject(1,id);
            preparedStatement.executeUpdate();
            System.out.println("删除用户成功");

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            userDBConnection.close();
        }


    }

    public void updateUser(User user) {
        String sql="update userinformation set Password=?,Name=?,IPAddr=?,SessionID=?,flag=? where UserID=?";
        UserDBConnection userDBConnection=new UserDBConnection();
        try
        {
            connection=userDBConnection.getConnection();
            preparedStatement=connection.prepareStatement(sql);
            preparedStatement.setString(1,user.getEmp_password());
            preparedStatement.setString(2,user.getUserName());
            preparedStatement.setString(3,user.getIpAddress());
            preparedStatement.setString(4,user.getSessionID());
            preparedStatement.setInt(5,user.getFlag());
            preparedStatement.setString(6,user.getEmp_phone());
            preparedStatement.executeUpdate();
            System.out.println("修改用户成功");
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            userDBConnection.close();
        }

    }

    public void updateFlag(User user){
        String sql="update userinformation set flag=? where UserID=?";
        UserDBConnection userDBConnection = new UserDBConnection();
        try{
            connection = userDBConnection.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,user.getFlag());
            preparedStatement.setString(2,user.getEmp_phone());
            preparedStatement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            userDBConnection.close();
        }
    }

    public List<User> getAllUser() {
        List<User> users=new ArrayList<User>();
        String sql="select * from userinformation";
        UserDBConnection userDBConnection=new UserDBConnection();
        try
        {
            connection=userDBConnection.getConnection();
            preparedStatement=connection.prepareStatement(sql);
            resultSet=preparedStatement.executeQuery();
            while(resultSet.next())
            {
                User user=new User(resultSet.getString(1),resultSet.getString(2),
                        resultSet.getString(3),resultSet.getString(4),
                        resultSet.getString(5),resultSet.getInt(6));
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            userDBConnection.close();
        }
        return users;
    }

    public static void printUsers(List<User> list)
    {
        Iterator<User> iterator=list.iterator();
        while (iterator.hasNext())
        {
            User user=iterator.next();
            System.out.println("用户ID："+user.getEmp_phone()+"  "+"用户密码： "+user.getEmp_password()
            +"  "+"用户名： "+user.getUserName()+"  "+"IP地址:  "+user.getIpAddress()+"\n");
        }
    }

    public User getUser(String id) {
        String sql="select * from userinformation where UserID=?";
        User user=null;
        UserDBConnection userDBConnection=new UserDBConnection();
        try {
            connection=userDBConnection.getConnection();
            preparedStatement=connection.prepareStatement(sql);
            preparedStatement.setObject(1,id);
            resultSet=preparedStatement.executeQuery();
            if (resultSet.next())
            {
                user=new User(resultSet.getString(1),resultSet.getString(2),
                        resultSet.getString(3),resultSet.getString(4),
                        resultSet.getString(5),resultSet.getInt(6));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            userDBConnection.close();
        }
        return user;
    }

    public boolean isExist(String id){
        UserDBConnection userDBConnection=new UserDBConnection();
        String sql="select * from userinformation where UserID=?";
        try{
            connection=userDBConnection.getConnection();
            preparedStatement=connection.prepareStatement(sql);
            preparedStatement.setObject(1,id);
            resultSet=preparedStatement.executeQuery();
            if (resultSet.next())
            {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            userDBConnection.close();
        }
        return false;
    }


}
