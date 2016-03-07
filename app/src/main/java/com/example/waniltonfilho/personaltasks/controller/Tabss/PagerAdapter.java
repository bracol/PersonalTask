package com.example.waniltonfilho.personaltasks.controller.Tabss;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.waniltonfilho.personaltasks.controller.fragment.ListTransactionFragment;
import com.example.waniltonfilho.personaltasks.controller.fragment.WalletFragment;

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
                WalletFragment tab3 = new WalletFragment();
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