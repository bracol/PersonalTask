package com.example.waniltonfilho.personaltasks.controller.tasks;

import android.os.AsyncTask;

import com.example.waniltonfilho.personaltasks.model.entities.GroupCategoryTransaction;
import com.example.waniltonfilho.personaltasks.model.entities.Wallet;
import com.example.waniltonfilho.personaltasks.model.http.GroupCategoryTransactionHttpService;

import java.util.List;

/**
 * Created by wanilton.filho on 27/04/2016.
 */
public class TaskGetGroupWts extends AsyncTask<Void, Void, List<GroupCategoryTransaction>> {

    private String mYear;
    private String mMonth;
    private String mWallet_id;

    public TaskGetGroupWts(String walle_id, String year, String month){
        mYear = year;
        mMonth = month;
        mWallet_id = walle_id;
    }


    @Override
    protected List<GroupCategoryTransaction> doInBackground(Void... params) {
        return GroupCategoryTransactionHttpService.getGroupCategories(mWallet_id, mYear, mMonth);
    }
}
