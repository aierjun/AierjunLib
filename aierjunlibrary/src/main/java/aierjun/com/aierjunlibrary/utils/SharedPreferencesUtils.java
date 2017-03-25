package aierjun.com.aierjunlibrary.utils;

import android.content.Context;
import android.content.SharedPreferences;


/**
 * Created by aierJun on 2016/12/13.
 */
public class SharedPreferencesUtils {
    /*存储1个int数据*/
    public static void setSave(Context context,String nameSre,int modle,String  keyStr,int content){
        SharedPreferences sharedPreferences=context.getSharedPreferences(nameSre,modle);
        sharedPreferences.edit().putInt(keyStr,content).commit();
    }
    /*获取1个int数据*/
    public static int getSave(Context context,String nameSre,int modle,String  keyStr,int defaultContent){
        SharedPreferences sharedPreferences=context.getSharedPreferences(nameSre,modle);
        int toget=sharedPreferences.getInt(keyStr,defaultContent);
        return toget;
    }
    /*存储1个String数据*/
    public static void setSave(Context context,String nameSre,int modle,String  keyStr,String content){
        SharedPreferences sharedPreferences=context.getSharedPreferences(nameSre,modle);
        sharedPreferences.edit().putString(keyStr,content).commit();
    }
    /*获取1个String数据*/
    public static String getSave(Context context,String nameSre,int modle,String  keyStr,String defaultContent){
        SharedPreferences sharedPreferences=context.getSharedPreferences(nameSre,modle);
        String toget=sharedPreferences.getString(keyStr,defaultContent);
        return toget;
    }
    /*存储int[]数据*/
    public static void setSave(Context context, String nameSre, int modle,String nameStr, int[] ints){
        SharedPreferences sharedPreferences=context.getSharedPreferences(nameSre,modle);
        for (int i=0;i<=ints.length;i++){
            sharedPreferences.edit().putInt(nameStr+i,ints[i]).commit();
        }
    }
    /*存储int[]数据*/
    public static void setSave(Context context, String nameSre, int modle,String nameStr, String[] ints){
        SharedPreferences sharedPreferences=context.getSharedPreferences(nameSre,modle);
        for (int i=0;i<ints.length;i++){
            i++;
            sharedPreferences.edit().putString(nameStr+i,ints[i]).commit();
        }
    }
}
