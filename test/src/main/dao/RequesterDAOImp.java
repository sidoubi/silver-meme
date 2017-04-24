import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Yang on 2017/2/24.
 */
public class RequesterDAOImp implements RequesterDAO{
    Connection connection;
    PreparedStatement preparedStatement;
    ResultSet resultSet;


    public void addRequester(Requester requester) {
        UserDBConnection userDBConnection=new UserDBConnection();
        String sql="insert into requestfriend value(?,?,?,?)";
        try {
            connection=userDBConnection.getConnection();
            preparedStatement=connection.prepareStatement(sql);
            preparedStatement.setString(1,requester.getRequesterID());
            preparedStatement.setString(2,requester.getResSessionID());
            preparedStatement.setInt(3,requester.getIsdeal());
            preparedStatement.setString(4,requester.getUserID());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            userDBConnection.close();
        }
    }

    public void deleteRequester(String requesterID, String UserID) {
        String sql="delete from requestfriend where RequesterID=? and UserID=?";
        UserDBConnection userDBConnection = new UserDBConnection();
        try{
            connection = userDBConnection.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,requesterID);
            preparedStatement.setString(2,UserID);
            preparedStatement.executeUpdate();

        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            userDBConnection.close();
        }


    }

    public void updateRequester(Requester requester) {

        String sql="update requestfriend set ReqSessionID=?,isdeal=? where RequesterID=? and UserID=?";
        UserDBConnection userDBConnection = new UserDBConnection();
        try{
            connection = userDBConnection.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,requester.getResSessionID());
            preparedStatement.setInt(2,requester.getIsdeal());
            preparedStatement.setString(3,requester.getRequesterID());
            preparedStatement.setString(4,requester.getUserID());
            preparedStatement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            userDBConnection.close();
        }


    }

    public void updateIsDeal(Requester requester){
        String sql="update requestfriend set isdeal=? where UserID=? and RequesterID=?";
        UserDBConnection userDBConnection =new UserDBConnection();
        try{
            connection = userDBConnection.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,requester.getIsdeal());
            preparedStatement.setString(2,requester.getUserID());
            preparedStatement.setString(3,requester.getRequesterID());
            preparedStatement.executeUpdate();
            System.out.println("更改isdeal");
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            userDBConnection.close();
        }
    }

    public Requester getRequester(String requesterID, String userID) {
        String sql="select * from requestfriend where RequesterID=? and UserID=? ";
        Requester requester = null;
        UserDBConnection userDBConnection = new UserDBConnection();
        try {
            connection = userDBConnection.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,requesterID);
            preparedStatement.setString(2,userID);
            resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                requester = new Requester(resultSet.getString(1),resultSet.getString(2),
                        resultSet.getInt(3),resultSet.getString(4));
            }

        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            userDBConnection.close();
        }



        return requester;

    }

    public List<Requester> getAllRequester(String requesterID) {
        String sql = "select * from requestfriend where RequesterID=?";
        List<Requester> requesters = new ArrayList<Requester>();
        UserDBConnection userDBConnection = new UserDBConnection();
        try{
            connection = userDBConnection.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,requesterID);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                 Requester requester = new Requester(resultSet.getString(1),
                         resultSet.getString(2),resultSet.getInt(3)
                         ,resultSet.getString(4));
                 requesters.add(requester);
            }

        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            userDBConnection.close();
        }
        return requesters;
    }

    public List<Requester> getAllResponse(String userID) {
        String sql = "select * from requestfriend where UserID=?";
        List<Requester> requesters = new ArrayList<Requester>();
        UserDBConnection userDBConnection = new UserDBConnection();
        try{
            connection = userDBConnection.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,userID);
            resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                Requester requester = new Requester(resultSet.getString(1),resultSet.getString(2),
                        resultSet.getInt(3),resultSet.getString(4));
                requesters.add(requester);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            userDBConnection.close();
        }
        return requesters;
    }
}
