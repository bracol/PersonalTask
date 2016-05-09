package com.example.waniltonfilho.personaltasks.controller.tasks;

import android.os.AsyncTask;

import com.example.waniltonfilho.personaltasks.model.entities.GroupCategoryTransaction;
import com.example.waniltonfilho.personaltasks.model.entities.SumWalletTransaction;
import com.example.waniltonfilho.personaltasks.model.entities.WalletTransaction;
import com.example.waniltonfilho.personaltasks.model.http.GroupCategoryTransactionHttpService;
import com.example.waniltonfilho.personaltasks.model.http.WalletTransactionHttpService;

import java.util.List;

/**
 * Created by wanilton.filho on 09/05/2016.
 */
public class TaskGetWtsSumMonth  extends AsyncTask<Void, Void, SumWalletTransaction> {

    private String mYear;
    private String mMonth;
    private String mWallet_id;

    public TaskGetWtsSumMonth(String wallet_id, String year, String month){
        mYear = year;
        mMonth = month;
        mWallet_id = wallet_id;
    }


    @Override
    protected SumWalletTransaction doInBackground(Void... params) {
        return WalletTransactionHttpService.getSumMonthWalletTransaction(mWallet_id, mYear, mMonth);
    }
}
