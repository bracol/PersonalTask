package com.example.waniltonfilho.personaltasks.model.persistance.wallet_transaction;

import android.content.ContentValues;
import android.database.Cursor;

import com.example.waniltonfilho.personaltasks.model.entities.Login;
import com.example.waniltonfilho.personaltasks.model.entities.WalletTransaction;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by wanilton.filho on 29/01/2016.
 */
public class WalletTransactionContract {
    public static final String TABLE = "walletTransaction";
    public static final String ID = "id";
    public static final String NAME = "name";
    public static final String DATE = "date";
    public static final String PRICE = "price";
    //public static final String ACTION = "action";
    public static final String ICON_CATEGORY = "item_category";
    public static final String LOGIN_ID = "login_ID";
    public static final String[] COLUMNS = {ID, NAME, DATE, PRICE, ICON_CATEGORY, LOGIN_ID};

    private WalletTransactionContract() {
    }

    public static String getCreateTableScript() {
        final StringBuilder sb = new StringBuilder();
        sb.append("CREATE TABLE " + TABLE);
        sb.append(" ( ");
        sb.append(ID + " INTEGER PRIMARY KEY, ");
        sb.append(NAME + " TEXT, ");
        sb.append(DATE + " TEXT, ");
        sb.append(PRICE + " NUMERIC, ");
        sb.append(ICON_CATEGORY + " TEXT, ");
        sb.append(LOGIN_ID + " INTEGER ");
        sb.append(" ) ");

        return sb.toString();
    }

    public static ContentValues getContentValues(WalletTransaction walletTransaction) {
        ContentValues cv = new ContentValues();
        cv.put(WalletTransactionContract.ID, walletTransaction.getId());
        cv.put(WalletTransactionContract.NAME, walletTransaction.getName());
        cv.put(WalletTransactionContract.DATE, walletTransaction.getDate().toString());
        cv.put(WalletTransactionContract.PRICE, walletTransaction.getPrice());
        cv.put(WalletTransactionContract.ICON_CATEGORY, walletTransaction.getItemCategory());
        cv.put(WalletTransactionContract.LOGIN_ID, walletTransaction.getLogin_id());
        return cv;
    }

    public static WalletTransaction getTransaction(Cursor cursor) {
        if (!cursor.isBeforeFirst() || cursor.moveToNext()) {
            WalletTransaction walletTransaction = new WalletTransaction();
            /* get column index pega o indice de acordo com o nome da coluna passado */
            walletTransaction.setId(cursor.getLong(cursor.getColumnIndex(WalletTransactionContract.ID)));
            walletTransaction.setName(cursor.getString(cursor.getColumnIndex(WalletTransactionContract.NAME)));
            walletTransaction.setDate((cursor.getString(cursor.getColumnIndex(WalletTransactionContract.DATE))));
            walletTransaction.setPrice(cursor.getFloat(cursor.getColumnIndex(WalletTransactionContract.PRICE)));
            walletTransaction.setItemCategory(cursor.getInt(cursor.getColumnIndex(WalletTransactionContract.ICON_CATEGORY)));
            walletTransaction.setLogin_id(cursor.getLong(cursor.getColumnIndex(WalletTransactionContract.LOGIN_ID)));

            return walletTransaction;
        }
        return null;
    }


    public static List<WalletTransaction> getTransactions(Cursor cursor) {
        ArrayList<WalletTransaction> walletTransactions = new ArrayList<>();
        while (cursor.moveToNext()) {
            /* get colum index pega o indice de acordo com o nome da coluna passado */
            walletTransactions.add(getTransaction(cursor));

        }
        return walletTransactions;
    }


}
