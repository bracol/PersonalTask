package com.example.waniltonfilho.personaltasks.controller.tasks;

import android.os.AsyncTask;

import com.example.waniltonfilho.personaltasks.model.entities.Wallet;
import com.example.waniltonfilho.personaltasks.model.http.WalletHttpService;

/**
 * Created by Wanilton on 14/04/2016.
 */
public class TaskUpdateWallet extends AsyncTask<Void, Void, Wallet> {

    private Wallet mWallet;

    public TaskUpdateWallet(Wallet wallet){
        mWallet = wallet;
    }

    @Override
    protected Wallet doInBackground(Void... params) {
        return WalletHttpService.updateWallet(mWallet);
    }
}
