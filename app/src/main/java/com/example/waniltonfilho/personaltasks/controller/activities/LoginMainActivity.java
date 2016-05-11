package com.example.waniltonfilho.personaltasks.controller.activities;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.waniltonfilho.personaltasks.R;
import com.example.waniltonfilho.personaltasks.controller.tasks.TaskGetLogin;
import com.example.waniltonfilho.personaltasks.model.entities.User;
import com.example.waniltonfilho.personaltasks.model.entities.Wallet;
import com.example.waniltonfilho.personaltasks.model.service.WalletService;
import com.melnykov.fab.FloatingActionButton;

/**
 * Created by wanilton.filho on 19/01/2016.
 */
public class LoginMainActivity extends AppCompatActivity implements View.OnClickListener {


    private final int FIRST_LOGIN = 0;
    private final int NOT_FIRST_LOGIN = 1;
    private EditText mEditTextUser;
    private EditText mEditTextPassword;
    private Button mButtonLogin;
    private Button mButtonOfflineLogin;
    private Toolbar toolbar;
    private FloatingActionButton mFabAdd;
    private User mUser;
    public static final String PREFERENCE_NAME = "PREFERENCE_NAME";
    private ImageView mImgViewLogout;
    private TextView mTvName;
    private CardView mCardViewLoginSign;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_login);
        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        bindElements();
        checkPreferences();

    }

    private void bindElements() {
        mEditTextUser = (EditText) findViewById(R.id.editTextUsername);
        mEditTextPassword = (EditText) findViewById(R.id.editTextPassword);
        mButtonLogin = (Button) findViewById(R.id.buttonSignLogin);
        mButtonLogin.setOnClickListener(this);
        mButtonOfflineLogin = (Button) findViewById(R.id.buttonSignOffline);
        mButtonOfflineLogin.setOnClickListener(this);
        final ColorStateList colors = mEditTextPassword.getHintTextColors();
        mFabAdd = (FloatingActionButton) findViewById(R.id.fabAddLogin);
        mFabAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginMainActivity.this, LoginFormActivity.class));
            }
        });
        mCardViewLoginSign = (CardView) findViewById(R.id.cardViewSignLogin);



        toolbar = (Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);
    }

    private void checkPreferences() {
        SharedPreferences sharedPref = getSharedPreferences(LoginMainActivity.PREFERENCE_NAME, Context.MODE_PRIVATE);
        String login = sharedPref.getString("login", null);
        String pass = sharedPref.getString("pass", null);
        String name = sharedPref.getString("name", null);
        if(login != null){
            mUser = new User();
            mUser.setName(name);
            mUser.setUserName(login);
            mUser.setPassword(pass);
            goToMainActivity();
        } else{
            bindElements();
        }
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
            Intent goToMainActivity = new Intent(LoginMainActivity.this, MainActivity.class);
            startActivity(goToMainActivity);
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



    private void startOffline() {
        if (WalletService.getWallet() == null) {
            Wallet wallet = new Wallet();
            wallet.setValue(0f);
            WalletService.save(wallet);
        }
        Intent goToActivityCategory = new Intent(LoginMainActivity.this, MainActivity.class);
        startActivity(goToActivityCategory);
    }

    public void savePreferences() {
        SharedPreferences sharedPref = getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString("login", mUser.getUserName());
        editor.putString("pass", mUser.getPassword());
        editor.putString("name", mUser.getName());
        editor.commit();
    }

    private void onPauseAnimation() {
        Animation swipeLeftAnimation = AnimationUtils.loadAnimation(this, R.anim.swipe_to_left);
        mCardViewLoginSign.startAnimation(swipeLeftAnimation);

    }

    private void onResumeAnimation() {
        Animation swipeRightAnimation = AnimationUtils.loadAnimation(this, R.anim.swipe_from_left);
        mCardViewLoginSign.startAnimation(swipeRightAnimation);
    }

    @Override
    protected void onPause() {
        super.onPause();
        onPauseAnimation();
        mCardViewLoginSign.setVisibility(View.GONE);
    }

    @Override
    protected void onResume() {
        onResumeAnimation();
        mCardViewLoginSign.setVisibility(View.VISIBLE);
        super.onResume();
    }
}
