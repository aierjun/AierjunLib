package aierjun.com.aierjunlibrary.http.excutor;

import android.util.Log;

import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

/**
 * Created by aierJun on 2017/1/23.
 */
public class GetJsonData {

    public static String getJson(String baseURL, String page){
        URL url = null;
        try {
            url = new URL(baseURL+page);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setConnectTimeout(5000);
            conn.setReadTimeout(5000);
            InputStream in = conn.getInputStream();
            ByteArrayOutputStream out=new ByteArrayOutputStream();
            byte[] buf=new byte[1024];
            int len;
            while((len=in.read(buf))!=-1){
                out.write(buf,0,len);
            }
            return new String(out.toByteArray());
        } catch (Exception e) {
            Log.d("","数据接收出错。。。。");
        }
        return null;
    }
    public static String getJsonUTF(String baseURL, String page){
        URL url = null;
        try {
//            byte[] b1 = page.getBytes();
//            String str = new String(b1, "UTF-8");
            String str=URLEncoder.encode(page,"UTF-8");
            url = new URL(baseURL+str);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setConnectTimeout(5000);
            conn.setReadTimeout(5000);
            InputStream in = conn.getInputStream();
            ByteArrayOutputStream out=new ByteArrayOutputStream();
            byte[] buf=new byte[1024];
            int len;
            while((len=in.read(buf))!=-1){
                out.write(buf,0,len);
            }
            return new String(out.toByteArray());
        } catch (Exception e) {
            Log.d("","数据接收出错。。。。");
        }
        return null;
    }

    public static   String getJson(String urlStr){
        URL url = null;
        try {
            url = new URL(urlStr);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setConnectTimeout(5000);
            conn.setReadTimeout(5000);
            InputStream in = conn.getInputStream();
            ByteArrayOutputStream out=new ByteArrayOutputStream();
            byte[] buf=new byte[1024];
            int len;
            while((len=in.read(buf))!=-1){
                out.write(buf,0,len);
            }
            return new String(out.toByteArray());
        } catch (Exception e) {
            Log.d("","数据接收出错。。。。");
        }
        return null;

    }

    public static JSONObject getJsonObject(String urlStr){
        URL url = null;
        try {
            url = new URL(urlStr);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setConnectTimeout(5000);
            conn.setReadTimeout(5000);
            InputStream in = conn.getInputStream();
            ByteArrayOutputStream out=new ByteArrayOutputStream();
            byte[] buf=new byte[1024];
            int len;
            while((len=in.read(buf))!=-1){
                out.write(buf,0,len);
            }
            String string=new String(out.toByteArray());
            return string==null|| string.equals("")? null:new JSONObject(string);
        } catch (Exception e) {
            Log.d("","数据接收出错。。。。");
        }
        return null;

    }
}
