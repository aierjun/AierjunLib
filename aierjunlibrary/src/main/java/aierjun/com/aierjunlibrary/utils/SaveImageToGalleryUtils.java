package aierjun.com.aierjunlibrary.utils;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;

import java.io.File;
import java.io.FileOutputStream;

/**
 * Created by aierJun on 2017/1/16.
 */
public class SaveImageToGalleryUtils {
    public static String saveImage(Context context, Bitmap bitmap) {
        File appDir = new File(Environment.getExternalStorageDirectory()
                .getAbsolutePath(), "image");
        if (!appDir.exists()) {
            // 目录不存在 则创建
            appDir.mkdirs();
        }
        String fileName = System.currentTimeMillis() + ".jpg";
        File file = new File(appDir, fileName);
        try {
            FileOutputStream fos = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos); // 保存bitmap至本地
            fos.flush();
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {

            ScannerByReceiver(context, file.getAbsolutePath());
            if (bitmap!=null)
            if (!bitmap.isRecycled()) {
                // bitmap.recycle(); 当存储大图片时，为避免出现OOM ，及时回收Bitmap
                System.gc(); // 通知系统回收
            }
            // Toast.makeText(context, "图片保存成功" ,
            // Toast.LENGTH_SHORT).show();
        }
        return file.getAbsolutePath();
    }

    /** Receiver扫描更新图库图片 **/

    private static void ScannerByReceiver(Context context, String path) {
        context.sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE,
                Uri.parse("file://" + path)));
    }
    public static void DelImageFile(){
        File file=new File("/sdcard/image");
        if (file.exists()) {
            file.delete();
        }
    }
    public static void DelImageFileImage(String name){
        File file=new File("/sdcard/image/"+name);
        if (file.exists()) {
            file.delete();
        }
    }
}
