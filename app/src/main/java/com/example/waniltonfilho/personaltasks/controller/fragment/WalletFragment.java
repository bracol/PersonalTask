package com.example.waniltonfilho.personaltasks.controller.fragment;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.content.DialogInterface;
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
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
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
    private FrameLayout mFrameLayoutAdd;
    private EditText mEditTextDate;
    private EditText mEditTextName;
    private EditText mEditTextPrice;
    private boolean clicked;
    private LinearLayout mLinearLayoutAdd;


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
        mFrameLayoutAdd = (FrameLayout) v.findViewById(R.id.frameLayoutTransaction);
        mLinearLayoutAdd = (LinearLayout) v.findViewById(R.id.dialogAdd);
        mEditTextDate = (EditText) v.findViewById(R.id.editTextDateWallet);
        mEditTextName = (EditText) v.findViewById(R.id.editTextName);
        mEditTextPrice = (EditText) v.findViewById(R.id.editTextPriceWallet);
        mButtonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAddDialog(v);
            }


        });
        mButtonRemove = (Button) v.findViewById(R.id.buttonRemove);
        return v;
    }

    private void showAddDialog(View v){
        FragmentManager fm = getActivity().getSupportFragmentManager();

        ChangeWalletFragment fragment = new ChangeWalletFragment(buttonAddCalculatePosition(v));
        mFrameLayoutAdd.setVisibility(View.VISIBLE);
        fm.beginTransaction().add(R.id.frameLayoutTransaction, fragment).commit();
    }

    private int[] buttonAddCalculatePosition(View v) {
        int[] clickCooords = new int[2];
        v.getLocationInWindow(clickCooords);
        clickCooords[0] += v.getWidth() / 2;
        clickCooords[1] += v.getHeight() / 2;
        return clickCooords;
    }

//    private void showAddDialog(View v, int flag) {
//        int[] clickCooords = new int[2];
//        v.getLocationInWindow(clickCooords);
//        clickCooords[0] += v.getWidth() / 2;
//        clickCooords[1] += v.getHeight() / 2;
//        FragmentManager fm = getActivity().getSupportFragmentManager();
//        ChangeWalletFragment fragment = new ChangeWalletFragment();
//        fm.beginTransaction().add(R.id.frameLayoutTransaction, fragment).commit();
//        if (flag == 0) {
//            mFrameLayoutAdd.setVisibility(View.VISIBLE);
//            performRevealAnimationIn(mLinearLayoutAdd, clickCooords[0], clickCooords[1]);
//        } else {
//            performRevealAnimationOut(mLinearLayoutAdd, clickCooords[0], clickCooords[1]);
//        }
//    }
//
//    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
//    private void performRevealAnimationIn(final View v, int x, int y) {
//        int[] animationCoords = new int[2];
//        v.getLocationInWindow(animationCoords);
//        animationCoords[0] = x - animationCoords[0];
//        animationCoords[1] = y - animationCoords[1];
//
//        Point size = new Point();
//
//        getActivity().getWindowManager().getDefaultDisplay().getSize(size);
//        int maximunRadius = size.y;
//
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//            Animator animator = ViewAnimationUtils.createCircularReveal(v, animationCoords[0], animationCoords[1], 0, maximunRadius);
//            animator.setDuration(1000);
//            animator.start();
//            v.setVisibility(View.VISIBLE);
//        }
//
//
//    }
//
//
//    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
//    private void performRevealAnimationOut(final View v, int x, int y) {
//        int[] animationCoords = new int[2];
//        v.getLocationInWindow(animationCoords);
//        animationCoords[0] = x - animationCoords[0];
//        animationCoords[1] = y - animationCoords[1];
//
//        Point size = new Point();
//
//        getActivity().getWindowManager().getDefaultDisplay().getSize(size);
//        int maximunRadius = size.y;
//
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//            Animator animator = ViewAnimationUtils.createCircularReveal(v, animationCoords[0], animationCoords[1], maximunRadius, 0);
//            animator.setDuration(1000);
//            animator.start();
//            animator.addListener(new AnimatorListenerAdapter() {
//                @Override
//                public void onAnimationEnd(Animator animator) {
//                    v.setVisibility(View.GONE);
//                    mFrameLayoutAdd.setVisibility(View.GONE);
//                }
//            });
//
//
//        }
//    }

    private void showAddDialog2() {
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
