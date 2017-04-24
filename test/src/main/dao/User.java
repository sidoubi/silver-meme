/**
 * Created by Yang on 2017/2/13.
 */
public class User {
    private String emp_phone;
    private String emp_password;
    private String userName=emp_phone;
    private String ipAddress=null;
    private String SessionID;
    private int flag;

    public String getSessionID() {
        return SessionID;
    }

    public void setSessionID(String sessionID) {
        SessionID = sessionID;
    }



    public User()
    {

    }

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    public User(String userID, String password, String userName,
                String ipAddress, String SessionID, int flag)
    {
        this.emp_phone=userID;
        this.emp_password=password;
        this.userName=userName;
        this.ipAddress=ipAddress;
        this.SessionID=SessionID;
        this.flag=flag;
    }




    public String getEmp_phone()
    {
        return emp_phone;
    }
    public void setEmp_phone(String userID)
    {
        this.emp_phone=userID;
    }
    public String getUserName()
    {
        return userName;
    }
    public void setUserName(String userName)
    {
        this.userName=userName;
    }
    public String getEmp_password()
    {
        return emp_password;
    }
    public void setEmp_password(String password)
    {
        this.emp_password=password;
    }
    public String getIpAddress()
    {
        return ipAddress;
    }
    public void setIpAddress(String ipAddress)
    {
        this.ipAddress=ipAddress;
    }
}
