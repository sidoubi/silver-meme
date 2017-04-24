import java.util.List;

/**
 * Created by Yang on 2017/2/24.
 */
public interface RequesterDAO {
    public void addRequester(Requester requester);

    public void deleteRequester(String requesterID,String UserID);

    public void updateRequester(Requester requester);

    public void updateIsDeal(Requester requester);

    public Requester getRequester(String requesterID,String userID);

    public List<Requester> getAllRequester(String requesterID);

    public List<Requester> getAllResponse(String userID);
}
