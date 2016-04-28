package com.example.waniltonfilho.personaltasks.teste;

import android.app.AlarmManager;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.waniltonfilho.personaltasks.R;
import com.example.waniltonfilho.personaltasks.controller.activities.LoginFormActivity;
import com.example.waniltonfilho.personaltasks.controller.activities.LoginMainActivity;
import com.example.waniltonfilho.personaltasks.controller.activities.MainActivity;

import java.util.GregorianCalendar;

/**
 * Created by Wanilton on 22/04/2016.
 */
public class TesteAlarmNotification extends AppCompatActivity {

    Button btnStart;
    NotificationManager notificationManager;
    boolean isNotificated = false;
    int notID = 33;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teste);
        bind();
    }

    private void bind() {
        bindBtnStart();
    }

    private void bindBtnStart() {
        btnStart = (Button) findViewById(R.id.button1);
        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setAlarm(v);
            }
        });
    }

    public void showNotification(){
        NotificationCompat.Builder notiBuilder = new
                NotificationCompat.Builder(this)
                .setContentTitle("ContentTitle")
                .setContentText("ContentText")
                .setTicker("TICKER")
                .setSmallIcon(R.drawable.bus);

        Intent goToMainActivity = new Intent(TesteAlarmNotification.this, MainActivity.class);

        TaskStackBuilder taskStackBuilder = TaskStackBuilder.create(this);

        taskStackBuilder.addParentStack(MainActivity.class);

        taskStackBuilder.addNextIntent(goToMainActivity);

        PendingIntent pendingIntent = taskStackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);

        notiBuilder.setContentIntent(pendingIntent);

        notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        notificationManager.notify(notID, notiBuilder.build());

        isNotificated = true;
    }

    public void setAlarm(View v){
        manipulaPreferences();
        Long alertTime = new GregorianCalendar().getTimeInMillis()+5*1000;

        Intent alertIntent = new Intent(this, AlarmReceiverNotification.class);

        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);

        //alarmManager.set(AlarmManager.RTC_WAKEUP, alertTime, PendingIntent.getBroadcast(this, 1, alertIntent, PendingIntent.FLAG_UPDATE_CURRENT));

        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, alertTime, 60000, PendingIntent.getBroadcast(this, 1, alertIntent, PendingIntent.FLAG_ONE_SHOT));

    }

    public void manipulaPreferences(){
        SharedPreferences sharedPref = getSharedPreferences("ab", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString("primeiro", "3");
        editor.putString("primeiro", "1");
        editor.commit();
        sharedPref.getString("primeiro", null);
        editor.putString("segundo", "2");
        editor.commit();
    }


}
