/**
 * Created by Yang on 2017/2/24.
 */
public class Requester {
    private String requesterID;
    private String resSessionID;
    private String userID;
    private int isdeal;

    public Requester()
    {

    }

    public Requester(String requesterID,String resSessionID,int isdeal,String userID)
    {
        this.requesterID=requesterID;
        this.resSessionID=resSessionID;
        this.isdeal=isdeal;
        this.userID=userID;

    }

    public String getRequesterID() {
        return requesterID;
    }

    public void setRequesterID(String requesterID) {
        this.requesterID = requesterID;
    }

    public String getResSessionID() {
        return resSessionID;
    }

    public void setResSessionID(String resSessionID) {
        this.resSessionID = resSessionID;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public int getIsdeal() {
        return isdeal;
    }

    public void setIsdeal(int isdeal) {
        this.isdeal = isdeal;
    }
}
