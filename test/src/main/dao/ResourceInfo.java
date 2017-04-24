/**
 * Created by Yang on 2017/2/20.
 */
public class ResourceInfo {
    private String emp_phone;
    private String resourceID;
    private String resourceName;


    public ResourceInfo(){

    }

    public ResourceInfo(String emp_phoen, String resourceID, String resourceName){
        this.emp_phone=emp_phoen;
        this.resourceID=resourceID;
        this.resourceName=resourceName;
    }

    public String getEmp_phoen() {
        return emp_phone;
    }

    public String getResourceID() {
        return resourceID;
    }

    public String getResourceName() {
        return resourceName;
    }

    public void setEmp_phoen(String emp_phoen) {
        this.emp_phone = emp_phoen;
    }

    public void setResourceID(String resourceID) {
        this.resourceID = resourceID;
    }

    public void setResourceName(String resourceName) {
        this.resourceName = resourceName;
    }
}
