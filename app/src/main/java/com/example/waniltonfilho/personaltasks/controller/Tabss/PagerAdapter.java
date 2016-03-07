package com.example.waniltonfilho.personaltasks.controller.Tabss;

import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.waniltonfilho.personaltasks.controller.fragment.FragmentGraph;
import com.example.waniltonfilho.personaltasks.controller.fragment.ListTransactionFragment;
import com.example.waniltonfilho.personaltasks.controller.fragment.WalletFragment;
import com.example.waniltonfilho.personaltasks.model.entities.WalletTransaction;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;

import java.util.ArrayList;
import java.util.List;

public class PagerAdapter extends FragmentStatePagerAdapter {
    int mNumOfTabs;
    // cores do grafico
    private final int[] CORES_GRAFICO = { Color.rgb(180, 0, 157), Color.rgb(0, 103, 208), Color.rgb(255, 54, 6),
            Color.rgb(255, 154, 0), Color.rgb(1, 151, 0) };

    private PieChart mPieChart;
    private List<WalletTransaction> mListaDadosGrafico;

    public PagerAdapter(FragmentManager fm, int NumOfTabs) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                WalletFragment tab1 = new WalletFragment();
                return tab1;
            case 1:
                ListTransactionFragment tab2 = new ListTransactionFragment();
                return tab2;
            case 2:
                FragmentGraph tab3 = new FragmentGraph();
                return tab3;
            default:
                return null;
        }
    }

//    private void preencherConteudo() {
//
//        if (mListaDadosGrafico != null) {
//
//            // referencia
//            List<String> xAxis = new ArrayList<>();
//
//            // volume
//            List<Entry> yAxis = new ArrayList<>();
//
//            // popular eixos do grafico
//            for (WalletTransaction dado : mListaDadosGrafico) {
//                // volume
//                yAxis.add(new Entry(Float.parseFloat(dado.getPrice()), mListaDadosGrafico.indexOf(dado)));
//                // referencia
//                xAxis.add(dado.getChave());
//            }
//
//            PieDataSet pieDataSet = new PieDataSet(yAxis, "Volume Transportado");
//            pieDataSet.setSelectionShift(8f);
//            int[] colors = CORES_SFB;
//            pieDataSet.setColors(colors);
//
//            PieData pieData = new PieData(xAxis, pieDataSet);
//            // pieData.setValueFormatter(new PercentFormatter());
//            pieData.setValueTextSize(10f);
//            pieData.setValueTextColor(Color.WHITE);
//            mPieChart.setData(pieData);
//            mPieChart.invalidate();
//        }
//    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}