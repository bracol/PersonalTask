package com.example.waniltonfilho.personaltasks.controller.activities;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.graphics.Point;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.waniltonfilho.personaltasks.R;
import com.example.waniltonfilho.personaltasks.controller.adapter.WalletTransactionAdapter;
import com.example.waniltonfilho.personaltasks.model.MonthList;
import com.example.waniltonfilho.personaltasks.model.entities.WalletTransaction;
import com.example.waniltonfilho.personaltasks.model.service.WalletTransactionService;

import java.text.SimpleDateFormat;
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
    private List<WalletTransaction> mTransactionsMonth;
    private RelativeLayout relativeContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_month);
        bindComponents();
    }

    private void bindComponents() {
        bindToolbar();
        bindRelativeContainer();
        bindTextViewInfoMonth();
        bindTextViewMonth();
        bindRecyclerViewList();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
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
                getWindowManager().getDefaultDisplay().getSize(size);
                float eventX = event.getX();
                if (eventX < 200) {
                    Animation animation = AnimationUtils.loadAnimation(ListActivity.this, R.anim.swipe_left);
                    relativeContainer.startAnimation(animation);
                    mMonthTitle.setText(mManipulateList.swipeLeft(mMonthTitle.getText().toString()));
                }
                if (eventX > size.x - 200) {
                    Animation animation = AnimationUtils.loadAnimation(ListActivity.this, R.anim.swipe_right);
                    relativeContainer.startAnimation(animation);
                    mMonthTitle.setText(mManipulateList.swipeRight(mMonthTitle.getText().toString()));
                }
                updateList();
                return false;
            }
        });
    }

    private void bindRecyclerViewList() {
        String tv = mMonthTitle.getText().toString();
        String month = tv.substring(0, 2);
        mTransactionsMonth = WalletTransactionService.getMonthTransaction(month);
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerViewMonth);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(new WalletTransactionAdapter(mTransactionsMonth, this));
    }

    private void updateList() {
        String tv = mMonthTitle.getText().toString();
        String month = tv.substring(0, 2);
        mTransactionsMonth = WalletTransactionService.getMonthTransaction(month);
        if (mTransactionsMonth.size() > 0) {
            mInfoTransaction.setVisibility(View.INVISIBLE);
            mRecyclerView.setVisibility(View.VISIBLE);
            Collections.sort(mTransactionsMonth, Collections.reverseOrder());
        } else {
            mRecyclerView.setVisibility(View.INVISIBLE);
            mInfoTransaction.setVisibility(View.VISIBLE);
        }

        WalletTransactionAdapter adapter = (WalletTransactionAdapter) mRecyclerView.getAdapter();
        adapter.setItens(mTransactionsMonth);
        adapter.notifyDataSetChanged();
    }

    private void bindToolbar() {
        setupToolbar(mToolbar);
    }


}
