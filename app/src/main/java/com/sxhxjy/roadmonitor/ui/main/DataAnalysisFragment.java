package com.sxhxjy.roadmonitor.ui.main;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.TimePicker;

import com.sxhxjy.roadmonitor.R;
import com.sxhxjy.roadmonitor.base.BaseFragment;
import com.sxhxjy.roadmonitor.base.MySubscriber;
import com.sxhxjy.roadmonitor.entity.RealTimeData;
import com.sxhxjy.roadmonitor.entity.SimpleItem;
import com.sxhxjy.roadmonitor.util.ActivityUtil;
import com.sxhxjy.roadmonitor.view.LineChartView;
import com.sxhxjy.roadmonitor.view.MyLinearLayout;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Random;

/**
 * 2016/9/26
 *
 * @author Michael Zhao
 */
public class DataAnalysisFragment extends BaseFragment {
    /**\
     * 数据分析——fragment页
     */
    private CountDownTimer mTimer;
    private TextView tv1,tv2,tv3,tv4,tv5;
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.CHINA);
    private Random random = new Random();
    private LinearLayout layout_2,layout_3,layout_4;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.data_analysis_fragment, null);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init(view);
        initToolBar(view, "数据分析", false);
        mToolbar.inflateMenu(R.menu.data_right);
        mToolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if (item.getItemId() == R.id.data_contrast) {//数据对比
                    Intent intent = new Intent(getActivity(), AddDataContrastActivity.class);
                    startActivityForResult(intent, 1000);

                } else if (item.getItemId() == R.id.data_correlation) {//数据关联
                    Intent intent = new Intent(getActivity(), AddDataCorrelationActivity.class);
                    startActivityForResult(intent, 1001);

                }
                return true;
            }
        });

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, final Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // data contrast
        if (requestCode == 1000 && resultCode == Activity.RESULT_OK) {
            if (mTimer != null)
                mTimer.cancel();
            final LineChartView lineChartView = (LineChartView) getView().findViewById(R.id.chart);
            lineChartView.getLines().clear();
            final ArrayList<SimpleItem> positionItems = (ArrayList<SimpleItem>) data.getSerializableExtra("positionItems");

            if (positionItems.size() > 1) {//多位置
                mTimer = new CountDownTimer(100000, 10000) {
                    @Override
                    public void onTick(long millisUntilFinished) {
                        lineChartView.getLines().clear();
                        String str="";
                        for (final SimpleItem item : positionItems) {
                            getMessage(getHttpService().getRealTimeData(item.getCode(), data.getLongExtra("start", 0), data.getLongExtra("end", System.currentTimeMillis())), new MySubscriber<List<RealTimeData>>() {
                                @Override
                                protected void onMyNext(List<RealTimeData> realTimeDatas) {
                                    LineChartView lineChartView = (LineChartView) getView().findViewById(R.id.chart);
                                    lineChartView.addPoints(lineChartView.convert(realTimeDatas), item.getTitle(), item.getColor());
                                }
                            });
                            if (str.equals("")){
                                str+=item.getTitle();
                            }else {
                                str=str+","+item.getTitle();
                            }
                        }
                        long start= data.getLongExtra("start", 0);
                        long end= data.getLongExtra("end", 0);
                        tv2.setText(str);
                        tv5.setText(sdf.format(new Date(start))+"---"+sdf.format(new Date(end)));

                    }

                    @Override
                    public void onFinish() {

                    }
                };
                mTimer.start();
            } else {//多时间
                final ArrayList<String> times = (ArrayList<String>) data.getSerializableExtra("times");
                mTimer = new CountDownTimer(100000, 10000) {
                    @Override
                    public void onTick(long millisUntilFinished) {
                        lineChartView.getLines().clear();
                        String str="";
                        for (String s : times) {
                            String[] strings = s.split(" ---- ");

                        for (final String s : times) {
                            String[] strings = s.split("  ----  ");
                            getMessage(getHttpService().getRealTimeData(positionItems.get(0).getCode(), sdf.parse(strings[0], new ParsePosition(0)).getTime(), sdf.parse(strings[1], new ParsePosition(0)).getTime()), new MySubscriber<List<RealTimeData>>() {
                                @Override
                                protected void onMyNext(List<RealTimeData> realTimeDatas) {
                                    LineChartView lineChartView = (LineChartView) getView().findViewById(R.id.chart);
                                    lineChartView.addPoints(lineChartView.convert(realTimeDatas), s, Color.argb(255, random.nextInt(256), random.nextInt(256), random.nextInt(256)));
                                }
                            });
                            String time=strings[0]+"---"+strings[1];
                            if (str.equals("")){
                                str+=time;
                            }else {
                                str=str+"\n"+time;
                            }
                        }
                        tv2.setText(positionItems.get(0).getTitle());
                        tv5.setText(str);
                    }
                    @Override
                    public void onFinish() {
                    }
                };
            }
            // TODO
            tv1.setText(data.getStringExtra("title"));
            layout_3.setVisibility(View.GONE);
            layout_4.setVisibility(View.GONE);
            mTimer.start();
        }
        // data correlation

        if (requestCode == 1001 && resultCode == Activity.RESULT_OK) {
            final ArrayList<SimpleItem> positionItems = (ArrayList<SimpleItem>) data.getSerializableExtra("positionItems");
            final ArrayList<SimpleItem> positionItemsCorrelation = (ArrayList<SimpleItem>) data.getSerializableExtra("positionItemsCorrelation");
            if (mTimer != null)
                mTimer.cancel();
            final LineChartView lineChartView =  (LineChartView) getView().findViewById(R.id.chart);
            mTimer = new CountDownTimer(30000, 10000) {
                @Override
                public void onTick(long millisUntilFinished) {
                    lineChartView.getLines().clear();
                    for (final SimpleItem item : positionItems) {
                        getMessage(getHttpService().getRealTimeData(item.getCode(), data.getLongExtra("start", 0), data.getLongExtra("end", System.currentTimeMillis())), new MySubscriber<List<RealTimeData>>() {
                            @Override
                            protected void onMyNext(List<RealTimeData> realTimeDatas) {
                                lineChartView.addPoints(lineChartView.convert(realTimeDatas), item.getTitle(), item.getColor());
                            }
                        });
                    }
                    for (final SimpleItem item : positionItemsCorrelation) {
                        getMessage(getHttpService().getRealTimeData(item.getCode(), data.getLongExtra("start", 0), data.getLongExtra("end", System.currentTimeMillis())), new MySubscriber<List<RealTimeData>>() {
                            @Override
                            protected void onMyNext(List<RealTimeData> realTimeDatas) {
                                lineChartView.addPoints(lineChartView.convert(realTimeDatas), item.getTitle(), item.getColor());
                            }
                        });
                    }
                }
                @Override
                public void onFinish() {
                }
            };
            layout_3.setVisibility(View.VISIBLE);
            layout_4.setVisibility(View.VISIBLE);
            long start= data.getLongExtra("start", 0);
            long end= data.getLongExtra("end", 0);
            String title1="";
            String title2="";
            tv1.setText(data.getStringExtra("title"));
            for (SimpleItem sim:positionItems){
                if (title1.equals("")){
                    title1+=sim.getTitle();
                }else {
                    title1=title1+","+sim.getTitle();
                }
            }
            tv2.setText(title1);
            tv3.setText(data.getStringExtra("titleCorrelation"));
            for (SimpleItem sim:positionItemsCorrelation){
                if (title2.equals("")){
                    title2+=sim.getTitle();
                }else {
                    title2=title2+","+sim.getTitle();
                }
            }
            tv4.setText(title2);
            tv5.setText(sdf.format(new Date(start))+"---"+sdf.format(new Date(end)));
            mTimer.start();
        }
    }
    public void init(View v){
        tv1= (TextView) v.findViewById(R.id.tv1_data);
        tv2= (TextView) v.findViewById(R.id.tv2_data);
        tv3= (TextView) v.findViewById(R.id.tv3_data);
        tv4= (TextView) v.findViewById(R.id.tv4_data);
        tv5= (TextView) v.findViewById(R.id.tv5_data);
        layout_3= (LinearLayout) v.findViewById(R.id.layout_3);
        layout_4= (LinearLayout) v.findViewById(R.id.layout_4);
    }
}
