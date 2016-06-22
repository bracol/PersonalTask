package com.example.waniltonfilho.personaltasks.model.service;

import com.example.waniltonfilho.personaltasks.model.entities.Wallet;
import com.example.waniltonfilho.personaltasks.model.entities.WalletTransaction;
import com.example.waniltonfilho.personaltasks.model.persistance.wallet_transaction.WalletTransactionRepository;
import com.example.waniltonfilho.personaltasks.util.StringUtil;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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

    public static void save(WalletTransaction walletTransaction, int qtMes){
        Float newValue;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date actualDate = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(actualDate);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);
        int year = cal.get(Calendar.YEAR);

        for (int i = 0; i < qtMes; i++) {
            Date date = StringUtil.setDate(year, month + i, day);
            String formatedDate = sdf.format(date);
            walletTransaction.setDate(formatedDate);
            WalletTransactionRepository.save(walletTransaction);
        }


        Wallet wallet = WalletService.getWallet();
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
            for(int i = 1; lastList.size() < 3 && i <= listaSize; i++) {
                if (i <= listaSize ) {
                    if (StringUtil.compareMonths(walletTransactions.get(listaSize - i).getDate())){
                        lastList.add(walletTransactions.get(listaSize - i));
                    }
                }
            }
        }
        return lastList;
    }

    public static List<WalletTransaction> getMonthTransaction(String yearMonth){
        List<WalletTransaction> walletTransactions = WalletTransactionRepository.getByMonth(yearMonth);
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

    public static List<WalletTransaction> getSumCategoryService(String yearMonth){
        List<WalletTransaction> walletTransactions = WalletTransactionRepository.getSumCategory(yearMonth);
//        for (WalletTransaction walletTransaction : walletTransactions) {
//            walletTransaction.setCategory(CategoryRepository.getById(walletTransaction.getCategory().getId()));
//        }
        return walletTransactions;
    }




}

