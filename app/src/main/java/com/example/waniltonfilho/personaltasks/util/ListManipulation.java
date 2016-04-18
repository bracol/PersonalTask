package com.example.waniltonfilho.personaltasks.util;

import android.app.Activity;
import android.app.ProgressDialog;

import com.example.waniltonfilho.personaltasks.controller.tasks.TaskGetWalletTransaction;
import com.example.waniltonfilho.personaltasks.model.entities.WalletTransaction;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Wanilton on 15/04/2016.
 */
public class ListManipulation {

    private List<WalletTransaction> mListTransaction;
    private String mMonth;

    public ListManipulation(String month, List<WalletTransaction> transactions) {
        mMonth = month;
        mListTransaction = transactions;
    }

    public List<WalletTransaction> getByMonth() {
        List<WalletTransaction> listByMonth = new ArrayList<>();

        for (WalletTransaction wt : mListTransaction) {
            if (mMonth.equals(wt.getDate().substring(5, 7))) {
                listByMonth.add(wt);
            }
        }

        return listByMonth;
    }



}
