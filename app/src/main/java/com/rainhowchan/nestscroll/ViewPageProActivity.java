package com.rainhowchan.nestscroll;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.PagerTabStrip;
import android.support.v4.view.PagerTitleStrip;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/11/18.
 */
public class ViewPageProActivity extends Activity {

    @Bind(R.id.pts)
    PagerTitleStrip pts;
    @Bind(R.id.viewpager)
    ViewPager viewpager;
    @Bind(R.id.pts1)
    PagerTabStrip pts1;
    @Bind(R.id.viewpager1)
    ViewPager viewpager1;
    private List<View> viewList;
    private List<String> titleList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewpagepro);
        ButterKnife.bind(this);

        initViewPager();

    }

    private void initViewPager() {
        LayoutInflater inflater = getLayoutInflater();
        viewList = new ArrayList<>();
        viewList.add(inflater.inflate(R.layout.view_item1, null));
        viewList.add(inflater.inflate(R.layout.view_item2, null));
        viewList.add(inflater.inflate(R.layout.view_item3, null));


        titleList = new ArrayList<>();
        titleList.add("RainhowChan");
        titleList.add("LiYaqin");
        titleList.add("zhy");

        PagerAdapter adapter = new PagerAdapter() {
            @Override
            public int getCount() {
                return viewList.size();
            }

            @Override
            public boolean isViewFromObject(View view, Object object) {
                return view == object;
            }

            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                View view = viewList.get(position);
                container.addView(view);
                return view;
            }

            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
                container.removeView((View) object);
            }

            @Override
            public CharSequence getPageTitle(int position) {
                return titleList.get(position);
            }
        };
        pts1.setTabIndicatorColorResource(R.color.yellow);
        pts1.setDrawFullUnderline(true);
//        viewpager.setAdapter(adapter);
        viewpager1.setAdapter(adapter);
    }
}
