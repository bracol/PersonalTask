package com.example.waniltonfilho.personaltasks.controller.adapter;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.waniltonfilho.personaltasks.R;
import com.example.waniltonfilho.personaltasks.model.entities.Wallet;
import com.example.waniltonfilho.personaltasks.model.entities.WalletTransaction;
import com.example.waniltonfilho.personaltasks.model.service.WalletService;

import java.util.List;

/**
 * Created by Wanilton on 28/02/2016.
 */
public class WalletTransactionAdapter extends RecyclerView.Adapter<WalletTransactionAdapter.MyViewHolder> {

    private List<WalletTransaction> mTransactions;
    private Activity mContext;
    private List<WalletTransaction> itens;


    public WalletTransactionAdapter(List<WalletTransaction> transactions, Activity context){
        mTransactions = transactions;
        mContext = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_transaction, parent, false);
        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        WalletTransaction walletTransaction = mTransactions.get(position);
        List<Wallet> wallets = WalletService.findAll();
        Double actualValue = wallets.get(0).getValue();
        Double newValue = actualValue - walletTransaction.getPrice();


        holder.mTextViewDate.setText(walletTransaction.getDate());
        holder.mTextViewValue.setText(walletTransaction.getPrice().toString());
        holder.mTextViewNewValue.setText(newValue.toString());
    }

    @Override
    public int getItemCount() {
        return mTransactions.size();
    }

    public void setItens(List<WalletTransaction> itens) {
        mTransactions = itens;
    }

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
