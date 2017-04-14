package aierjun.com.aierjunlibrary.http.volley;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import aierjun.com.aierjunlibrary.utils.sysout.T;

/**
 * Created by aierJun on 2017/4/8.
 */
public class VolleyService {

    public static void httpGetJson(Context context, String url, Response.Listener<JSONObject> listener, Response.ErrorListener errorListener){
        RequestQueue mQueue = Volley.newRequestQueue(context);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(url,null,listener,errorListener);
        mQueue.add(jsonObjectRequest);
    }

    public static void httpGetJson(final Context context, String url, Response.Listener<JSONObject> listener){
        RequestQueue mQueue = Volley.newRequestQueue(context);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(url, null, listener, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                T.toast(context,"请求数据错误");
            }
        });
        mQueue.add(jsonObjectRequest);
    }

    public static void httpGetString(Context context, String url, Response.Listener<String> listener, Response.ErrorListener errorListener){
        RequestQueue mQueue = Volley.newRequestQueue(context);
        StringRequest stringRequest = new StringRequest(url,listener,errorListener);
        mQueue.add(stringRequest);
    }

    public static void httpGetString(final Context context, String url, Response.Listener<String> listener){
        RequestQueue mQueue = Volley.newRequestQueue(context);
        StringRequest stringRequest = new StringRequest(url, listener, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                T.toast(context,"请求数据错误");
            }
        });
        mQueue.add(stringRequest);
    }
}
