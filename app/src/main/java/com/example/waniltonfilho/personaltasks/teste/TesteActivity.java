package com.example.waniltonfilho.personaltasks.teste;

import java.util.Calendar;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.example.waniltonfilho.personaltasks.R;
import com.example.waniltonfilho.personaltasks.model.entities.User;
import com.example.waniltonfilho.personaltasks.model.entities.WalletTransaction;

public class TesteActivity extends FragmentActivity {
    private static int timeHour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
    private static int timeMinute = Calendar.getInstance().get(Calendar.MINUTE);
    AlarmManager alarmManager;
    private PendingIntent pendingIntent;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teste);

//        alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
//        Intent myIntent = new Intent(TesteActivity.this, AlarmReceiver.class);
        User user = new User();
        user.setName("hue");
        user.setUserName("hue1");
        user.setPassword("hue2");
        AlarmReceiver alarmReceiver = new AlarmReceiver();
        alarmReceiver.setAlarm(this, user);
//        myIntent.putExtra("user", user);
//        pendingIntent = PendingIntent.getBroadcast(TesteActivity.this, 0, myIntent, 0);


        Button btn1 = (Button) findViewById(R.id.button1);
        btn1.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                alarmManager.setRepeating(AlarmManager.RTC, 500, 500, pendingIntent);
            }
        });

    }
}
