import java.sql.*;

/**
 * Created by Yang on 2017/3/1.
 */
public class UserAuth {
    private int identity;//自增主键
    private String User_id;//发送文件用户ID
    private String Friend_id;//接收文件用户ID
    private String File_name;//发送文件的名字
    private String Key_id;//密钥编码ID
    private int isHandle;//纪录是否被处理标志位
    private Timestamp Share_time;//用户请求分享时间


    public  UserAuth(){

    }

    public UserAuth(int identity,String User_id,String Friend_id,
                    String File_name,String Key_id, int isHandle,Timestamp Share_time){
        this.identity = identity;
        this.User_id = User_id;
        this.Friend_id = Friend_id;
        this.File_name = File_name;
        this.Key_id = Key_id;
        this.isHandle = isHandle;
        this.Share_time = Share_time;
    }

    public int getIdentity() {
        return identity;
    }

    public void setIdentity(int identity) {
        this.identity = identity;
    }

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

    public int getIsHandle() {
        return isHandle;
    }

    public void setIsHandle(int isHandle) {
        this.isHandle = isHandle;
    }

    public Timestamp getShare_time() {
        return Share_time;
    }

    public void setShare_time(Timestamp share_time) {
        Share_time = share_time;
    }
}
