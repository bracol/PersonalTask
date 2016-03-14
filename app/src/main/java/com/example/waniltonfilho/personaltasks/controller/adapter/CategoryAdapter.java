package com.example.waniltonfilho.personaltasks.controller.adapter;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.waniltonfilho.personaltasks.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Wanilton on 13/03/2016.
 */
public class CategoryAdapter extends BaseAdapter {

    private List<Integer> mIconsCategory;
    private List<String> mNameCategory;
    private Activity mContext;


    public CategoryAdapter(Activity context) {
        mContext = context;
        fillLists();
    }

    private void fillLists() {
        mIconsCategory = new ArrayList<>();
        mNameCategory = new ArrayList<>();
        mIconsCategory.add(R.drawable.shopping);
        mNameCategory.add("Shopping");
        mIconsCategory.add(R.drawable.sport);
        mNameCategory.add("Sport");
        mIconsCategory.add(R.drawable.coffee);
        mNameCategory.add("Coffe");
        mIconsCategory.add(R.drawable.food);
        mNameCategory.add("Food");
        mIconsCategory.add(R.drawable.bus);
        mNameCategory.add("Bus");
        mIconsCategory.add(R.drawable.business);
        mNameCategory.add("Business");
        mIconsCategory.add(R.drawable.car);
        mNameCategory.add("Car");
        mIconsCategory.add(R.drawable.cd);
        mNameCategory.add("Computer");
        mIconsCategory.add(R.drawable.cell);
        mNameCategory.add("Cell phone");
        mIconsCategory.add(R.drawable.game);
        mNameCategory.add("Game");
        mIconsCategory.add(R.drawable.medic);
        mNameCategory.add("Hospital");
        mIconsCategory.add(R.drawable.mala);
        mNameCategory.add("Suitcase");
        mIconsCategory.add(R.drawable.travel);
        mNameCategory.add("Travel");
    }


    @Override
    public int getCount() {
        return mIconsCategory.size();
    }

    @Override
    public Integer getItem(int position) {
        return mIconsCategory.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = mContext.getLayoutInflater().inflate(R.layout.list_item_category, parent, false);
        ImageView mIvCategoryIcon = (ImageView) v.findViewById(R.id.ivCategoryIcon);
        TextView mTvCategoryName = (TextView) v.findViewById(R.id.tvCategoryName);
        mIvCategoryIcon.setBackgroundResource(mIconsCategory.get(position));
        mTvCategoryName.setText(mNameCategory.get(position));
        return v;
    }
}
