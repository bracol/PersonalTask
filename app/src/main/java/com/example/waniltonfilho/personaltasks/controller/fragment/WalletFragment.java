package com.example.waniltonfilho.personaltasks.controller.fragment;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.waniltonfilho.personaltasks.R;
import com.example.waniltonfilho.personaltasks.controller.activities.ActivityCategory;
import com.example.waniltonfilho.personaltasks.model.entities.Wallet;
import com.example.waniltonfilho.personaltasks.model.entities.WalletTransaction;
import com.example.waniltonfilho.personaltasks.model.persistance.wallet_transaction.WalletRepository;
import com.example.waniltonfilho.personaltasks.model.service.WalletService;

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
    private int mFrameId;

    public WalletFragment(int frameID){
        mFrameId = frameID;
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
//        fillRecyclerView();
        return v;
    }

//    private void fillRecyclerView() {
//        mTransactions = new ArrayList<>();
//        WalletTransactionAdapter adapter = new WalletTransactionAdapter(mTransactions, getActivity());
//        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
//        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
//        mRecyclerView.setAdapter(adapter);
//    }

    private void showAddDialog(View v){
        FragmentTransaction fm = getActivity().getFragmentManager().beginTransaction();
        fm.setCustomAnimations(R.animator.slide_in_left, R.animator.slide_in_right);
        ChangeWalletFragment fragment = new ChangeWalletFragment(1);
        fm.replace(mFrameId, fragment);
        fm.commit();
    }
//
//    private void updateWalletTransaction(){
//        mTransactions = WalletTransactionService.findAll();
//        WalletTransactionAdapter adapter = (WalletTransactionAdapter) mRecyclerView.getAdapter();
//        adapter.setItens(mTransactions);
//        adapter.notifyDataSetChanged();
//        mTextViewMoneyInfo.setText(mWallet.getValue().toString());
//    }

    @Override
    public void onResume() {
        super.onResume();
        //updateWalletTransaction();
    }

}
