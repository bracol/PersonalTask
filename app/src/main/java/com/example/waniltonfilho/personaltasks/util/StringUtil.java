package com.example.waniltonfilho.personaltasks.util;

import android.app.Activity;
import android.widget.EditText;

import com.example.waniltonfilho.personaltasks.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

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
            String cleanString = s.toString().replaceAll("[R$,.]", "");
            d = Double.parseDouble(cleanString);
            return true;
        } catch (NumberFormatException e) {
            //Se chegou aqui, não é um double!
            editText.setError(context.getString(R.string.error_valid_value));
            return false;
        }
    }

    public static String[] getActualMonthYear() {
        String[] actualDate;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        String dateFormated = simpleDateFormat.format(date);
        String mes = dateFormated.substring(5, 7);
        String ano = dateFormated.substring(0, 4);
        actualDate = new String[]{ano, mes};

        return actualDate;
    }

    public static boolean compareMonths(String date1) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date2 = new Date();
        String date2Formated = simpleDateFormat.format(date2);
        String year1 = date1.substring(0, 4);
        String year2 = date2Formated.substring(0, 4);
        String mes1 = date1.substring(5, 7);
        String mes2 = date2Formated.substring(5, 7);
//        String ano = dateFormated.substring(0, 4);
//        String dia = dateFormated.substring(8, 10);
//        String data2 = ano + "-" + mes + "-" +  dia;


        return mes1.equals(mes2) && year1.equals(year2);
    }

    public static Date setDate(int year, int month, int day) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, month);
        cal.set(Calendar.DAY_OF_MONTH, day);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }
}
