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

public class AlarmReceiver extends WakefulBroadcastReceiver {

    private User mUser;

    @Override
    public void onReceive(Context context, Intent intent) {
        final Intent service = new Intent(context, TestBroadcast.class);
        startWakefulService(context, service);
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
        alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP,
                System.currentTimeMillis(), 500, pendingIntent);
    }

}
