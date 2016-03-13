package com.example.waniltonfilho.personaltasks.controller.adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.waniltonfilho.personaltasks.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Wanilton on 13/03/2016.
 */
public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.MyViewHolder> {

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
        mNameCategory.add("shopping");
        mIconsCategory.add(R.drawable.sport);
        mNameCategory.add("sport");
        mIconsCategory.add(R.drawable.coffee);
        mNameCategory.add("coffe");
        mIconsCategory.add(R.drawable.food);
        mNameCategory.add("food");
        mIconsCategory.add(R.drawable.bus);
        mNameCategory.add("bus");
        mIconsCategory.add(R.drawable.business);
        mNameCategory.add("business");
        mIconsCategory.add(R.drawable.car);
        mNameCategory.add("car");
        mIconsCategory.add(R.drawable.cd);
        mNameCategory.add("cd");
        mIconsCategory.add(R.drawable.cell);
        mNameCategory.add("cell");
        mIconsCategory.add(R.drawable.game);
        mNameCategory.add("game");
        mIconsCategory.add(R.drawable.medic);
        mNameCategory.add("medic");
        mIconsCategory.add(R.drawable.mala);
        mNameCategory.add("mala");
        mIconsCategory.add(R.drawable.travel);
        mNameCategory.add("travel");
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_category, parent, false);
        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.mIvCategoryIcon.setBackgroundResource(mIconsCategory.get(position));
        holder.mTvCategoryName.setText(mNameCategory.get(position));
    }

    @Override
    public int getItemCount() {
        return mIconsCategory.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView mIvCategoryIcon;
        TextView mTvCategoryName;

        public MyViewHolder(View v) {
            super(v);
            mIvCategoryIcon = (ImageView) v.findViewById(R.id.ivCategoryIcon);
            mTvCategoryName = (TextView) v.findViewById(R.id.tvCategoryName);
        }
    }
}
