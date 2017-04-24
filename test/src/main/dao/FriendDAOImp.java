/**
 * Created by Yang on 2017/2/20.
 */

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class FriendDAOImp implements FriendDAO {
    Connection connection;
    PreparedStatement preparedStatement;
    ResultSet resultSet;

    public void addFriend(Friend friend){
        String sql ="insert into friend value(?,?,?,?)";
        UserDBConnection userDBConnection = new UserDBConnection();
        try{

            connection = userDBConnection.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,friend.getEmp_phone());
            preparedStatement.setString(2,friend.getUsername());
            preparedStatement.setString(3,friend.getFriendID());
            preparedStatement.setString(4,friend.getFriendname());
            preparedStatement.executeUpdate();

        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            userDBConnection.close();
        }
    }

    public void deleteFriend(String emp_phone,String friendID){
        String sql = "delete from friend where FriendID=? AND emp_phone=?";
        UserDBConnection userDBConnection = new UserDBConnection();
        try{
            connection = userDBConnection.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,friendID);
            preparedStatement.setString(2,emp_phone);
            preparedStatement.executeUpdate();

        }catch(SQLException e){
            e.printStackTrace();
        }finally {
            userDBConnection.close();
        }
    }

    public void updateFriend(Friend friend){
        String sql = "update friend set Name=?,FriendName=?  WHERE emp_phone=? AND FriendID=?";
        UserDBConnection userDBConnection = new UserDBConnection();
        try{
            connection = userDBConnection.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,friend.getUsername());
            preparedStatement.setString(2,friend.getFriendname());
            preparedStatement.setString(3,friend.getEmp_phone());
            preparedStatement.setString(4,friend.getFriendID());
            preparedStatement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            userDBConnection.close();
        }
    }

    public List<Friend> getAllFriend(String emp_phone){
        List<Friend> friends = new ArrayList<Friend>();
        String sql = "select * from friend WHERE emp_phone=?";
        UserDBConnection userDBConnection = new UserDBConnection();
        try{
            connection =userDBConnection.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setObject(1,emp_phone);
            resultSet=preparedStatement.executeQuery();
            while(resultSet.next()){
                Friend friend=new Friend(resultSet.getString(1),resultSet.getString(2),
                        resultSet.getString(3),resultSet.getString(4));
                friends.add(friend);
            }

        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            userDBConnection.close();
        }
        return friends;
    }

    public static void printFriend(List<Friend> friends)
    {
        Iterator<Friend>iterator=friends.iterator();
        if(iterator.hasNext())
        {
            Friend friend=iterator.next();
            System.out.println(friend.getUsername()+friend.getFriendname());
        }
    }

    public Friend getFriend(String emp_phone,String friendID){
        String sql = "select * from friend where FriendID=? AND emp_phone=?";
        Friend friend=null;
        UserDBConnection userDBConnection = new UserDBConnection();
        try{

            connection=userDBConnection.getConnection();
            preparedStatement =connection.prepareStatement(sql);
            preparedStatement.setString(1,friendID);
            preparedStatement.setString(2,emp_phone);
            resultSet=preparedStatement.executeQuery();
            if(resultSet.next()){
                friend = new Friend(resultSet.getString(1),resultSet.getString(2),
                        resultSet.getString(3),resultSet.getString(4));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            userDBConnection.close();
        }
        return friend;
    }
}
