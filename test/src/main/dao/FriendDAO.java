import java.util.List;

/**
 * Created by Yang on 2017/2/20.
 */
public interface FriendDAO {

    public void addFriend(Friend friend);

    public void deleteFriend(String emp_phone,String frinedID);

    public void updateFriend(Friend friend);

    public List<Friend> getAllFriend(String emp_phone);

    public Friend getFriend(String emp_phone,String friendID);

}
