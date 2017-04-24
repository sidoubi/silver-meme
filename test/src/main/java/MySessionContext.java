import javax.servlet.http.HttpSession;
import java.util.HashMap;

/**
 * Created by Yang on 2017/2/23.
 */
public class MySessionContext {
    private static HashMap mymap=new HashMap();

    public static synchronized void addSession(HttpSession session)
    {
        if(session!=null)
        {
            mymap.put(session.getId(),session);
        }
    }

    public static synchronized void delSession(HttpSession session)
    {
        if (session!=null)
        {
            mymap.remove(session.getId());
        }
    }

    public static synchronized HttpSession getSession(String session_id)
    {
        if (session_id==null)
        {
            return null;
        }
        return (HttpSession)mymap.get(session_id);
    }

}
