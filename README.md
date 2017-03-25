# AierjunLib
## View
### CarouselfigureViewPager(轮播图)
####
 ```
  new CarouselfigureViewPager(context,viewPager,linearLayout,list) {
            @Override
            protected void setPositions(int i) {
                //第几张图片
            }

            @Override
            protected void setImageOnClickLister(View view) {
                //图片点击处理
            }
        };
 ```
 ## upload(form表单上传，图片key为image，视频key为video)
 ### UploadAsyncTack
 ####
  ```
   UploadAsyncTack asyncUploadFile= new UploadAsyncTack(PushVedioActivity.this,filesImage,filesVedio, APIManger.PUSH_VEDIO, names, values) {
                        @Override
                        protected void progress(float progress) {
                            Log.d("Jun",progress+"");
							//进度（视频的上传进度）
                        }

                        @Override
                        protected void CallBackCodeMes(int code, String mes) {
                            Log.d("Jun",code+"..."+mes);
							//返回code和mes（State，Message）
                        }
                    };
                    asyncUploadFile.execute();
  ```
  ## 加载库
  ### Circularimageview 圆形头像
  #### layout
   ```
   <com.mikhaellopez.circularimageview.CircularImageView
                    android:id="@+id/person_icon_image"
                    android:layout_width="65dp"
                    android:layout_height="65dp"
                    android:src="@mipmap/user_icon"
                    app:civ_border_color="#EEEEEE"
                    app:civ_border_width="1dp"
                    app:civ_shadow="true"
                    app:civ_shadow_radius="1"
                    app:civ_shadow_color="@android:color/transparent"
                    android:layout_centerHorizontal="true"
                    android:layout_marginTop="59dp" />
   ```
   ### MainActivity(主框架)
   ####
    ```
	public class NewMainActivity extends BaseFragmentActivity implements InteractionFragment.OnFragmentInteractionListener {
    @Override
    protected void initPageInfo() {
        broadCastReceiver();
        StateBitmapImageButton ball=new StateBitmapImageButton(this);
        ball.setImageResource(R.mipmap.add);
        RelativeLayout.LayoutParams lp=new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,RelativeLayout.LayoutParams.WRAP_CONTENT);
        lp.addRule(RelativeLayout.CENTER_HORIZONTAL);
        lp.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        lp.bottomMargin=getResources().getDimensionPixelSize(R.dimen.six);
        ball.setLayoutParams(lp);
        ball.setBackgroundColor(Color.WHITE);
        getLayoutView().addView(ball);
        ball.setPadding(0,2,0,0);
        NavigationBar.NavigationItemEntity[] items={
                new NavigationBar.NavigationItemEntity(NewMainActivity.this,R.string.index,R.mipmap.index_black,R.mipmap.index_red),
                new NavigationBar.NavigationItemEntity(NewMainActivity.this,R.string.deal,R.mipmap.deal_black,R.mipmap.deal_red),
                new NavigationBar.NavigationItemEntity(NewMainActivity.this,R.string.push,R.mipmap.push_black,R.mipmap.push_red),
                new NavigationBar.NavigationItemEntity(NewMainActivity.this,R.string.activity,R.mipmap.activity_black,R.mipmap.activity_red),
                new NavigationBar.NavigationItemEntity(NewMainActivity.this,R.string.person,R.mipmap.person_black,R.mipmap.person_red),
        };
        Fragment[] fragments={IndexFragment.getInstance(), DealFragment.getInstance(), PushFragment.getInstance(), ActivityFragment.getInstance(), PersonFragment.getInstance()};
        setPageInfo(items,fragments,0);
        setNavigationBarTranslucent(true);
        getNavigationBar().setMinimumHeight(5);
        getNavigationBar().setNavItemNameSpSize(13);
        getNavigationBar().setNavItemNameCheckedColorResId(R.color.red);
        getNavigationBar().setNavItemNameUncheckedColorResId(R.color.black);
        getNavigationBar().setPaddingTopBottomDp(4,3);
        getNavigationBar().setBackgroundColor(getResources().getColor(R.color.white));
        getNavigationBar().setNavItemNameIconPadding(5);

        //这里因为bar上面有线，所以这样才能获取到item
        ((ViewGroup)getNavigationBar().getChildAt(1)).getChildAt(2).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                showWindow(view);
            }
        });
        ((ViewGroup)getNavigationBar().getChildAt(1)).getChildAt(4).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                if (GetShareIDUtils.getUserID(NewMainActivity.this)!=null&&!GetShareIDUtils.getUserID(NewMainActivity.this).equals("")){
                    getNavigationBar().setCurrentCheckedPosition(4);}else{
                    startActivity(new Intent(NewMainActivity.this,LoginActivity.class));}
            }
        });
    }
    public void setPosition(int postion){
        getNavigationBar().setCurrentCheckedPosition(postion);
    }

    @Override
    public void showFragment(Fragment fragment) {
        super.showFragment(fragment);
    }

   
    @Override
    public void onBackPressed() {
        setHookBackKeyPress();
        super.onBackPressed();
    }

    @Override
    public void onReplaceSelfWithFragment(Fragment fragment) {
        showFragment(fragment);
        if (PersonFragment.getInstance().isAdded())
        if (fragment==(PersonFragment.getInstance()))
        getNavigationBar().setCurrentCheckedPosition(4);
    }

    @Override
    public void onBackToPreviewFragment() {

    }
	}
	```