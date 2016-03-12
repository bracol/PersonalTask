package com.example.waniltonfilho.personaltasks.controller.activities;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.melnykov.fab.FloatingActionButton;

/**
 * Created by Wanilton on 12/03/2016.
 */
public class BaseActivity extends AppCompatActivity{


    public void setupToolbar(Toolbar toolbar){
        setSupportActionBar(toolbar);
    }

    public void setupFloatingButton(FloatingActionButton fab){

    }
}
