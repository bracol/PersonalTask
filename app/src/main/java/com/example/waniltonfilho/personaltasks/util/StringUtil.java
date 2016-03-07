package com.example.waniltonfilho.personaltasks.util;

import android.app.Activity;
import android.widget.EditText;

import com.example.waniltonfilho.personaltasks.R;

/**
 * Created by Wanilton on 06/03/2016.
 */
public class StringUtil {


    // Verifica se a String é null ou vazia ou só tem espaços em branco
    public static boolean isNullOrBlank(String s, EditText editText, Activity context) {
        if (s == null || s.trim().equals("")){
            editText.setError(context.getString(R.string.error_valid_value));
            return true;
        }
        return false;
        
    }

    public static Boolean isDouble(String s, EditText editText, Activity context){
        Double d;
        try {
            d = Double.parseDouble(s);
            return true;
        } catch (NumberFormatException e) {
            //Se chegou aqui, não é um double!
            editText.setError(context.getString(R.string.error_valid_value));
            return false;
        }
    }
}
