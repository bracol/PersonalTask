package com.example.waniltonfilho.personaltasks.model.persistance.category;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.waniltonfilho.personaltasks.model.entities.Category;
import com.example.waniltonfilho.personaltasks.model.persistance.DataBaseHelper;

import java.util.List;

/**
 * Created by wanilton.filho on 19/01/2016.
 */
public class CategoryRepository {
    private CategoryRepository() {
        super();
    }

    public static void save(Category category) {
        DataBaseHelper dataBaseHelper = DataBaseHelper.getInstance();
        SQLiteDatabase db = dataBaseHelper.getWritableDatabase();

        ContentValues values = CategoryContract.getContentValues(category);
        if (category.getId() == null) {
            db.insert(CategoryContract.TABLE, null, values);
        } else {
            String where = CategoryContract.ID + " = ? ";
            String[] params = {category.getId().toString()};
            db.update(CategoryContract.TABLE, values, where, params);
        }

        db.close();
        dataBaseHelper.close();
    }

    public static void delete(long id) {
        DataBaseHelper dataBaseHelper = DataBaseHelper.getInstance();
        SQLiteDatabase db = dataBaseHelper.getWritableDatabase();

        String where = CategoryContract.ID + " = ? ";
        String[] params = {String.valueOf(id)};
        db.delete(CategoryContract.TABLE, where, params);

        db.close();
        dataBaseHelper.close();
    }

    public static List<Category> getAll() {
        DataBaseHelper dataBaseHelper = DataBaseHelper.getInstance();
        SQLiteDatabase db = dataBaseHelper.getReadableDatabase();

        /* ResultSet do Android */
        Cursor cursor = db.query(CategoryContract.TABLE, CategoryContract.COLUMNS, null, null, null, null, CategoryContract.ID);

        List<Category> values = CategoryContract.getCategories(cursor);

        db.close();
        dataBaseHelper.close();

        return values;
    }

    public static Category getById(long id){

        DataBaseHelper dataBaseHelper = DataBaseHelper.getInstance();
        SQLiteDatabase db = dataBaseHelper.getReadableDatabase();

        String where = CategoryContract.ID + " = ? ";
        String[] params = {String.valueOf(id)};

        Cursor cursor = db.query(CategoryContract.TABLE, CategoryContract.COLUMNS, where, params, null, null, null, null);
        Category category = CategoryContract.getCategory(cursor);

        db.close();
        dataBaseHelper.close();
        return category;
    }



}
