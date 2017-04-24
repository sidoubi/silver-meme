import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by Yang on 2017/3/2.
 */
public class MD5 {

    public static String getMD5(String str){
        try{
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(str.getBytes());
            byte b[] = md.digest();

            int i;

            StringBuffer buf  = new StringBuffer("");

            for(int offset = 0; offset < b.length; offset++){
                i=b[offset];
                if((i & 0xff)>>4 ==0){
                    buf.append("0").append(Integer.toHexString(i));
                }else {
                    buf.append(Integer.toHexString(i & 0xff));
                }
            }

            return  buf.toString();

        }catch (NoSuchAlgorithmException e){
            e.printStackTrace();
            return null;
        }
    }

}


