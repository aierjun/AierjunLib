package aierjun.com.aierjunlibrary.utils;

import android.content.Context;
import android.view.inputmethod.InputMethodManager;

/**
 * Created by aierJun on 2017/3/22.
 */

/*
* 弹出软键盘
* */
public class InputMethodUtils {
    public static void getMethod(  Context context){
        InputMethodManager imm=(InputMethodManager)context.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(0,InputMethodManager.HIDE_NOT_ALWAYS);
    }
}
