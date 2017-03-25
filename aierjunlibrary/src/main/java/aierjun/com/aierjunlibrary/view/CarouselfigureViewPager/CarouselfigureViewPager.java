package aierjun.com.aierjunlibrary.view.CarouselfigureViewPager;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import aierjun.com.aierjunlibrary.R;

/**
 * Created by aierJun on 2017/3/19.
 */
public abstract class CarouselfigureViewPager {
    private Context context;
    private List<String> list;
    private int positions;
    private int time=3000;
    //统计下载了几张图片
    int n=0;
    //统计当前viewpager轮播到第几页
    int p=0;
    private ViewPager vp;
    //装载下载图片的集合
    private List<ImageView> data;
    //控制图片是否开始轮播的开关,默认关的
    private boolean isStart=false;
    //开始图片轮播的线程
    private MyThread t;
    //存放代表viewpager播到第几张的小圆点
    private LinearLayout ll_tag;
    //存储小圆点的一维数组
    private ImageView tag[];
    private Handler mHandler=new Handler(){
        public void handleMessage(Message msg) {
            switch(msg.what){
                case 0:
                    n++;
                    Bitmap bitmap=(Bitmap) msg.obj;
                    ImageView iv=new ImageView(context);
                    iv.setScaleType(ImageView.ScaleType.CENTER_CROP);
                    iv.setImageBitmap(bitmap);
                    //把图片添加到集合里
                    data.add(iv);
                    //当接收到第三张图片的时候，设置适配器,
                    if(n==list.size()){
                        vp.setAdapter(new MyAdapter(data,context,imageOnClick,imageOnClickLister));
                        //创建小圆点
                        creatTag();
                        //把开关打开
                        isStart=true;
                        t=new MyThread();
                        //启动轮播图片线程
                        t.start();

                    }
                    break;
                case 1:
                    //接受到的线程发过来的p数字
                    int page=(Integer) msg.obj;
                    vp.setCurrentItem(page);

                    break;

            }
        };
    };

    public CarouselfigureViewPager(Context context, ViewPager viewPager, LinearLayout linearLayout, List<String> list){
        this.context=context;
        this.vp=viewPager;
        this.ll_tag=linearLayout;
        this.list=list;
        init();
    }
    public CarouselfigureViewPager(Context context, ViewPager viewPager, LinearLayout linearLayout, List<String> list, int time){
        this.context=context;
        this.vp=viewPager;
        this.ll_tag=linearLayout;
        this.list=list;
        this.time=time;
        init();
    }

    private void init() {
        // TODO Auto-generated method stub
      if (vp!=null)
        vp.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageSelected(int position) {
                // TODO Auto-generated method stub
                //把当前的页数赋值给P
                p=position;
                //得到当前图片的索引,如果图片只有三张，那么只有0，1，2这三种情况
                int currentIndex=(position%list.size());
                for(int i=0;i<tag.length;i++){
                    if(i==currentIndex){
                        tag[i].setBackgroundResource(R.drawable.vp_tag_on);
                    }else{
                        tag[i].setBackgroundResource(R.drawable.vp_tag_off);
                    }
                }

            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onPageScrollStateChanged(int state) {
                //这个switch语句我注掉了，我觉得这个语句没有问题啊，可是为什么加上以下语句，当用手拉一次viewpager的时候，轮播就停止了，再也恢复不过来了?有人知道吗
                //switch(state){
                //当页面被手指拉动的时候，暂停轮播
                //case ViewPager.SCROLL_STATE_DRAGGING:
                //  isStart=false;
                //  break;
                //当手指拉完松开或者页面自己翻到下一页静止的时候,开始轮播
                //case ViewPager.SCROLL_STATE_IDLE:
                //  isStart=true;

                //  break;
                //
                //}
            }
        });
        //构造一个存储照片的集合
        data=new ArrayList<ImageView>();
        //从网络上把图片下载下来
        for(int i=0;i<list.size();i++){
            getImageFromNet(list.get(i)+"");

        }
    }

    private void getImageFromNet(final String imagePath) {
        // TODO Auto-generated method stub
        new Thread(){
            public void run() {
                try {
                    URL url=new URL(imagePath);
                    HttpURLConnection con=(HttpURLConnection) url.openConnection();
                    con.setRequestMethod("GET");
                    con.setConnectTimeout(10*1000);
                    InputStream is=con.getInputStream();
                    //把流转换为bitmap
                    Bitmap bitmap= BitmapFactory.decodeStream(is);
                    Message message=new Message();
                    message.what=0;
                    message.obj=bitmap;
                    //把这个bitmap发送到hanlder那里去处理
                    mHandler.sendMessage(message);

                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

            };
        }.start();

    }

    //控制图片轮播
    class MyThread extends Thread{
        @Override
        public void run() {
            // TODO Auto-generated method stub
            super.run();
            while(isStart){
                Message message=new Message();
                message.what=1;
                message.obj=p;
                mHandler.sendMessage(message);
                try {
                    //睡眠3秒,在isStart为真的情况下，一直每隔三秒循环
                    Thread.sleep(time);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                p++;
            }
        }
    }
    protected void creatTag() {
        tag=new ImageView[list.size()];
        for(int i=0;i<list.size();i++){

            tag[i]=new ImageView(context);
            //第一张图片画的小圆点是白点
            if(i==0){
//                tag[i].setBackgroundResource(R.drawable.vp_tag_on);
            }else{
                //其它的画灰点
//                tag[i].setBackgroundResource(R.drawable.vp_tag_off);
            }
            //设置上下左右的间隔
            tag[i].setPadding(7, 7, 7, 7);
            LinearLayout.LayoutParams layoutParams=new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            layoutParams.setMargins(5,0,5,0);
            tag[i].setLayoutParams(layoutParams);
            //添加到viewpager底部的线性布局里面
            ll_tag.addView(tag[i]);
        }

    }

    public int getImagePositions(){
        return positions;
    }

    ImageOnClick imageOnClick=new ImageOnClick() {
        @Override
        public void setOnClick(View.OnClickListener listener) {

        }

        @Override
        public void setPosition(int position) {
            positions=position;
            setPositions(position);
        }
    };

    protected abstract void setPositions(int positions);
    protected abstract void setImageOnClickLister(View view);

    View.OnClickListener imageOnClickLister =new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            setImageOnClickLister(view);;
        }
    };
}

