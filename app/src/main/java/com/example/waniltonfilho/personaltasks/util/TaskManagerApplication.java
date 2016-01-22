package com.example.waniltonfilho.personaltasks.util;

import android.app.Application;

/**
 * Created by Administrador on 01/10/2015.
 */
public class TaskManagerApplication extends Application {
    //application manipula ciclo de vida da aplicação

    public void onCreate(){
        super.onCreate();
        ApplicationUtil.setContext(getApplicationContext());
    }

}
