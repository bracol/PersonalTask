package com.example.waniltonfilho.personaltasks.controller.adapter;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.waniltonfilho.personaltasks.R;
import com.example.waniltonfilho.personaltasks.model.entities.Category;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Wanilton on 13/03/2016.
 */
public class CategoryAdapter extends BaseAdapter {

    private List<Category> mCategories;
    private Activity mContext;


    public CategoryAdapter(Activity context, List<Category> categories) {
        mContext = context;
        mCategories = categories;
    }


    @Override
    public int getCount() {
        return mCategories.size();
    }

    @Override
    public Category getItem(int position) {
        return mCategories.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Category category = getItem(position);
        View v = mContext.getLayoutInflater().inflate(R.layout.list_item_category, parent, false);
        ImageView mIvCategoryIcon = (ImageView) v.findViewById(R.id.ivCategoryIcon);
        TextView mTvCategoryName = (TextView) v.findViewById(R.id.tvCategoryName);
        mIvCategoryIcon.setBackgroundResource((int) category.getCategory_icon());
        mTvCategoryName.setText(category.getName());
        return v;
    }
}
