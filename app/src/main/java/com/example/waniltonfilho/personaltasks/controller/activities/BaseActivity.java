package com.example.waniltonfilho.personaltasks.controller.activities;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.example.waniltonfilho.personaltasks.R;
import com.example.waniltonfilho.personaltasks.model.entities.Category;
import com.example.waniltonfilho.personaltasks.model.service.CategoryService;
import com.melnykov.fab.FloatingActionButton;

import java.util.List;

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
