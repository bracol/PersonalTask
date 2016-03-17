package com.example.waniltonfilho.personaltasks.controller.activities;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.graphics.Point;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.widget.TextView;

import com.example.waniltonfilho.personaltasks.R;
import com.example.waniltonfilho.personaltasks.controller.adapter.WalletTransactionAdapter;
import com.example.waniltonfilho.personaltasks.model.MonthList;
import com.example.waniltonfilho.personaltasks.model.entities.WalletTransaction;
import com.example.waniltonfilho.personaltasks.model.service.WalletTransactionService;

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
    private List<WalletTransaction> mTransactionsMonth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_month);
        bindComponents();
    }

    private void bindComponents() {
        bindToolbar();
        bindTextViewInfoMonth();
        bindTextViewMonth();
        bindRecyclerViewList();
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
        mManipulateList = new MonthList(mes, ano);
        mMonthTitle.setText(mes + "/" + ano);

        mMonthTitle.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Point size = new Point();
                getWindowManager().getDefaultDisplay().getSize(size);
                float eventX = event.getX();
                if (eventX < 200) {
                    mMonthTitle.setText(mManipulateList.swipeLeft(mMonthTitle.getText().toString()));
                }
                if (eventX > size.x - 200) {
                    mMonthTitle.setText(mManipulateList.swipeRight(mMonthTitle.getText().toString()));
                }
                updateList();
                return false;
            }
        });
    }

    private void bindRecyclerViewList() {
        mTransactionsMonth = new ArrayList<>();
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerViewMonth);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(new WalletTransactionAdapter(mTransactionsMonth, this));
    }

    @Override
    protected void onResume() {
        super.onResume();
        updateList();
    }

    private void updateList() {
        String tv = mMonthTitle.getText().toString();
        String month = tv.substring(0, 2);
        mTransactionsMonth = WalletTransactionService.getMonthTransaction(month);
        if (mTransactionsMonth.size() > 0) {
            if (mInfoTransaction.getVisibility() == View.VISIBLE) {
                viewOut(mInfoTransaction);
                viewIn(mRecyclerView);
            }
            Collections.sort(mTransactionsMonth, Collections.reverseOrder());
        } else {
            if (mInfoTransaction.getVisibility() == View.INVISIBLE) {
                viewOut(mRecyclerView);
                viewIn(mInfoTransaction);
            }
        }

        WalletTransactionAdapter adapter = (WalletTransactionAdapter) mRecyclerView.getAdapter();
        adapter.setItens(mTransactionsMonth);
        adapter.notifyDataSetChanged();
    }

    private void bindToolbar() {
        setupToolbar(mToolbar);
    }

    private void viewIn(View myView) {
        // previously invisible view

// get the center for the clipping circle
        int cx = (myView.getLeft() + myView.getRight()) / 2;
        int cy = (myView.getTop() + myView.getBottom()) / 2;

// get the final radius for the clipping circle
        int finalRadius = Math.max(myView.getWidth(), myView.getHeight());

// create the animator for this view (the start radius is zero)
        Animator anim =
                ViewAnimationUtils.createCircularReveal(myView, cx, cy, 0, finalRadius);

        anim.setDuration(1000);

// make the view visible and start the animation
        myView.setVisibility(View.VISIBLE);
        anim.start();
    }

    private void viewOut(final View myView) {

// get the center for the clipping circle
        int cx = (myView.getLeft() + myView.getRight()) / 2;
        int cy = (myView.getTop() + myView.getBottom()) / 2;

// get the initial radius for the clipping circle
        int initialRadius = myView.getWidth();

// create the animation (the final radius is zero)
        Animator anim =
                ViewAnimationUtils.createCircularReveal(myView, cx, cy, initialRadius, 0);

        anim.setDuration(1000);

// make the view invisible when the animation is done
        anim.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                myView.setVisibility(View.INVISIBLE);
            }
        });

// start the animation
        anim.start();
    }


}
