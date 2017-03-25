package aierjun.com.aierjunlibrary.utils;

/**
 * Created by aierJun on 2017/2/4.
 */
public class StringNoNullUtils {
    public static String changeStr(String str){
        String string=" ";
        if (str==null){
            return null;
        }
        for (int i=0;i<str.length();i++){
            char s=str.charAt(i);
            if (String.valueOf(s).equals(" ")){
                string=str.replace(" ","");
            }
        }
        return string;
    }

    public static String changeNoSlantStr(String str){
        String string="/";
        if (str==null){
            return null;
        }
        for (int i=0;i<str.length();i++){
            char s=str.charAt(i);
            if (String.valueOf(s).equals("/")){
                string=str.replace("/","");
            }
        }
        return string;
    }
}
