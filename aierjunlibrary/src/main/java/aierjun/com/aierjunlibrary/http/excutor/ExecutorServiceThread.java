package aierjun.com.aierjunlibrary.http.excutor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import aierjun.com.aierjunlibrary.utils.sysout.L;

/**
 * Created by aierJun on 2017/3/14.
 */
public abstract class ExecutorServiceThread {
    public static void getJson(final String url,final CallBackStr callBackStr){
        ExecutorService newCachedThreadPool = Executors.newFixedThreadPool(3);
        newCachedThreadPool.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    String data = GetJsonData.getJson(url);
                    if (data!=null&&!data.equals("")){
                    callBackStr.callBack(data);}else{
                        L.d("ExecutorServiceThread数据为空");
                    }
                }catch (Exception e){
                    L.d("ExecutorServiceThread异常");
                }
            }
        });
    }
}
