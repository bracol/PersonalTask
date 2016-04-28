package com.example.waniltonfilho.personaltasks.teste;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;

import com.example.waniltonfilho.personaltasks.R;
import com.example.waniltonfilho.personaltasks.controller.activities.MainActivity;

/**
 * Created by Wanilton on 22/04/2016.
 */
public class AlarmReceiverNotification extends BroadcastReceiver {


    @Override
    public void onReceive(Context context, Intent intent) {
        createNotification(context, "TIMES UP", "1 minute has passed", "Alert");
    }

    private void createNotification(Context context, String msg, String msgText, String alert) {
        PendingIntent pIntent = PendingIntent.getActivity(context, 0,
                new Intent(context, MainActivity.class), 0);

        NotificationCompat.Builder mBuilder = new
                NotificationCompat.Builder(context)
                .setContentTitle(msg)
                .setContentText(msgText)
                .setTicker(alert)
                .setSmallIcon(R.drawable.bus);

        mBuilder.setContentIntent(pIntent);
        mBuilder.setDefaults(NotificationCompat.DEFAULT_VIBRATE);
        mBuilder.setAutoCancel(true);

        NotificationManager notificationManager = (NotificationManager) context.getSystemService(context.NOTIFICATION_SERVICE);

        notificationManager.notify(1, mBuilder.build());


    }
}
