package com.example.waniltonfilho.personaltasks.model.persistance;

import android.content.ContentValues;
import android.database.Cursor;

import com.example.waniltonfilho.personaltasks.model.entities.Login;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wanilton.filho on 19/01/2016.
 */
public class LoginContract {

    public static final String TABLE = "login";
    public static final String ID = "id";
    public static final String LOGIN = "login";
    public static final String PASSWORD = "password";
    public static final String[] COLUMNS = {ID, LOGIN, PASSWORD};

    private LoginContract(){}

    public static String getCreateTableScript(){
        final StringBuilder sb = new StringBuilder();
        sb.append("CREATE TABLE " + TABLE);
        sb.append(" ( ");
        sb.append(ID + " INTEGER PRIMARY KEY, ");
        sb.append(LOGIN + " TEXT, ");
        sb.append(PASSWORD + " TEXT ");
        sb.append(" ) ");

        return sb.toString();
    }

    public static ContentValues getContentValues(Login login){
        ContentValues cv = new ContentValues();
        cv.put(LoginContract.ID, login.getId());
        cv.put(LoginContract.LOGIN, login.getLogin());
        cv.put(LoginContract.PASSWORD, login.getPassword());
        return cv;
    }

    public static Login getLogin(Cursor cursor) {
        if (!cursor.isBeforeFirst() || cursor.moveToNext()) {
            Login login = new Login();
            /* get column index pega o indice de acordo com o nome da coluna passado */
            login.setId(cursor.getLong(cursor.getColumnIndex(LoginContract.ID)));
            login.setLogin(cursor.getString(cursor.getColumnIndex(LoginContract.LOGIN)));
            login.setPassword(cursor.getString(cursor.getColumnIndex(LoginContract.PASSWORD)));

            return login;
        }
        return null;
    }


    public static List<Login> getLogins(Cursor cursor) {
        ArrayList<Login> logins = new ArrayList<>();
        while (cursor.moveToNext()) {
            /* get colum index pega o indice de acordo com o nome da coluna passado */
            logins.add(getLogin(cursor));

        }
        return logins;
    }






}
