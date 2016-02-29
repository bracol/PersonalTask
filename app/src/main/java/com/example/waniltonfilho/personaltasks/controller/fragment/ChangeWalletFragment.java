package com.example.waniltonfilho.personaltasks.controller.fragment;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.app.FragmentTransaction;
import android.content.Context;
import android.graphics.Point;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.ViewGroup;
import android.view.animation.BounceInterpolator;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.waniltonfilho.personaltasks.R;
import com.example.waniltonfilho.personaltasks.model.entities.WalletTransaction;
import com.example.waniltonfilho.personaltasks.model.persistance.wallet_transaction.WalletRepository;
import com.example.waniltonfilho.personaltasks.model.service.WalletTransactionService;

/**
 * Created by wanilton.filho on 04/02/2016.
 */
public class ChangeWalletFragment extends Fragment implements View.OnClickListener {

    private TextView textViewTitle;
    private EditText editTextName;
    private EditText editTextDate;
    private EditText editTextPrice;
    private Button mButtonConfirm;
    private Button mButtonCancel;
    private FrameLayout mPrincipalLinear;
    private int[] coordsButtonAdd;
    private LinearLayout mLinearLayout;
    private WalletTransaction mWalletTransaction;
    private int mOperation;

    public ChangeWalletFragment(int operation){
        mOperation = operation;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_change_wallet, container, false);
        textViewTitle = (TextView) v.findViewById(R.id.textViewTitleChangeWallet);
        editTextName = (EditText) v.findViewById(R.id.editTextName);
        editTextDate = (EditText) v.findViewById(R.id.editTextDateWallet);
        editTextPrice = (EditText) v.findViewById(R.id.editTextPriceWallet);
        mButtonCancel = (Button) v.findViewById(R.id.buttonCancelChange);
        mButtonCancel.setOnClickListener(this);
        mButtonConfirm = (Button) v.findViewById(R.id.buttonConfirmChange);
        mButtonConfirm.setOnClickListener(this);
        return v;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.buttonCancelChange:
                Toast.makeText(getActivity(), "testeCancel", Toast.LENGTH_SHORT).show();
                //getFragmentManager().beginTransaction().addToBackStack(null);
                getActivity().getFragmentManager().popBackStack();
                break;
            case R.id.buttonConfirmChange:
                onButtonConfirm();

        }
    }

    private void onButtonConfirm() {
        bindWalletTransaction();
        WalletTransactionService.save(mWalletTransaction, mOperation);
        getActivity().getSupportFragmentManager().beginTransaction()
                .setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right)
                .remove(this)
                .commit();
        Snackbar.make(getView(), getString(R.string.action_button_transaction_confirm), Snackbar.LENGTH_SHORT).show();
    }

    private void bindWalletTransaction() {
        mWalletTransaction = new WalletTransaction();
        mWalletTransaction.setAction(mOperation);
        mWalletTransaction.setDate(editTextDate.getText().toString());
        mWalletTransaction.setPrice(Double.parseDouble(editTextPrice.getText().toString()));
    }
}
