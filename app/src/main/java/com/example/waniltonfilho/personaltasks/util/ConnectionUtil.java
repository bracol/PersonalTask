package com.example.waniltonfilho.personaltasks.util;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by wanilton.filho on 26/04/2016.
 */
public class ConnectionUtil {

//    public static final String URL_WALLET = "http://10.0.3.2:3000/api/v1/";
    public static final String URL_WALLET = "http://192.168.0.12:3000/api/v1/";

    public static boolean isConnected(Activity context)
    {
        try{
            ConnectivityManager cm = (ConnectivityManager) context.getSystemService
                    (Context.CONNECTIVITY_SERVICE);
            NetworkInfo netInfo = cm.getActiveNetworkInfo();

            if (netInfo != null && netInfo.isConnected())
            {
                //Network is available but check if we can get access from the network.
                URL url = new URL(URL_WALLET + "wts");
                HttpURLConnection urlc = (HttpURLConnection) url.openConnection();
                urlc.setRequestProperty("Connection", "close");
                urlc.setConnectTimeout(2000); // Timeout 2 seconds.
                urlc.connect();

                if (urlc.getResponseCode() == 200)  //Successful response.
                {
                    return true;
                }
                else
                {
                    Log.d("NO INTERNET", "NO INTERNET");
                    return false;
                }
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return false;
    }
}
