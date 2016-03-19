package com.example.waniltonfilho.personaltasks.model;

import com.example.waniltonfilho.personaltasks.model.service.WalletTransactionService;

import java.util.Arrays;
import java.util.List;

/**
 * Created by wanilton.filho on 14/03/2016.
 */
public class MonthList {
    private String initMonth;
    private int initYear;
    private String lastMonth;
    private int lastYear;
    //private List<String> mMonths = Arrays.asList("Jan", "Fev", "Mar", "Abr", "Mai", "Jun", "Jul", "Ago", "Set", "Out", "Nov", "Dez");
    private List<String> mMonthsNumber = Arrays.asList("01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12");

    public MonthList() {
        initMonth = WalletTransactionService.getFirstLastTransaction(0).getDate().substring(5, 7);
        initYear = Integer.parseInt(WalletTransactionService.getFirstLastTransaction(0).getDate().substring(0, 4));
        lastMonth = WalletTransactionService.getFirstLastTransaction(1).getDate().substring(5, 7);
        lastYear = Integer.parseInt(WalletTransactionService.getFirstLastTransaction(1).getDate().substring(0, 4));
    }

    public String swipeLeft(String current) {
        String[] date = current.split("\\/");
        String leftMonth = date[0];;
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
        } else if (leftYear > initYear){
            leftMonth = mMonthsNumber.get(11);
            leftYear--;
        }

        return leftMonth + "/" + leftYear;
    }

    public String swipeRight(String current) {
        String[] date = current.split("\\/");
        String rightMonth = date[0];
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
        } else if (rightYear < lastYear){
            rightMonth = mMonthsNumber.get(0);
            rightYear++;
        }

        return rightMonth + "/" + rightYear;
    }
}
