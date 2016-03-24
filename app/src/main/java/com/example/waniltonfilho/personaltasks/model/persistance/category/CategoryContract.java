package com.example.waniltonfilho.personaltasks.model.persistance.category;

import android.content.ContentValues;
import android.database.Cursor;

import com.example.waniltonfilho.personaltasks.model.entities.Category;
import com.example.waniltonfilho.personaltasks.model.entities.Category;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wanilton.filho on 19/01/2016.
 */
public class CategoryContract {

    public static final String TABLE = "category";
    public static final String ID = "id";
    public static final String NAME = "name";
    public static final String CATEGORY_ICON = "category_icon";
    public static final String[] COLUMNS = {ID, NAME, CATEGORY_ICON};

    private CategoryContract(){}

    public static String getCreateTableScript(){
        final StringBuilder sb = new StringBuilder();
        sb.append("CREATE TABLE " + TABLE);
        sb.append(" ( ");
        sb.append(ID + " INTEGER PRIMARY KEY, ");
        sb.append(NAME + " TEXT, ");
        sb.append(CATEGORY_ICON + " TEXT ");
        sb.append(" ) ");

        return sb.toString();
    }

    public static ContentValues getContentValues(Category category){
        ContentValues cv = new ContentValues();
        cv.put(CategoryContract.ID, category.getId());
        cv.put(CategoryContract.NAME, category.getName());
        cv.put(CategoryContract.CATEGORY_ICON, category.getCategory_icon());
        return cv;
    }

    public static Category getCategory(Cursor cursor) {
        if (!cursor.isBeforeFirst() || cursor.moveToNext()) {
            Category category = new Category();
            /* get column index pega o indice de acordo com o nome da coluna passado */
            category.setId(cursor.getLong(cursor.getColumnIndex(CategoryContract.ID)));
            category.setName(cursor.getString(cursor.getColumnIndex(CategoryContract.NAME)));
            category.setCategory_icon(cursor.getLong(cursor.getColumnIndex(CategoryContract.CATEGORY_ICON)));

            return category;
        }
        return null;
    }


    public static List<Category> getCategories(Cursor cursor) {
        ArrayList<Category> categories = new ArrayList<>();
        while (cursor.moveToNext()) {
            /* get colum index pega o indice de acordo com o nome da coluna passado */
            categories.add(getCategory(cursor));

        }
        return categories;
    }






}
