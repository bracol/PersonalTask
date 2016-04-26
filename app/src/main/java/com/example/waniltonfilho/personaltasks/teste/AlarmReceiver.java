package com.example.waniltonfilho.personaltasks.teste;

/**
 * Created by wanilton.filho on 19/04/2016.
 */

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.WakefulBroadcastReceiver;

import com.example.waniltonfilho.personaltasks.controller.tasks.TaskPostLogin;
import com.example.waniltonfilho.personaltasks.model.entities.User;

import java.util.Calendar;

public class AlarmReceiver extends WakefulBroadcastReceiver {

    private User mUser;

    @Override
    public void onReceive(Context context, Intent intent) {
        Bundle extras = intent.getExtras();
        if (extras != null) {
            mUser = extras.getParcelable("EZE_USER");
            new TaskPostLogin(mUser).execute();
        }
    }

    public void setAlarm(final Context context, User user) {
        final AlarmManager alarmManager = (AlarmManager) context
                .getSystemService(Context.ALARM_SERVICE);
        final Intent intent = new Intent(context, TestBroadcast.class);
        Bundle bundle = new Bundle();
        bundle.putParcelable("EZE_USER", user);
        intent.putExtras(bundle);
        final PendingIntent pendingIntent = PendingIntent.getBroadcast(context,
                0, intent, 0);
        Calendar calendar = Calendar.getInstance();
        alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP,
                calendar.getTimeInMillis(), 500, pendingIntent);
    }

}
