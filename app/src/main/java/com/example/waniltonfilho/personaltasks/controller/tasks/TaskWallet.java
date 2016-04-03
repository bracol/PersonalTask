package com.example.waniltonfilho.personaltasks.controller.tasks;

import android.os.AsyncTask;

import com.example.waniltonfilho.personaltasks.model.entities.Wallet;
import com.example.waniltonfilho.personaltasks.model.http.WalletHttpService;

import java.util.List;

/**
 * Created by wanilton.filho on 14/03/2016.
 */
public class TaskWallet extends AsyncTask<Void, Void, List<Wallet>> {
    @Override
    protected List<Wallet> doInBackground(Void... params) {
        return WalletHttpService.getWallets();
    }
}
