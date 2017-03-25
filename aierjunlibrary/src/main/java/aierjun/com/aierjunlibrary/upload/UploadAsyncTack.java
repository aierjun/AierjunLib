package aierjun.com.aierjunlibrary.upload;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by aierJun on 2017/1/8.
 */


public abstract class UploadAsyncTack extends AsyncTask<Integer, Integer, Integer> {
        private  Context context;
        private  ArrayList<File> imageList;
        private  ArrayList<File> vedioList;
        private  String url;
        private  ArrayList<String> names;
        private  ArrayList<Object> values;

        public UploadAsyncTack(Context context, ArrayList<File> imageList, ArrayList<File> vedioList, String url, ArrayList<String> names, ArrayList<Object> values) {
            this.context=context;
            this.imageList=imageList;
            this.vedioList=vedioList;
            this.url=url;
            this.names=names;
            this.values=values;

        }
        @Override
        protected Integer doInBackground(Integer... integers) {  //2
            return UploadCallBackUtils.uploadFile(imageList, vedioList, url, names, values, new UploadFileCallBack() {
                @Override
                public void onProgress(float progress) {
                    progress(progress);
                }
                @Override
                public void onCallBackCodeMes(int code, String mes) {
                    CallBackCodeMes(code,mes);
                }
            });
        }

        @Override
        protected void onPreExecute() {
            Log.d("JunLog","onPreExecute"); //1
            super.onPreExecute();
        }
        @Override
        protected void onPostExecute(Integer integer) {  //4
            super.onPostExecute(integer);
        }

        @Override
        protected void onProgressUpdate(Integer... values) {  //3
//            progress(values[0]);
            super.onProgressUpdate(values);
        }

        protected abstract void progress(float progress);
        protected abstract void CallBackCodeMes(int code,String mes);
}


