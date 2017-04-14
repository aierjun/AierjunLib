/*
 * Copyright (C) 2013 Andreas Stuetz <andreas.stuetz@gmail.com>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

    package aierjun.com.aierjunlibrary.widget.tablayout;

import android.content.Context;
import android.support.annotation.DrawableRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

public class SlidingTabFragmentViewPager extends LinearLayout {
    private PagerSlidingTabStrip tabs;
    private ViewPager pager;
    public SlidingTabFragmentViewPager(Context context) {
        this(context,null);
    }

    public SlidingTabFragmentViewPager(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

public SlidingTabFragmentViewPager(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
    init();
}

protected void init() {
    setOrientation(LinearLayout.VERTICAL);
    tabs = new PagerSlidingTabStrip(getContext());
    tabs.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
    pager = new ViewPager(getContext());
    pager.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
    addView(tabs);
    addView(pager);
}

public void setPagerViews(String[] tabNames, View[] pageViews){
    if(tabNames == null || tabNames.length == 0) throw new NullPointerException("导航菜单文本列表为空，请检查！");
    if(tabNames.length != pageViews.length) throw new IllegalArgumentException("传入的菜单文本数量和页面数量要保持一致，他们是一一对应的。");
    ViewPagerAdapter adapter = new ViewPagerAdapter(tabNames,pageViews);
    pager.setAdapter(adapter);
    tabs.setViewPager(pager);
}
public void setPagerViews(@DrawableRes int[] iconResIds, View[] pageViews){
    if(iconResIds == null || iconResIds.length == 0) throw new NullPointerException("导航菜单图标资源ID列表为空，请检查！");
    if(iconResIds.length != pageViews.length) throw new IllegalArgumentException("传入的菜单图标资源ID数量和页面数量要保持一致，他们是一一对应的。");
    IconTabViewPagerAdapter adapter = new IconTabViewPagerAdapter(iconResIds,pageViews);
    pager.setAdapter(adapter);
    tabs.setViewPager(pager);
}

public void setPagerFragments(FragmentManager fm, String[] tabNames, Fragment[] fragments){
    if(tabNames == null || tabNames.length == 0) throw new NullPointerException("导航菜单文本列表为空，请检查！");
    if(tabNames.length != fragments.length) throw new IllegalArgumentException("传入的菜单文本数量和Fragment数量要保持一致，他们是一一对应的。");
    MyFragmentPagerAdapter adapter = new MyFragmentPagerAdapter(fm,tabNames,fragments);
    pager.setAdapter(adapter);
    tabs.setViewPager(pager);
}

public void setPagerFragments(FragmentManager fm, @DrawableRes int[] iconResIds, Fragment[] fragments){
    if(iconResIds == null || iconResIds.length == 0) throw new NullPointerException("导航菜单图标资源ID列表为空，请检查！");
    if(iconResIds.length != fragments.length) throw new IllegalArgumentException("传入的菜单图标资源ID数量和Fragment数量要保持一致，他们是一一对应的。");
    MyIconTabFragmentPagerAdapter adapter = new MyIconTabFragmentPagerAdapter(fm,iconResIds,fragments);
    pager.setAdapter(adapter);
    tabs.setViewPager(pager);
}

public class IconTabViewPagerAdapter extends ViewPagerAdapter implements PagerSlidingTabStrip.IconTabProvider{
    @DrawableRes int[] iconResIds;
    public IconTabViewPagerAdapter(@DrawableRes int[] iconResIds, View[] pageViews) {
        super(null, pageViews);
        this.iconResIds = iconResIds;
    }

    @Override
    public int getPageIconResId(int position) {
        return iconResIds[position];
    }
}


public class ViewPagerAdapter extends PagerAdapter {
    private String[] tabNames;
    private View[] pageViews;

    public ViewPagerAdapter(String[] tabNames, View[] pageViews) {
        this.tabNames = tabNames;
        this.pageViews = pageViews;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        if(tabNames == null || tabNames.length == 0) return "";
        return tabNames[position];
    }

    @Override
    public int getCount() {
        return tabNames.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        container.addView(pageViews[position]);
        return super.instantiateItem(container, position);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(pageViews[position]);
    }
}

public class MyIconTabFragmentPagerAdapter extends MyFragmentPagerAdapter implements PagerSlidingTabStrip.IconTabProvider{
    int[] iconResIds;

    public MyIconTabFragmentPagerAdapter(FragmentManager fm, @DrawableRes int[] iconResIds, Fragment[] fragments) {
        super(fm,null,fragments);
        this.iconResIds = iconResIds;
    }

    @Override
    public int getPageIconResId(int position) {
        return iconResIds[position];
    }
}

public class MyFragmentPagerAdapter extends FragmentPagerAdapter {
    String[] tabNames;
    Fragment[] fragments;

    public MyFragmentPagerAdapter(FragmentManager fm, String[] tabNames, Fragment[] fragments) {
        super(fm);
        this.tabNames = tabNames;
        this.fragments = fragments;
    }

    public MyFragmentPagerAdapter(FragmentManager fm) {
        super(fm);
    }
    @Override
    public CharSequence getPageTitle(int position) {
        if(tabNames == null || tabNames.length == 0) return "";
        return tabNames[position];
    }

    @Override
    public int getCount() {
        return tabNames.length;
    }

    @Override
    public Fragment getItem(int position) {
        return fragments[position];
    }

}

}