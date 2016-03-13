package com.example.waniltonfilho.personaltasks.controller.activities;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.app.FragmentManager;
import android.graphics.Point;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.example.waniltonfilho.personaltasks.R;
import com.example.waniltonfilho.personaltasks.controller.adapter.WalletTransactionAdapter;
import com.example.waniltonfilho.personaltasks.controller.fragment.ChangeWalletFragment;
import com.example.waniltonfilho.personaltasks.model.entities.Login;
import com.example.waniltonfilho.personaltasks.model.entities.WalletTransaction;
import com.example.waniltonfilho.personaltasks.model.service.WalletTransactionService;
import com.melnykov.fab.FloatingActionButton;
import com.miguelcatalan.materialsearchview.MaterialSearchView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wanilton.filho on 22/01/2016.
 */
public class MainActivity extends BaseActivity {


    private Toolbar mToolbar;
    public static Login selectedLogin;
    private MaterialSearchView mSearchView;
    private FloatingActionButton mFloatingActionButton;
    private RecyclerView mRecyclerView;
    private List<WalletTransaction> mListTransactions;
    private TextView mTextViewMoney;
    private boolean clicked;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bindComponents();
    }

    private void bindComponents() {
        bindToolbar();
        bindFloatingButton();
        bindRecyclerView();
        bindTextViewMoney();
        //bindSearchView();

    }

    private void bindTextViewMoney() {
        mTextViewMoney = (TextView) findViewById(R.id.textViewMoney);
    }

    private void bindRecyclerView() {
        mListTransactions = WalletTransactionService.getLastTransactions(2);
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerLastTransaction);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(new WalletTransactionAdapter(mListTransactions, this));
    }

    @Override
    protected void onResume() {
        super.onResume();
        updateListTransaction();
    }

    private void updateListTransaction(){

    }

    private void bindFloatingButton() {
        mFloatingActionButton = (FloatingActionButton) findViewById(R.id.fabAddTransaction);
        setupFloatingButton(mFloatingActionButton);
        mFloatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(v);
            }
        });
    }



    private void bindSearchView() {
        //mSearchView = (MaterialSearchView) findViewById(R.id.search_view);
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
                Animation animation = AnimationUtils.loadAnimation(MainActivity.this, android.R.anim.fade_out);
                animation.setDuration(3000);
                animation.start();
            }
        });
        initLogin();
        mSearchView.setSuggestions(getResources().getStringArray(R.array.query_suggestions));
        mSearchView.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        mSearchView.setTextColor(android.graphics.Color.parseColor("#FFFFFF"));
    }


    private void initLogin() {
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            this.selectedLogin = getIntent().getExtras().getParcelable(LoginMainActivity.PARAM_LOGIN);
        }
        this.selectedLogin = this.selectedLogin == null ? new Login() : this.selectedLogin;
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.menu_search, menu);
//
//        MenuItem item = menu.findItem(R.id.action_search);
//        mSearchView.setMenuItem(item);
//
//        return true;
//    }

    private void bindToolbar() {
        mToolbar = (Toolbar) findViewById(R.id.app_bar);
        setupToolbar(mToolbar);
    }




    private void showDialog(View v){
        FrameLayout frameLayout = (FrameLayout) findViewById(R.id.frameChange);
        int[] clickCooords = new int[2];
        v.getLocationInWindow(clickCooords);
        clickCooords[0] += v.getWidth() / 2;
        clickCooords[1] += v.getHeight() / 2;
        FragmentManager fm = getFragmentManager();
        ChangeWalletFragment fragment = new ChangeWalletFragment(0, mTextViewMoney, mRecyclerView);
        fm.beginTransaction().add(R.id.frameChange, fragment).commit();
        if (clicked == false) {
            performRevealAnimationIn(frameLayout, clickCooords[0], clickCooords[1]);
            clicked = true;
        } else {
            performRevealAnimationOut(frameLayout, clickCooords[0], clickCooords[1]);
            clicked = false;

        }
    }

    private void performRevealAnimationIn(final View v, int x, int y) {
        int[] animationCoords = new int[2];
        v.getLocationInWindow(animationCoords);
        animationCoords[0] = x - animationCoords[0];
        animationCoords[1] = y - animationCoords[1];

        Point size = new Point();

        this.getWindowManager().getDefaultDisplay().getSize(size);
        int maximunRadius = size.y;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Animator animator = ViewAnimationUtils.createCircularReveal(v, x, y, 0, maximunRadius);
            animator.start();
            v.setVisibility(View.VISIBLE);
        }
    }

    private void performRevealAnimationOut(final View v, int x, int y) {
        int[] animationCoords = new int[2];
        v.getLocationInWindow(animationCoords);
        animationCoords[0] = x - animationCoords[0];
        animationCoords[1] = y - animationCoords[1];

        Point size = new Point();

        getWindowManager().getDefaultDisplay().getSize(size);
        int maximunRadius = size.y;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Animator animator = ViewAnimationUtils.createCircularReveal(v, x, y, maximunRadius, 0);
            animator.start();
            animator.addListener(new AnimatorListenerAdapter() {

                @Override
                public void onAnimationEnd(Animator animator) {
                    v.setVisibility(View.GONE);
                }
            });


        }
    }

    @Override
    protected void onResumeFragments() {
        super.onResumeFragments();
    }

    @Override
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
    }
}
