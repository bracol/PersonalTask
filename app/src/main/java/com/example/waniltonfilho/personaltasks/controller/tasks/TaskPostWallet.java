package com.example.waniltonfilho.personaltasks.controller.tasks;

import android.os.AsyncTask;

import com.example.waniltonfilho.personaltasks.model.entities.User;
import com.example.waniltonfilho.personaltasks.model.entities.Wallet;
import com.example.waniltonfilho.personaltasks.model.http.WalletHttpService;

/**
 * Created by Wanilton on 09/04/2016.
 */
public class TaskPostWallet extends AsyncTask<Void, Void, Wallet> {

    User mUser;

    public TaskPostWallet(User user){
        mUser = user;
    }

    @Override
    protected Wallet doInBackground(Void... params) {
        Wallet wallet = new Wallet();
        wallet.setValue(0f);
        wallet.setLogin_id(mUser.getId());
        return WalletHttpService.postWallet(wallet);
    }
}
