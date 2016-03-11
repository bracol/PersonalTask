package com.example.waniltonfilho.personaltasks.controller.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.example.waniltonfilho.personaltasks.R;
import com.example.waniltonfilho.personaltasks.model.entities.Login;
import com.example.waniltonfilho.personaltasks.model.service.LoginBusinessService;

/**
 * Created by wanilton.filho on 20/01/2016.
 */
public class LoginFormActivity extends BaseActivity{

    private EditText mEditTextName;
    private EditText mEditTextUsername;
    private EditText mEditTextPassword;
    private Toolbar mToolbar;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_login);

        bindElements();
    }

    private void bindElements() {
        //MaskEditTextChangedListener maskEditTextChangedListener = new MaskEditTextChangedListener("##/##/####", mEditTextName);
        mEditTextName = (EditText) findViewById(R.id.editTextName);
       // mEditTextName.addTextChangedListener(maskEditTextChangedListener);
        mEditTextUsername = (EditText) findViewById(R.id.editTextUsername);
        mEditTextPassword = (EditText) findViewById(R.id.editTextPassword);
        mToolbar = (Toolbar) findViewById(R.id.app_bar);
        setupToolbar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_login_form, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_login_OK:
                loginSave();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    private void loginSave() {
        try {
            Login login = new Login();
            login.setLogin(mEditTextUsername.getText().toString());
            login.setPassword(mEditTextPassword.getText().toString());
            login.setName(mEditTextName.getText().toString());
            LoginBusinessService.save(login);
            startActivity(new Intent(this, LoginMainActivity.class));
        } catch (Exception e){
            Toast.makeText(getBaseContext(), "Ocorreu um erro e o login não foi salvo!", Toast.LENGTH_SHORT).show();
        }
    }
}
