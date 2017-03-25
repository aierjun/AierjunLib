package aierjun.com.aierjunlibrary.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by aierJun on 2017/2/14.
 */
/*
* 倾斜45TextView
* */
public class Slant45TextView extends TextView {

        public Slant45TextView(Context context) {
            super(context);
        }

        public Slant45TextView(Context context, AttributeSet attrs) {
            super(context, attrs);
        }

        @Override
        protected void onDraw(Canvas canvas) {
            //倾斜度45,上下左右居中
            canvas.rotate(45, getMeasuredWidth()/3, getMeasuredHeight()/3);
            super.onDraw(canvas);
        }


}
