package aierjun.com.aierjunlibrary.view.CarouselfigureViewPager;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.ImageView;


import java.util.List;

/**
 * Created by aierJun on 2017/2/20.
 */
public class MyAdapter extends PagerAdapter {
    private List<ImageView> data;
    private ImageOnClick onClick;
    private Context context;
    private View.OnClickListener onClickListener;
    public MyAdapter(List<ImageView> data, Context context, ImageOnClick onClick, View.OnClickListener onClickListener) {
        this.data=data;
        this.context=context;
        this.onClick=onClick;
        this.onClickListener=onClickListener;
    }

    @Override
    public int getCount() {
        //返回一个无穷大的值，
        return Integer.MAX_VALUE;
    }

    @Override
    public boolean isViewFromObject(View arg0, Object arg1) {

        return arg0==arg1;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        //注意，这里什么也不做!!!

    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {
        final ImageView image=data.get(position%data.size());
        //如果View已经在之前添加到了一个父组件，则必须先remove，否则会抛出IllegalStateException。
        ViewParent vp=image.getParent();
        if(vp!=null){
            ViewGroup vg=(ViewGroup) vp;
            vg.removeView(image);
        }
        onClick.setPosition(position%data.size());
        image.setOnClickListener(onClickListener);
        container.addView(data.get(position%data.size()));
        return data.get(position%data.size());
    }

}