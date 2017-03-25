package aierjun.com.aierjunlibrary.view.dialog;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import aierjun.com.aierjunlibrary.R;

/**
 * Created by aierJun on 2017/1/16.
 */
public class GifDialog extends Dialog {
    private ImageView imageView;
    public GifDialog(Context context, View view) {
        super(context, R.style.style_dialog);
        if (view==null){
        setContentView(R.layout.gif_dialog_layout);}else{
        setContentView(view);}
        imageView= (ImageView) findViewById(R.id.gif_image);
        Glide.with(context).load(R.drawable.loadgif).into(imageView);
    }

}
