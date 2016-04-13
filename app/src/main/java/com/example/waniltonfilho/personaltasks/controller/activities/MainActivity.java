package com.example.waniltonfilho.personaltasks.controller.activities;

import android.app.FragmentTransaction;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.waniltonfilho.personaltasks.R;
import com.example.waniltonfilho.personaltasks.controller.adapter.WalletTransactionAdapter;
import com.example.waniltonfilho.personaltasks.controller.fragment.ChangeWalletFragment;
import com.example.waniltonfilho.personaltasks.controller.tasks.TaskGetLogin;
import com.example.waniltonfilho.personaltasks.controller.tasks.TaskPostWallet;
import com.example.waniltonfilho.personaltasks.controller.tasks.TaskWallet;
import com.example.waniltonfilho.personaltasks.model.entities.User;
import com.example.waniltonfilho.personaltasks.model.entities.Wallet;
import com.example.waniltonfilho.personaltasks.model.entities.WalletTransaction;
import com.example.waniltonfilho.personaltasks.model.persistance.wallet_transaction.WalletRepository;
import com.example.waniltonfilho.personaltasks.model.service.WalletService;
import com.example.waniltonfilho.personaltasks.model.service.WalletTransactionService;
import com.example.waniltonfilho.personaltasks.util.MyValueFormatter;
import com.melnykov.fab.FloatingActionButton;

import java.util.List;

/**
 * Created by wanilton.filho on 22/01/2016.
 */
public class MainActivity extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener {


    private Toolbar mToolbar;
    public static User selectedUser;
    private FloatingActionButton mFloatingActionButton;
    private RecyclerView mRecyclerView;
    private List<WalletTransaction> mListTransactions;
    private TextView mTextViewMoney;
    private boolean dialogVisible = false;
    private ChangeWalletFragment changeFragment;
    private Wallet mWallet;
    private User mUser;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        checkWallet();
    }

    private void bindComponents() {
        bindToolbar();
        bindFloatingButton();
        bindRecyclerView();
        bindTextViewMoney();
        bindNavigationView();
    }

    private void checkWallet() {
        User user = getPreferenceLogin();
        if (user == null) {
            if (WalletRepository.getWallet() == null) {
                mWallet = new Wallet();
                mWallet.setValue(0f);
                WalletService.save(mWallet);
            } else {
                mWallet = WalletService.getWallet();
                bindComponents();
            }
        } else {
            getHttpLogin(user);
        }
    }

    private void getHttpWallet() {
        new TaskWallet(mUser) {
            @Override
            protected void onPostExecute(Wallet wallet) {
                mWallet = wallet;
                if (mWallet == null) {
                    new TaskPostWallet(mUser){
                        @Override
                        protected void onPostExecute(Wallet walletResult) {
                            mWallet = walletResult;
                            bindComponents();
                            super.onPostExecute(walletResult);
                        }
                    }.execute();
                } else {
                    bindComponents();
                }

                super.onPostExecute(wallet);
            }
        }.execute();
    }

    private void bindNavigationView() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, mToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    private void bindTextViewMoney() {
        MyValueFormatter myValueFormatter = new MyValueFormatter();
        mTextViewMoney = (TextView) findViewById(R.id.textViewMoney);
        mTextViewMoney.setText(myValueFormatter.getMaskFormatted(mWallet.getValue()));
    }

    private void bindRecyclerView() {
        if(getPreferenceLogin() == null) {
            mListTransactions = WalletTransactionService.getLastTransactions(2);
            mRecyclerView = (RecyclerView) findViewById(R.id.recyclerLastTransaction);
            mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
            mRecyclerView.setAdapter(new WalletTransactionAdapter(mListTransactions, this));
        } else {

        }
    }

    public User getPreferenceLogin() {
        SharedPreferences sharedPref = getSharedPreferences(LoginMainActivity.PREFERENCE_NAME, Context.MODE_PRIVATE);
        String login = sharedPref.getString("login", null);
        String pass = sharedPref.getString("pass", null);
        if (login == null && pass == null) {
            return null;
        } else {
            User userC = new User();
            userC.setUserName(login);
            userC.setPassword(pass);
            return userC;
        }
    }

    public void getHttpLogin(User user) {
        new TaskGetLogin(user) {
            ProgressDialog dialog;

            @Override
            protected void onPreExecute() {
                dialog = new ProgressDialog(MainActivity.this);
                dialog.setMessage("Loading...");
                dialog.show();
                super.onPreExecute();
            }

            @Override
            protected void onPostExecute(User user) {
                super.onPostExecute(user);
                mUser = user;
                getHttpWallet();
                dialog.dismiss();
            }
        }.execute();
    }

    private void bindFloatingButton() {
        mFloatingActionButton = (FloatingActionButton) findViewById(R.id.fabAddTransaction);
        setupFloatingButton(mFloatingActionButton);
        mFloatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAddDialog(v);
            }
        });
    }

    private void bindToolbar() {
        mToolbar = (Toolbar) findViewById(R.id.app_bar);
        setupToolbar(mToolbar);
    }

    private void showAddDialog(View v) {
        String ae = "";
        if (!dialogVisible) {
            changeFragment = new ChangeWalletFragment(0, mTextViewMoney, mRecyclerView, getCategories());
            FragmentTransaction fm = getFragmentManager().beginTransaction();
            fm.setCustomAnimations(R.animator.slide_in_left, R.animator.slide_in_right);
            fm.replace(R.id.frameChange, changeFragment);
            fm.commit();
            dialogVisible = true;
        } else {
            getFragmentManager().beginTransaction()
                    .setCustomAnimations(R.animator.slide_in_left, R.animator.slide_in_right)
                    .remove(changeFragment)
                    .commit();
            dialogVisible = false;
        }

    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_list:
                Intent goToListActivity = new Intent(MainActivity.this, ListActivity.class);
                startActivity(goToListActivity);
                break;
            case R.id.nav_graph:
                Intent goToGraphActivity = new Intent(MainActivity.this, Chart.class);
                startActivity(goToGraphActivity);
                break;
        }

        return true;
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    //    mMonthTitle.setOnTouchListener(new View.OnTouchListener() {
//        @Override
//        public boolean onTouch(View v, MotionEvent event) {
//            Drawable[] compoundDrawables = mMonthTitle.getCompoundDrawables();
//            Drawable leftDrawable = compoundDrawables[0];
//            Drawable rightDrawable = compoundDrawables[2];
//            float viewX = mMonthTitle.getWidth();
//            float eventX = event.getX();
//            if (eventX < leftDrawable.getMinimumWidth()) {
//                mMonthTitle.setText(mManipulateList.swipe_left(mMonthTitle.getText().toString()));
//            }
//            if (eventX > (viewX - rightDrawable.getMinimumWidth())) {
//                mMonthTitle.setText(mManipulateList.swipe_right(mMonthTitle.getText().toString()));
//            }
//
//            return false;
//        }
//    });
}
