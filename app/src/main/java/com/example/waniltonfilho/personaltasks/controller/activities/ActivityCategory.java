package com.example.waniltonfilho.personaltasks.controller.activities;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.SpannableString;
import android.text.style.ImageSpan;

import com.example.waniltonfilho.personaltasks.R;
import com.example.waniltonfilho.personaltasks.controller.fragment.MyFragment;
import com.example.waniltonfilho.personaltasks.controller.fragment.WalletFragment;
import com.example.waniltonfilho.personaltasks.controller.tabs.SlidingTabLayout;
import com.example.waniltonfilho.personaltasks.model.entities.Login;

/**
 * Created by wanilton.filho on 22/01/2016.
 */
public class ActivityCategory extends AppCompatActivity {


    private ViewPager mViewPager;
    private SlidingTabLayout mSlidingTabLayout;
    private Toolbar mToolbar;

    public static Login selectedLogin;



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
        initLogin();
    }

    private void initLogin() {
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            this.selectedLogin = getIntent().getExtras().getParcelable(LoginMainActivity.PARAM_LOGIN);
        }
        this.selectedLogin = this.selectedLogin == null ? new Login() : this.selectedLogin;
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

    class MyPageAdapter extends FragmentPagerAdapter {
        String[] tabText = getResources().getStringArray(R.array.tabs);
        int icons[] = {R.drawable.ic_walleticon, R.drawable.ic_debts, R.drawable.ic_lend};

        public MyPageAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            Fragment myFragment = null;
            //if(position == 0){
                myFragment = new WalletFragment();
            //.getInstance(position);

            //}

            return myFragment;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return tabText[position];
//            Drawable drawable = getResources().getDrawable(icons[position]);
//            drawable.setBounds(0, 0, 100, 100);
//            ImageSpan imageSpan = new ImageSpan(drawable);
//            SpannableString spannableString = new SpannableString(" ");
//            spannableString.setSpan(imageSpan, 0, spannableString.length(), spannableString.SPAN_EXCLUSIVE_EXCLUSIVE);
//            return spannableString;
        }

        @Override
        public int getCount() {
            return 3;
        }
    }
}
