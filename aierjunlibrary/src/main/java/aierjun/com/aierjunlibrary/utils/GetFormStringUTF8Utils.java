package aierjun.com.aierjunlibrary.utils;

import java.io.UnsupportedEncodingException;

/**
 * Created by aierJun on 2017/3/13.
 */
public class GetFormStringUTF8Utils {
    public static String getUTF8String(String string){
        String s=null;
            try {
                s=new String(string.getBytes("UTF-8"),"ISO8859-1");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        return s;
    }
}
