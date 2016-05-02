package com.example.waniltonfilho.personaltasks.controller.activities;

import android.app.ProgressDialog;
import android.graphics.Color;
import android.graphics.Point;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.waniltonfilho.personaltasks.R;
import com.example.waniltonfilho.personaltasks.controller.tasks.TaskGetGroupWts;
import com.example.waniltonfilho.personaltasks.model.MonthList;
import com.example.waniltonfilho.personaltasks.model.entities.Category;
import com.example.waniltonfilho.personaltasks.model.entities.GroupCategoryTransaction;
import com.example.waniltonfilho.personaltasks.model.entities.Wallet;
import com.example.waniltonfilho.personaltasks.model.entities.WalletTransaction;
import com.example.waniltonfilho.personaltasks.model.persistance.category.CategoryRepository;
import com.example.waniltonfilho.personaltasks.model.service.WalletTransactionService;
import com.example.waniltonfilho.personaltasks.util.ListManipulation;
import com.example.waniltonfilho.personaltasks.util.MyValueFormatter;
import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by wanilton.filho on 22/03/2016.
 */
public class ChartActivity extends BaseActivity {
    private PieChart mChart;
    private SpannableString mCenterText;
    private Toolbar mToolbar;
    private TextView mMonthTitle;
    private MonthList mManipulateList;
    private RelativeLayout relativeContainer;
    private TextView mInfoTransaction;
    private List<Category> mCategories;
    private List<GroupCategoryTransaction> mGroupCategories;
    private List<WalletTransaction> mListaDadosGrafico;
    private Wallet mWallet;

    // cores do grafico
    private final int[] CORES_GRAFICO = {Color.rgb(180, 0, 157), Color.rgb(0, 103, 208), Color.rgb(255, 54, 6),
            Color.rgb(255, 154, 0), Color.rgb(1, 151, 0)};


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_graph);
        initWallet();
        bindCompontents();


    }

    private void initWallet() {
        Bundle extras = getIntent().getExtras();
        if(extras != null){
            mWallet = extras.getParcelable(MainActivity.WALLET_PARAM);
        }
    }

    private void attGraphOffline() {
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
                Category category = CategoryRepository.getById(Long.parseLong(dado.getCategory()));
                yAxis.add(new Entry(dado.getPrice(), mListaDadosGrafico.indexOf(dado)));
                xAxis.add(category.getName());
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

    private void attGraphOnline() {
        String tv = mMonthTitle.getText().toString();
        String month = tv.substring(0, 2);
        String year = tv.substring(3, 7);
        new TaskGetGroupWts(mWallet.get_id(), year, month){
            ProgressDialog dialog;

            @Override
            protected void onPreExecute() {
                dialog = new ProgressDialog(ChartActivity.this);
                dialog.setMessage("Loading...");
                dialog.show();
                super.onPreExecute();
            }

            @Override
            protected void onPostExecute(List<GroupCategoryTransaction> groupTransactions) {
                super.onPostExecute(groupTransactions);
                mGroupCategories = groupTransactions;
                attOnline();
                dialog.dismiss();
            }
        }.execute();

    }

    private void attOnline() {
        if (mGroupCategories.size() > 0) {
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
        if (mGroupCategories != null && mGroupCategories.size() > 0) {
            mChart.invalidate();
            for (GroupCategoryTransaction dado : mGroupCategories) {
                // volume
                Category category = CategoryRepository.getById(Long.parseLong(dado.get_id()));
                yAxis.add(new Entry(dado.getTotal(), mGroupCategories.indexOf(dado)));
                xAxis.add(category.getName());
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
        mCategories = Category.getCategories();
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

        verifyGraphState();
    }

    private void verifyGraphState() {
        if (mWallet == null){
            attGraphOffline();
        }else{
            attGraphOnline();
        }
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
                    Animation animation = AnimationUtils.loadAnimation(ChartActivity.this, R.anim.swipe_left);
                    relativeContainer.startAnimation(animation);
                    mMonthTitle.setText(mManipulateList.swipeLeft(mMonthTitle.getText().toString()));
                }
                if (eventX > size.x - 200) {
                    Animation animation = AnimationUtils.loadAnimation(ChartActivity.this, R.anim.swipe_right);
                    relativeContainer.startAnimation(animation);
                    mMonthTitle.setText(mManipulateList.swipeRight(mMonthTitle.getText().toString()));
                }
                verifyGraphState();
                return false;
            }
        });
    }
}
