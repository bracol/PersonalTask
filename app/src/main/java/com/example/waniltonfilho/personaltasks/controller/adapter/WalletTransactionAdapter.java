package com.example.waniltonfilho.personaltasks.controller.adapter;

import android.app.Activity;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.waniltonfilho.personaltasks.R;
import com.example.waniltonfilho.personaltasks.controller.activities.ListActivity;
import com.example.waniltonfilho.personaltasks.model.entities.Category;
import com.example.waniltonfilho.personaltasks.model.entities.Wallet;
import com.example.waniltonfilho.personaltasks.model.entities.WalletTransaction;
import com.example.waniltonfilho.personaltasks.model.persistance.category.CategoryRepository;
import com.example.waniltonfilho.personaltasks.model.service.WalletTransactionService;
import com.example.waniltonfilho.personaltasks.util.ILongClickListener;
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
    private WalletTransaction itemSelected;


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
        holder.setLongClickListener(new ILongClickListener() {
            @Override
            public void onItemLongClick(int pos) {
                itemSelected = mTransactions.get(pos);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mTransactions.size();
    }

    public WalletTransaction getItem(){
        return itemSelected;
    }

    public void getItemSelected(MenuItem item){
    }

    public void setItens(List<WalletTransaction> itens) {
        mTransactions = itens;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnLongClickListener, View.OnCreateContextMenuListener {
        ImageView mImageViewTransactionCategory;
        TextView mTextViewName;
        TextView mTextViewDate;
        TextView mTextViewValue;
        ILongClickListener longClickListener;

        public MyViewHolder(View v) {
            super(v);
            mImageViewTransactionCategory = (ImageView) v.findViewById(R.id.imageViewOperation);
            mTextViewDate = (TextView) v.findViewById(R.id.textViewDate);
            mTextViewName = (TextView) v.findViewById(R.id.textViewName);
            mTextViewValue = (TextView) v.findViewById(R.id.textViewValue);
            if(mContext instanceof ListActivity) {
                v.setOnLongClickListener(this);
                v.setOnCreateContextMenuListener(this);
            }
        }

        @Override
        public boolean onLongClick(View v) {
            this.longClickListener.onItemLongClick(getLayoutPosition());
            return false;
        }

        public void setLongClickListener(ILongClickListener lc){
            this.longClickListener = lc;
        }

        @Override
        public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
            menu.setHeaderTitle(R.string.action_select_action);
            menu.add(0,0, 0, R.string.action_delete_transaction);
        }
    }
}
