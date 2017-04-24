import java.util.List;

/**
 * Created by Yang on 2017/2/20.
 */
public interface ResourceInfoDAO {

    public void addResource(ResourceInfo resourceinfo);

    public void deleteResource(String emp_phone,String resourceID );

    public void updateResource(ResourceInfo resourceInfo);

    public List<ResourceInfo> getAllResourceInfo(String emp_phone);

    public ResourceInfo getResourceInfo(String emp_phone,String resourceID);

}
