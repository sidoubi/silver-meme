import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * Created by Yang on 2017/2/23.
 */
public class MySessionListener implements HttpSessionListener{


    public void sessionCreated(HttpSessionEvent httpSessionEvent) {
        MySessionContext.addSession(httpSessionEvent.getSession());
    }

    public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {
        MySessionContext.delSession(httpSessionEvent.getSession());
    }
}
