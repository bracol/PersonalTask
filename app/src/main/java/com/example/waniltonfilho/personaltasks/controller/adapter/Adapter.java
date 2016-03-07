package com.example.waniltonfilho.personaltasks.controller.adapter;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.waniltonfilho.personaltasks.R;
import com.example.waniltonfilho.personaltasks.controller.fragment.WalletFragment;
import com.example.waniltonfilho.personaltasks.model.entities.Wallet;
import com.example.waniltonfilho.personaltasks.model.entities.WalletTransaction;
import com.example.waniltonfilho.personaltasks.model.persistance.wallet_transaction.WalletRepository;
import com.example.waniltonfilho.personaltasks.model.service.WalletService;
import com.poliveira.parallaxrecyclerview.ParallaxRecyclerAdapter;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Wanilton on 03/03/2016.
 */
public class Adapter {

    private List<WalletTransaction> mTransactions;
    private Activity mContext;
    public RecyclerView mRecyclerView;

    public Adapter(List<WalletTransaction> mTransactions, Activity mContext, RecyclerView recyclerView) {
        this.mTransactions = mTransactions;
        this.mContext = mContext;
        mRecyclerView = recyclerView;
        createAdapter();
    }

    private void createAdapter() {
        LinearLayoutManager manager = new LinearLayoutManager(mContext);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(manager);

        final ParallaxRecyclerAdapter<WalletTransaction> adapter = new ParallaxRecyclerAdapter<WalletTransaction>(mTransactions) {

            @Override
            public void onBindViewHolderImpl(RecyclerView.ViewHolder holder, ParallaxRecyclerAdapter<WalletTransaction> parallaxRecyclerAdapter, int i) {
                // If you're using your custom handler (as you should of course)
                // you need to cast viewHolder to it.
                WalletTransaction walletTransaction = mTransactions.get(i);
                Wallet wallet = WalletRepository.getWallet();
                Double actualValue = wallet.getValue();
                Double newValue = actualValue - walletTransaction.getPrice();


                ((MyViewHolder)holder).mTextViewDate.setText(walletTransaction.getDate().toString());
                ((MyViewHolder)holder).mTextViewValue.setText(walletTransaction.getPrice().toString());
                ((MyViewHolder)holder).mTextViewNewValue.setText(newValue.toString());
            }

            @Override
            public RecyclerView.ViewHolder onCreateViewHolderImpl(ViewGroup parent, ParallaxRecyclerAdapter<WalletTransaction> parallaxRecyclerAdapter, int i) {
                // Here is where you inflate your row and pass it to the constructor of your ViewHolder
                View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_transaction, parent, false);
                MyViewHolder vh = new MyViewHolder(v);
                return vh;
            }

            @Override
            public int getItemCountImpl(ParallaxRecyclerAdapter<WalletTransaction> parallaxRecyclerAdapter) {
                // return the content of your array
                return mTransactions.size();
            }

            @Override
            public void setData(List<WalletTransaction> data) {
                //ParallaxRecyclerAdapter parallaxRecyclerAdapter = (ParallaxRecyclerAdapter) mRecyclerView.getAdapter();
                mTransactions = data;
                createAdapter();
            }
        };


//        View v = LayoutInflater.from(mContext).inflate(R.layout.header, mRecyclerView, false);
//            //createFragment();
//        adapter.setParallaxHeader(v, mRecyclerView);
//        adapter.notifyDataSetChanged();
//        mRecyclerView.setAdapter(adapter);
    }

//    private void createFragment() {
//        FragmentManager fragmentManager = mContext.getFragmentManager();
//        FragmentTransaction fm = fragmentManager.beginTransaction();
//        WalletFragment walletFragment = WalletFragment.getInstance();
//                //WalletFragment walletFragment = WalletFragment.getInstance(R.id.frameHeader, mRecyclerView);
//        fm.replace(R.id.frameHeader, walletFragment);
//        fm.commit();
//    }


    public class MyViewHolder extends RecyclerView.ViewHolder{
        ImageView mImageViewOperation;
        TextView mTextViewDate;
        TextView mTextViewValue;
        TextView mTextViewNewValue;

        public MyViewHolder(View v) {
            super(v);
            mImageViewOperation = (ImageView) v.findViewById(R.id.imageViewOperation);
            mTextViewDate = (TextView) v.findViewById(R.id.textViewDate);
            mTextViewValue = (TextView) v.findViewById(R.id.textViewValue);
            mTextViewNewValue = (TextView) v.findViewById(R.id.textViewNewValue);
        }
    }
}
