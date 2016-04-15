package com.example.waniltonfilho.personaltasks.controller.tasks;

import android.os.AsyncTask;

import com.example.waniltonfilho.personaltasks.model.entities.User;
import com.example.waniltonfilho.personaltasks.model.entities.Wallet;
import com.example.waniltonfilho.personaltasks.model.http.WalletHttpService;

/**
 * Created by wanilton.filho on 14/03/2016.
 */
public class TaskGetWallet extends AsyncTask<Void, Void, Wallet> {

    private String mUserId;

    public TaskGetWallet(String user_id){
        mUserId = user_id;
    }


    @Override
    protected Wallet doInBackground(Void... params) {
        return WalletHttpService.getWallet(mUserId);
    }
}
