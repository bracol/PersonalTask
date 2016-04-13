package com.example.waniltonfilho.personaltasks.controller.tasks;

import android.os.AsyncTask;

import com.example.waniltonfilho.personaltasks.model.entities.WalletTransaction;
import com.example.waniltonfilho.personaltasks.model.http.WalletTransactionHttpService;

/**
 * Created by wanilton.filho on 13/04/2016.
 */
public class TaskPostWalletTransaction extends AsyncTask<Void, Void, Void> {

    private WalletTransaction mWalletTransaction;

    public TaskPostWalletTransaction(WalletTransaction mWt) {
        mWalletTransaction = mWt;
    }

    @Override
    protected Void doInBackground(Void... params) {
        WalletTransactionHttpService.postWalletTransaction(mWalletTransaction);
        return null;
    }
}
