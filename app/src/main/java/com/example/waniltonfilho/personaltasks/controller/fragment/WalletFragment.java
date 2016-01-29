package com.example.waniltonfilho.personaltasks.controller.fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.waniltonfilho.personaltasks.R;
import com.example.waniltonfilho.personaltasks.controller.activities.ActivityCategory;
import com.example.waniltonfilho.personaltasks.model.entities.WalletTransaction;
import com.example.waniltonfilho.personaltasks.model.persistance.wallet_transaction.WalletTransactionRepository;

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
        mTextViewNameInfo.setText(ActivityCategory.selectedLogin.getName());
        mTextViewMoneyInfo = (TextView) v.findViewById(R.id.textViewMoneyInfo);
        mButtonAdd = (Button) v.findViewById(R.id.buttonPut);
        mButtonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAddDialog();
            }


        });
        mButtonRemove = (Button) v.findViewById(R.id.buttonRemove);
        RelativeLayout relativeLayout = (RelativeLayout) v.findViewById(R.id.ae);
        return v;
    }

    private void showAddDialog() {
        final WalletTransaction walletTransaction = new WalletTransaction();
        final AlertDialog.Builder alert = new AlertDialog.Builder(getContext());
        final LinearLayout linearLayout = new LinearLayout(getContext());
        final EditText inputDate = new EditText(getContext());
        final EditText inputName = new EditText(getContext());
        final EditText inputPrice = new EditText(getContext());
        inputDate.setHint("Date");
        inputDate.setPadding(0, 24, 0, 48);
        inputName.setHint("Name");
        inputName.setPadding(0, 24, 0, 48);
        inputPrice.setHint("Price");
        inputPrice.setPadding(0, 24, 0, 48);
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        linearLayout.setPadding(48, 48, 48, 48);
        linearLayout.addView(inputDate);
        linearLayout.addView(inputName);
        linearLayout.addView(inputPrice);
        alert.setView(linearLayout);
        alert.setTitle("Wallet Manager");
        alert.setPositiveButton("Save", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                walletTransaction.setDate(inputDate.getText().toString());
                walletTransaction.setName(inputName.getText().toString());
                walletTransaction.setPrice(Double.valueOf(inputPrice.getText().toString()));
                walletTransaction.setAction(0);
                walletTransaction.setLogin_id(ActivityCategory.selectedLogin.getId());
                WalletTransactionRepository.save(walletTransaction);
            }
        })
                .setNeutralButton("Nao", null)
                .create()
                .show();
    }
}
