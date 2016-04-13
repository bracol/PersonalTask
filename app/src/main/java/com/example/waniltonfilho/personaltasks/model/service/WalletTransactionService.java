package com.example.waniltonfilho.personaltasks.model.service;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.app.FragmentManager;
import android.graphics.Point;
import android.os.Build;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;

import com.example.waniltonfilho.personaltasks.R;
import com.example.waniltonfilho.personaltasks.model.entities.Category;
import com.example.waniltonfilho.personaltasks.model.entities.Wallet;
import com.example.waniltonfilho.personaltasks.model.entities.WalletTransaction;
import com.example.waniltonfilho.personaltasks.model.persistance.category.CategoryRepository;
import com.example.waniltonfilho.personaltasks.model.persistance.wallet_transaction.WalletRepository;
import com.example.waniltonfilho.personaltasks.model.persistance.wallet_transaction.WalletTransactionRepository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wanilton.filho on 29/01/2016.
 */
public class WalletTransactionService {

    private WalletTransactionService(){super();}

    public static List<WalletTransaction> findAll(){
        List<WalletTransaction> walletTransactions = WalletTransactionRepository.getAll();
//        for (WalletTransaction walletTransaction : walletTransactions) {
//            walletTransaction.setCategory(CategoryRepository.getById(walletTransaction.getCategory().getId()));
//        }
        return walletTransactions;
    }

    public static void save(WalletTransaction walletTransaction, int operation){
        Float newValue;
        WalletTransactionRepository.save(walletTransaction);
        Wallet wallet = WalletRepository.getWallet();
        Float actualValue = wallet.getValue();
        newValue = actualValue + walletTransaction.getPrice();
        WalletService.update(newValue);
    }

    public static void delete(WalletTransaction selectedWalletTransaction){
        WalletTransactionRepository.delete(selectedWalletTransaction.getId());
    }

    public static List<WalletTransaction> getLastTransactions(int cont){
        List<WalletTransaction> walletTransactions = WalletTransactionRepository.getAll();
//        for (WalletTransaction walletTransaction : walletTransactions) {
//            walletTransaction.setCategory(CategoryRepository.getById(walletTransaction.getCategory().getId()));
//        }
        List<WalletTransaction> lastList = new ArrayList<>();
        int listaSize = walletTransactions.size();

        if(listaSize > 0){
            for(int i = 1; i <= cont; i++){
                if (i <= listaSize)
                    lastList.add(walletTransactions.get(listaSize - i));
            }
        }
        return lastList;
    }

    public static List<WalletTransaction> getMonthTransaction(String month){
        List<WalletTransaction> walletTransactions = WalletTransactionRepository.getByMonth(month);
//        for (WalletTransaction walletTransaction : walletTransactions) {
//            walletTransaction.setCategory(CategoryRepository.getById(walletTransaction.getCategory().getId()));
//        }
        return walletTransactions;
    }

    public static WalletTransaction getFirstLastTransaction(int type){
        if(type == 0){
            return WalletTransactionRepository.getFirstTransaction();
        } else {
            return WalletTransactionRepository.getLastTransaction();
        }
    }

    public static List<WalletTransaction> getSumCategoryService(String month){
        List<WalletTransaction> walletTransactions = WalletTransactionRepository.getSumCategory(month);
//        for (WalletTransaction walletTransaction : walletTransactions) {
//            walletTransaction.setCategory(CategoryRepository.getById(walletTransaction.getCategory().getId()));
//        }
        return walletTransactions;
    }




}

