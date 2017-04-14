package aierjun.com.aierjunlibrary.http.excutor;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by aierJun on 2017/3/24.
 */
public abstract class RetrofitServiceUtils {
    public static void getJson(String url, Callback<Object> callback) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        RestApiService getJson = retrofit.create(RestApiService.class);
        Call<Object> call = getJson.getJson();
        call.enqueue(callback);
    }

}
