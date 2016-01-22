package com.example.waniltonfilho.personaltasks.controller.activities;

import android.os.Bundle;
import android.os.StrictMode;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.EditText;

import com.example.waniltonfilho.personaltasks.R;
import com.example.waniltonfilho.personaltasks.model.entities.Login;
import com.example.waniltonfilho.personaltasks.model.service.LoginBusinessService;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wanilton.filho on 19/01/2016.
 */
public class LoginMainActivity extends AppCompatActivity{

    private EditText mEditTextUser;
    private EditText mEditTextPassword;
    private Button mButtonLogin;
    private Toolbar toolbar;


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
        mButtonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean teste = attempLogin();
                teste = false;
            }
        });


        toolbar = (Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);
    }

    private boolean attempLogin() {
        Login login = new Login();
        login.setLogin(mEditTextUser.getText().toString());
        login.setPassword((mEditTextPassword.getText().toString()));
        List<Login> loginList = new ArrayList<>();
        loginList = LoginBusinessService.findAll();

        for(Login l : loginList) {
            if (l.getLogin().equals(login.getLogin()) && l.getPassword().equals(login.getPassword())) {
                return true;
            }
        }
        return false;
    }





}
