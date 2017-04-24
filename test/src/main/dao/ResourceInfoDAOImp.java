import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Yang on 2017/2/20.
 */
public class ResourceInfoDAOImp implements ResourceInfoDAO {

    Connection connection;
    PreparedStatement preparedStatement;
    ResultSet resultSet;

    public void addResource(ResourceInfo resourceInfo){
        String sql ="insert into userinformation value(?,?,?)";
        UserDBConnection userDBConnection = new UserDBConnection();
        try{
            connection = userDBConnection.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,resourceInfo.getEmp_phoen());
            preparedStatement.setString(2,resourceInfo.getResourceID());
            preparedStatement.setString(3,resourceInfo.getResourceName());
            preparedStatement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            userDBConnection.close();
        }
    }

    public void deleteResource(String emp_phone,String resourceID){
        String sql="delete from userinformation where emp_phone=? and ResourceID=?";
        UserDBConnection userDBConnection = new UserDBConnection();
        try{
            connection = userDBConnection.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,emp_phone);
            preparedStatement.setString(2,resourceID);
            preparedStatement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            userDBConnection.close();
        }
    }

    public void updateResource(ResourceInfo resourceInfo){
        String sql="update userinformation set Name=? where emp_phone=? and ResourceID=?";
        UserDBConnection userDBConnection = new UserDBConnection();
        try{
            connection = userDBConnection.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,resourceInfo.getResourceName());
            preparedStatement.setString(2,resourceInfo.getEmp_phoen());
            preparedStatement.setString(3,resourceInfo.getResourceID());
            preparedStatement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            userDBConnection.close();
        }
    }

    public List<ResourceInfo> getAllResourceInfo(String emp_phone){
        List<ResourceInfo> resourceInfos = new ArrayList<ResourceInfo>();
        String sql = "select * from userinformation where emp_phone = ?";
        UserDBConnection userDBConnection = new UserDBConnection();
        try{
            connection = userDBConnection.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setObject(1,emp_phone);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                ResourceInfo resourceInfo = new ResourceInfo(resultSet.getString(1),resultSet.getString(2),
                        resultSet.getString(3));
                resourceInfos.add(resourceInfo);
            }


        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            userDBConnection.close();
        }
        return resourceInfos;
    }

    public ResourceInfo getResourceInfo(String emp_phone,String resourceID){
        String sql="select * from userinformation where emp_phone=? and ResourceID=?";
        ResourceInfo resourceInfo = null;
        UserDBConnection userDBConnection = new UserDBConnection();
        try {
            connection = userDBConnection.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,emp_phone);
            preparedStatement.setString(2,resourceID);
            resultSet=preparedStatement.executeQuery();
            if (resultSet.next()){
                resourceInfo=new ResourceInfo(resultSet.getString(1),resultSet.getString(2),
                        resultSet.getString(3));
            }

        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            userDBConnection.close();
        }
        return resourceInfo;
    }

}
