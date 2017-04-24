/**
 * Created by Yang on 2017/2/13.
 */

import java.sql.*;
public class UserDBConnection {
    private final String DBDRIVER ="com.mysql.jdbc.Driver";
    private final String DBURL ="jdbc:mysql://localhost:3306/servlet";
    private final String DBUSER ="root";
    private final String DBPASSWORD ="123456";
    private Connection connection=null;

    public UserDBConnection(){
        try {
            Class.forName(DBDRIVER);
            this.connection=DriverManager.getConnection(DBURL,DBUSER,DBPASSWORD);

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public Connection getConnection()
    {
        return connection;
    }
    public void close()
    {
        try
        {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
