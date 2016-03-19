package com.example.waniltonfilho.personaltasks.teste;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.waniltonfilho.personaltasks.R;
import com.example.waniltonfilho.personaltasks.model.service.WalletTransactionService;

import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * Created by Wanilton on 19/03/2016.
 */
public class CustomPagerAdapter extends PagerAdapter {

    private Context mContext;
    private TextView mTextView;
    private int last;

    public CustomPagerAdapter(Context context) {
        mContext = context;

    }

    @Override
    public Object instantiateItem(ViewGroup collection, int position) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        ViewGroup layout = (ViewGroup) inflater.inflate(R.layout.fragment_teste, collection, false);
        mTextView = (TextView) layout.findViewById(R.id.tvTeste);
        if(last > position) {
            //customPageAdapter.setTextViewTitle(mManipulateList.swipe_left(lastTitle));
            last = position;
        } else {
            //customPageAdapter.setTextViewTitle(mManipulateList.swipe_right(lastTitle));
            last = position;
        }
        collection.addView(layout);
        return layout;
    }



    public void bindFirstTime(){
    }

    @Override
    public void destroyItem(ViewGroup collection, int position, Object view) {
        collection.removeView((View) view);
    }

    @Override
    public int getCount() {
        return (1 + (Integer.parseInt(WalletTransactionService.getFirstLastTransaction(1).getDate().substring(0, 4)) - Integer.parseInt(WalletTransactionService.getFirstLastTransaction(0).getDate().substring(0, 4)))) * 12;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        CustomPagerEnum customPagerEnum = new CustomPagerEnum("MAOE");
        return(customPagerEnum.getTitleResId());
    }

    public void setTextViewTitle(String title){
        if (mTextView != null)
            mTextView.setText(title);
    }

    public String getTvTitle() {
        return mTextView.getText().toString();
    }
}
