package com.example.waniltonfilho.personaltasks.controller.fragment;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.graphics.Point;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.FrameLayout;
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
public class WalletFragment extends Fragment implements View.OnClickListener {

    public final static String TAB_POSITION_KEY = "TAB_POSITION_KEY";

    private TextView mTextViewNameInfo;
    private TextView mTextViewMoneyInfo;
    private Button mButtonAdd;
    private Button mButtonRemove;
    private Button mButtonShowTransactions;
    private FrameLayout mFrameListTransaction;
    private Wallet mWallet;
    private int mOperation;
    private Boolean clicked = false;


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
        mButtonAdd.setOnClickListener(this);
        mButtonShowTransactions = (Button) v.findViewById(R.id.buttonShowTransactions);
        mButtonShowTransactions.setOnClickListener(this);
        mButtonRemove = (Button) v.findViewById(R.id.buttonRemove);
        mButtonRemove.setOnClickListener(this);
        mFrameListTransaction = (FrameLayout) v.findViewById(R.id.frameListTransaction);
        v.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                return true;
            }
        });
        return v;
    }

//    private void fillRecyclerView() {
//        mTransactions = new ArrayList<>();
//        WalletTransactionAdapter adapter = new WalletTransactionAdapter(mTransactions, getActivity());
//        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
//        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
//        mRecyclerView.setAdapter(adapter);
//    }

    private void showAddDialog(View v) {
        FragmentTransaction fm = getActivity().getFragmentManager().beginTransaction();
        fm.setCustomAnimations(R.animator.slide_in_left, R.animator.slide_in_right);
        ChangeWalletFragment changeFragment = new ChangeWalletFragment(mOperation, mTextViewMoneyInfo);
        fm.replace(R.id.frameLayoutTransaction, changeFragment);
        fm.commit();
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.buttonPut:
                mOperation = 1;
                showAddDialog(v);
                break;
            case R.id.buttonRemove:
                mOperation = 0;
                showAddDialog(v);
                break;
            case R.id.buttonShowTransactions:
                animationCalculate(v);
                break;
        }
    }

    private void animationCalculate(View v) {
        int[] clickCooords = new int[2];
        v.getLocationInWindow(clickCooords);
        clickCooords[0] += v.getWidth() / 2;
        clickCooords[1] += v.getHeight() / 2;
        android.support.v4.app.FragmentManager fm = getActivity().getSupportFragmentManager();
        ListTransactionFragment fragment = new ListTransactionFragment();
        fm.beginTransaction().replace(R.id.frameListTransaction, fragment).commit();
        if (clicked == false) {
            performRevealAnimationIn(mFrameListTransaction, clickCooords[0], clickCooords[1]);
            clicked = true;
        } else {
            performRevealAnimationOut(mFrameListTransaction, clickCooords[0], clickCooords[1]);
            clicked = false;

        }
    }

    private void performRevealAnimationIn(final View v, int x, int y) {
        int[] animationCoords = new int[2];
        v.getLocationInWindow(animationCoords);
        animationCoords[0] = x - animationCoords[0];
        animationCoords[1] = y - animationCoords[1];

        Point size = new Point();

        getActivity().getWindowManager().getDefaultDisplay().getSize(size);
        int maximunRadius = size.y;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Animator animator = ViewAnimationUtils.createCircularReveal(v, x, y, 0, maximunRadius);
            animator.start();
            v.setVisibility(View.VISIBLE);
        }


    }

    private void performRevealAnimationOut(final View v, int x, int y) {
        int[] animationCoords = new int[2];
        v.getLocationInWindow(animationCoords);
        animationCoords[0] = x - animationCoords[0];
        animationCoords[1] = y - animationCoords[1];

        Point size = new Point();

        getActivity().getWindowManager().getDefaultDisplay().getSize(size);
        int maximunRadius = size.y;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Animator animator = ViewAnimationUtils.createCircularReveal(v, x, y, maximunRadius, 0);
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
