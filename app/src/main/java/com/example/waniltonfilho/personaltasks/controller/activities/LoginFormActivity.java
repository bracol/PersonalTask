package com.example.waniltonfilho.personaltasks.controller.activities;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.CardView;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.waniltonfilho.personaltasks.R;
import com.example.waniltonfilho.personaltasks.controller.tasks.TaskPostLogin;
import com.example.waniltonfilho.personaltasks.model.entities.User;

/**
 * Created by wanilton.filho on 20/01/2016.
 */
public class LoginFormActivity extends BaseActivity implements View.OnClickListener{

    private EditText mEditTextName;
    private EditText mEditTextUsername;
    private EditText mEditTextPassword;
    private Button mButtonConfirm;
    private Button mButtonCancel;
    private CardView mCardViewLoginForm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_login);

        bindElements();
    }

    private void bindElements() {
        bindEditTextName();
        bindEditTextUsername();
        bindEditTextPassword();
        bindBtnConfirm();
        bindBtnCancel();
        bindCardViewFormLogin();
    }

    private void bindCardViewFormLogin() {
        mCardViewLoginForm = (CardView) findViewById(R.id.cardViewFormLogin);
    }

    private void bindBtnConfirm() {
        mButtonConfirm = (Button) findViewById(R.id.buttonSaveFormLogin);
        mButtonConfirm.setOnClickListener(this);
    }

    private void bindBtnCancel() {
        mButtonCancel = (Button) findViewById(R.id.buttonCancelFormLogin);
        mButtonCancel.setOnClickListener(this);
    }

    private void bindEditTextName() {
        mEditTextName = (EditText) findViewById(R.id.editTextName);
    }

    private void bindEditTextPassword() {
        mEditTextPassword = (EditText) findViewById(R.id.editTextPassword);
    }

    private void bindEditTextUsername() {
        mEditTextUsername = (EditText) findViewById(R.id.editTextUsername);
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.menu_login_form, menu);
//        return super.onCreateOptionsMenu(menu);
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        switch (item.getItemId()){
//            case R.id.menu_login_OK:
//                onOKClick();
//                break;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }

    private void onOKClick() {

    }

    private void saveLogin(User user) {
        new TaskPostLogin(user).execute();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.buttonSaveFormLogin:
                onButtonConfirm();
                break;
            case R.id.buttonCancelFormLogin:
                onButtonCancel();
                break;
        }
    }

    private void onButtonCancel() {
        finish();
    }

    private void onButtonConfirm() {
        try {
            User user = new User();
            user.setUserName(mEditTextUsername.getText().toString());
            user.setPassword(mEditTextPassword.getText().toString());
            user.setName(mEditTextName.getText().toString());
            saveLogin(user);
            Snackbar.make(mButtonConfirm, R.string.action_save_user, Snackbar.LENGTH_SHORT).show();
            onPauseAnimation();
        } catch (Exception e){
            Toast.makeText(getBaseContext(), "Ocorreu um erro e o login não foi salvo!", Toast.LENGTH_SHORT).show();
        }
    }



    private void onPauseAnimation() {
        Animation swipeToRightAnimation = AnimationUtils.loadAnimation(this, R.anim.swipe_to_right);
        mCardViewLoginForm.startAnimation(swipeToRightAnimation);
    }

    private void onResumeAnimation() {
        Animation swipeRightAnimation = AnimationUtils.loadAnimation(this, R.anim.swipe_from_right);
        mCardViewLoginForm.startAnimation(swipeRightAnimation);
    }

    @Override
    protected void onResume() {
        onResumeAnimation();
        mCardViewLoginForm.setVisibility(View.VISIBLE);
        super.onResume();
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(0, R.anim.swipe_to_right);
    }
}
