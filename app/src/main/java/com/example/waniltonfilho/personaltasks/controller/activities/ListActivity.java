package com.example.waniltonfilho.personaltasks.controller.activities;

import android.app.ProgressDialog;
import android.graphics.Point;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.waniltonfilho.personaltasks.R;
import com.example.waniltonfilho.personaltasks.controller.adapter.WalletTransactionAdapter;
import com.example.waniltonfilho.personaltasks.controller.tasks.TaskGetWalletTransaction;
import com.example.waniltonfilho.personaltasks.model.MonthList;
import com.example.waniltonfilho.personaltasks.model.entities.Wallet;
import com.example.waniltonfilho.personaltasks.model.entities.WalletTransaction;
import com.example.waniltonfilho.personaltasks.model.service.WalletTransactionService;
import com.example.waniltonfilho.personaltasks.util.ListManipulation;

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
    private MonthList mManipulateList;
    private RecyclerView mRecyclerView;
    private RelativeLayout relativeContainer;
    private Wallet mWallet;
    private String mMonth;
    private String mYear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_month);
        bindComponents();
    }

    private void bindComponents() {
        initWallet();
        bindToolbar();
        bindRelativeContainer();
        bindTextViewInfoMonth();
        bindTextViewMonth();
        bindRecyclerViewList();
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void initWallet() {
        Bundle extras = getIntent().getExtras();
        if(extras != null){
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
                ListManipulation listManipulation = new ListManipulation(mMonth, transactions);
                updateList(listManipulation.getByMonth());
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
        if(mWallet == null){
            List<WalletTransaction> mTransactionsMonth = WalletTransactionService.getMonthTransaction(mMonth);
            updateList(mTransactionsMonth);
        } else {
            getList();
        }

    }

    private void bindRecyclerViewList(){
        List<WalletTransaction> transactions = new ArrayList<>();
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerViewMonth);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(new WalletTransactionAdapter(transactions, this));
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


}
