package com.example.waniltonfilho.personaltasks.model.persistance.wallet;

import android.content.ContentValues;
import android.database.Cursor;

import com.example.waniltonfilho.personaltasks.model.entities.Wallet;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Wanilton on 28/02/2016.
 */
public class WalletContract {

    public static final String TABLE = "wallet";
    public static final String ID = "id";
    public static final String VALUE = "price";
    public static final String[] COLUMNS = {ID, VALUE};

    private WalletContract() {
    }

    public static String getCreateTableScript() {
        final StringBuilder sb = new StringBuilder();
        sb.append("CREATE TABLE " + TABLE);
        sb.append(" ( ");
        sb.append(ID + " INTEGER PRIMARY KEY, ");
        sb.append(VALUE + " NUMERIC ");
        sb.append(" ) ");

        return sb.toString();
    }

    public static ContentValues getContentValues(Wallet wallet) {
        ContentValues cv = new ContentValues();
        cv.put(WalletContract.ID, wallet.get_id());
        cv.put(WalletContract.VALUE, wallet.getValue());
        return cv;
    }

    public static Wallet getTransaction(Cursor cursor) {
        if (!cursor.isBeforeFirst() || cursor.moveToNext()) {
            Wallet wallet = new Wallet();
            /* get column index pega o indice de acordo com o nome da coluna passado */
            wallet.set_id(cursor.getLong(cursor.getColumnIndex(WalletContract.ID)));
            wallet.setValue(cursor.getDouble(cursor.getColumnIndex(WalletContract.VALUE)));
            return wallet;
        }
        return null;
    }


    public static List<Wallet> getTransactions(Cursor cursor) {
        ArrayList<Wallet> wallets = new ArrayList<>();
        while (cursor.moveToNext()) {
            /* get colum index pega o indice de acordo com o nome da coluna passado */
            wallets.add(getTransaction(cursor));

        }
        return wallets;
    }


}
