package com.example.waniltonfilho.personaltasks.controller.activities;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import com.example.waniltonfilho.personaltasks.R;
import com.example.waniltonfilho.personaltasks.model.MonthList;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by wanilton.filho on 14/03/2016.
 */

public class ListActivity extends BaseActivity {

    private Toolbar mToolbar;
    private TextView mMonthTitle;
    private MonthList mManipulateList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_month);
        bindComponents();
    }

    private void bindComponents() {
        bindToolbar();
        bindTextViewMonth();
    }

    private void bindTextViewMonth() {
        mMonthTitle = (TextView) findViewById(R.id.tvListMonth);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        String actualDate = simpleDateFormat.format(date);
        int mes = Integer.parseInt(actualDate.substring(5, 7));
        int ano = Integer.parseInt(actualDate.substring(0, 4));
        mManipulateList = new MonthList(mes, ano);

        mMonthTitle.setText(mManipulateList.getMonthFromPosition(mes - 1) + "/" + ano);

        mMonthTitle.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Drawable[] compoundDrawables = mMonthTitle.getCompoundDrawables();
                Drawable leftDrawable = compoundDrawables[0];
                Drawable rightDrawable = compoundDrawables[2];
                float viewWidth = mMonthTitle.getWidth();
                float eventX = event.getX();
                if (eventX < 200) {
                    mMonthTitle.setText(mManipulateList.swipeLeft(mMonthTitle.getText().toString()));
                }
                if (eventX > 650) {
                    mMonthTitle.setText(mManipulateList.swipeRight(mMonthTitle.getText().toString()));
                }

                return false;
            }
        });
    }

    private void bindToolbar() {
        setupToolbar(mToolbar);
    }
}
