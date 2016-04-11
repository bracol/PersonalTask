package com.example.waniltonfilho.personaltasks.controller.tasks;

import android.os.AsyncTask;

import com.example.waniltonfilho.personaltasks.model.entities.User;
import com.example.waniltonfilho.personaltasks.model.http.LoginHttpService;

/**
 * Created by Wanilton on 08/04/2016.
 */
public class TaskGetLogin extends AsyncTask<Void, Void, User> {

    private User mUser;

    public TaskGetLogin(User user){
        mUser = user;
    }

    @Override
    protected User doInBackground(Void... params) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return LoginHttpService.getLogin(mUser);
    }
}

