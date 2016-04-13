package com.example.waniltonfilho.personaltasks.controller.adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.waniltonfilho.personaltasks.R;
import com.example.waniltonfilho.personaltasks.model.entities.Category;
import com.example.waniltonfilho.personaltasks.model.entities.Wallet;
import com.example.waniltonfilho.personaltasks.model.entities.WalletTransaction;
import com.example.waniltonfilho.personaltasks.model.persistance.category.CategoryRepository;
import com.example.waniltonfilho.personaltasks.model.persistance.wallet_transaction.WalletRepository;
import com.example.waniltonfilho.personaltasks.util.MyValueFormatter;

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


    public WalletTransactionAdapter(List<WalletTransaction> transactions, Activity context) {
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
        Float actualValue = wallet.getValue();
        Float newValue = actualValue - walletTransaction.getPrice();
        String outputDateStr = "";
        Date inputDate;
        String s = "";
        Category category = CategoryRepository.getById(Long.parseLong(walletTransaction.getCategory()));
        holder.mImageViewTransactionCategory.setBackgroundResource((int) category.getCategory_icon());
        holder.mTextViewValue.setTextAppearance(mContext, R.style.shadowNegative);


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
        holder.mTextViewValue.setText("R$ " + walletTransaction.getPrice().toString());
        MyValueFormatter formatter = new MyValueFormatter();
        holder.mTextViewValue.setText(formatter.getMaskFormatted(walletTransaction.getPrice()));
        holder.mTextViewName.setText(walletTransaction.getName() == null ? "Transação" : walletTransaction.getName().toString());
    }

    @Override
    public int getItemCount() {
        return mTransactions.size();
    }

    public void setItens(List<WalletTransaction> itens) {
        mTransactions = itens;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView mImageViewTransactionCategory;
        TextView mTextViewName;
        TextView mTextViewDate;
        TextView mTextViewValue;

        public MyViewHolder(View v) {
            super(v);
            mImageViewTransactionCategory = (ImageView) v.findViewById(R.id.imageViewOperation);
            mTextViewDate = (TextView) v.findViewById(R.id.textViewDate);
            mTextViewName = (TextView) v.findViewById(R.id.textViewName);
            mTextViewValue = (TextView) v.findViewById(R.id.textViewValue);
        }
    }
}
