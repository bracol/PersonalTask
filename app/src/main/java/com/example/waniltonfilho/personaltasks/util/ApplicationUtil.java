package com.example.waniltonfilho.personaltasks.util;

import android.content.Context;

/**
 * Created by wanilton.filho on 19/01/2016.
 */
public class ApplicationUtil {
    private static Context APPLICATION_CONTEXT;

    private ApplicationUtil() {
        super();
    }

    public static void setContext(Context context){
        APPLICATION_CONTEXT = context;
    }

    public static Context getContext(){
        return ApplicationUtil.APPLICATION_CONTEXT;
    }
}
