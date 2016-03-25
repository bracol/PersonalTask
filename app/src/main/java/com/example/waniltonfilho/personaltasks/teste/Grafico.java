package com.example.waniltonfilho.personaltasks.teste;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.waniltonfilho.personaltasks.model.entities.WalletTransaction;
import com.example.waniltonfilho.personaltasks.model.persistance.wallet_transaction.WalletTransactionRepository;
import com.example.waniltonfilho.personaltasks.model.service.WalletTransactionService;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wanilton.filho on 22/03/2016.
 */
public class Grafico extends AppCompatActivity {

    private List<BarEntry> entries;
    private BarDataSet dataSet;
    private ArrayList<String> labels;
    private BarChart barChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        bindChartList();
        bindChart();
        bindChartData();
    }

    private void bindChartData() {
        BarData data = new BarData(labels, dataSet);
        barChart.setData(data);
    }

    private void bindChart() {
        barChart = new BarChart(getBaseContext());
        setContentView(barChart);
        barChart.setDescription("HODOR");
    }
//    case 21308375843:
//            labels.add("Negócios");
//    break;

    private void bindChartList() {
        List<WalletTransaction> transactions = WalletTransactionRepository.getSumCategory("03");
        entries = new ArrayList<>();
        int i = 0;
        for (WalletTransaction wt : transactions) {
            entries.add(new BarEntry(wt.getPrice(), i));
            i++;
        }
        dataSet = new BarDataSet(entries, "# of Calls");
        dataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        labels = new ArrayList<String>();
        for (WalletTransaction wt : transactions) {
//            switch (wt.getItemCategory()){
//                case 2130837668:
//                    labels.add("Esporte");
//                    break;
//                case 2130837666:
//                    labels.add("Shopping");
//                    break;
//                case 2130837593:
//                    labels.add("Café");
//                    break;
//                case 2130837606:
//                    labels.add("Alimentação");
//                    break;
//                case 2130837583:
//                    labels.add("Transporte");
//                    break;
//                case 2130837587:
//                    labels.add("Carro");
//                    break;
//                case 2130837588:
//                    labels.add("Computador");
//                    break;
//                case 2130837608:
//                    labels.add("Jogos");
//                    break;
//                case 2130837589:
//                    labels.add("Celular");
//                    break;
//                case 2130837660:
//                    labels.add("Hospital");
//                    break;
//                case 2130837659:
//                    labels.add("Compras");
//                    break;
//                case 2130837672:
//                    labels.add("Viagens");
//                    break;
        }
    }
}
