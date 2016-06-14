package fr.sleeptight.ui.chart;

import android.annotation.SuppressLint;
import android.graphics.PointF;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.formatter.YAxisValueFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import fr.sleeptight.R;
import fr.sleeptight.data.acces.APIClient.APIClass;
import fr.sleeptight.data.acces.APIClient.APIService;
import fr.sleeptight.data.acces.APIClient.ServiceGenerator;
import fr.sleeptight.ui.SyncPrensenter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BarChartActivity extends  BasicChartPage implements OnChartValueSelectedListener {

    protected BarChart mChart;
    private Typeface mTf;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bar_chart);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Slide_Bar(toolbar);

        mChart = (BarChart) findViewById(R.id.chart1);
        initBarChart();
        mChart.animateY(2000);
    }

    private void initBarChart() {
        mChart.setOnChartValueSelectedListener(this);

        mChart.setDrawBarShadow(false);
        mChart.setDrawValueAboveBar(true);

        mChart.setDescription("");

        // if more than 60 entries are displayed in the chart, no values will be
        // drawn
        mChart.setMaxVisibleValueCount(60);

        // scaling can now only be done on x- and y-axis separately
        mChart.setPinchZoom(false);

        mChart.setDrawGridBackground(false);
        // mChart.setDrawYLabels(false);

        mTf = Typeface.createFromAsset(getAssets(), "OpenSans-Regular.ttf");

        XAxis xAxis = mChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setTypeface(mTf);
        xAxis.setDrawGridLines(false);
        xAxis.setSpaceBetweenLabels(2);

        YAxisValueFormatter custom = new MyYAxisValueFormatter();

        YAxis leftAxis = mChart.getAxisLeft();
        leftAxis.setTypeface(mTf);
        leftAxis.setLabelCount(8, false);
        leftAxis.setValueFormatter(custom);
        leftAxis.setPosition(YAxis.YAxisLabelPosition.OUTSIDE_CHART);
        leftAxis.setSpaceTop(15f);
        leftAxis.setAxisMinValue(0f); // this replaces setStartAtZero(true)

        YAxis rightAxis = mChart.getAxisRight();
        rightAxis.setDrawGridLines(false);
        rightAxis.setTypeface(mTf);
        rightAxis.setLabelCount(8, false);
        rightAxis.setValueFormatter(custom);
        rightAxis.setSpaceTop(15f);
        rightAxis.setAxisMinValue(0f); // this replaces setStartAtZero(true)

        Legend l = mChart.getLegend();
        l.setPosition(Legend.LegendPosition.BELOW_CHART_LEFT);
        l.setForm(Legend.LegendForm.SQUARE);
        l.setFormSize(9f);
        l.setTextSize(11f);
        l.setXEntrySpace(4f);


        setData(7);
    }

    private void setData(int count) {

        float range =50;
        ArrayList<String> xVals = new ArrayList<String>();
        for (int i = count; i > 0; i--) {
            xVals.add(this.get_Date(i));
        }

        ArrayList<BarEntry> yVals1 = new ArrayList<BarEntry>();

/*        for (int i = 0; i < count; i++) {

            float val = SyncPrensenter.getDurationOfDay("2016-06-12");
            yVals1.add(new BarEntry(val, i));
        }*/
        float val0 = SyncPrensenter.getDurationOfDay("2016-06-14");
        float val1 = SyncPrensenter.getDurationOfDay("2016-06-13");
        float val2 = SyncPrensenter.getDurationOfDay("2016-06-12");
        float val3 = SyncPrensenter.getDurationOfDay("2016-06-11");
        float val4 = SyncPrensenter.getDurationOfDay("2016-06-10");
        float val5 = SyncPrensenter.getDurationOfDay("2016-06-09");
        float val6 = SyncPrensenter.getDurationOfDay("2016-06-08");
        float val7 = SyncPrensenter.getDurationOfDay("2016-06-07");
        float val8 = SyncPrensenter.getDurationOfDay("2016-06-06");
        float val9 = SyncPrensenter.getDurationOfDay("2016-06-05");

        BarDataSet set1;

        if (mChart.getData() != null &&
                mChart.getData().getDataSetCount() > 0) {
            set1 = (BarDataSet)mChart.getData().getDataSetByIndex(0);
            set1.setYVals(yVals1);
            mChart.getData().setXVals(xVals);
            mChart.getData().notifyDataChanged();
            mChart.notifyDataSetChanged();
        } else {
            set1 = new BarDataSet(yVals1, "DataSet");
            set1.setBarSpacePercent(35f);
            set1.setColors(ColorTemplate.MATERIAL_COLORS);

            ArrayList<IBarDataSet> dataSets = new ArrayList<IBarDataSet>();
            dataSets.add(set1);

            BarData data = new BarData(xVals, dataSets);
            data.setValueTextSize(10f);
            data.setValueTypeface(mTf);

            mChart.setData(data);
        }

    }



    @Override
    public void onValueSelected(Entry e, int dataSetIndex, Highlight h) {

        if (e == null)
            return;

        RectF bounds = mChart.getBarBounds((BarEntry) e);
        PointF position = mChart.getPosition(e, YAxis.AxisDependency.LEFT);

        Log.i("bounds", bounds.toString());
        Log.i("position", position.toString());

        Log.i("x-index",
                "low: " + mChart.getLowestVisibleXIndex() + ", high: "
                        + mChart.getHighestVisibleXIndex());
    }
    @Override
    public void onNothingSelected() {
    }

}
