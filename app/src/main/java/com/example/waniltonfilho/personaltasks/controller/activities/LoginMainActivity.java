package com.example.waniltonfilho.personaltasks.controller.activities;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.waniltonfilho.personaltasks.R;
import com.example.waniltonfilho.personaltasks.controller.fragment.FragmentDialogWallet;
import com.example.waniltonfilho.personaltasks.controller.tasks.TaskGetLogin;
import com.example.waniltonfilho.personaltasks.controller.tasks.TaskPostWallet;
import com.example.waniltonfilho.personaltasks.model.entities.User;
import com.example.waniltonfilho.personaltasks.model.entities.Wallet;
import com.example.waniltonfilho.personaltasks.model.persistance.wallet_transaction.WalletRepository;
import com.example.waniltonfilho.personaltasks.model.service.WalletService;
import com.melnykov.fab.FloatingActionButton;

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
    private User mUser;
    public static final String PREFERENCE_NAME = "PREFERENCE_NAME";

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
        clearPreferences();
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

    private void clearPreferences() {
        SharedPreferences sharedPref = getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.remove("login");
        editor.remove("pass");
        editor.remove("name");
        editor.commit();
    }

    private void attempLogin() {
        User user = new User();
        user.setUserName(mEditTextUser.getText().toString());
        user.setPassword((mEditTextPassword.getText().toString()));


        loginAuthentication(user);
    }

    private void loginAuthentication(User user) {
        new TaskGetLogin(user) {
            ProgressDialog dialog;

            @Override
            protected void onPreExecute() {
                dialog = new ProgressDialog(LoginMainActivity.this);
                dialog.setMessage("Loading...");
                dialog.show();
                super.onPreExecute();
            }

            @Override
            protected void onPostExecute(User user) {
                super.onPostExecute(user);
                mUser = user;
                dialog.dismiss();
                goToMainActivity();
            }
        }.execute();
    }

    private void goToMainActivity() {
        if (mUser != null) {
            savePreferences();
            Intent goToEstoqueForm = new Intent(LoginMainActivity.this, MainActivity.class);
            startActivity(goToEstoqueForm);
        } else {
            Snackbar.make(mButtonLogin, R.string.error_password, Snackbar.LENGTH_LONG).show();
        }
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.buttonSignLogin:
                attempLogin();
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
        ft.replace(R.id.frameDialogLoginTransaction, dialogFragment);
        ft.commit();
    }


    private void startOffline() {
        if (WalletRepository.getWallet() != null) {
            Intent goToActivityCategory = new Intent(LoginMainActivity.this, MainActivity.class);
            startActivity(goToActivityCategory);
        } else {
            Wallet wallet = new Wallet();
            wallet.setValue(0f);
            WalletService.save(wallet);
        }
    }

    public void savePreferences() {
        SharedPreferences sharedPref = getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString("login", mUser.getUserName());
        editor.putString("pass", mUser.getPassword());
        editor.putString("name", mUser.getName());
        editor.commit();
    }
}
