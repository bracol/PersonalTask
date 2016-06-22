package com.example.waniltonfilho.personaltasks.controller.tasks;

import android.os.AsyncTask;

import com.example.waniltonfilho.personaltasks.model.entities.WalletTransaction;
import com.example.waniltonfilho.personaltasks.model.http.WalletTransactionHttpService;

/**
 * Created by Wanilton on 11/06/2016.
 */
public class TaskDeleteWalletTransaction extends AsyncTask<Void, Void, Void> {

    private WalletTransaction mWalletTransaction;

    public TaskDeleteWalletTransaction(WalletTransaction mWt) {
        mWalletTransaction = mWt;
    }

    @Override
    protected Void doInBackground(Void... params) {
        WalletTransactionHttpService.deleteWalletTransaction(mWalletTransaction);
        return null;
    }
}