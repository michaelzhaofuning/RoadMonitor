package com.sxhxjy.roadmonitor.ui.main.picture;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.sxhxjy.roadmonitor.R;
import com.sxhxjy.roadmonitor.base.BaseActivity;
import com.sxhxjy.roadmonitor.entity.RecordResult;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Random;

/**
 *
 * 把对比图的list传过来，也就是下面的pics, extra's name 是: data
 *
 *
 *
 * @author Michael Zhao
 */
public class ChartActivity extends BaseActivity {
    private LineChart lineChartX, lineChartY;

    private RecordResult.DataBean.ContentBean content;
    // This
    private List<RecordResult.DataBean.ContentBean.NewPicListBean> pics;
    // This

    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA);
    private Random random = new Random(47);

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chartp_activity);
        initToolBar("位移变化", true);

        lineChartX = (LineChart) findViewById(R.id.line_chart_x);
        lineChartY = (LineChart) findViewById(R.id.line_chart_y);

        // This
        content = (RecordResult.DataBean.ContentBean) getIntent().getSerializableExtra("data");

        if (content == null) return;

        pics = content.getNewPicList();

        ArrayList<String> xVals = new ArrayList<String>();
        ArrayList<Entry> entriesX1 = new ArrayList<Entry>();
        ArrayList<Entry> entriesX2 = new ArrayList<Entry>();
        ArrayList<Entry> entriesX3 = new ArrayList<Entry>();
        ArrayList<Entry> entriesX4 = new ArrayList<Entry>();
        ArrayList<Entry> entriesY1 = new ArrayList<Entry>();
        ArrayList<Entry> entriesY2 = new ArrayList<Entry>();
        ArrayList<Entry> entriesY3 = new ArrayList<Entry>();
        ArrayList<Entry> entriesY4 = new ArrayList<Entry>();
        int i = 0;
        Collections.reverse(pics);
        for (RecordResult.DataBean.ContentBean.NewPicListBean d : pics) {
            entriesX1.add(new Entry((float) d.getX1Change(), i));
            entriesX2.add(new Entry((float) d.getX2Change(), i));
            entriesX3.add(new Entry((float) d.getX3Change(), i));
            entriesX4.add(new Entry((float) d.getX4Change(), i));
            entriesY1.add(new Entry((float) d.getY1Change(), i));
            entriesY2.add(new Entry((float) d.getY2Change(), i));
            entriesY3.add(new Entry((float) d.getY3Change(), i));
            entriesY4.add(new Entry((float) d.getY4Change(), i));

            xVals.add(i, simpleDateFormat.format(new Date(d.getSaveTime())));

            i++;
        }

        ArrayList<ILineDataSet> dataSetsX = new ArrayList<ILineDataSet>();
        dataSetsX.add(getLineDataset(entriesX1, "x1"));
        dataSetsX.add(getLineDataset(entriesX2, "x2"));
        dataSetsX.add(getLineDataset(entriesX3, "x3"));
        dataSetsX.add(getLineDataset(entriesX4, "x4"));

        ArrayList<ILineDataSet> dataSetsY = new ArrayList<ILineDataSet>();
        dataSetsY.add(getLineDataset(entriesY1, "y1"));
        dataSetsY.add(getLineDataset(entriesY2, "y2"));
        dataSetsY.add(getLineDataset(entriesY3, "y3"));
        dataSetsY.add(getLineDataset(entriesY4, "y4"));

        setChart(lineChartX, dataSetsX, xVals);
        setChart(lineChartY, dataSetsY, xVals);
    }

    private LineDataSet getLineDataset(ArrayList<Entry> entries, String s) {
        final LineDataSet set = new LineDataSet(entries, s);
        set.setCubicIntensity(0.5f);
        set.setColor(Color.argb(255, random.nextInt(256), random.nextInt(256), random.nextInt(256)));
        set.setAxisDependency(YAxis.AxisDependency.LEFT);
        set.setDrawCubic(false);
        set.setDrawCircleHole(false);
        set.setCircleRadius(2.0f);
        return set;
    }

    public void setChart(LineChart chart, ArrayList<ILineDataSet> dataSets, ArrayList<String> xVals) {
        LineData data = new LineData(xVals, dataSets);
        chart.setData(data);

        XAxis xAxis = chart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setDrawGridLines(false);
        xAxis.setTextSize(7);
        xAxis.setLabelRotationAngle(40f);
        YAxis yAxis = chart.getAxisLeft();
        yAxis.setDrawZeroLine(true);
        yAxis.setDrawGridLines(false);
        yAxis.setLabelCount(5, false);
        chart.getAxisRight().setEnabled(false);
        chart.setDescription("");
        chart.setDragEnabled(true);
        chart.setScaleXEnabled(true);
        chart.setScaleYEnabled(true);
//        chart.setDescriptionColor(getResources().getColor(R.color.colorPrimary));

        chart.invalidate();
    }
}
