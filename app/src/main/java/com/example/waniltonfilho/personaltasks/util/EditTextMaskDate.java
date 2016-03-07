package com.example.waniltonfilho.personaltasks.util;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

/**
 * Created by Wanilton on 06/03/2016.
 */
public class EditTextMaskDate implements TextWatcher {
    private String mask1 = "##/##/####";
    private boolean isUpdating;
    private EditText mEditText;
    private String old = "";

    public EditTextMaskDate(EditText editText){
        mEditText = editText;
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        String str = s.toString().replaceAll("[^0-9]*", "");
        String mask = mask1;
        String mascara = "";
        if (isUpdating) {
            old = str;
            isUpdating = false;
            return;
        }
        int i = 0;
        for (char m : mask.toCharArray()) {
            if ((m != '#' && str.length() > old.length()) || (m != '#' && str.length() < old.length() && str.length() != i)) {
                mascara += m;
                continue;
            }

            try {
                mascara += str.charAt(i);
            } catch (Exception e) {
                break;
            }
            i++;
        }
        isUpdating = true;
        mEditText.setText(mascara);
        mEditText.setSelection(mascara.length());
    }

    @Override
    public void afterTextChanged(Editable s) {

    }

    //    public static TextWatcher insert(final EditText editText) {
//        return new TextWatcher() {
//            boolean isUpdating;
//            String old = "";
//
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//
//            }
//
//
//
//            public void beforeTextChanged(CharSequence s, int start, int count,int after) {}
//            @Override
//            public void afterTextChanged(Editable s) {}
//        };
//    }

//    private static String getDefaultMask(String str) {
//        String defaultMask = mask8;
//        if (str.length() > 11){
//            defaultMask = mask11;
//        }
//        return defaultMask;
//    }

}
