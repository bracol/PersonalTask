package com.example.waniltonfilho.personaltasks.controller.activities;

import android.animation.AnimatorInflater;
import android.animation.ObjectAnimator;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
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
    private FloatingActionButton mFabAdd;
    public static final String PARAM_LOGIN = "LOGIN";

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
            @TargetApi(Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View v) {
                mButtonLogin.setElevation(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 8f, getResources().getDisplayMetrics()));
                boolean teste = attempLogin();
            }
        });
        mFabAdd = (FloatingActionButton) findViewById(R.id.fabAddLogin);
        mFabAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginMainActivity.this, LoginFormActivity.class));
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
                Intent intent = new Intent(LoginMainActivity.this, ActivityCategory.class);
                intent.putExtra(PARAM_LOGIN, l);
                startActivity(intent);
            }
        }
        return false;
    }





}
