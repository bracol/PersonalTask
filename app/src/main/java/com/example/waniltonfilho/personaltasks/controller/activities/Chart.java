package com.example.waniltonfilho.personaltasks.controller.activities;

import android.graphics.Color;
import android.graphics.Point;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.waniltonfilho.personaltasks.R;
import com.example.waniltonfilho.personaltasks.controller.adapter.WalletTransactionAdapter;
import com.example.waniltonfilho.personaltasks.model.MonthList;
import com.example.waniltonfilho.personaltasks.model.entities.Category;
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

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * Created by wanilton.filho on 22/03/2016.
 */
public class Chart extends BaseActivity {
    private PieChart mChart;
    private SpannableString mCenterText;
    private Toolbar mToolbar;
    private TextView mMonthTitle;
    private MonthList mManipulateList;
    private RelativeLayout relativeContainer;
    private TextView mInfoTransaction;
    private List<Category> mCategories;

    // cores do grafico
    private final int[] CORES_GRAFICO = {Color.rgb(180, 0, 157), Color.rgb(0, 103, 208), Color.rgb(255, 54, 6),
            Color.rgb(255, 154, 0), Color.rgb(1, 151, 0)};

    private List<WalletTransaction> mListaDadosGrafico;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_graph);
        bindCompontents();


    }

    private void attGraph() {
        String tv = mMonthTitle.getText().toString();
        String month = tv.substring(0, 2);
        mListaDadosGrafico = WalletTransactionService.getSumCategoryService(month);
        if (mListaDadosGrafico.size() > 0) {
            mInfoTransaction.setVisibility(View.INVISIBLE);
            mChart.setVisibility(View.VISIBLE);
        } else {
            mChart.setVisibility(View.INVISIBLE);
            mInfoTransaction.setVisibility(View.VISIBLE);
        }

        // referencia
        List<String> xAxis = new ArrayList<>();

        // volume
        List<Entry> yAxis = new ArrayList<>();

        MyValueFormatter myValueFormatter = new MyValueFormatter();

        // popular eixos do grafico
        if (mListaDadosGrafico != null && mListaDadosGrafico.size() > 0) {
            mChart.invalidate();
            for (WalletTransaction dado : mListaDadosGrafico) {
                // volume

                yAxis.add(new Entry(dado.getPrice(), mListaDadosGrafico.indexOf(dado)));
                xAxis.add(dado.getCategory().getName());
            }
        }
        PieDataSet pieDataSet = new PieDataSet(yAxis, "");
        pieDataSet.setSelectionShift(12f);
        int[] colors = CORES_GRAFICO;
        pieDataSet.setColors(colors);

        PieData pieData = new PieData(xAxis, pieDataSet);
        pieData.setValueFormatter(new MyValueFormatter());
        pieData.setValueTextSize(10f);
        pieData.setValueTextColor(Color.WHITE);

        mChart.setData(pieData);
    }

    private void bindCompontents() {
        mCategories = getCategories();
        bindToolbar();
        bindRelativeContainer();
        bindTextViewInfoMonth();
        bindTextViewMonth();
        bindGraph();

    }

    private void bindTextViewInfoMonth() {
        mInfoTransaction = (TextView) findViewById(R.id.tvNothingToShow);
    }

    private void bindRelativeContainer() {
        relativeContainer = (RelativeLayout) findViewById(R.id.listContainer);
    }

    private void bindGraph() {
        mListaDadosGrafico = WalletTransactionService.getSumCategoryService(mMonthTitle.getText().toString());
        mChart = (PieChart) findViewById(R.id.chart);
        mChart.setDescription("");
        mCenterText = generateCenterText();


        // radius of the center hole in percent of maximum radius
        mChart.setHoleRadius(52f);
        mChart.setTransparentCircleRadius(57f);
        mChart.setCenterText(mCenterText);
        mChart.setCenterTextSize(9f);
        //mChart.setExtraOffsets(5, 10, 50, 10);

        // do not forget to refresh the chart
        Legend legend = mChart.getLegend();
        legend.setEnabled(false);

        attGraph();
    }

    private void bindToolbar() {
        mToolbar = (Toolbar) findViewById(R.id.app_bar);
        setupToolbar(mToolbar);
        mToolbar.setTitle("Gráfico de Transações");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private SpannableString generateCenterText() {
        SpannableString s = new SpannableString("Revenues\nQuarters 2015");
        s.setSpan(new RelativeSizeSpan(2f), 0, 8, 0);
        s.setSpan(new ForegroundColorSpan(Color.GRAY), 8, s.length(), 0);
        return s;
    }

    private void bindTextViewMonth() {
        mMonthTitle = (TextView) findViewById(R.id.tvListMonth);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        String actualDate = simpleDateFormat.format(date);
        String mes = actualDate.substring(5, 7);
        int ano = Integer.parseInt(actualDate.substring(0, 4));
        mManipulateList = new MonthList();
        mMonthTitle.setText(mes + "/" + ano);

        mMonthTitle.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Point size = new Point();
                getWindowManager().getDefaultDisplay().getSize(size);
                float eventX = event.getX();
                if (eventX < 200) {
                    Animation animation = AnimationUtils.loadAnimation(Chart.this, R.anim.swipe_left);
                    relativeContainer.startAnimation(animation);
                    mMonthTitle.setText(mManipulateList.swipeLeft(mMonthTitle.getText().toString()));
                }
                if (eventX > size.x - 200) {
                    Animation animation = AnimationUtils.loadAnimation(Chart.this, R.anim.swipe_right);
                    relativeContainer.startAnimation(animation);
                    mMonthTitle.setText(mManipulateList.swipeRight(mMonthTitle.getText().toString()));
                }
                attGraph();
                return false;
            }
        });
    }
}
