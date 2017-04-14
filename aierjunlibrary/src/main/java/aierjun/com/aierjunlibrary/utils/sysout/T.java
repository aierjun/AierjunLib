package aierjun.com.aierjunlibrary.utils.sysout;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by aierJun on 2017/3/29.
 */
public class T {
    public static void toast(Context context,Object object){
        Toast.makeText(context, object+"",Toast.LENGTH_SHORT).show();
    }
}
