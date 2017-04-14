package aierjun.com.aierjunlibrary.widget.pop;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Handler;
import android.support.annotation.LayoutRes;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import java.util.ArrayList;
import java.util.List;

import aierjun.com.aierjunlibrary.R;

/**
 * Created by aierJun on 2017/4/14.
 */
public class PopMenu extends PopupWindow {

    private String TAG = PopMenu.class.getSimpleName();
    Activity mContext;
    private int mWidth;
    private int mHeight;
    private int statusBarHeight;
    private Bitmap mBitmap = null;
    private Bitmap overlay = null;
    private List<View> aniViews;
    private List<Integer> listViews=new ArrayList<>();
    private int coloseId;
    private View.OnClickListener onClickListener;

    private Handler mHandler = new Handler();

    public PopMenu(Activity context) {
        mContext = context;
        init();
    }

    public void init() {
        Rect frame = new Rect();
        mContext.getWindow().getDecorView().getWindowVisibleDisplayFrame(frame);
        statusBarHeight = frame.top;
        DisplayMetrics metrics = new DisplayMetrics();
        mContext.getWindowManager().getDefaultDisplay().getMetrics(metrics);
        mWidth = metrics.widthPixels;
        mHeight = metrics.heightPixels;

        setWidth(mWidth);
        setHeight(mHeight);
    }

    private Bitmap blur() {
        if (null != overlay) {
            return overlay;
        }
        long startMs = System.currentTimeMillis();

        View view = mContext.getWindow().getDecorView();
        view.setDrawingCacheEnabled(true);
        view.buildDrawingCache(true);
        mBitmap = view.getDrawingCache();

        float scaleFactor = 8;//图片缩放比例；
        float radius = 6;//模糊程度
        int width = mBitmap.getWidth();
        int height = mBitmap.getHeight();

        overlay = Bitmap.createBitmap((int) (width / scaleFactor), (int) (height / scaleFactor), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(overlay);
        canvas.scale(1 / scaleFactor, 1 / scaleFactor);
        Paint paint = new Paint();
        paint.setFlags(Paint.FILTER_BITMAP_FLAG);
        canvas.drawBitmap(mBitmap, 0, 0, paint);

        overlay = FastBlur.doBlur(overlay, (int) radius, true);
        Log.i(TAG, "blur time is:" + (System.currentTimeMillis() - startMs));
        return overlay;
    }

    private Animation showAnimation1(final View view, int fromY, int toY) {
        AnimationSet set = new AnimationSet(true);
        TranslateAnimation go = new TranslateAnimation(0, 0, fromY, toY);
        go.setDuration(300);
        TranslateAnimation go1 = new TranslateAnimation(0, 0, -10, 2);
        go1.setDuration(100);
        go1.setStartOffset(250);
        set.addAnimation(go1);
        set.addAnimation(go);

        set.setAnimationListener(new Animation.AnimationListener() {

            @Override
            public void onAnimationEnd(Animation animation) {
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }

            @Override
            public void onAnimationStart(Animation animation) {

            }

        });
        return set;
    }

    @SuppressLint("InflateParams")
    public void showMenuWindow(View anchor, int bottomMargin, @LayoutRes int view, int colseViewId, List<Integer> listView, View.OnClickListener onClickListener) {
        this.listViews=listView;
        this.coloseId=colseViewId;
        this.onClickListener=onClickListener;
        final RelativeLayout layout = (RelativeLayout) LayoutInflater.from(mContext).inflate(view, null);
        layout.setPadding(0,10,0,0);
        setContentView(layout);
        ImageView close = (ImageView) layout.findViewById(colseViewId);
//		android.widget.RelativeLayout.LayoutParams params =new android.widget.RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);
        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) close.getLayoutParams();
        params.bottomMargin = params.bottomMargin + bottomMargin;
        close.setLayoutParams(params);

        //showAnimation(layout);
        aniViews = new ArrayList<View>();
        aniViews.add(close);
        if (listView!=null&&listView.size()>0)
        for (int i=0;i<listView.size();i++)
            aniViews.add(layout.findViewById(listView.get(i)));
        showAnimation(aniViews);

        close.setOnClickListener(closeMenuClickListener);
        layout.setOnClickListener(closeMenuClickListener);

        //不做高斯模糊  影响性能
        //setBackgroundDrawable(new BitmapDrawable(mContext.getResources(), blur()));
        setOutsideTouchable(true);
        setFocusable(true);
        showAtLocation(anchor, Gravity.BOTTOM, 0, statusBarHeight);
    }

    private View.OnClickListener closeMenuClickListener = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            if (isShowing()) {
                //closeAnimation(layout);
                closeAnimation(aniViews);
            }
        }

    };

    /**
     * 为指定的View添加动画效果
     */
    private void showAnimation(List<View> aniViews) {
        for (int i = 0; i < aniViews.size(); i++) {
            final View child = aniViews.get(i);
            child.setOnClickListener(onClickListener);
            child.setVisibility(View.INVISIBLE);
            mHandler.postDelayed(new Runnable() {

                @Override
                public void run() {
                    child.setVisibility(View.VISIBLE);
                    ValueAnimator fadeAnim = ObjectAnimator.ofFloat(child, "translationY", 800, 50);
                    fadeAnim.setDuration(600);
                    KickBackAnimator kickAnimator = new KickBackAnimator();
                    kickAnimator.setDuration(150);
                    fadeAnim.setEvaluator(kickAnimator);
                    fadeAnim.start();
                }
            }, i * 200);
        }

    }

    /**
     * 为指定的View添加退出动画
     *
     * @param aniViews
     */
    private void closeAnimation(List<View> aniViews) {
        for (int i = 0; i < aniViews.size(); i++) {
            final View child = aniViews.get(i);
            child.setOnClickListener(onClickListener);
            mHandler.postDelayed(new Runnable() {

                @Override
                public void run() {
                    child.setVisibility(View.VISIBLE);
                    ValueAnimator fadeAnim = ObjectAnimator.ofFloat(child, "translationY", 0, 800);
                    fadeAnim.setDuration(200);
                    KickBackAnimator kickAnimator = new KickBackAnimator();
                    kickAnimator.setDuration(100);
                    fadeAnim.setEvaluator(kickAnimator);
                    fadeAnim.start();
                    fadeAnim.addListener(new Animator.AnimatorListener() {

                        @Override
                        public void onAnimationStart(Animator animation) {

                        }

                        @Override
                        public void onAnimationRepeat(Animator animation) {

                        }

                        @Override
                        public void onAnimationEnd(Animator animation) {
                            child.setVisibility(View.INVISIBLE);
                            dismiss();
                        }

                        @Override
                        public void onAnimationCancel(Animator animation) {

                        }
                    });
                }
            }, (aniViews.size() - i - 1) * 30);

        }
    }


    /**
     * 为layout下的子控件添加显示动画效果
     *
     * @param layout
     */
    private void showAnimation(ViewGroup layout) {
        for (int i = 0; i < layout.getChildCount(); i++) {
            final View child = layout.getChildAt(i);
            if (child.getId() == coloseId) {
                continue;
            }
            child.setOnClickListener(onClickListener);
            child.setVisibility(View.INVISIBLE);
            mHandler.postDelayed(new Runnable() {

                @Override
                public void run() {
                    child.setVisibility(View.VISIBLE);
                    ValueAnimator fadeAnim = ObjectAnimator.ofFloat(child, "translationY", 600, 0);
                    fadeAnim.setDuration(300);
                    KickBackAnimator kickAnimator = new KickBackAnimator();
                    kickAnimator.setDuration(150);
                    fadeAnim.setEvaluator(kickAnimator);
                    fadeAnim.start();
                }
            }, i * 50);
        }
    }


    /**
     * 为指定ViewGroup下所有的子控件添加退出动画
     *
     * @param layout
     */
    private void closeAnimation(ViewGroup layout) {
        for (int i = 0; i < layout.getChildCount(); i++) {
            final View child = layout.getChildAt(i);
            if (child.getId() ==coloseId ) {
                continue;
            }
            child.setOnClickListener(onClickListener);
            mHandler.postDelayed(new Runnable() {

                @Override
                public void run() {
                    child.setVisibility(View.VISIBLE);
                    ValueAnimator fadeAnim = ObjectAnimator.ofFloat(child, "translationY", 0, 600);
                    fadeAnim.setDuration(200);
                    KickBackAnimator kickAnimator = new KickBackAnimator();
                    kickAnimator.setDuration(100);
                    fadeAnim.setEvaluator(kickAnimator);
                    fadeAnim.start();
                    fadeAnim.addListener(new Animator.AnimatorListener() {

                        @Override
                        public void onAnimationStart(Animator animation) {

                        }

                        @Override
                        public void onAnimationRepeat(Animator animation) {

                        }

                        @Override
                        public void onAnimationEnd(Animator animation) {
                            child.setVisibility(View.INVISIBLE);
                            dismiss();
                        }

                        @Override
                        public void onAnimationCancel(Animator animation) {

                        }
                    });
                }
            }, (layout.getChildCount() - i - 1) * 30);

        }
    }


    public void destroy() {
        if (null != overlay) {
            overlay.recycle();
            overlay = null;
            System.gc();
        }
        if (null != mBitmap) {
            mBitmap.recycle();
            mBitmap = null;
            System.gc();
        }
    }
}
