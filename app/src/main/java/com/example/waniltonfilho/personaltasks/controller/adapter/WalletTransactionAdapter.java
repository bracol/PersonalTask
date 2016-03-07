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
import com.example.waniltonfilho.personaltasks.model.persistance.wallet_transaction.WalletRepository;
import com.example.waniltonfilho.personaltasks.model.service.WalletService;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
        Wallet wallet = WalletRepository.getWallet();
        Double actualValue = wallet.getValue();
        Double newValue = actualValue - walletTransaction.getPrice();
        String outputDateStr = "";
        Date inputDate;
        String s = "";


        try {
            String data = walletTransaction.getDate();
            DateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd");
            DateFormat outputFormat = new SimpleDateFormat("dd/MM/yyyy");
            inputDate = inputFormat.parse(data);
            outputDateStr = outputFormat.format(inputDate);
            DateFormat dateFormat = android.text.format.DateFormat.getLongDateFormat(mContext);
            s = dateFormat.format(inputDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        holder.mTextViewDate.setText(s);
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
