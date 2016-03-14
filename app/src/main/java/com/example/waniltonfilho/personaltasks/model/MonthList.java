package com.example.waniltonfilho.personaltasks.model;

import java.util.Arrays;
import java.util.List;

/**
 * Created by wanilton.filho on 14/03/2016.
 */
public class MonthList {
    private int initMonth;
    private int initYear;
    private List<String> mMonths = Arrays.asList("Jan", "Fev", "Mar", "Abr", "Mai", "Jun", "Jul", "Ago", "Set", "Out", "Nov", "Dez");
    private int year;

    public MonthList(int month, int year) {
        initMonth = month;
        initYear = year;
    }

    public String swipeLeft(String current) {
        String[] date = current.split("\\/");
        String leftMonth;
        int leftYear = Integer.parseInt(date[1]);
        int i = 0;
        for (String month : mMonths) {

            if (date[0].equals(month)) {
                break;
            }
            i++;
        }
        if (i > 0) {
            leftMonth = mMonths.get(i - 1);
        } else {
            leftMonth = mMonths.get(11);
            leftYear--;
        }
        return leftMonth + "/" + leftYear;
    }

    public String swipeRight(String current) {
        String[] date = current.split("\\/");
        String rightMonth;
        int rightYear = Integer.parseInt(date[1]);
        int i = 0;
        for (String month : mMonths) {

            if (date[0].equals(month)) {
                break;
            }
            i++;
        }
        if (i < 11) {
            rightMonth = mMonths.get(i + 1);
        } else {
            rightMonth = mMonths.get(0);
            rightYear++;
        }
        return rightMonth + "/" + rightYear;
    }

    public String getMonthFromPosition(int position){
        return mMonths.get(position);
    }

}
