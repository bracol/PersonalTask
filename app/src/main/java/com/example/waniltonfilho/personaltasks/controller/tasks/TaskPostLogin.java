package com.example.waniltonfilho.personaltasks.controller.tasks;

import android.os.AsyncTask;

import com.example.waniltonfilho.personaltasks.model.entities.User;
import com.example.waniltonfilho.personaltasks.model.http.LoginHttpService;

/**
 * Created by Wanilton on 08/04/2016.
 */
public class TaskPostLogin extends AsyncTask<Void, Void, Void> {

    User mUser;

    public TaskPostLogin(User user){
        mUser = user;
    }


    @Override
    protected Void doInBackground(Void... params) {
        LoginHttpService.postLogin(mUser);
        return null;
    }
}
