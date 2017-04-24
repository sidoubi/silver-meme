import java.util.List;

/**
 * Created by Yang on 2017/3/1.
 */
public interface UserAuthDAO {

    public void addUserAuth(UserAuth userAuth);

    public void deleteUserAuth(int identity);

    public void updateUserAuth(UserAuth userAuth);

    public void updateIsHandle(UserAuth userAuth);

    public UserAuth getUserAuth(int identity);

    public List<UserAuth> getAll();

    public List<UserAuth> getAllSender(String friend_id);

    public List<UserAuth> getAllReceiver(String user_id);

    public List<UserAuth> getAllSameKey(String key_id);
}
