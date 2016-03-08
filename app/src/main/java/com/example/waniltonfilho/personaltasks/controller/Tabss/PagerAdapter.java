package com.example.waniltonfilho.personaltasks.controller.Tabss;

import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.waniltonfilho.personaltasks.controller.fragment.FragmentGraph;
import com.example.waniltonfilho.personaltasks.controller.fragment.ListTransactionFragment;
import com.example.waniltonfilho.personaltasks.controller.fragment.WalletFragment;
import com.example.waniltonfilho.personaltasks.model.entities.WalletTransaction;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;

import java.util.ArrayList;
import java.util.List;

public class PagerAdapter extends FragmentStatePagerAdapter {
    int mNumOfTabs;


    public PagerAdapter(FragmentManager fm, int NumOfTabs) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                WalletFragment tab1 = new WalletFragment();
                return tab1;
            case 1:
                ListTransactionFragment tab2 = new ListTransactionFragment();
                return tab2;
            case 2:
                FragmentGraph tab3 = new FragmentGraph();
                return tab3;
            default:
                return null;
        }
    }



    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}