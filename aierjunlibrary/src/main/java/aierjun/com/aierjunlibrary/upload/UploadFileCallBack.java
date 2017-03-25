package aierjun.com.aierjunlibrary.upload;

/**
 * Created by aierJun on 2017/3/24.
 */
public interface UploadFileCallBack {
    public void onProgress(float progress);

    public void onCallBackCodeMes(int code, String mes);

}
