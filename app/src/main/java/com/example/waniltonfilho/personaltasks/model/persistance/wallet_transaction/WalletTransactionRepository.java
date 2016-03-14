package com.example.waniltonfilho.personaltasks.model.persistance.wallet_transaction;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.waniltonfilho.personaltasks.model.entities.Wallet;
import com.example.waniltonfilho.personaltasks.model.entities.WalletTransaction;
import com.example.waniltonfilho.personaltasks.model.persistance.DataBaseHelper;

import java.util.List;

/**
 * Created by wanilton.filho on 29/01/2016.
 */
public class WalletTransactionRepository {
    private WalletTransactionRepository() {
        super();
    }

    public static void save(WalletTransaction walletTransaction) {
        DataBaseHelper dataBaseHelper = DataBaseHelper.getInstance();
        SQLiteDatabase db = dataBaseHelper.getWritableDatabase();

        ContentValues values = WalletTransactionContract.getContentValues(walletTransaction);
        if (walletTransaction.getId() == null) {
            db.insert(WalletTransactionContract.TABLE, null, values);
        } else {
            String where = WalletTransactionContract.ID + " = ? ";
            String[] params = {walletTransaction.getId().toString()};
            db.update(WalletTransactionContract.TABLE, values, where, params);
        }

        db.close();
        dataBaseHelper.close();
    }

    public static void delete(long id) {
        DataBaseHelper dataBaseHelper = DataBaseHelper.getInstance();
        SQLiteDatabase db = dataBaseHelper.getWritableDatabase();

        String where = WalletTransactionContract.ID + " = ? ";
        String[] params = {String.valueOf(id)};
        db.delete(WalletTransactionContract.TABLE, where, params);

        db.close();
        dataBaseHelper.close();
    }

    public static List<WalletTransaction> getAll() {
        DataBaseHelper dataBaseHelper = DataBaseHelper.getInstance();
        SQLiteDatabase db = dataBaseHelper.getReadableDatabase();

        /* ResultSet do Android */
        Cursor cursor = db.query(WalletTransactionContract.TABLE, WalletTransactionContract.COLUMNS, null, null, null, null, WalletTransactionContract.ID);

        List<WalletTransaction> values = WalletTransactionContract.getTransactions(cursor);

        db.close();
        dataBaseHelper.close();

        return values;
    }

    public static List<WalletTransaction> selectByMonth(int month) {
        DataBaseHelper dataBaseHelper = DataBaseHelper.getInstance();
        SQLiteDatabase db = dataBaseHelper.getReadableDatabase();

        String where = "strftime('%m', " + WalletTransactionContract.DATE + ") = ? ";
        String[] params = {String.valueOf(month)};

        /* ResultSet do Android */
        Cursor cursor = db.query(WalletTransactionContract.TABLE, WalletTransactionContract.COLUMNS, where, params, null, null, null);
        //Cursor cursor2 = db.rawQuery("select strftime('%m' date) from " + WalletTransactionContract.TABLE, null);
        List<WalletTransaction> transactions = WalletTransactionContract.getTransactions(cursor);

        db.close();
        dataBaseHelper.close();

        return transactions;
    }

    public static WalletTransaction selectByMonths(int month) {
        DataBaseHelper dataBaseHelper = DataBaseHelper.getInstance();
        SQLiteDatabase db = dataBaseHelper.getReadableDatabase();

        String where = "strftime('%m', " + WalletTransactionContract.DATE + ")"  + " = 11 ";
        String[] params = {("'" + String.valueOf(month) + "'")};

        /* ResultSet do Android */
        Cursor cursor = db.query(WalletTransactionContract.TABLE, WalletTransactionContract.COLUMNS, where, null, null, null, null);

        WalletTransaction walletTransaction = WalletTransactionContract.getTransaction(cursor);

        db.close();
        dataBaseHelper.close();
        // "strftime('%m', date) AS month"
        return walletTransaction;
    }
}
