/**
 * Created by Yang on 2017/2/20.
 */

public class Friend {
    private String emp_phone;
    private String username;
    private String friendID;
    private String friendname;

    public Friend(){
    }

    public Friend(String emp_phone, String username, String friendID, String friendname){

        this.emp_phone=emp_phone;
        this.username=username;
        this.friendID=friendID;
        this.friendname=friendname;
    }

    public String getEmp_phone() {
        return emp_phone;
    }

    public String getUsername() {
        return username;
    }

    public String getFriendID() {
        return friendID;
    }

    public String getFriendname() {
        return friendname;
    }

    public void setEmp_phone(String emp_phone) {
        this.emp_phone = emp_phone;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setFriendID(String friendID) {
        this.friendID = friendID;
    }

    public void setFriendname(String friendname) {
        this.friendname = friendname;
    }
}

