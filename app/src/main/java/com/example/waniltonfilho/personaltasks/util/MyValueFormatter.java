package com.example.waniltonfilho.personaltasks.util;

import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.utils.ViewPortHandler;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;

/**
 * Created by wanilton.filho on 08/03/2016.
 */
public class MyValueFormatter implements ValueFormatter {

    private DecimalFormat mFormat;


    public MyValueFormatter() {
        // use one decimal
        mFormat = (DecimalFormat) NumberFormat.getInstance(new Locale("pt", "BR"));
        mFormat.setMaximumFractionDigits(2);
        mFormat.setMinimumFractionDigits(2);
    }

    @Override
    public String getFormattedValue(float value, Entry entry, int dataSetIndex, ViewPortHandler viewPortHandler) {
        // write your logic here
        return "R$ " + mFormat.format(value); // e.g. append a dollar-sign
    }

    public String getMaskFormatted(float value){
        return "R$ " + mFormat.format(value);
    }

    public String getMaskNumbers(float value){
        return mFormat.format(value);
    }

    public static String formatPrice(String value){
        String formated;
        formated = value.replace("R$ ", "");
        formated = formated.replace(".", "");
        formated = formated.replace(",", ".");
        return formated;
    }


}
