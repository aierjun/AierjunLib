package aierjun.com.aierjunlibrary.utils;

import android.content.Context;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by aierJun on 2017/3/22.
 */
/*
* 历史记录的保存和显示
* */
public class HistoryKeyUtils {
    private static int position;
    private static List<String> strList=new ArrayList<>();
    public static void setSaveKey(Context content,String string){
        getSaveHistory(content);
        position++;
        SharedPreferencesUtils.setSave(content,"HistoryCheck", Context.MODE_APPEND,"position",position);
        SharedPreferencesUtils.setSave(content,"HistoryCheck", Context.MODE_APPEND,"key"+position,string);
    }

    public static List<String> getSaveHistory(Context content){
        position=SharedPreferencesUtils.getSave(content,"HistoryCheck", Context.MODE_APPEND,"position",0);
        if (strList!=null)
            strList.clear();
        for (int i=0;i<=position;i++){
            String string=SharedPreferencesUtils.getSave(content,"HistoryCheck", Context.MODE_APPEND,"key"+i,"");
            strList.add(string);
        }
        return strList;
    }

    /*
    * 最新的在最前面
    * 已经做过倒序了
    * */
    public static List<String> getSaveHistoryNew(Context content){
        position=SharedPreferencesUtils.getSave(content,"HistoryCheck", Context.MODE_APPEND,"position",0);
        if (strList!=null)
            strList.clear();
        for (int i=0;i<=position;i++){
            String string=SharedPreferencesUtils.getSave(content,"HistoryCheck", Context.MODE_APPEND,"key"+i,"");
            strList.add(string);
        }
        Collections.reverse(strList);
        return strList;
    }

    /*num为要保存的数据的数量
    * list的数量
    * */
    public static List<String> removeNumList(List<String> list,int num){
        for (int i=0;i<list.size();i++){
            if (list.size()>num){
                list.remove(list.get(num));
            }
        }
        return list;
    }

    /*
    * 删除history记录
    * */
    public static void clearHistory(Context context){
        File file = new File("/data/data/" + context.getPackageName().toString() + "/shared_prefs", "HistoryCheck.xml");
        if (file.exists()) {
            SharedPreferencesUtils.setSave(context,"HistoryCheck", Context.MODE_APPEND,"position",0);
            file.delete();
        }
    }
}
