package aierjun.com.aierjunlibrary.http.excutor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by aierJun on 2017/3/27.
 */
public class ExecutorServiceUtils {
    public static void thread(Runnable runnable){
        ExecutorService newCachedThreadPool = Executors.newFixedThreadPool(1);
        newCachedThreadPool.execute(runnable);
    }
}
