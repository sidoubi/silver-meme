import java.util.List;

/**
 * Created by Yang on 2017/2/13.
 */
public interface UserDAO {
    public void addUser(User user);

    public void deleteUser(String id);

    public void updateUser(User user);

    public void updateFlag(User user);

    public List<User> getAllUser();

    public User getUser(String id);

    public boolean isExist(String id);

}
