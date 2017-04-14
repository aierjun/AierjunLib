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
	
	### pop(1)
	
	``` 
	 public  void create(){
        ImageView imageView=new ImageView(this);
        imageView.setImageResource(R.mipmap.button_action_touch);
        FloatingActionButton actionButton=new FloatingActionButton.Builder(this).setContentView(imageView).build();

        SubActionButton.Builder itemBuilder=new SubActionButton.Builder(this);
        ImageView imageView1=new ImageView(this);
        imageView.setImageResource(R.mipmap.button_sub_action_dark);
        SubActionButton button1=itemBuilder.setContentView(imageView1).build();

        FloatingActionMenu actionMenu=new FloatingActionMenu.Builder(this)
                .addSubActionView(button1)
                .attachTo(actionButton)
                .build();

        actionMenu.getSubActionItems().get(0).view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                T.toast(TestActivity.this,"1");
            }
        });
    }
	``` 
	
	### pop(2)
	``` 
	new PopMenu(NewMainActivity.this).showMenuWindow(view,55,colseviewid,listview,OnClickListener);
	``` 
	
	### DelWeightDialog
	``` 
	 delDialog= new DelWeightDialog(PushStoryActivity.this) {
                            @Override
                            protected void clectSure(TextView textView) {
                                if (textView!=null)
                                delDialog.dismiss();
                                pushStory();
                            }

                            @Override
                            protected void clectQuire(TextView textView) {
                                if (textView!=null)
                                delDialog.dismiss();
                            }
                        };
                        delDialog.setDelMes(a.toString);
                        delDialog.show();
	``` 
	
	### SensitivewordFilter(敏感词检查) assets 下SensitiveWord.txt  格式：  女人={MOD}
	``` 
	 SensitivewordFilter filter = new SensitivewordFilter(PushStoryActivity.this);
     Set<String> a=filter.getSensitiveWord(String,1);
                    if (a.size()==0){
                        
                    } else{
					
					}
	
	``` 
	
	### TabLayout
	``` 
	pagerSlidingTabStrip= (PagerSlidingTabStrip) findViewById(R.id.myworkorder_tablayout);
        if (pagerSlidingTabStrip != null) {
            pagerSlidingTabStrip.setIndicatorHeight(3);
            pagerSlidingTabStrip.setUnderlineHeight(1);
            pagerSlidingTabStrip.setBackgroundColor(getResources().getColor(R.color.white));
            pagerSlidingTabStrip.setIndicatorColor(getResources().getColor(R.color.red));
            pagerSlidingTabStrip.setUnderlineColor(getResources().getColor(R.color.line));
            pagerSlidingTabStrip.setViewPager(threeCustomViewPager);
        }
	``` 
	
	### WrappingViewPager
	``` 
	threeCustomViewPager = (WrappingViewPager) findViewById(R.id.view_pager_my_work_change);
	threeCustomViewPager.setAdapter(new WrappingFragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                switch (position) {
                    case 0:
                        return OneFragment.getInstance();
                    case 1:
                        return TwoFragment.getInstance();
                    case 2:
                        return ThreeFragment.getInstance();
                    default:
                        return OneFragment.getInstance();
                }
            }

            @Override
            public int getCount() {
                return 3;
            }

            @Override
            public CharSequence getPageTitle(int position) {
                switch (position) {
                    case 0:
                        return getString(R.string.all);
                    case 1:
                        return getString(R.string.no_send);
                    case 2:
                        return getString(R.string.ok_over);
                    default:
                        return getString(R.string.all);
                }
            }
        });
        pagerSlidingTabStrip= (PagerSlidingTabStrip) findViewById(R.id.myworkorder_tablayout);
        if (pagerSlidingTabStrip != null) {
            pagerSlidingTabStrip.setIndicatorHeight(3);
            pagerSlidingTabStrip.setUnderlineHeight(1);
            pagerSlidingTabStrip.setBackgroundColor(getResources().getColor(R.color.white));
            pagerSlidingTabStrip.setIndicatorColor(getResources().getColor(R.color.red));
            pagerSlidingTabStrip.setUnderlineColor(getResources().getColor(R.color.line));
            pagerSlidingTabStrip.setViewPager(threeCustomViewPager);
        }
    };
	``` 
	
	### Image_Loader init (bugly init)
	``` 
	 private DisplayImageOptions options;
    private static ImageLoaderConfiguration config;
//    String  appCacheDir  =  getApplicationContext().getDir("cache", Context.MODE_PRIVATE).getPath();
//    File file=new File(appCacheDir);

    private int vv;
    @Override
    public void onCreate() {
        super.onCreate();
        options = new DisplayImageOptions.Builder()// 开始构建, 显示的图片的各种格式
                .resetViewBeforeLoading(true)// 设置图片在下载前是否重置，复位
                .cacheInMemory(true)// 开启内存缓存
                .cacheOnDisk(true) // 开启硬盘缓存
                .displayer(new RoundedBitmapDisplayer(20))// 是否设置为圆角，弧度为多少；避免使用RoundedBitmapDisplayer.他会创建新的ARGB_8888格式的Bitmap对象；
                .displayer(new FadeInBitmapDisplayer(100))// 是否图片加载好后渐入的动画时间
                .displayer(new SimpleBitmapDisplayer())// 正常显示一张图片　
                .bitmapConfig(Bitmap.Config.RGB_565)// 设置图片的解码类型;使用.bitmapConfig(Bitmap.config.RGB_565)代替ARGB_8888;
                .considerExifParams(true)// 是否考虑JPEG图像EXIF参数（旋转，翻转）
                .imageScaleType(ImageScaleType.EXACTLY)// 缩放级别
                .imageScaleType(ImageScaleType.IN_SAMPLE_INT)//这两种配置缩放都推荐
                .build();// 构建完成（参数可以不用设置全，根据需要来配置）

        config = new ImageLoaderConfiguration.Builder(getApplicationContext())// 开始构建 ,图片加载配置
                .threadPriority(Thread.NORM_PRIORITY - 2)// 设置线程优先级
                .threadPoolSize(3)// 线程池内加载的数量 ;减少配置之中线程池的大小，(.threadPoolSize).推荐1-5；
                .denyCacheImageMultipleSizesInMemory()// 设置加载的图片有多样的
                .tasksProcessingOrder(QueueProcessingType.LIFO)// 图片加载任务顺序
                .memoryCache(new WeakMemoryCache())//使用.memoryCache(new WeakMemoryCache())，不要使用.cacheInMemory();
                .memoryCacheExtraOptions(480, 800) // 即保存的每个缓存文件的最大长宽
                .memoryCacheSizePercentage(60)// 图片内存占应用的60%；
                .diskCacheFileNameGenerator(new HashCodeFileNameGenerator())//使用HASHCODE对UIL进行加密命名
                .diskCacheFileNameGenerator(new Md5FileNameGenerator())// 将保存的时候的URI名称用MD5 加密
                .diskCacheSize(50 * 1024 * 1024) // 缓存设置大小为50 Mb
//                .diskCache(new UnlimitedDiskCache(file))// 自定义缓存路径
                .diskCacheFileCount(100) // 缓存的文件数量
                .denyCacheImageMultipleSizesInMemory()// 自动缩放
                .imageDownloader(new BaseImageDownloader(getApplicationContext(), 5 * 1000, 30 * 1000)) // connectTimeout (5 s), readTimeout (30 s)超时时间
                .memoryCacheExtraOptions(480, 800)//设置缓存图片时候的宽高最大值，默认为屏幕宽高;保存的每个缓存文件的最大长宽
                .defaultDisplayImageOptions(options)// 如果需要打开缓存机制，需要自己builde一个option,可以是DisplayImageOptions.createSimple()
                .writeDebugLogs() // Remove for release app
                .build();       //构建完成（参数可以不用设置全，根据需要来配置）

        ImageLoader.getInstance().init(config);    //初始化完成



        //bugly 版本更新和渠道设置
        CrashReport.UserStrategy strategy = new CrashReport.UserStrategy(getApplicationContext());
        strategy.setAppChannel("bugly");  //设置渠道
        strategy.setAppVersion(VersionNameUtils.getVersionName(getApplicationContext())+"."+VersionNameUtils.getVersionCode(getApplicationContext())+"");      //App的版本
        Log.d("Jun",VersionNameUtils.getVersionName(getApplicationContext())+"."+VersionNameUtils.getVersionCode(getApplicationContext())+"");
        strategy.setAppPackageName("com.aierjun.woxiangwoxiang");  //App的包名
        Bugly.init(getApplicationContext(), "52c4184322", false,strategy);

    }
	``` 