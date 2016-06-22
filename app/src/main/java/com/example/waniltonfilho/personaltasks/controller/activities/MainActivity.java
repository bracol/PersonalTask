package com.example.waniltonfilho.personaltasks.controller.activities;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.waniltonfilho.personaltasks.R;
import com.example.waniltonfilho.personaltasks.controller.adapter.WalletTransactionAdapter;
import com.example.waniltonfilho.personaltasks.controller.fragment.ChangeWalletFragment;
import com.example.waniltonfilho.personaltasks.controller.tasks.TaskGetLastTransaction;
import com.example.waniltonfilho.personaltasks.controller.tasks.TaskGetLogin;
import com.example.waniltonfilho.personaltasks.controller.tasks.TaskGetWallet;
import com.example.waniltonfilho.personaltasks.controller.tasks.TaskGetWtsSumMonth;
import com.example.waniltonfilho.personaltasks.controller.tasks.TaskPostWallet;
import com.example.waniltonfilho.personaltasks.model.entities.Category;
import com.example.waniltonfilho.personaltasks.model.entities.SumWalletTransaction;
import com.example.waniltonfilho.personaltasks.model.entities.User;
import com.example.waniltonfilho.personaltasks.model.entities.Wallet;
import com.example.waniltonfilho.personaltasks.model.entities.WalletTransaction;
import com.example.waniltonfilho.personaltasks.model.service.WalletService;
import com.example.waniltonfilho.personaltasks.model.service.WalletTransactionService;
import com.example.waniltonfilho.personaltasks.util.ConnectionUtil;
import com.example.waniltonfilho.personaltasks.util.MyValueFormatter;
import com.example.waniltonfilho.personaltasks.util.StringUtil;
import com.melnykov.fab.FloatingActionButton;

import java.util.List;
import java.util.Locale;

/**
 * Created by wanilton.filho on 22/01/2016.
 */
public class MainActivity extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener {


    public static final String WALLET_PARAM = "WALLET_PARAM";
    private Toolbar mToolbar;
    private FloatingActionButton mFloatingActionButton;
    private RecyclerView mRecyclerView;
    private List<WalletTransaction> mListTransactions;
    private TextView mTextViewMoney;
    public static boolean dialogVisible = false;
    private ChangeWalletFragment changeFragment;
    private Wallet mWallet;
    private User mUser;
    private LinearLayout linearLayoutLogged;
    private ImageView mImgViewLogout;
    private TextView mTvName;
    private boolean isOnline;
    private TextView mTextViewMonthInfo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        checkWallet();
    }

    private void bindComponents() {
        bindToolbar();
        bindFloatingButton();
        Category.getCategories();
        bindRecyclerView();
        bindTextViewMoney();
        bindNavigationView();
        bindLinearLogged();
        bindTvName();
        bindBtnLogout();
        bindTextViewInfoMonth();
    }

    private void bindTextViewInfoMonth() {
        mTextViewMonthInfo = (TextView) findViewById(R.id.textViewMoneyInfo);
        if (Locale.getDefault().equals(Locale.US))
            mTextViewMonthInfo.setText(StringUtil.getActualMonthYear()[0] + "/" + StringUtil.getActualMonthYear()[1] + " - " + getResources().getString(R.string.info_money));
        else
            mTextViewMonthInfo.setText(StringUtil.getActualMonthYear()[1] + "/" + StringUtil.getActualMonthYear()[0] + " - " + getResources().getString(R.string.info_money));
    }

    private void bindLinearLogged() {
        linearLayoutLogged = (LinearLayout) findViewById(R.id.linearLoginActive);
        if (mUser != null) {
            linearLayoutLogged.setVisibility(View.VISIBLE);
        }
    }

    private void bindBtnLogout() {
        mImgViewLogout = (ImageView) findViewById(R.id.btnLogout);
        if (mUser != null) {
            mImgViewLogout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onLogoutClick();
                }
            });
        }

    }

    private void goToLoginActivity() {
        Intent goToLoginActivity = new Intent(MainActivity.this, LoginMainActivity.class);
        startActivity(goToLoginActivity);
    }

    private void bindTvName() {
        mTvName = (TextView) findViewById(R.id.tvName);
        if (mUser != null) {
            mTvName.setText(mUser.getName());
        }
    }

    private void checkWallet() {
        User user = getPreferenceLogin();
        if (user == null) {
            if (WalletService.getWallet() == null) {
                mWallet = new Wallet();
                mWallet.setValue(0f);
                WalletService.save(mWallet);
            } else {
                mWallet = WalletService.getWallet();
                bindComponents();
            }
            isOnline = false;
        } else {
            if (ConnectionUtil.isConnected(this)) {
                getHttpLogin(user);
            } else {
                Toast.makeText(this, R.string.info_connection, Toast.LENGTH_LONG).show();
                clearPreferences();
                startActivity(new Intent(MainActivity.this, LoginMainActivity.class));
            }
            isOnline = true;
        }
    }

    private void getHttpWallet() {
        new TaskGetWallet(mUser.getId()) {
            @Override
            protected void onPostExecute(Wallet wallet) {
                mWallet = wallet;
                if (mWallet == null) {
                    new TaskPostWallet(mUser) {
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
        final MyValueFormatter myValueFormatter = new MyValueFormatter();
        mTextViewMoney = (TextView) findViewById(R.id.textViewMoney);
        if (isOnline) {
            new TaskGetWtsSumMonth(mWallet.get_id(), StringUtil.getActualMonthYear()[0], StringUtil.getActualMonthYear()[1]) {
                @Override
                protected void onPostExecute(SumWalletTransaction sumWalletTransaction) {
                    super.onPostExecute(sumWalletTransaction);
                    if (sumWalletTransaction != null) {
                        mTextViewMoney.setText(myValueFormatter.getMaskFormatted(sumWalletTransaction.getPrice()));
                    } else {
                        mTextViewMoney.setText(myValueFormatter.getMaskFormatted(0f));
                    }
                }
            }.execute();
        } else {
            mTextViewMoney.setText(myValueFormatter.getMaskFormatted(mWallet.getValue()));
        }


    }

    private void bindRecyclerView() {
        if (getPreferenceLogin() == null) {
            mListTransactions = WalletTransactionService.getLastTransactions(2);
            mRecyclerView = (RecyclerView) findViewById(R.id.recyclerLastTransaction);
            mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
            mRecyclerView.setAdapter(new WalletTransactionAdapter(mListTransactions, this));
        } else {
            new TaskGetLastTransaction(mWallet.get_id()) {

                ProgressDialog dialog;

                @Override
                protected void onPreExecute() {
                    dialog = new ProgressDialog(MainActivity.this);
                    dialog.setMessage("Loading...");
                    dialog.show();
                }

                @Override
                protected void onPostExecute(List<WalletTransaction> transactions) {
                    mListTransactions = transactions;
                    mRecyclerView = (RecyclerView) findViewById(R.id.recyclerLastTransaction);
                    mRecyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                    mRecyclerView.setAdapter(new WalletTransactionAdapter(mListTransactions, MainActivity.this));
                    dialog.dismiss();
                }
            }.execute();
        }

    }

    public User getPreferenceLogin() {
        SharedPreferences sharedPref = getSharedPreferences(LoginMainActivity.PREFERENCE_NAME, Context.MODE_PRIVATE);
        String login = sharedPref.getString("login", null);
        String pass = sharedPref.getString("pass", null);
        String name = sharedPref.getString("name", null);
        if (login == null && pass == null) {
            return null;
        } else {
            User userC = new User();
            userC.setUserName(login);
            userC.setPassword(pass);
            userC.setName(name);
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
            }

            @Override
            protected void onPostExecute(User user) {
                if (user != null) {
                    mUser = user;
                    getHttpWallet();
                } else {
                    clearPreferences();
                    finish();
                }

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
        if (!dialogVisible) {
            changeFragment = new ChangeWalletFragment();
            Bundle args = new Bundle();
            if (mUser != null) {
                args.putParcelable("wallet", mWallet);
            }
            changeFragment.setArguments(args);
            FragmentTransaction fm = getSupportFragmentManager().beginTransaction();
            fm.setCustomAnimations(R.anim.fade_in, R.anim.fade_out);
            fm.replace(R.id.frameChange, changeFragment);
            fm.commit();
            dialogVisible = true;
        }

    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_list:
                onNavListClick();
                break;
            case R.id.nav_graph:
                onNavGraphClick();
                break;
        }

        return true;
    }

    private void onNavGraphClick() {
        Intent goToGraphActivity = new Intent(MainActivity.this, ChartActivity.class);
        if (mUser != null) {
            Bundle bundle = new Bundle();
            bundle.putParcelable(WALLET_PARAM, mWallet);
            goToGraphActivity.putExtras(bundle);
        }
        startActivity(goToGraphActivity);
    }

    private void onNavListClick() {
        Intent goToListActivity = new Intent(MainActivity.this, ListActivity.class);
        if (mUser != null) {
            goToListActivity.putExtra(WALLET_PARAM, mWallet);
        }
        startActivity(goToListActivity);
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

    private void clearPreferences() {
        SharedPreferences sharedPref = getSharedPreferences(LoginMainActivity.PREFERENCE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.remove("login");
        editor.remove("pass");
        editor.remove("name");
        editor.commit();
    }

    private void onLogoutClick() {
        new AlertDialog.Builder(MainActivity.this)
                .setTitle(R.string.lbl_confirm)
                .setMessage(R.string.info_confirm_logout)
                .setPositiveButton(R.string.lbl_yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        clearPreferences();
                        goToLoginActivity();
                    }
                })
                .setNeutralButton(R.string.lbl_no, null)
                .create()
                .show();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (mUser != null) {
                return true;
            }
        }
        return super.onKeyDown(keyCode, event);
    }


}
