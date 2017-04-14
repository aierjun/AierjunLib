package aierjun.com.aierjunlibrary.http.excutor;


import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by aierJun on 2017/3/24.
 */
public interface RestApiService {
    @GET()
    Call<Object> getJson();

    @POST("/DoPayment/Index")
    Call<Object> alipay(@Body String body);
}
