package com.example.waniltonfilho.personaltasks.model.http;

import android.util.Log;

import com.example.waniltonfilho.personaltasks.model.entities.GroupCategoryTransaction;
import com.example.waniltonfilho.personaltasks.model.entities.WalletTransaction;
import com.example.waniltonfilho.personaltasks.util.ConnectionUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by wanilton.filho on 26/04/2016.
 */
public class GroupCategoryTransactionHttpService {

    private static String URL = ConnectionUtil.URL_WALLET;

    public static List<GroupCategoryTransaction> getGroupCategories(String wallet_id, String year, String month) {
        List<GroupCategoryTransaction> listGroupWts = new ArrayList<>();


        try {
            java.net.URL url = new URL(URL + "wts/group/" + wallet_id + "/" + year + "/" + month);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");

            int responseCode = conn.getResponseCode();

            if (responseCode == HttpURLConnection.HTTP_OK) {
                InputStream inputStream = conn.getInputStream();
                ObjectMapper objectMapper = new ObjectMapper();
                CollectionType collectionType = objectMapper.getTypeFactory().constructCollectionType(List.class, GroupCategoryTransaction.class);
                listGroupWts = objectMapper.readValue(inputStream, collectionType);
            }
            conn.disconnect();

        } catch (Exception e) {
            Log.e(WalletHttpService.class.getName() + "-------------------", e.getMessage());
        }

        return listGroupWts;
    }
}
