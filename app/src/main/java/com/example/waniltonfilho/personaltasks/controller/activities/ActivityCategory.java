package com.example.waniltonfilho.personaltasks.controller.activities;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.example.waniltonfilho.personaltasks.R;
import com.example.waniltonfilho.personaltasks.controller.fragment.ListTransactionFragment;
import com.example.waniltonfilho.personaltasks.controller.fragment.WalletFragment;
import com.example.waniltonfilho.personaltasks.controller.tabs.PagerAdapter;
import com.example.waniltonfilho.personaltasks.model.entities.Login;
import com.miguelcatalan.materialsearchview.MaterialSearchView;

/**
 * Created by wanilton.filho on 22/01/2016.
 */
public class ActivityCategory extends AppCompatActivity {


    private ViewPager mViewPager;
    private Toolbar mToolbar;
    public static Login selectedLogin;
    private MaterialSearchView mSearchView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        bindComponents();
    }

    private void bindComponents() {
        //bindPager();
        //bindTabs();
        bindMaterialTabs();
        bindToolbar();
        mSearchView = (MaterialSearchView) findViewById(R.id.search_view);
        mSearchView.setOnQueryTextListener(new MaterialSearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                //Do some magic
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                //Do some magic
                return false;
            }
        });

        mSearchView.setOnSearchViewListener(new MaterialSearchView.SearchViewListener() {
            @Override
            public void onSearchViewShown() {
                //Do some magic
            }

            @Override
            public void onSearchViewClosed() {
                Animation animation = AnimationUtils.loadAnimation(ActivityCategory.this, android.R.anim.fade_out);
                animation.setDuration(3000);
                animation.start();
            }
        });
        initLogin();
        mSearchView.setSuggestions(getResources().getStringArray(R.array.query_suggestions));
        mSearchView.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        mSearchView.setTextColor(android.graphics.Color.parseColor("#FFFFFF"));

    }

    private void bindMaterialTabs() {
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        tabLayout.addTab(tabLayout.newTab().setText("Tab 1"));
        tabLayout.addTab(tabLayout.newTab().setText("Tab 2"));
        tabLayout.addTab(tabLayout.newTab().setText("Tab 3"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        final ViewPager viewPager = (ViewPager) findViewById(R.id.pager);
        final PagerAdapter adapter = new PagerAdapter
                (getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    private void initLogin() {
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            this.selectedLogin = getIntent().getExtras().getParcelable(LoginMainActivity.PARAM_LOGIN);
        }
        this.selectedLogin = this.selectedLogin == null ? new Login() : this.selectedLogin;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_search, menu);

        MenuItem item = menu.findItem(R.id.action_search);
        mSearchView.setMenuItem(item);

        return true;
    }

    private void bindToolbar() {
        mToolbar = (Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(mToolbar);
    }

//    private void bindPager() {
//        mViewPager = (ViewPager) findViewById(R.id.pager);
//        mViewPager.setAdapter(new MyPageAdapter(getSupportFragmentManager()));
//    }
//
//    private void bindTabs() {
//        mSlidingTabLayout = (SlidingTabLayout) findViewById(R.id.tabs);
//        mSlidingTabLayout.setDistributeEvenly(true);
//        mSlidingTabLayout.setCustomTabView(R.layout.custom_tab_view, R.id.tabText);
//        mSlidingTabLayout.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
//        mSlidingTabLayout.setSelectedIndicatorColors(getResources().getColor(R.color.colorAccent));
//        mSlidingTabLayout.setViewPager(mViewPager);
//
//    }

    class MyPageAdapter extends FragmentPagerAdapter {
        String[] tabText = getResources().getStringArray(R.array.tabs);
        int icons[] = {R.drawable.ic_walleticon, R.drawable.ic_debts, R.drawable.ic_lend};

        public MyPageAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            Fragment myFragment = null;
            if(position == 0) {
                myFragment = new WalletFragment();
            } else if(position == 1){
                myFragment = new ListTransactionFragment();
            } else if(position == 2){
                myFragment = new WalletFragment();
            }
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
