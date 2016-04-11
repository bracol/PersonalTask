package com.example.waniltonfilho.personaltasks.model.persistance.login;

import android.content.ContentValues;
import android.database.Cursor;

import com.example.waniltonfilho.personaltasks.model.entities.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wanilton.filho on 19/01/2016.
 */
public class LoginContract {

    public static final String TABLE = "login";
    public static final String ID = "id";
    public static final String NAME = "name";
    public static final String LOGIN = "login";
    public static final String PASSWORD = "password";
    public static final String[] COLUMNS = {ID, NAME, LOGIN, PASSWORD};

    private LoginContract(){}

    public static String getCreateTableScript(){
        final StringBuilder sb = new StringBuilder();
        sb.append("CREATE TABLE " + TABLE);
        sb.append(" ( ");
        sb.append(ID + " INTEGER PRIMARY KEY, ");
        sb.append(NAME + " TEXT, ");
        sb.append(LOGIN + " TEXT, ");
        sb.append(PASSWORD + " TEXT ");
        sb.append(" ) ");

        return sb.toString();
    }

    public static ContentValues getContentValues(User user){
        ContentValues cv = new ContentValues();
        cv.put(LoginContract.ID, user.getId());
        cv.put(LoginContract.NAME, user.getName());
        cv.put(LoginContract.LOGIN, user.getUserName());
        cv.put(LoginContract.PASSWORD, user.getPassword());
        return cv;
    }

    public static User getLogin(Cursor cursor) {
        if (!cursor.isBeforeFirst() || cursor.moveToNext()) {
            User user = new User();
            /* get column index pega o indice de acordo com o nome da coluna passado */
            user.setId(cursor.getString(cursor.getColumnIndex(LoginContract.ID)));
            user.setUserName(cursor.getString(cursor.getColumnIndex(LoginContract.LOGIN)));
            user.setPassword(cursor.getString(cursor.getColumnIndex(LoginContract.PASSWORD)));
            user.setName(cursor.getString(cursor.getColumnIndex(LoginContract.NAME)));

            return user;
        }
        return null;
    }


    public static List<User> getLogins(Cursor cursor) {
        ArrayList<User> users = new ArrayList<>();
        while (cursor.moveToNext()) {
            /* get colum index pega o indice de acordo com o nome da coluna passado */
            users.add(getLogin(cursor));

        }
        return users;
    }






}
