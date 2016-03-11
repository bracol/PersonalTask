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
import com.example.waniltonfilho.personaltasks.model.entities.Wallet;
import com.example.waniltonfilho.personaltasks.model.entities.WalletTransaction;
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
        return WalletTransactionRepository.getAll();
    }

    public static void save(WalletTransaction walletTransaction, int operation){
        Float newValue;
        WalletTransactionRepository.save(walletTransaction);
        Wallet wallet = WalletRepository.getWallet();
        Float actualValue = wallet.getValue();

        if(operation == 0){
            newValue = actualValue - walletTransaction.getPrice();
        } else{
            newValue = actualValue + walletTransaction.getPrice();
        }
        WalletService.update(newValue);
    }

    public static void delete(WalletTransaction selectedWalletTransaction){
        WalletTransactionRepository.delete(selectedWalletTransaction.getId());
    }

    public static List<WalletTransaction> getLastTransactions(int cont){
        List<WalletTransaction> allList = WalletTransactionService.findAll();
        List<WalletTransaction> lastList = new ArrayList<>();
        int listaSize = allList.size();

        if(listaSize >= cont && listaSize > 0){
            for(int i = listaSize; i >= (listaSize - cont); i--){
                lastList.add(allList.get(i - 1));
            }
        }

        return lastList;
    }


}

