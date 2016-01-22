package com.example.waniltonfilho.personaltasks.model;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.example.waniltonfilho.personaltasks.R;
import com.example.waniltonfilho.personaltasks.util.CustomViews;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by wanilton.filho on 21/01/2016.
 */
public class TesteCustomView extends AppCompatActivity {

    private CustomViews mView;
    private SwipeRefreshLayout mRefreshLayout;
    private Adapter mAdapter;
    private ListView mListView;
    private String[] mCatNames;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_swipe_down);
        //setSupportActionBar((Toolbar) findViewById(R.id.app_bar));

        //mView = (CustomViews) findViewById(R.id.customView);
//        mView.setCircleColor(Color.RED);
//        mView.setCircleTextColor(Color.BLACK);
//        mView.setCircleTextSize(50);
//        mView.setCircleText("STOP");




        mRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.list_swipe_down);
        mRefreshLayout.setColorSchemeResources(R.color.orange, R.color.green, R.color.blue);
        mListView = (ListView) findViewById(R.id.list_view_swipe);
        mCatNames = getResources().getStringArray(R.array.cat_names);
        mAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, mCatNames);
        mListView.setAdapter((ListAdapter) mAdapter);
        mRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshContent();
            }
        });
    }

    private void refreshContent() {
        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                mAdapter = new ArrayAdapter<String>(TesteCustomView.this, android.R.layout.simple_list_item_1, getNewCatNames());
                mListView.setAdapter((ListAdapter) mAdapter);
                mRefreshLayout.setRefreshing(false);
            }
        }, 0);
    }

    private List<String> getNewCatNames() {
        List<String> newCatNames = new ArrayList<String>();
        for (int i = 0; i < mCatNames.length; i++) {
            int randomCatNameIndex = new Random().nextInt(mCatNames.length - 1);
            newCatNames.add(mCatNames[randomCatNameIndex]);
        }
        return newCatNames;
    }


}
