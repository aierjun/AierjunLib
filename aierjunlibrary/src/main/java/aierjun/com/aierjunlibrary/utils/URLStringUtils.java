package aierjun.com.aierjunlibrary.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * Created by aierJun on 2017/2/24.
 */

/*
* url转编码
* */
public class URLStringUtils {
    public static String urlString(String str){
        String string= null;
        try {
            string = URLEncoder.encode(str,"UTF-8").replaceAll("\\+","%20").replaceAll("%2F","/").replaceAll("%3A",":");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return string;
    }
}
