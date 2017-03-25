package aierjun.com.aierjunlibrary.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.webkit.WebSettings;

/**
 * Created by aierJun on 2016/12/13.
 */
public class SettingsWebViewUtils {
    @SuppressLint("NewApi")
    public static void setSettings(Context context,WebSettings setting) {
        setting.setJavaScriptEnabled(true);
        setting.setBuiltInZoomControls(true);
        setting.setDisplayZoomControls(false);
        setting.setSupportZoom(true);

        setting.setDomStorageEnabled(true);
        setting.setDatabaseEnabled(true);
        // 全屏显示
        setting.setLoadWithOverviewMode(true);
        setting.setUseWideViewPort(true);
        setting.setAppCacheEnabled(true);
        setting.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
//        setting.setAppCacheMaxSize(1024*1024*10);

//        String  appCacheDir  =  context.getApplicationContext().getDir("cache", Context.MODE_PRIVATE).getPath();
//        setting.setAppCachePath(appCacheDir);
    }
}
