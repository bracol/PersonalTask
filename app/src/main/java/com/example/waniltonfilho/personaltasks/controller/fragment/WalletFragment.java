package com.example.waniltonfilho.personaltasks.controller.fragment;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.waniltonfilho.personaltasks.R;

/**
 * Created by wanilton.filho on 22/01/2016.
 */
public class WalletFragment extends Fragment {

    public final static String TAB_POSITION_KEY = "TAB_POSITION_KEY";

    private TextView mTextViewNameInfo;
    private TextView mTextViewMoneyInfo;
    private Button mButtonAdd;
    private Button mButtonRemove;


    public static WalletFragment getInstance(int position) {
        WalletFragment walletFragment = new WalletFragment();
        Bundle args = new Bundle();
        args.putInt(TAB_POSITION_KEY, position);
        walletFragment.setArguments(args);
        return walletFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_wallet, container, false);
        mTextViewNameInfo = (TextView) v.findViewById(R.id.textViewNameInfo);
        mTextViewMoneyInfo = (TextView) v.findViewById(R.id.textViewMoneyInfo);
        mButtonAdd = (Button) v.findViewById(R.id.buttonPut);
        mButtonRemove = (Button) v.findViewById(R.id.buttonRemove);
        RelativeLayout relativeLayout = (RelativeLayout) v.findViewById(R.id.ae);
        int ae = relativeLayout.getSolidColor();
        Drawable background = relativeLayout.getBackground();

        return v;
    }
}
