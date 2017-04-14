package aierjun.com.aierjunlibrary.view.dialog;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.LayoutRes;
import android.view.View;
import android.widget.TextView;

import aierjun.com.aierjunlibrary.R;


/**
 * Created by aierJun on 2017/1/16.
 */
public abstract class DelWeightDialog extends Dialog{
    private String string=null;
    private TextView mes;
    public DelWeightDialog(Context context, View view) {
        super(context, R.style.style_dialog);
        setContentView(view);
    }
    public DelWeightDialog(Context context, @LayoutRes int view) {
        super(context,R.style.style_dialog);
        setContentView(view);
    }
    public DelWeightDialog(Context context) {
        super(context,R.style.style_dialog);
        View view=View.inflate(context,R.layout.activity_dialog_del,null);
        setContentView(view);
        mes= (TextView) view.findViewById(R.id.del_dialog_mes);
        final TextView sure= (TextView) view.findViewById(R.id.sel_sure);
        final TextView quire= (TextView) view.findViewById(R.id.sel_quire);
        sure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clectSure(sure);
            }
        });
        quire.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clectQuire(quire);
            }
        });
    }

    protected abstract void clectSure(TextView textView);

    protected abstract void clectQuire(TextView textView);


    public void setDelMes(String string){
        this.string=string;
        if (mes!=null)
            mes.setText(string);
    }
}
