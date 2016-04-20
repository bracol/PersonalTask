package com.example.waniltonfilho.personaltasks.teste;

import android.app.IntentService;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.waniltonfilho.personaltasks.controller.tasks.TaskPostLogin;
import com.example.waniltonfilho.personaltasks.model.entities.User;
import com.example.waniltonfilho.personaltasks.model.http.LoginHttpService;

/**
 * Created by wanilton.filho on 20/04/2016.
 */
public class TestBroadcast extends IntentService {

    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     */
    public TestBroadcast() {
        super("TestBroadcast");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        AlarmReceiver.completeWakefulIntent(intent);

        Bundle extras = intent.getExtras();
        if (extras != null) {
            User user = extras.getParcelable("EZE_USER");
//            new TaskPostLogin(user).execute(); Chamada HTTP
            LoginHttpService.postLogin(user);
        }
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Toast.makeText(this, "Braodcast", Toast.LENGTH_LONG).show();
    }
}
