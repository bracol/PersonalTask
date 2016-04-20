package com.example.waniltonfilho.personaltasks.controller.fragment;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.waniltonfilho.personaltasks.R;
import com.example.waniltonfilho.personaltasks.model.entities.WalletTransaction;
import com.example.waniltonfilho.personaltasks.model.service.WalletTransactionService;
import com.example.waniltonfilho.personaltasks.util.MyValueFormatter;
import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.LineChart;
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
 * Created by wanilton.filho on 07/03/2016.
 */
public class FragmentGraph extends Fragment {
    private PieChart mChart;
    private SpannableString mCenterText;
    // cores do grafico
    private final int[] CORES_GRAFICO = {Color.rgb(180, 0, 157), Color.rgb(0, 103, 208), Color.rgb(255, 54, 6),
            Color.rgb(255, 154, 0), Color.rgb(1, 151, 0)};

    private List<WalletTransaction> mListaDadosGrafico;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_graph, container, false);
        mListaDadosGrafico = WalletTransactionService.findAll();
        mChart = (PieChart) v.findViewById(R.id.chart);
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
        for (WalletTransaction dado : mListaDadosGrafico) {
            // volume
            try {

                yAxis.add(new Entry(dado.getPrice(), mListaDadosGrafico.indexOf(dado)));

                String data = dado.getDate();
                DateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd");
                DateFormat outputFormat = new SimpleDateFormat("dd/MM/yyyy");
                Date inputDate = inputFormat.parse(data);
                String outputDateStr = outputFormat.format(inputDate);
                xAxis.add(outputDateStr.substring(0, 5));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        PieDataSet pieDataSet = new PieDataSet(yAxis, "Meses");
        pieDataSet.setSelectionShift(12f);
        int[] colors = CORES_GRAFICO;
        pieDataSet.setColors(colors);

        PieData pieData = new PieData(xAxis, pieDataSet);
        pieData.setValueFormatter(new MyValueFormatter());
        pieData.setValueTextSize(10f);
        pieData.setValueTextColor(Color.WHITE);

        mChart.setData(pieData);
        return v;
    }


    private SpannableString generateCenterText() {
        SpannableString s = new SpannableString("");
        s.setSpan(new RelativeSizeSpan(2f), 0, 8, 0);
        s.setSpan(new ForegroundColorSpan(Color.GRAY), 8, s.length(), 0);
        return s;
    }
}
