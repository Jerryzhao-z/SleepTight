package fr.sleeptight.ui.chart;

import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import android.support.v4.content.ContextCompat;
import android.support.v7.widget.Toolbar;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.LimitLine;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.listener.OnChartGestureListener;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.github.mikephil.charting.utils.Utils;

import java.util.ArrayList;

import fr.sleeptight.R;
import fr.sleeptight.ui.SyncPrensenter;


public class LineChartActivity extends BasicChartPage implements  OnChartValueSelectedListener {

    private LineChart mChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_line_chart);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Slide_Bar(toolbar);

        mChart = (LineChart) findViewById(R.id.chart1);
        initBarChart();
        mChart.invalidate();
        mChart.animateY(1400, Easing.EasingOption.EaseInOutQuart);
    }

    private void initBarChart() {


        //mChart.getAxisLeft().setAxisMaxValue(100f);
       // mChart.getAxisLeft().setAxisMinValue(0f);
       // mChart.getAxisLeft().setDrawGridLines(false);
        mChart.getXAxis().setDrawGridLines(false);
        mChart.setDragEnabled(true);
        mChart.setScaleEnabled(true);
        mChart.setPinchZoom(true);
        mChart.setDescription("Your Sleep Quality Evaluation");
        YAxis rightAxis = mChart.getAxisRight();
        rightAxis.setEnabled(false);

        setData(10);

    }


    private void setData(int count) {

        ArrayList<String> xVals = new ArrayList<String>();
        for (int i = count; i >= 0; i--) {
            xVals.add(this.get_Date(i+this.WEEK*count));
        }

        ArrayList<Entry> yVals = new ArrayList<Entry>();


        for (int i = 0; i <= count; i++) {

            float val = SyncPrensenter.getEvaluation(get_Date_yy(count-i+this.WEEK*count));
            yVals.add(new BarEntry(val, i));
        }


        LineDataSet set1;

        if (mChart.getData() != null &&
                mChart.getData().getDataSetCount() > 0) {
            set1 = (LineDataSet)mChart.getData().getDataSetByIndex(0);
            set1.setYVals(yVals);
            mChart.getData().setXVals(xVals);
            mChart.getData().notifyDataChanged();
            mChart.notifyDataSetChanged();
        } else {

            set1 = new LineDataSet(yVals, "DataSet 1");

            set1.setLabel("Sleep Quality Point");
            set1.setDrawCircleHole(false);
            set1.setColor(ColorTemplate.rgb("#FF5722"));
            set1.setCircleColor(ColorTemplate.rgb("#FF5722"));
            set1.setLineWidth(1.8f);

            set1.setFillAlpha(30);
            set1.setFillColor(Color.RED);
            set1.setDrawFilled(true);
            set1.setValueTextSize(12f);



            ArrayList<ILineDataSet> dataSets = new ArrayList<ILineDataSet>();
            dataSets.add(set1); // add the datasets

            // create a data object with the datasets
            LineData data = new LineData(xVals, dataSets);

            // set data
            mChart.setData(data);
        }
    }


    @Override
    public void onValueSelected(Entry e, int dataSetIndex, Highlight h) {

    }

    @Override
    public void onNothingSelected() {

    }
}
