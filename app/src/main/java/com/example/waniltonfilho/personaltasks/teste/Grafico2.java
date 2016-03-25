package com.example.waniltonfilho.personaltasks.teste;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.waniltonfilho.personaltasks.R;
import com.example.waniltonfilho.personaltasks.model.entities.WalletTransaction;
import com.example.waniltonfilho.personaltasks.model.persistance.wallet_transaction.WalletTransactionRepository;
import com.example.waniltonfilho.personaltasks.model.service.WalletTransactionService;
import com.example.waniltonfilho.personaltasks.util.MyValueFormatter;
import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by wanilton.filho on 22/03/2016.
 */
public class Grafico2 extends AppCompatActivity{
    private PieChart mChart;
    private SpannableString mCenterText;
    // cores do grafico
    private final int[] CORES_GRAFICO = {Color.rgb(180, 0, 157), Color.rgb(0, 103, 208), Color.rgb(255, 54, 6),
            Color.rgb(255, 154, 0), Color.rgb(1, 151, 0)};

    private List<WalletTransaction> mListaDadosGrafico;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_graph);
        mListaDadosGrafico = WalletTransactionRepository.getSumCategory("03");
        mChart = (PieChart) findViewById(R.id.chart);
        mChart.setDescription("");
        mCenterText = generateCenterText();


        // radius of the center hole in percent of maximum radius
        mChart.setHoleRadius(52f);
        mChart.setTransparentCircleRadius(57f);
        mChart.setCenterText(mCenterText);
        mChart.setCenterTextSize(9f);
        mChart.setUsePercentValues(true);
        //mChart.setExtraOffsets(5, 10, 50, 10);

        // do not forget to refresh the chart
        // mChart.invalidate();

        Legend l = mChart.getLegend();
        l.setPosition(Legend.LegendPosition.RIGHT_OF_CHART);
        l.setYEntrySpace(0f);
        l.setYOffset(0f);

        // referencia
        List<String> xAxis = new ArrayList<>();

        // volume
        List<Entry> yAxis = new ArrayList<>();

        MyValueFormatter myValueFormatter = new MyValueFormatter();

        // popular eixos do grafico
//        for (WalletTransaction dado : mListaDadosGrafico) {
            // volume

//            yAxis.add(new Entry(dado.getPrice(), mListaDadosGrafico.indexOf(dado)));
//            Integer item = dado.get();
//            switch (item){
//                case 2130837668:
//                    xAxis.add("Esporte");
//                    break;
//                case 2130837666:
//                    xAxis.add("Shopping");
//                    break;
//                case 2130837593:
//                    xAxis.add("Café");
//                    break;
//                case 2130837606:
//                    xAxis.add("Alimentação");
//                    break;
//                case 2130837583:
//                    xAxis.add("Transporte");
//                    break;
//                case 2130837587:
//                    xAxis.add("Carro");
//                    break;
//                case 2130837588:
//                    xAxis.add("Computador");
//                    break;
//                case 2130837608:
//                    xAxis.add("Jogos");
//                    break;
//                case 2130837589:
//                    xAxis.add("Celular");
//                    break;
//                case 2130837660:
//                    xAxis.add("Hospital");
//                    break;
//                case 2130837659:
//                    xAxis.add("Compras");
//                    break;
//                case 2130837672:
//                    xAxis.add("Viagens");
//                    break;
//                default:
//                    xAxis.add("Negocios");
//                    break;
//            }
//        }
//
//        PieDataSet pieDataSet = new PieDataSet(yAxis, "Meses");
//        pieDataSet.setSelectionShift(12f);
//        int[] colors = CORES_GRAFICO;
//        pieDataSet.setColors(colors);
//
//        PieData pieData = new PieData(xAxis, pieDataSet);
//        pieData.setValueFormatter(new MyValueFormatter());
//        pieData.setValueTextSize(10f);
//        pieData.setValueTextColor(Color.WHITE);
//
//        mChart.setData(pieData);
    }

    private void preencherConteudo() {
        mListaDadosGrafico = WalletTransactionService.findAll();
        if (mListaDadosGrafico != null) {

            // referencia
            List<String> xAxis = new ArrayList<>();

            // volume
            List<Entry> yAxis = new ArrayList<>();

            MyValueFormatter myValueFormatter = new MyValueFormatter();
            // popular eixos do grafico
            for (WalletTransaction dado : mListaDadosGrafico) {
                // volume
                yAxis.add(new Entry(Float.parseFloat(""), mListaDadosGrafico.indexOf(dado)));
                // referencia
                xAxis.add("R$ " + String.valueOf(dado.getPrice()));
            }

            PieDataSet pieDataSet = new PieDataSet(yAxis, "Meses");
            pieDataSet.setSelectionShift(8f);
            int[] colors = CORES_GRAFICO;
            pieDataSet.setColors(colors);

            PieData pieData = new PieData(xAxis, pieDataSet);
            // pieData.setValueFormatter(new PercentFormatter());
            pieData.setValueTextSize(10f);
            pieData.setValueTextColor(Color.WHITE);
            mChart.setData(pieData);
            mChart.invalidate();
        }
    }

    private void preencherEstilo() {
        // necessario para remover o valor default da descricao do grafico
        mChart.setDescription("");
        // remover a legenda dos segmentos do grafico
        mChart.getLegend().setEnabled(false);
        // seta esta classe como listener dos eventos de toque
        // configura o tipo e a velocidade da animacao de apresentacao do
        // grafico
        mChart.animateXY(1000, 1000, Easing.EasingOption.Linear, Easing.EasingOption.Linear);
        // apresenta um texto no lugar do grafico quando nao houverem dados para
        // apresentacao
        // configuracoes gerais de estilo
        mChart.setCenterTextColor(Color.TRANSPARENT);
        mChart.setBackgroundColor(Color.TRANSPARENT);
        mChart.setHoleColorTransparent(true);
        mChart.setHoleRadius(0F);
        mChart.setTransparentCircleRadius(0F);

    }

    private SpannableString generateCenterText() {
        SpannableString s = new SpannableString("Revenues\nQuarters 2015");
        s.setSpan(new RelativeSizeSpan(2f), 0, 8, 0);
        s.setSpan(new ForegroundColorSpan(Color.GRAY), 8, s.length(), 0);
        return s;
    }
}
