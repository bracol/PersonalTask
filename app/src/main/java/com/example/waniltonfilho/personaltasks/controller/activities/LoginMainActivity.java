package com.example.waniltonfilho.personaltasks.controller.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.waniltonfilho.personaltasks.R;
import com.example.waniltonfilho.personaltasks.controller.fragment.FragmentDialogWallet;
import com.example.waniltonfilho.personaltasks.model.entities.Login;
import com.example.waniltonfilho.personaltasks.model.persistance.wallet_transaction.WalletRepository;
import com.example.waniltonfilho.personaltasks.model.service.LoginBusinessService;
import com.melnykov.fab.FloatingActionButton;

import java.util.List;

/**
 * Created by wanilton.filho on 19/01/2016.
 */
public class LoginMainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText mEditTextUser;
    private EditText mEditTextPassword;
    private Button mButtonLogin;
    private Button mButtonOfflineLogin;
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
        mButtonLogin.setOnClickListener(this);
        mButtonOfflineLogin = (Button) findViewById(R.id.buttonSignOffline);
        mButtonOfflineLogin.setOnClickListener(this);
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
        List<Login> loginList = LoginBusinessService.findAll();

        for (Login l : loginList) {
            if (l.getLogin().equals(login.getLogin()) && l.getPassword().equals(login.getPassword())) {
                Intent intent = new Intent(LoginMainActivity.this, ActivityMain.class);
                intent.putExtra(PARAM_LOGIN, l);
                startActivity(intent);
            }
        }
        return false;
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.buttonSignLogin:
                if (WalletRepository.getWallet() != null) {
                    attempLogin();
                } else {
                    openDialog();
                }
                break;
            case R.id.buttonSignOffline:
                startOffline();
                break;
        }
    }

    private void openDialog() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction ft = fragmentManager.beginTransaction();
        ft.setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
        FragmentDialogWallet dialogFragment = new FragmentDialogWallet();
        //ft.replace(R.id.frameDialogLoginTransaction, dialogFragment);
        ft.commit();
    }


    private void startOffline() {
        Intent goToActivityCategory = new Intent(LoginMainActivity.this, ActivityMain.class);
        startActivity(goToActivityCategory);
    }
}
