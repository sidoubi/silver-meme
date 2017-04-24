/**
 * Created by Yang on 2017/2/19.
 */
public class Result {
    private String status;
    private Object content;

    public void setStatus(String status) {
        this.status = status;
    }

    public void setContent(Object content) {
        this.content = content;
    }

    public String getStatus() {

        return status;
    }

    public Object getContent() {
        return content;
    }
}
