package com.example.waniltonfilho.personaltasks.model.persistance.login;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.waniltonfilho.personaltasks.model.entities.User;
import com.example.waniltonfilho.personaltasks.model.persistance.DataBaseHelper;

import java.util.List;

/**
 * Created by wanilton.filho on 19/01/2016.
 */
public class LoginRepository {
    private LoginRepository() {
        super();
    }

    public static void save(User user) {
        DataBaseHelper dataBaseHelper = DataBaseHelper.getInstance();
        SQLiteDatabase db = dataBaseHelper.getWritableDatabase();

        ContentValues values = LoginContract.getContentValues(user);
        if (user.getId() == null) {
            db.insert(LoginContract.TABLE, null, values);
        } else {
            String where = LoginContract.ID + " = ? ";
            String[] params = {user.getId().toString()};
            db.update(LoginContract.TABLE, values, where, params);
        }

        db.close();
        dataBaseHelper.close();
    }

    public static void delete(String id) {
        DataBaseHelper dataBaseHelper = DataBaseHelper.getInstance();
        SQLiteDatabase db = dataBaseHelper.getWritableDatabase();

        String where = LoginContract.ID + " = ? ";
        String[] params = {id};
        db.delete(LoginContract.TABLE, where, params);

        db.close();
        dataBaseHelper.close();
    }

    public static List<User> getAll() {
        DataBaseHelper dataBaseHelper = DataBaseHelper.getInstance();
        SQLiteDatabase db = dataBaseHelper.getReadableDatabase();

        /* ResultSet do Android */
        Cursor cursor = db.query(LoginContract.TABLE, LoginContract.COLUMNS, null, null, null, null, LoginContract.ID);

        List<User> values = LoginContract.getLogins(cursor);

        db.close();
        dataBaseHelper.close();

        return values;
    }

    public static User getByLoginPassword(String $login, String $password) {
        DataBaseHelper dataBaseHelper = DataBaseHelper.getInstance();
        SQLiteDatabase db = dataBaseHelper.getReadableDatabase();

        String where = LoginContract.LOGIN + " = ? AND " + LoginContract.PASSWORD + " = ? ";
        String[] params = {$login, $password};

        Cursor cursor = db.query(LoginContract.TABLE, LoginContract.COLUMNS, where, params, null, null, null, null);
        User user = LoginContract.getLogin(cursor);

        db.close();
        dataBaseHelper.close();

        return user;
    }

    public static User getById(long id){

        DataBaseHelper dataBaseHelper = DataBaseHelper.getInstance();
        SQLiteDatabase db = dataBaseHelper.getReadableDatabase();

        String where = LoginContract.ID + " = ? ";
        String[] params = {String.valueOf(id)};

        Cursor cursor = db.query(LoginContract.TABLE, LoginContract.COLUMNS, where, params, null, null, null, null);
        User user = LoginContract.getLogin(cursor);

        db.close();
        dataBaseHelper.close();
        return user;
    }



}
