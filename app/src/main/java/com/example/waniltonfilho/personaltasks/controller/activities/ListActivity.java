package com.example.waniltonfilho.personaltasks.controller.activities;

import android.app.ProgressDialog;
import android.graphics.Point;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.waniltonfilho.personaltasks.R;
import com.example.waniltonfilho.personaltasks.controller.adapter.WalletTransactionAdapter;
import com.example.waniltonfilho.personaltasks.controller.tasks.TaskDeleteWalletTransaction;
import com.example.waniltonfilho.personaltasks.controller.tasks.TaskGetWalletTransaction;
import com.example.waniltonfilho.personaltasks.model.MonthList;
import com.example.waniltonfilho.personaltasks.model.entities.Wallet;
import com.example.waniltonfilho.personaltasks.model.entities.WalletTransaction;
import com.example.waniltonfilho.personaltasks.model.service.WalletTransactionService;
import com.example.waniltonfilho.personaltasks.util.ListManipulation;
import com.example.waniltonfilho.personaltasks.util.MyValueFormatter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * Created by wanilton.filho on 14/03/2016.
 */

public class ListActivity extends BaseActivity {

    private Toolbar mToolbar;
    private TextView mMonthTitle;
    private TextView mInfoTransaction;
    private TextView mTvListMonthTotal;
    private MonthList mManipulateList;
    private RecyclerView mRecyclerView;
    private WalletTransactionAdapter walletTransactionAdapter;
    private RelativeLayout relativeContainer;
    private Wallet mWallet;
    private String mMonth;
    private String mYear;
    private List<WalletTransaction> mWalletTransactionsWeb;
    private ListManipulation listManipulation;
    private MyValueFormatter myValueFormatter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_month);
        bindComponents();
    }

    private void bindComponents() {
        initWallet();
        bindToolbar();
        bindValueFormat();
        bindTvListMonthTotal();
        bindRelativeContainer();
        bindTextViewInfoMonth();
        bindTextViewMonth();
        bindRecyclerViewList();
        if (mWallet != null) {
            getList();
        }
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void bindValueFormat() {
        myValueFormatter = new MyValueFormatter();
    }

    private void bindTvListMonthTotal() {
        mTvListMonthTotal = (TextView) findViewById(R.id.tvListMonthTotal);
    }

    private void initWallet() {
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            mWallet = extras.getParcelable(MainActivity.WALLET_PARAM);

        }
    }

    public void getList() {
        new TaskGetWalletTransaction(mWallet.get_id()) {
            ProgressDialog dialog;

            @Override
            protected void onPreExecute() {
                dialog = new ProgressDialog(ListActivity.this);
                dialog.setMessage("Loading...");
                dialog.show();
                super.onPreExecute();
            }

            @Override
            protected void onPostExecute(List<WalletTransaction> transactions) {
                super.onPostExecute(transactions);
                mWalletTransactionsWeb = transactions;
                listVerify();
                dialog.dismiss();

            }
        }.execute();
    }

    private void bindRelativeContainer() {
        relativeContainer = (RelativeLayout) findViewById(R.id.listContainer);
    }

    private void bindTextViewInfoMonth() {
        mInfoTransaction = (TextView) findViewById(R.id.tvNothingToShow);
    }

    private void bindTextViewMonth() {
        mMonthTitle = (TextView) findViewById(R.id.tvListMonth);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        String actualDate = simpleDateFormat.format(date);
        String mes = actualDate.substring(5, 7);
        int ano = Integer.parseInt(actualDate.substring(0, 4));
        mManipulateList = new MonthList();
        mMonthTitle.setText(mes + "/" + ano);

        mMonthTitle.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Point size = new Point();
                ArrayList<View> touchables = v.getTouchables();
                getWindowManager().getDefaultDisplay().getSize(size);
                float eventX = event.getX();
                if (eventX < 200) {
                    mMonthTitle.setText(mManipulateList.swipeLeft(mMonthTitle.getText().toString()));
                    Animation animation = AnimationUtils.loadAnimation(ListActivity.this, R.anim.swipe_from_left);
                    relativeContainer.startAnimation(animation);
                }
                if (eventX > size.x - 200) {
                    mMonthTitle.setText(mManipulateList.swipeRight(mMonthTitle.getText().toString()));
                    Animation animation = AnimationUtils.loadAnimation(ListActivity.this, R.anim.swipe_from_right);
                    relativeContainer.startAnimation(animation);
                }
                listVerify();
                return false;
            }
        });
    }

    private void listVerify() {
        String tv = mMonthTitle.getText().toString();
        mMonth = tv.substring(0, 2);
        mYear = tv.substring(3, 7);
        String monthYear = mYear + "-" + mMonth;

        if (mWallet == null) {
            List<WalletTransaction> mTransactionsMonth = WalletTransactionService.getMonthTransaction(monthYear);
            updateList(mTransactionsMonth);
            mTvListMonthTotal.setText(myValueFormatter.getMaskFormatted(getTotalMonthOffline(mTransactionsMonth)));
        } else if (mWalletTransactionsWeb != null) {
            listManipulation = new ListManipulation(mMonth, mYear, mWalletTransactionsWeb);
            updateList(listManipulation.getByMonth());
            mTvListMonthTotal.setText(myValueFormatter.getMaskFormatted(listManipulation.getTotalMonth()));
        }

    }

    private void bindRecyclerViewList() {
        List<WalletTransaction> transactions = new ArrayList<>();
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerViewMonth);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        walletTransactionAdapter = new WalletTransactionAdapter(transactions, this);
        mRecyclerView.setAdapter(walletTransactionAdapter);
        listVerify();
    }

    private void updateList(List<WalletTransaction> transactions) {
        String tv = mMonthTitle.getText().toString();
        mMonth = tv.substring(0, 2);
        visibilityListChange(transactions);

        WalletTransactionAdapter adapter = (WalletTransactionAdapter) mRecyclerView.getAdapter();
        adapter.setItens(transactions);
        adapter.notifyDataSetChanged();
    }

    private void visibilityListChange(List<WalletTransaction> transactions) {
        if (transactions.size() > 0) {
            mInfoTransaction.setVisibility(View.INVISIBLE);
            mRecyclerView.setVisibility(View.VISIBLE);
            Collections.sort(transactions, Collections.reverseOrder());
        } else {
            mRecyclerView.setVisibility(View.INVISIBLE);
            mInfoTransaction.setVisibility(View.VISIBLE);
        }
    }

    private void bindToolbar() {
        mToolbar = (Toolbar) findViewById(R.id.app_bar);
        setupToolbar(mToolbar);
        mToolbar.setTitle("Lista de Transações");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        walletTransactionAdapter.getItemSelected(item);
        WalletTransaction w = walletTransactionAdapter.getItem();
        deleteTransaction(w);
        Snackbar.make(mRecyclerView, R.string.action_transaction_deleted, Snackbar.LENGTH_SHORT).show();
        return super.onContextItemSelected(item);
    }

    private void deleteTransaction(WalletTransaction w) {
        if (mWallet == null) {
            WalletTransactionService.delete(w);
            listVerify();
        } else {
            new TaskDeleteWalletTransaction(w) {
                @Override
                protected void onPostExecute(Void aVoid) {
                    super.onPostExecute(aVoid);
                    listVerify();
                }
            }.execute();
        }
    }

    public float getTotalMonthOffline(List<WalletTransaction> wts) {
        Float totalMonthOffline = 0f;
        for (WalletTransaction wt : wts)
            totalMonthOffline += wt.getPrice();
        return totalMonthOffline;
    }
}
