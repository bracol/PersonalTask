package com.example.waniltonfilho.personaltasks.controller.activities;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.example.waniltonfilho.personaltasks.R;
import com.example.waniltonfilho.personaltasks.controller.tabs.SlidingTabLayout;
import com.example.waniltonfilho.personaltasks.controller.tabs.SlidingTabStrip;

/**
 * Created by wanilton.filho on 22/01/2016.
 */
public class ActivityCategory extends AppCompatActivity {

    private ViewPager mViewPager;
    private SlidingTabLayout mSlidingTabLayout;
    private Toolbar mToolbar;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        bindComponents();
    }

    private void bindComponents() {
        bindPager();
        bindTabs();
        bindToolbar();

    }

    private void bindToolbar() {
        mToolbar = (Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(mToolbar);
    }

    private void bindPager() {
        mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPager.setAdapter(new MyPageAdapter(getSupportFragmentManager()));
    }

    private void bindTabs() {
        mSlidingTabLayout = (SlidingTabLayout) findViewById(R.id.tabs);
        mSlidingTabLayout.setDistributeEvenly(true);
        mSlidingTabLayout.setCustomTabView(R.layout.custom_tab_view, R.id.tabText);
        mSlidingTabLayout.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        mSlidingTabLayout.setSelectedIndicatorColors(getResources().getColor(R.color.colorAccent));
        mSlidingTabLayout.setViewPager(mViewPager);

    }
}
