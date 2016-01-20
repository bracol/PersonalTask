package com.example.waniltonfilho.personaltasks.controller.activities;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;

import com.example.waniltonfilho.personaltasks.R;

/**
 * Created by wanilton.filho on 19/01/2016.
 */
public class MainLoginActivity extends AppCompatActivity{

    private EditText mEditTextUser;
    private EditText mEditTextPassword;
    private Button mButtonLogin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acitivity_main_login);
        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        bindElements();
    }

    private void bindElements() {
        mEditTextUser = (EditText) findViewById(R.id.editTextUsername);
        mEditTextPassword = (EditText) findViewById(R.id.editTextPassword);
        mButtonLogin = (Button) findViewById(R.id.buttonSignLogin);

    }

    //public class UserLogin extends AsyncTask



}
