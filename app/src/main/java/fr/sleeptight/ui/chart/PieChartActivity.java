package fr.sleeptight.ui.chart;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.SpannableString;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.Legend.LegendPosition;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.formatter.PercentFormatter;

import java.util.ArrayList;
import java.util.List;

import fr.sleeptight.R;
import fr.sleeptight.data.localdb.CommitUtils;
import fr.sleeptight.data.localdb.Sleep;
import fr.sleeptight.ui.BasicPage;
import fr.sleeptight.ui.ContextHolder;
import fr.sleeptight.ui.SyncPrensenter;

public class PieChartActivity extends BasicChartPage implements OnChartValueSelectedListener {

    private PieChart mChart;

    private Typeface tf;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_pie_chart);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Slide_Bar(toolbar);

        /*生成饼图*/
        mChart = (PieChart) findViewById(R.id.chart1);
        initChartPie();
        mChart.animateY(2000);
    }

    private void initChartPie(){
        mChart.setUsePercentValues(true);
        mChart.setDescription("");
        mChart.setExtraOffsets(5, 10, 5, 5);
        mChart.setDragDecelerationFrictionCoef(0.95f);

        tf = Typeface.createFromAsset(getAssets(), "OpenSans-Regular.ttf");
        mChart.setCenterTextTypeface(Typeface.createFromAsset(getAssets(), "OpenSans-Light.ttf"));
        // 字体兼容问题这里我暂时删去 by ZHOU

        //mChart.setCenterText(generateCenterSpannableText()); 中心文字

        //中心挖洞
        mChart.setDrawHoleEnabled(false);
        mChart.setHoleColor(Color.WHITE);

        mChart.setTransparentCircleColor(Color.WHITE);
        mChart.setTransparentCircleAlpha(110);

        mChart.setHoleRadius(58f);
        mChart.setTransparentCircleRadius(61f);

        mChart.setDrawCenterText(true);

        mChart.setRotationAngle(0);

        // enable rotation of the chart by touch
        mChart.setRotationEnabled(true);
        mChart.setHighlightPerTapEnabled(true);

        // mChart.setUnit(" €");
        // mChart.setDrawUnitsInChart(true);

        // add a selection listener
        mChart.setOnChartValueSelectedListener(this);

        //setData(3, 100);
        initData(2);

        mChart.animateY(1400, Easing.EasingOption.EaseInOutQuad);
        // mChart.spin(2000, 0, 360);

        Legend l = mChart.getLegend();
        l.setPosition(LegendPosition.ABOVE_CHART_RIGHT);
        l.setXEntrySpace(7f);
        l.setTextSize(12f);
        l.setYOffset(100f);
        l.setForm(Legend.LegendForm.SQUARE);
    }


    /* 关于数据的一切 */
    private void initData(int day) {
        ArrayList<Entry> yVals = new ArrayList<Entry>();

        float f1 = SyncPrensenter.getDataOfDay(get_Date_yy(day),SyncPrensenter.AWAKEDURATION);
        float f2 = SyncPrensenter.getDataOfDay(get_Date_yy(day),SyncPrensenter.SLEEPTIME);
        float f3 = SyncPrensenter.getDataOfDay(get_Date_yy(day),SyncPrensenter.RESTLESSDURATION);

        yVals.add(new Entry(f1, 0));
        yVals.add( new Entry(f2, 1));
        yVals.add(new Entry(f3, 2));
        PieDataSet dataSet = new PieDataSet(yVals, "");

        //饼块间距
        dataSet.setSliceSpace(5f);
        dataSet.setSelectionShift(20f);

        dataSet.setColors(initColor());

        ArrayList<String> xVals = new ArrayList<String>();
        xVals.add("Awake Duration");
        xVals.add("Deep Sleep Duration");
        xVals.add("Restless Duration");

        //dataSet.setYValuePosition(PieDataSet.ValuePosition.OUTSIDE_SLICE);

        PieData data = new PieData(xVals, dataSet);
        data.setValueFormatter(new PercentFormatter());
        data.setValueTextSize(12f);
        data.setValueTextColor(Color.WHITE);
        mChart.setData(data);

    }

    /* 关于颜色的一切 */
    private ArrayList<Integer> initColor(){

    ArrayList<Integer> colors = new ArrayList<Integer>();
          final int[] my_COLORS = {
                Color.rgb(192, 255, 140), Color.rgb(255, 247, 140), Color.rgb(140, 234, 255), Color.rgb(255, 140, 157)};
       /* for (int c : ColorTemplate.VORDIPLOM_COLORS)
                colors.add(c);*/

       /* for (int c : ColorTemplate.JOYFUL_COLORS)
                colors.add(c);*/

/*

        for (int c : ColorTemplate.LIBERTY_COLORS)
                colors.add(c);

        for (int c : my_COLORS)
                colors.add(c);*/
       // colors.add(ColorTemplate.getHoloBlue());

        for (int c : ColorTemplate.COLORFUL_COLORS)
                colors.add(c);
        return colors;
    }

    /*中心文字*/
    private SpannableString generateCenterSpannableText() {

       /* SpannableString s = new SpannableString("SleepTight\ndeveloped by Philipp Jahoda");
        s.setSpan(new RelativeSizeSpan(1.7f), 0, 10, 0);
        s.setSpan(new StyleSpan(Typeface.NORMAL), 10, s.length() - 15, 0);
        s.setSpan(new ForegroundColorSpan(Color.GRAY), 14, s.length() - 15, 0);
        s.setSpan(new RelativeSizeSpan(.8f), 14, s.length() - 15, 0);
        s.setSpan(new StyleSpan(Typeface.ITALIC), s.length() - 14, s.length(), 0);
        s.setSpan(new ForegroundColorSpan(ColorTemplate.getHoloBlue()), s.length() - 14, s.length(), 0);
        */
        SpannableString s = new SpannableString("");
        return s;
    }

    @Override
    public void onValueSelected(Entry e, int dataSetIndex, Highlight h) {

        if (e == null)
            return;
        Log.i("VAL SELECTED",
                "Value: " + e.getVal() + ", xIndex: " + e.getXIndex()
                        + ", DataSet index: " + dataSetIndex);
    }

    @Override
    public void onNothingSelected() {
        Log.i("PieChart", "nothing selected");
    }
}
