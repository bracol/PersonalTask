package com.example.waniltonfilho.personaltasks.model.http;

import android.util.Log;

import com.example.waniltonfilho.personaltasks.model.entities.Wallet;
import com.example.waniltonfilho.personaltasks.model.entities.WalletTransaction;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by wanilton.filho on 13/04/2016.
 */
public class WalletTransactionHttpService {
    private static final String URL = "http://10.0.3.2:3000/api/v1/wallets/";

    private WalletTransactionHttpService() {
        super();
    }

    public static WalletTransaction getWalletTransaction(String wallet_id) {
        WalletTransaction walletTransaction = new WalletTransaction();


        try {
            //url que sera buscada na conexão, podendo ser concatenada com o id da da aplicação
            java.net.URL url = new URL(URL + wallet_id);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            //método de request, podendo ser get, post, put ou delete
            conn.setRequestMethod("GET");
            //tipo de resquest e tipo de aplicação, podendo ser xml ou json
            conn.setRequestProperty("Accept", "application/json");

            //codigo corresponde a ação, 200 para sucesso, 400 para não encontrado
            int responseCode = conn.getResponseCode();

            //Log.i("getAdressByZipCode", "Codigo de retorno de requisição de cep: " + responseCode);
            if (responseCode == HttpURLConnection.HTTP_OK) {
                InputStream is = conn.getInputStream();
                ObjectMapper objectMapper = new ObjectMapper();
                walletTransaction = objectMapper.readValue(is, WalletTransaction.class);
            }
            conn.disconnect();

        } catch (Exception e) {
            //faz aparecer mensagem no logcat do android
            Log.e(WalletHttpService.class.getName() + "-------------------", e.getMessage());
        }


        return walletTransaction;
    }


    public static WalletTransaction postWalletTransaction(WalletTransaction walletTransaction) {
        try {
            URL url = new URL(URL);
            final HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            //método de request, podendo ser get, post, put ou delete
            conn.setDoOutput(true);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");

            OutputStream os = conn.getOutputStream();
            os.write(new ObjectMapper().writeValueAsBytes(walletTransaction));
            os.flush();

            if (conn.getResponseCode() != HttpURLConnection.HTTP_CREATED) {
                throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
            }

            conn.disconnect();


        } catch (Exception e) {
            Log.e(WalletHttpService.class.getName(), e.getMessage());
        }

        return getWalletTransaction(walletTransaction.getWallet_id());

    }
}
