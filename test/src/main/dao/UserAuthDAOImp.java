import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Yang on 2017/3/1.
 */
public class UserAuthDAOImp implements UserAuthDAO {
    Connection connection ;
    PreparedStatement preparedStatement;
    ResultSet resultSet;

    public void addUserAuth(UserAuth userAuth) {
        UserDBConnection userDBConnection = new UserDBConnection();
        String sql = "insert into user_authorization_table value(?,?,?,?,?,?,?)";
        try {
            connection = userDBConnection.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,userAuth.getIdentity());
            preparedStatement.setString(2,userAuth.getUser_id());
            preparedStatement.setString(3,userAuth.getFriend_id());
            preparedStatement.setString(4,userAuth.getFile_name());
            preparedStatement.setString(5,userAuth.getKey_id());
            preparedStatement.setInt(6,userAuth.getIsHandle());
            preparedStatement.setTimestamp(7,userAuth.getShare_time());
            preparedStatement.executeUpdate();

        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            userDBConnection.close();
        }

    }


    public void deleteUserAuth(int identity) {
        UserDBConnection userDBConnection = new UserDBConnection();
        String sql = "delete from user_authorization_table where Identity=?";
        try{
            connection = userDBConnection.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,identity);
            preparedStatement.executeUpdate();


        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            userDBConnection.close();
        }
    }

    public void updateUserAuth(UserAuth userAuth) {
        UserDBConnection userDBConnection =new UserDBConnection();
        String sql ="update user_authorization_table set User_id=?,Frined_id=?,File_name=?,Kdy_id=? where Identity=?";
        try{
            connection = userDBConnection.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,userAuth.getUser_id());
            preparedStatement.setString(2,userAuth.getFriend_id());
            preparedStatement.setString(3,userAuth.getFile_name());
            preparedStatement.setString(4,userAuth.getKey_id());
            preparedStatement.setInt(5,userAuth.getIdentity());
            preparedStatement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            userDBConnection.close();
        }
    }

    public void updateIsHandle(UserAuth userAuth) {
        UserDBConnection userDBConnection = new UserDBConnection();
        String sql = "update user_authorization_table set IsHandle=? where Identity=?";
        try{
            connection = userDBConnection.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,userAuth.getIsHandle());
            preparedStatement.setInt(2,userAuth.getIdentity());
            preparedStatement.executeUpdate();

        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            userDBConnection.close();
        }
    }

    public UserAuth getUserAuth(int identity) {
        UserDBConnection userDBConnection = new UserDBConnection();
        String sql ="select * from user_authorization_table where Identity=?";
        UserAuth userAuth = new UserAuth();
        try{
            connection = userDBConnection.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,identity);
            resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                userAuth = new UserAuth(resultSet.getInt(1),resultSet.getString(2),
                        resultSet.getString(3),resultSet.getString(4),resultSet.getString(5),
                        resultSet.getInt(6),resultSet.getTimestamp(7));

            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            userDBConnection.close();
        }
        return userAuth;
    }

    public List<UserAuth> getAllSender(String friend_id) {
        UserDBConnection userDBConnection = new UserDBConnection();
        String sql="select * from user_authorization_table where Friend_id=?";
        List<UserAuth> userAuths = new ArrayList<UserAuth>();
        try{
            connection = userDBConnection.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,friend_id);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                UserAuth userAuth = new UserAuth(resultSet.getInt(1),resultSet.getString(2),
                        resultSet.getString(3),resultSet.getString(4),resultSet.getString(5),
                        resultSet.getInt(6),resultSet.getTimestamp(7));
                userAuths.add(userAuth);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            userDBConnection.close();
        }


        return userAuths;
    }

    public List<UserAuth> getAllReceiver(String user_id) {
        UserDBConnection userDBConnection = new UserDBConnection();
        String sql = "select * from user_authorization_table where User_id=?";
        List<UserAuth> userAuths = new ArrayList<UserAuth>();
        try{
            connection = userDBConnection.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,user_id);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                UserAuth userAuth = new UserAuth(resultSet.getInt(1),resultSet.getString(2),
                        resultSet.getString(3),resultSet.getString(4),resultSet.getString(5),
                        resultSet.getInt(6),resultSet.getTimestamp(7));
                userAuths.add(userAuth);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            userDBConnection.close();
        }

        return userAuths;
    }

    public List<UserAuth> getAll() {
        UserDBConnection userDBConnection = new UserDBConnection();
        String sql = "select * from user_authorization_table ";
        List<UserAuth> userAuths = new ArrayList<UserAuth>();
        try{
             connection = userDBConnection.getConnection();
             preparedStatement = connection.prepareStatement(sql);
             resultSet = preparedStatement.executeQuery();
             while (resultSet.next()){
                 UserAuth userAuth = new UserAuth(resultSet.getInt(1),resultSet.getString(2),
                         resultSet.getString(3),resultSet.getString(4),resultSet.getString(5),
                         resultSet.getInt(6),resultSet.getTimestamp(7));
                 userAuths.add(userAuth);
             }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            userDBConnection.close();
        }
        return userAuths;
    }

    public List<UserAuth> getAllSameKey(String key_id) {

        UserDBConnection userDBConnection = new UserDBConnection();
        String sql = "select * from user_authorization_table where Key_id=?";

        List<UserAuth>  userAuths = new ArrayList<UserAuth>();
        try{
            connection = userDBConnection.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,key_id);
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                UserAuth userAuth = new UserAuth(resultSet.getInt(1),resultSet.getString(2),
                        resultSet.getString(3),resultSet.getString(4),resultSet.getString(5),
                        resultSet.getInt(6),resultSet.getTimestamp(7));

                userAuths.add(userAuth);
            }

        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            userDBConnection.close();
        }
        return userAuths;
    }
}
