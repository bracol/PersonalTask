package com.example.waniltonfilho.personaltasks.controller.fragment;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.waniltonfilho.personaltasks.R;
import com.example.waniltonfilho.personaltasks.controller.activities.MainActivity;
import com.example.waniltonfilho.personaltasks.controller.adapter.CategoryAdapter;
import com.example.waniltonfilho.personaltasks.controller.adapter.WalletTransactionAdapter;
import com.example.waniltonfilho.personaltasks.controller.tasks.TaskGetLastTransaction;
import com.example.waniltonfilho.personaltasks.controller.tasks.TaskGetWalletTransaction;
import com.example.waniltonfilho.personaltasks.controller.tasks.TaskGetWtsSumMonth;
import com.example.waniltonfilho.personaltasks.controller.tasks.TaskPostWalletTransaction;
import com.example.waniltonfilho.personaltasks.controller.tasks.TaskUpdateWallet;
import com.example.waniltonfilho.personaltasks.model.entities.Category;
import com.example.waniltonfilho.personaltasks.model.entities.SumWalletTransaction;
import com.example.waniltonfilho.personaltasks.model.entities.Wallet;
import com.example.waniltonfilho.personaltasks.model.entities.WalletTransaction;
import com.example.waniltonfilho.personaltasks.model.service.WalletService;
import com.example.waniltonfilho.personaltasks.model.service.WalletTransactionService;
import com.example.waniltonfilho.personaltasks.util.ConnectionUtil;
import com.example.waniltonfilho.personaltasks.util.MyValueFormatter;
import com.example.waniltonfilho.personaltasks.util.StringUtil;
import com.melnykov.fab.FloatingActionButton;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by wanilton.filho on 04/02/2016.
 */
public class ChangeWalletFragment extends Fragment implements View.OnClickListener {

    private TextView textViewTitle;
    private EditText editTextName;
    private EditText editTextPrice;
    private Button mButtonConfirm;
    private Button mButtonCancel;
    private WalletTransaction mWalletTransaction;
    private LinearLayout mLinearAnimation;
    private FrameLayout mFrameAnimation;
    private Spinner mSpinnerIcons;
    private Category mCategorySelected;
    private TextView mTextViewMoney;
    private RecyclerView recyclerViewWallet;
    private List<Category> mCategories;
    private Wallet mWallet;
    private FloatingActionButton fab;
    private EditText mEditTextRecurrence;

    public ChangeWalletFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mWallet = getArguments().getParcelable("wallet");
        }
        getActivity().findViewById(R.id.textViewMoney);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_change_wallet, container, false);
        textViewTitle = (TextView) v.findViewById(R.id.textViewTitleChangeWallet);
        editTextName = (EditText) v.findViewById(R.id.editTextNameWallet);
        //editTextDate.addTextChangedListener(new EditTextMaskDate(editTextDate));
        editTextPrice = (EditText) v.findViewById(R.id.editTextPriceWallet);
        mTextViewMoney = (TextView) getActivity().findViewById(R.id.textViewMoney);
        recyclerViewWallet = (RecyclerView) getActivity().findViewById(R.id.recyclerLastTransaction);
        fab = (FloatingActionButton) getActivity().findViewById(R.id.fabAddTransaction);
        mEditTextRecurrence = (EditText) v.findViewById(R.id.editTextRecorrencia);
        fab.setVisibility(View.GONE);

        editTextPrice.addTextChangedListener(new TextWatcher() {
            String current = "";

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!s.toString().equals(current)) {

                    String cleanString = s.toString().replaceAll("[R$,.]", "");


                    float parsed = Float.parseFloat(cleanString);
                    MyValueFormatter myValueFormatter = new MyValueFormatter();
                    String formatted = myValueFormatter.getMaskNumbers(parsed / 100);
                    current = formatted;
                    editTextPrice.setText(formatted);
                    editTextPrice.setSelection(formatted.length());

                }
            }
        });
        mButtonCancel = (Button) v.findViewById(R.id.buttonCancelChange);
        mButtonCancel.setOnClickListener(this);
        mButtonConfirm = (Button) v.findViewById(R.id.buttonConfirmChange);
        mButtonConfirm.setOnClickListener(this);
        mLinearAnimation = (LinearLayout) v.findViewById(R.id.linearAnimation);
        mFrameAnimation = (FrameLayout) v.findViewById(R.id.frameAnimation);
        mSpinnerIcons = (Spinner) v.findViewById(R.id.spinnerIcons);
        bindSpinner(mSpinnerIcons);
        return v;
    }

    private void bindSpinner(Spinner spinner) {
        mCategories = Category.getCategories();
        spinner.setAdapter(new CategoryAdapter(getActivity(), mCategories));
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                mCategorySelected = (Category) parent.getSelectedItem();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        startFrameAnimation();


    }

    private void startFrameAnimation() {
        Animation swipeLeftAnimation = AnimationUtils.loadAnimation(getActivity(), R.anim.swipe_from_left);
        mLinearAnimation.startAnimation(swipeLeftAnimation);

    }

    private void endFrameAnimation() {
        Animation swipeRightAnimation = AnimationUtils.loadAnimation(getActivity(), R.anim.swipe_to_right);
        mLinearAnimation.startAnimation(swipeRightAnimation);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.buttonCancelChange:
                onButtonCancel();
                break;
            case R.id.buttonConfirmChange:
                onButtonConfirm();
                break;
        }
        StringUtil.closeKeyboard(getActivity());
    }

    private void onButtonCancel() {
        removeFragment();

    }

    private void removeFragment() {
        fab.setVisibility(View.VISIBLE);
        getActivity().getSupportFragmentManager().beginTransaction()
                .setCustomAnimations(R.anim.fade_in, R.anim.fade_out)
                .remove(this)
                .commit();
    }

    private void onButtonConfirm() {
        Integer qtMonth = mEditTextRecurrence.getText().toString().equals("") ? 1 : Integer.parseInt(mEditTextRecurrence.getText().toString());
        bindWalletTransaction();
        if (mWalletTransaction != null) {
            if (mWallet == null) {
                WalletTransactionService.save(mWalletTransaction, qtMonth);
            } else {
                mWalletTransaction.setWallet_id(mWallet.get_id());
                new TaskPostWalletTransaction(mWalletTransaction, qtMonth).execute();
            }
            updateTransactions();
            removeFragment();
            changeWalletTextValue();
            Snackbar.make(getView(), getString(R.string.action_button_transaction_confirm), Snackbar.LENGTH_SHORT).show();
        }
    }

    private void changeWalletTextValue() {
        final MyValueFormatter myValueFormatter = new MyValueFormatter();
        if (mWallet == null) {
            Wallet wallet = WalletService.getWallet();
            mTextViewMoney.setText(myValueFormatter.getMaskFormatted(wallet.getValue()));
        } else {
            mWallet.setValue(mWallet.getValue() + Float.parseFloat(MyValueFormatter.formatPrice(editTextPrice.getText().toString())));
            new TaskGetWtsSumMonth(mWallet.get_id(), StringUtil.getActualMonthYear()[0], StringUtil.getActualMonthYear()[1]) {
                @Override
                protected void onPostExecute(SumWalletTransaction sumWalletTransaction) {
                    super.onPostExecute(sumWalletTransaction);
                    if (sumWalletTransaction != null) {
                        mTextViewMoney.setText(myValueFormatter.getMaskFormatted(sumWalletTransaction.getPrice()));
                    } else {
                        mTextViewMoney.setText(myValueFormatter.getMaskFormatted(0f));
                    }
                }
            }.execute();
//            new TaskUpdateWallet(mWallet) {
//                ProgressDialog dialog;
//
//                @Override
//                protected void onPreExecute() {
//                    dialog = new ProgressDialog(getActivity());
//                    dialog.setMessage(getActivity().getString(R.string.log_info_att_wallet));
//                    dialog.show();
//                    super.onPreExecute();
//                }
//
//                @Override
//                protected void onPostExecute(Wallet wallet) {
//                    mWallet = wallet;
//                    mTextViewMoney.setText(myValueFormatter.getMaskFormatted(mWallet.getValue()));
//                    dialog.dismiss();
//                }
//            }.execute();

        }
    }


    private void bindWalletTransaction() {
        if (!StringUtil.isNullOrBlank(editTextPrice.getText().toString(), editTextPrice, getActivity()) && (StringUtil.isDouble(editTextPrice.getText().toString(), editTextPrice, getActivity()))) {
            mWalletTransaction = new WalletTransaction();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String currentDateandTime = sdf.format(new Date());
            mWalletTransaction.setDate(currentDateandTime);
            mWalletTransaction.setName(editTextName.getText() != null ? editTextName.getText().toString() : "Transação");
            mWalletTransaction.setPrice(Float.parseFloat(MyValueFormatter.formatPrice(editTextPrice.getText().toString())));
            mWalletTransaction.setCategory(String.valueOf(mCategorySelected.getId()));
        }
    }

    private void updateTransactions() {
        if (mWallet == null) {
            List<WalletTransaction> mListTransactions = WalletTransactionService.getLastTransactions(2);
            WalletTransactionAdapter adapter = (WalletTransactionAdapter) recyclerViewWallet.getAdapter();
            adapter.setItens(mListTransactions);
            adapter.notifyDataSetChanged();
        } else {
            new TaskGetLastTransaction(mWallet.get_id()) {

                ProgressDialog dialog;

                @Override
                protected void onPreExecute() {
                    dialog = new ProgressDialog(getActivity());
                    dialog.setMessage("Loading...");
                    dialog.show();
                    super.onPreExecute();
                }

                @Override
                protected void onPostExecute(List<WalletTransaction> transactions) {
                    super.onPostExecute(transactions);
                    WalletTransactionAdapter adapter = (WalletTransactionAdapter) recyclerViewWallet.getAdapter();
                    adapter.setItens(transactions);
                    adapter.notifyDataSetChanged();
                    dialog.dismiss();
                }
            }.execute();
        }
    }

    @Override
    public void onDestroyView() {
        endFrameAnimation();
        MainActivity.dialogVisible  = false;
        super.onDestroyView();
    }
}
