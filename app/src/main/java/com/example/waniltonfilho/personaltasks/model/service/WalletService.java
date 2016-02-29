package com.example.waniltonfilho.personaltasks.model.service;

import com.example.waniltonfilho.personaltasks.model.entities.Wallet;
import com.example.waniltonfilho.personaltasks.model.persistance.wallet_transaction.WalletRepository;

import java.util.List;

/**
 * Created by Wanilton on 28/02/2016.
 */
public class WalletService {
    private WalletService() {
        super();
    }

    public static List<Wallet> findAll() {
        return WalletRepository.getAll();
    }

    public static void save(Wallet wallet) {
        WalletRepository.save(wallet);
    }

    public static void update(Double value){
        WalletRepository.update(value);
    }

    public static void delete(Wallet selectedWallet) {
        WalletRepository.delete(selectedWallet.get_id());
    }

}
