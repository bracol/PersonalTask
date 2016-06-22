package com.example.waniltonfilho.personaltasks.model.http;

import android.util.Log;

import com.example.waniltonfilho.personaltasks.model.entities.SumWalletTransaction;
import com.example.waniltonfilho.personaltasks.model.entities.WalletTransaction;
import com.example.waniltonfilho.personaltasks.util.ConnectionUtil;
import com.example.waniltonfilho.personaltasks.util.StringUtil;
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

    private WalletTransactionHttpService() {
        super();
    }

    public static List<WalletTransaction> getWalletTransaction(String wallet_id) {
        List<WalletTransaction> listWts = new ArrayList<>();


        try {
            java.net.URL url = new URL(ConnectionUtil.URL_WALLET + "wts/" + wallet_id);
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

        return listWts;
    }


    public static List<WalletTransaction> getLastWalletTransaction(String wallet_id) {
        List<WalletTransaction> listWts = new ArrayList<>();


        try {
            java.net.URL url = new URL(ConnectionUtil.URL_WALLET + "wts/" + wallet_id);
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
        WalletTransaction lastWallet = null;

        if (listaSize > 0) {
            for (int i = 1; lastList.size() < 3 && i <= listaSize; i++) {
                if (i <= listaSize ) {
//                    if (lastWallet == null || (lastWallet.getName().equals(listWts.get(listaSize - i).getName()) && lastWallet.getPrice().equals(listWts.get(listaSize - i).getPrice()) && lastWallet.getCategory().equals(listWts.get(listaSize - i).getCategory())) == false) {
                    if (StringUtil.compareMonths(listWts.get(listaSize - i).getDate())){
                        lastList.add(listWts.get(listaSize - i));
                    }
                }
            }
        }
        return lastList;
    }


    public static void deleteWalletTransaction(WalletTransaction wt) {
        try {
            URL url = new URL(ConnectionUtil.URL_WALLET + "wts/" + wt.getId());
            final HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.setRequestMethod("DELETE");
            conn.setRequestProperty("Content-Type", "application/json");

            OutputStream os = conn.getOutputStream();
            os.write(new ObjectMapper().writeValueAsBytes(wt));
            os.flush();

            if (conn.getResponseCode() != HttpURLConnection.HTTP_OK) {
                throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
            }

            conn.disconnect();


        } catch (Exception e) {
            Log.e(WalletHttpService.class.getName(), e.getMessage());
        }
    }

    public static void postWalletTransaction(WalletTransaction walletTransaction, Integer qtMonth) {
        try {
            URL url = new URL(ConnectionUtil.URL_WALLET + "wts/" + qtMonth);
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


    public static SumWalletTransaction getSumMonthWalletTransaction(String wallet_id, String year, String month) {
        List<SumWalletTransaction> listWts = null;


        try {
            java.net.URL url = new URL(ConnectionUtil.URL_WALLET + "wts/sum/" + wallet_id + "/" + year + "/" + month);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");

            int responseCode = conn.getResponseCode();

            //Log.i("getAdressByZipCode", "Codigo de retorno de requisição de cep: " + responseCode);
            if (responseCode == HttpURLConnection.HTTP_OK) {
                InputStream inputStream = conn.getInputStream();
                ObjectMapper objectMapper = new ObjectMapper();
                CollectionType collectionType = objectMapper.getTypeFactory().constructCollectionType(List.class, SumWalletTransaction.class);
                listWts = objectMapper.readValue(inputStream, collectionType);
            }
            conn.disconnect();

        } catch (Exception e) {
            //faz aparecer mensagem no logcat do android
            Log.e(WalletHttpService.class.getName() + "-------------------", e.getMessage());
        }

        return listWts.size() > 0 ? listWts.get(0) : null;
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
