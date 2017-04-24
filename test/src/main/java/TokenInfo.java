/**
 * Created by Yang on 2017/3/3.
 */
public class TokenInfo {
    private String User_id;
    private String Friend_id;
    private String File_name;
    private String Key_id;
    private String Sys_time;
    private String token;

    public String getUser_id() {
        return User_id;
    }

    public void setUser_id(String user_id) {
        User_id = user_id;
    }

    public String getFriend_id() {
        return Friend_id;
    }

    public void setFriend_id(String friend_id) {
        Friend_id = friend_id;
    }

    public String getFile_name() {
        return File_name;
    }

    public void setFile_name(String file_name) {
        File_name = file_name;
    }

    public String getKey_id() {
        return Key_id;
    }

    public void setKey_id(String key_id) {
        Key_id = key_id;
    }

    public String getSys_time() {
        return Sys_time;
    }

    public void setSys_time(String sys_time) {
        Sys_time = sys_time;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
