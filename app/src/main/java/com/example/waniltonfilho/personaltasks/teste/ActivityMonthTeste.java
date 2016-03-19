package com.example.waniltonfilho.personaltasks.teste;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.ContextMenu;
import android.view.View;
import android.widget.Toast;

import com.example.waniltonfilho.personaltasks.R;
import com.example.waniltonfilho.personaltasks.model.MonthList;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Wanilton on 19/03/2016.
 */
public class ActivityMonthTeste extends AppCompatActivity {

    private MonthList mManipulateList;
    Toolbar mToolbar;
    ViewPager viewPager;
    int mCurrentSelectedScreen;
    int mNextSelectedScreen;
    boolean flag = false;
    int last;
    String lastTitle;
    CustomPagerAdapter customPageAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teste);
        mToolbar = (Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(mToolbar);

        customPageAdapter = new CustomPagerAdapter(this);
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        viewPager.setAdapter(customPageAdapter);
        viewPager.setCurrentItem(2);
        last = viewPager.getCurrentItem();
        mManipulateList = new MonthList();


    }


    @Override
    protected void onResume() {
        super.onResume();
        //customPageAdapter.bindFirstTime();
    }
}
