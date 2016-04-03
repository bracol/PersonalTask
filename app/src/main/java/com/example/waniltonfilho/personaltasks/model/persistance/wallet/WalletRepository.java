package com.example.waniltonfilho.personaltasks.model.persistance.wallet_transaction;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.waniltonfilho.personaltasks.model.entities.Wallet;
import com.example.waniltonfilho.personaltasks.model.persistance.DataBaseHelper;
import com.example.waniltonfilho.personaltasks.model.persistance.wallet.WalletContract;

import java.util.List;

/**
 * Created by wanilton.filho on 29/01/2016.
 */
public class WalletRepository {
    private WalletRepository() {
        super();
    }

    public static void save(Wallet wallet) {
        DataBaseHelper dataBaseHelper = DataBaseHelper.getInstance();
        SQLiteDatabase db = dataBaseHelper.getWritableDatabase();

        ContentValues values = WalletContract.getContentValues(wallet);
        if (wallet.get_id() == null) {
            db.insert(WalletContract.TABLE, null, values);
        } else {
            String where = WalletContract.ID + " = ? ";
            String[] params = {wallet.get_id().toString()};
            db.update(WalletContract.TABLE, values, where, params);
        }

        db.close();
        dataBaseHelper.close();
    }

    public static void delete(String id) {
        DataBaseHelper dataBaseHelper = DataBaseHelper.getInstance();
        SQLiteDatabase db = dataBaseHelper.getWritableDatabase();

        String where = WalletContract.ID + " = ? ";
        String[] params = {id};
        db.delete(WalletContract.TABLE, where, params);

        db.close();
        dataBaseHelper.close();
    }

    public static void update(Float value){
        DataBaseHelper dataBaseHelper = DataBaseHelper.getInstance();
        SQLiteDatabase db = dataBaseHelper.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(WalletContract.VALUE, value);

        db.update(WalletContract.TABLE, contentValues, null, null);
    }

    public static List<Wallet> getAll() {
        DataBaseHelper dataBaseHelper = DataBaseHelper.getInstance();
        SQLiteDatabase db = dataBaseHelper.getReadableDatabase();

        /* ResultSet do Android */
        Cursor cursor = db.query(WalletContract.TABLE, WalletContract.COLUMNS, null, null, null, null, WalletContract.ID);

        List<Wallet> values = WalletContract.getWallets(cursor);

        db.close();
        dataBaseHelper.close();

        return values;
    }

    public static Wallet getWallet(){
        DataBaseHelper dataBaseHelper = DataBaseHelper.getInstance();
        SQLiteDatabase db = dataBaseHelper.getReadableDatabase();

        /* ResultSet do Android */
        Cursor cursor = db.query(WalletContract.TABLE, WalletContract.COLUMNS, null, null, null, null, null);

        Wallet wallet = WalletContract.getWallet(cursor);

        return wallet;
    }

}
