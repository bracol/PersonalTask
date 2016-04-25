package com.example.waniltonfilho.personaltasks.model.http;

import android.util.Log;

import com.example.waniltonfilho.personaltasks.model.entities.Wallet;
import com.example.waniltonfilho.personaltasks.model.entities.WalletTransaction;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by wanilton.filho on 13/04/2016.
 */
public class WalletTransactionHttpService {
    private static final String URL = "http://10.0.3.2:3000/api/v1/wts/";

    private WalletTransactionHttpService() {
        super();
    }

    public static List<WalletTransaction> getWalletTransaction(String wallet_id) {
        List<WalletTransaction> listWts = new ArrayList<>();


        try {
            java.net.URL url = new URL(URL + wallet_id);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");

            int responseCode = conn.getResponseCode();

            if (responseCode == HttpURLConnection.HTTP_OK) {
                InputStream inputStream = conn.getInputStream();
                ObjectMapper objectMapper = new ObjectMapper();
                CollectionType collectionType = objectMapper.getTypeFactory().constructCollectionType(List.class, WalletTransaction.class);
                listWts = objectMapper.readValue(inputStream, collectionType);
            }
            conn.disconnect();

        } catch (Exception e) {
            Log.e(WalletHttpService.class.getName() + "-------------------", e.getMessage());
        }

        List<WalletTransaction> lastList = new ArrayList<>();
        int listaSize = listWts.size();

        if (listaSize > 0) {
            for (int i = 1; i <= 2; i++) {
                if (i <= listaSize)
                    lastList.add(listWts.get(listaSize - i));
            }
        }
        return lastList;
    }


    public static void postWalletTransaction(WalletTransaction walletTransaction) {
        try {
            URL url = new URL(URL);
            final HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");

            OutputStream os = conn.getOutputStream();
            os.write(new ObjectMapper().writeValueAsBytes(walletTransaction));
            os.flush();

            if (conn.getResponseCode() != HttpURLConnection.HTTP_OK) {
                throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
            }

            conn.disconnect();


        } catch (Exception e) {
            Log.e(WalletHttpService.class.getName(), e.getMessage());
        }
    }

    public static List<WalletTransaction> getSumCategoryWalletTransaction(String wallet_id) {
        List<WalletTransaction> listWts = new ArrayList<>();


        try {
            java.net.URL url = new URL(URL + wallet_id);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");

            int responseCode = conn.getResponseCode();

            if (responseCode == HttpURLConnection.HTTP_OK) {
                InputStream inputStream = conn.getInputStream();
                ObjectMapper objectMapper = new ObjectMapper();
                CollectionType collectionType = objectMapper.getTypeFactory().constructCollectionType(List.class, WalletTransaction.class);
                listWts = objectMapper.readValue(inputStream, collectionType);
            }
            conn.disconnect();

        } catch (Exception e) {
            Log.e(WalletHttpService.class.getName() + "-------------------", e.getMessage());
        }

        List<WalletTransaction> lastList = new ArrayList<>();
        int listaSize = listWts.size();

        if (listaSize > 0) {
            for (WalletTransaction w1 : listWts) {
                for(WalletTransaction w2 : lastList){

                }
            }
        }
        return lastList;
    }

//    public static List<WalletTransaction> getSumCategory(String month) {
////        SELECT dept, sum(salary) FROM employees where strftime('%m', hired_on) = '02' group by dept;;
//
//        String where = "strftime('%m', " + WalletTransactionContract.DATE + ") = ? ";
//        String[] params = {month};
//        String[] colum = {"sum(" + WalletTransactionContract.PRICE + "), " + WalletTransactionContract.CATEGORY_ID};
//        String groupBy = WalletTransactionContract.CATEGORY_ID;
//
//
//        Cursor cursor = db.query(WalletTransactionContract.TABLE, colum, where, params, groupBy, null, null);
//        List<WalletTransaction> transactions = WalletTransactionContract.getTransactionSumCategoryAll(cursor);
//
//        db.close();
//        dataBaseHelper.close();
//
//        return transactions;
//    }
}
