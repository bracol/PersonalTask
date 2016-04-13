package com.example.waniltonfilho.personaltasks.controller.tasks;

import android.os.AsyncTask;

import com.example.waniltonfilho.personaltasks.model.entities.User;
import com.example.waniltonfilho.personaltasks.model.entities.WalletTransaction;
import com.example.waniltonfilho.personaltasks.model.http.LoginHttpService;
import com.example.waniltonfilho.personaltasks.model.http.WalletTransactionHttpService;

import java.util.List;

/**
 * Created by wanilton.filho on 13/04/2016.
 */
public class TaskGetWalletTransaction extends AsyncTask<Void, Void, List<WalletTransaction>> {

    private String mWalletID;

    public TaskGetWalletTransaction(String id){
        mWalletID = id;
    }

    @Override
    protected List<WalletTransaction> doInBackground(Void... params) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return WalletTransactionHttpService.getWalletTransaction(mWalletID);
    }
}
