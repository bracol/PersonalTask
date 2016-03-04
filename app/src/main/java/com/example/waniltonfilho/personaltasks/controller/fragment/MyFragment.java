package com.example.waniltonfilho.personaltasks.controller.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.waniltonfilho.personaltasks.R;
import com.example.waniltonfilho.personaltasks.controller.adapter.Adapter;
import com.example.waniltonfilho.personaltasks.model.service.WalletTransactionService;

/**
 * Created by Wanilton on 03/03/2016.
 */
public class MyFragment extends Fragment {

    private RecyclerView mRecyclerView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_transaction_recycler, container, false);
        mRecyclerView = (RecyclerView) v.findViewById(R.id.recyclerWalletTransaction);
        return v;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Adapter adapter = new Adapter(WalletTransactionService.findAll(), getActivity(), mRecyclerView);
    }

}
