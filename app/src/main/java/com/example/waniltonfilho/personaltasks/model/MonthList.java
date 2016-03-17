package com.example.waniltonfilho.personaltasks.model;

import java.util.Arrays;
import java.util.List;

/**
 * Created by wanilton.filho on 14/03/2016.
 */
public class MonthList {
    private String initMonth;
    private int initYear;
    //private List<String> mMonths = Arrays.asList("Jan", "Fev", "Mar", "Abr", "Mai", "Jun", "Jul", "Ago", "Set", "Out", "Nov", "Dez");
    private List<String> mMonthsNumber = Arrays.asList("01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12");

    public MonthList(String month, int year) {
        initMonth = month;
        initYear = year;
    }

    public String swipeLeft(String current) {
        String[] date = current.split("\\/");
        String leftMonth;
        int leftYear = Integer.parseInt(date[1]);
        int i = 0;
        for (String month : mMonthsNumber) {
            if (date[0].equals(month)) {
                break;
            }
            i++;
        }
        if (i > 0) {
            leftMonth = mMonthsNumber.get(i - 1);
        } else {
            leftMonth = mMonthsNumber.get(11);
            leftYear--;
        }
        return leftMonth + "/" + leftYear;
    }

    public String swipeRight(String current) {
        String[] date = current.split("\\/");
        String rightMonth;
        int rightYear = Integer.parseInt(date[1]);
        int i = 0;
        for (String month : mMonthsNumber) {

            if (date[0].equals(month)) {
                break;
            }
            i++;
        }
        if (i < 11) {
            rightMonth = mMonthsNumber.get(i + 1);
        } else {
            rightMonth = mMonthsNumber.get(0);
            rightYear++;
        }
        return rightMonth + "/" + rightYear;
    }
}
