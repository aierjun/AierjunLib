package aierjun.com.aierjunlibrary.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by aierJun on 2017/2/14.
 */
public class StringChangeBitmapUtils {
    public   static Bitmap getImageString(String str){
        try {
            String string= URLStringUtils.urlString(str);
            URL url=new URL(string);
            HttpURLConnection conn = (HttpURLConnection)url.openConnection();
            conn.setConnectTimeout(5000);
            conn.setRequestMethod("GET");
            if(conn.getResponseCode() == 200){
                InputStream inputStream = conn.getInputStream();
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                return bitmap;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public  static List<Bitmap> getImageString(List<String> list){
        List bitmapList=new ArrayList();
        for (int i=0;i<list.size();i++){
            try {
                URL url=new URL(list.get(i));
                HttpURLConnection conn = (HttpURLConnection)url.openConnection();
                conn.setConnectTimeout(5000);
                conn.setRequestMethod("GET");
                if(conn.getResponseCode() == 200){
                    InputStream inputStream = conn.getInputStream();
                    Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                    bitmapList.add(bitmap);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return bitmapList;
    }

}
