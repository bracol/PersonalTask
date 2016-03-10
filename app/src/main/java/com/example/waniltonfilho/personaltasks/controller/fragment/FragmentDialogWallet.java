package com.example.waniltonfilho.personaltasks.controller.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;

import com.example.waniltonfilho.personaltasks.R;
import com.example.waniltonfilho.personaltasks.model.entities.Wallet;
import com.example.waniltonfilho.personaltasks.model.service.WalletService;

/**
 * Created by wanilton.filho on 10/03/2016.
 */
public class FragmentDialogWallet extends Fragment implements View.OnClickListener{

    private Button mButtonConfirm;
    private Button mButtonCancel;
    private EditText mEditTextStartValue;
    private FrameLayout mFrameLayout;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_dialog_wallet, container, false);
        mButtonCancel = (Button) v.findViewById(R.id.buttonCancelDialogStart);
        mButtonCancel.setOnClickListener(this);
        mButtonConfirm = (Button) v.findViewById(R.id.buttonConfirmDialogStart);
        mButtonConfirm.setOnClickListener(this);
        mEditTextStartValue = (EditText) v.findViewById(R.id.editTextStartValue);
        mFrameLayout = (FrameLayout) v.findViewById(R.id.frameBackgroundDialog);

        return v;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        startFrameAnimation();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.buttonConfirmDialogStart:
                endFrameAnimation();
                Wallet wallet = new Wallet();
                wallet.setValue(Double.valueOf(mEditTextStartValue.getText().toString()));
                WalletService.save(wallet);
                closeFragment();
                break;
            case R.id.buttonCancelDialogStart:
                endFrameAnimation();
                closeFragment();
                break;
        }
    }

    private void closeFragment() {
        FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
        ft.setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
        ft.remove(this);
        ft.commit();
    }

    private void startFrameAnimation() {
        Animation fadeInAnimation = AnimationUtils.loadAnimation(getActivity(), R.anim.fade_in);
        mFrameLayout.startAnimation(fadeInAnimation);
    }

    private void endFrameAnimation() {
        Animation fadeInAnimation = AnimationUtils.loadAnimation(getActivity(), R.anim.fade_out);
        mFrameLayout.startAnimation(fadeInAnimation);
    }
}
