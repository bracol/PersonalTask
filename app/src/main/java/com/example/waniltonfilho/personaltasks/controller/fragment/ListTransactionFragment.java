package com.example.waniltonfilho.personaltasks.controller.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.waniltonfilho.personaltasks.R;
import com.example.waniltonfilho.personaltasks.controller.adapter.Adapter;
import com.example.waniltonfilho.personaltasks.controller.adapter.WalletTransactionAdapter;
import com.example.waniltonfilho.personaltasks.model.entities.Wallet;
import com.example.waniltonfilho.personaltasks.model.entities.WalletTransaction;
import com.example.waniltonfilho.personaltasks.model.service.WalletTransactionService;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Wanilton on 03/03/2016.
 */
public class ListTransactionFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private List<WalletTransaction> mTransactions;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_transaction, container, false);
        mRecyclerView = (RecyclerView) v.findViewById(R.id.recyclerTransaction);
        mTransactions = new ArrayList<>();
        return v;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        mTransactions = new ArrayList<>();
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setAdapter(new WalletTransactionAdapter(mTransactions, getActivity()));
        super.onViewCreated(view, savedInstanceState);
    }

    public void updateList(){
        mTransactions = WalletTransactionService.findAll();
        WalletTransactionAdapter adapter = (WalletTransactionAdapter) mRecyclerView.getAdapter();
        adapter.setItens(mTransactions);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        if(isVisibleToUser)
            updateList();
    }
}
