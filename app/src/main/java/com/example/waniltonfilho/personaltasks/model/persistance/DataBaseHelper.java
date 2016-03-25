package com.example.waniltonfilho.personaltasks.model.persistance;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.waniltonfilho.personaltasks.model.entities.Category;
import com.example.waniltonfilho.personaltasks.model.entities.Wallet;
import com.example.waniltonfilho.personaltasks.model.entities.WalletTransaction;
import com.example.waniltonfilho.personaltasks.model.persistance.category.CategoryContract;
import com.example.waniltonfilho.personaltasks.model.persistance.login.LoginContract;
import com.example.waniltonfilho.personaltasks.model.persistance.wallet.WalletContract;
import com.example.waniltonfilho.personaltasks.model.persistance.wallet_transaction.WalletTransactionContract;
import com.example.waniltonfilho.personaltasks.model.service.WalletService;
import com.example.waniltonfilho.personaltasks.util.ApplicationUtil;

/**
 * Created by wanilton.filho on 19/01/2016.
 */

public class DataBaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "taskmanagerdb";
    private static final int DATABASE_VERSION = 1;

    private DataBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public static DataBaseHelper getInstance() {
        return new DataBaseHelper(ApplicationUtil.getContext());
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(LoginContract.getCreateTableScript());
        db.execSQL(WalletContract.getCreateTableScript());
        db.execSQL(WalletTransactionContract.getCreateTableScript());
        db.execSQL(CategoryContract.getCreateTableScript());
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}