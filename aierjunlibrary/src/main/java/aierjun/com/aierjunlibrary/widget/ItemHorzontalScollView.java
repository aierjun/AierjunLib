package aierjun.com.aierjunlibrary.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by aierJun on 2017/3/8.
 */
public class ItemHorzontalScollView extends HorizontalScrollView implements View.OnClickListener {
    private OnItemClickListener mOnClickListener;
    private LinearLayout mContainer;
    private int position;
    private TextView textView;
    public interface OnItemClickListener{
        void onClick(View view);
    }

    public ItemHorzontalScollView(Context context) {
        super(context);
    }

    public ItemHorzontalScollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ItemHorzontalScollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        mContainer= (LinearLayout)getChildAt(0);
    }

    @Override
    public void onClick(View v)
    {
        if (mOnClickListener != null)
        {
            mOnClickListener.onClick(v);
        }
    }

    public void setOnItemClickListener(OnItemClickListener mOnClickListener)
    {
        this.mOnClickListener = mOnClickListener;
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        if (ev.getAction()==MotionEvent.ACTION_DOWN){
            textView=(TextView) mContainer.getChildAt(position);
        }
        return super.onTouchEvent(ev);
    }
    public void setPosition(int position){
        this.position=position;
    }
}
