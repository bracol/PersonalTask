package com.example.waniltonfilho.personaltasks.controller.tasks;

import android.os.AsyncTask;

import com.example.waniltonfilho.personaltasks.model.entities.WalletTransaction;
import com.example.waniltonfilho.personaltasks.model.http.WalletTransactionHttpService;

import java.util.List;

/**
 * Created by wanilton.filho on 11/05/2016.
 */

public class TaskGetLastTransaction extends AsyncTask<Void, Void, List<WalletTransaction>> {

    private String mWalletID;

    public TaskGetLastTransaction(String id){
        mWalletID = id;
    }

    @Override
    protected List<WalletTransaction> doInBackground(Void... params) {
        return WalletTransactionHttpService.getLastWalletTransaction(mWalletID);
    }
}
