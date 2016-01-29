package com.example.waniltonfilho.personaltasks.model.service;

import com.example.waniltonfilho.personaltasks.model.entities.WalletTransaction;
import com.example.waniltonfilho.personaltasks.model.persistance.wallet_transaction.WalletTransactionRepository;

import java.util.List;

/**
 * Created by wanilton.filho on 29/01/2016.
 */
public class WalletTransactionService {

    private WalletTransactionService(){super();}

    public static List<WalletTransaction> findAll(){
        return WalletTransactionRepository.getAll();
    }

    public static void save(WalletTransaction walletTransaction){
        WalletTransactionRepository.save(walletTransaction);
    }

    public static void delete(WalletTransaction selectedWalletTransaction){
        WalletTransactionRepository.delete(selectedWalletTransaction.getId());
    }

}

