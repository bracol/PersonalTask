package com.example.waniltonfilho.personaltasks.controller.fragment;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Point;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.waniltonfilho.personaltasks.R;
import com.example.waniltonfilho.personaltasks.controller.activities.ActivityCategory;
import com.example.waniltonfilho.personaltasks.controller.adapter.WalletTransactionAdapter;
import com.example.waniltonfilho.personaltasks.model.entities.Wallet;
import com.example.waniltonfilho.personaltasks.model.entities.WalletTransaction;
import com.example.waniltonfilho.personaltasks.model.persistance.wallet_transaction.WalletRepository;
import com.example.waniltonfilho.personaltasks.model.persistance.wallet_transaction.WalletTransactionRepository;
import com.example.waniltonfilho.personaltasks.model.service.WalletService;
import com.example.waniltonfilho.personaltasks.model.service.WalletTransactionService;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wanilton.filho on 22/01/2016.
 */
public class WalletFragment extends Fragment {

    public final static String TAB_POSITION_KEY = "TAB_POSITION_KEY";

    private TextView mTextViewNameInfo;
    private TextView mTextViewMoneyInfo;
    private Button mButtonAdd;
    private Button mButtonRemove;
    private RecyclerView mRecyclerView;
    private Wallet mWallet;
    private List<WalletTransaction> mTransactions;


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
        List<Wallet> wallets = WalletService.findAll();
        mWallet = WalletRepository.getWallet();
        View v = inflater.inflate(R.layout.fragment_wallet, container, false);
        mTextViewNameInfo = (TextView) v.findViewById(R.id.textViewNameInfo);
        mTextViewNameInfo.setText(ActivityCategory.selectedLogin.getName());
        mTextViewMoneyInfo = (TextView) v.findViewById(R.id.textViewMoneyInfo);
        mTextViewMoneyInfo.setText(mWallet.getValue().toString());
        mButtonAdd = (Button) v.findViewById(R.id.buttonPut);
        mButtonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAddDialog(v);
            }


        });
        mButtonRemove = (Button) v.findViewById(R.id.buttonRemove);
        mRecyclerView = (RecyclerView) v.findViewById(R.id.recyclerViewWalletTransacion);
        fillRecyclerView();
        return v;
    }

    private void fillRecyclerView() {
        mTransactions = new ArrayList<>();
        WalletTransactionAdapter adapter = new WalletTransactionAdapter(mTransactions, getActivity());
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setAdapter(adapter);
    }

    private void showAddDialog(View v){
        FragmentTransaction fm = getActivity().getSupportFragmentManager().beginTransaction();
        fm.setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
        ChangeWalletFragment fragment = new ChangeWalletFragment(1);
        fm.replace(R.id.frameLayoutTransaction, fragment);
        fm.commit();
    }

    private void updateWalletTransaction(){
        mTransactions = WalletTransactionService.findAll();
        WalletTransactionAdapter adapter = (WalletTransactionAdapter) mRecyclerView.getAdapter();
        adapter.setItens(mTransactions);
        adapter.notifyDataSetChanged();
        mTextViewMoneyInfo.setText(mWallet.getValue().toString());
    }

    @Override
    public void onResume() {
        super.onResume();
        updateWalletTransaction();
    }

}
