package com.example.waniltonfilho.personaltasks.controller.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.waniltonfilho.personaltasks.R;
import com.github.mikephil.charting.charts.LineChart;

/**
 * Created by wanilton.filho on 07/03/2016.
 */
public class FragmentGraph extends Fragment {
    private LineChart mChart;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_graph, container, false);
        mChart = (LineChart) v.findViewById(R.id.chart);
        mChart.setBackgroundColor(getResources().getColor(R.color.blue));
        mChart.setGridBackgroundColor(getResources().getColor(R.color.positive));
        mChart.setDrawBorders(true);
        mChart.setBorderColor(getResources().getColor(R.color.negative));
        mChart.setBorderWidth(20);
        return v;
    }
}
