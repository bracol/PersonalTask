package com.example.waniltonfilho.personaltasks.controller.fragment;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Point;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
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

    public ChangeWalletFragment(int[] coords) {
        coordsButtonAdd = coords;
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
                getFragmentManager().beginTransaction().addToBackStack(null);
                getActivity().getFragmentManager().popBackStack();
                break;
            case R.id.buttonConfirmChange:
                Toast.makeText(getActivity(), "testeConfirm", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    private void showAddDialog(View v, int flag) {

        if (flag == 0) {
            performRevealAnimationIn(mLinearLayout, coordsButtonAdd[0], coordsButtonAdd[1]);
        } else {
            performRevealAnimationOut(mLinearLayout, coordsButtonAdd[0], coordsButtonAdd[1]);
        }
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    private void performRevealAnimationIn(final View v, int x, int y) {
        int[] animationCoords = new int[2];
        mPrincipalLinear.getLocationInWindow(animationCoords);
        int centerX = x - animationCoords[0];
        int centerY = y - animationCoords[1];

        Point size = new Point();

        getActivity().getWindowManager().getDefaultDisplay().getSize(size);
        int maximunRadius = size.y;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Animator animator = ViewAnimationUtils.createCircularReveal(v, centerX, centerY, 0, maximunRadius);
            animator.setDuration(1000);
            animator.start();
        }


    }




    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    private void performRevealAnimationOut(final View v, int x, int y) {
        int[] animationCoords = new int[2];
        v.getLocationInWindow(animationCoords);
        animationCoords[0] = x - animationCoords[0];
        animationCoords[1] = y - animationCoords[1];

        Point size = new Point();

        getActivity().getWindowManager().getDefaultDisplay().getSize(size);
        int maximunRadius = size.y;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Animator animator = ViewAnimationUtils.createCircularReveal(v, animationCoords[0], animationCoords[1], maximunRadius, 0);
            animator.setDuration(1000);
            animator.start();
            animator.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animator) {
                    v.setVisibility(View.GONE);
                }
            });


        }
    }
}
