package com.example.waniltonfilho.personaltasks.model.http;

import android.util.Log;

import com.example.waniltonfilho.personaltasks.model.entities.User;
import com.example.waniltonfilho.personaltasks.util.ConnectionUtil;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Wanilton on 03/04/2016.
 */
public class LoginHttpService {
    public static String URL = ConnectionUtil.URL_WALLET + "users";

    private LoginHttpService() {
        super();
    }

    public static User getLogin(User user) {
        HttpURLConnection conn = LoginAuthenticationService.getAuthentication(user.getUserName(), user.getPassword());
        User authenticatedUser = null;
        try{
            //java.net.URL url = new URL(URL);
            //final HttpURLConnection con = (HttpURLConnection) url.openConnection();
            //conn.setRequestMethod("GET");
            //conn.setRequestProperty("Accept", "application/json");

            int responseCode = conn.getResponseCode();

            if(responseCode == HttpURLConnection.HTTP_OK){
                InputStream is = conn.getInputStream();
                ObjectMapper objectMapper = new ObjectMapper();
                authenticatedUser = objectMapper.readValue(is, User.class);
            }
            conn.disconnect();
        }catch (Exception e){
            Log.e(LoginHttpService.class.getName(), e.getMessage());
        }
        return authenticatedUser;
    }


    public static void postLogin(User user) {
        try {
            //url que sera buscada na conexão, podendo ser concatenada com o id da da aplicação
            URL url = new URL(URL);
            final HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");

            OutputStream os = conn.getOutputStream();
            os.write(new ObjectMapper().writeValueAsBytes(user));
            os.flush();

            if (conn.getResponseCode() != HttpURLConnection.HTTP_OK) {
                throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
            }

            conn.disconnect();
        } catch (Exception e) {
            Log.e("ERROR: ", e.getMessage());
        }
    }
}
