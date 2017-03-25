package aierjun.com.aierjunlibrary.utils;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;

/**
 * Created by aierJun on 2017/1/20.
 */
public class ChangeListHeightUtils {
    private Context context;
    private void change(){}

    public ChangeListHeightUtils(Context context) {
        this.context = context;
    }

    public void setListViewHeightBasedOnChildren(ListView listView,int itemHeight) {
        //获取listview的适配器
        ListAdapter listAdapter = listView.getAdapter();
        //item的高度
//        int itemHeight = 46;

        if (listAdapter == null) {
            return;
        }

        int totalHeight = 0;

        for (int i = 0; i < listAdapter.getCount(); i++) {
            totalHeight += Dp2Px(context.getApplicationContext(),itemHeight)+listView.getDividerHeight();
        }

        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight;

        listView.setLayoutParams(params);
    }
    public int Dp2Px(Context context, float dp) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dp * scale + 0.5f);
    }
}
