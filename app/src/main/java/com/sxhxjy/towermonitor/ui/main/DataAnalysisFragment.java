package com.sxhxjy.towermonitor.ui.main;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.sxhxjy.towermonitor.R;
import com.sxhxjy.towermonitor.adapter.PopAdapter;
import com.sxhxjy.towermonitor.base.BaseActivity;
import com.sxhxjy.towermonitor.base.BaseFragment;
import com.sxhxjy.towermonitor.base.MyApplication;
import com.sxhxjy.towermonitor.base.MySubscriber;
import com.sxhxjy.towermonitor.entity.ComplexData;
import com.sxhxjy.towermonitor.entity.RealTimeData;
import com.sxhxjy.towermonitor.entity.SimpleItem;
import com.sxhxjy.towermonitor.view.LineChartView;
import com.sxhxjy.towermonitor.view.MyForm;
import com.sxhxjy.towermonitor.view.MyPopup;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
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
    private MyForm form_1,form_2,form_3,form_4,form_5;
    private ImageView form_option;
    private LinearLayout layout_correlation,layout_option;
    private MyPopup myPopupWindow;//弹出窗口
    private boolean option=true;


    private CountDownTimer mTimer;
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.CHINA);
    private Random random = new Random();
    private LinearLayout mChartsContainer;
    private ProgressDialog progressDialog;
    private TextView mTextViewCenter;
    private String stationId;





    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.data_analysis_fragment, null);
    }

    @Override
    protected void loadOnce() {
        super.loadOnce();
        CharSequence[] aType = {"数据对比", "数据关联"};
        new AlertDialog.Builder(getActivity()).setTitle("请选择分析条件").setSingleChoiceItems(aType, 0, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (which == 0) {//数据对比
                    Intent intent = new Intent(getActivity(), AddDataContrastActivity.class);
                    intent.putExtra("stationId", stationId);
                    startActivityForResult(intent, 1000);

                } else if (which == 1) {
                    Intent intent = new Intent(getActivity(), AddDataCorrelationActivity.class);
                    intent.putExtra("stationId", stationId);
                    startActivityForResult(intent, 1001);

                }
                dialog.dismiss();
            }
        }).create().show();
    }
    public List<Map<String,String>> getlist(){
        String [] str={"数据比对","数据关联"};
        int [] img={R.drawable.analysis1,R.drawable.analysis2};
        List<Map<String,String>> list=new ArrayList<>();
        for (int i=0;i<str.length;i++){
            Map<String,String> map=new HashMap<>();
            map.put("title",str[i]);
            map.put("img",img[i]+"");
            list.add(map);
        }
        return list;
    }
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init(view);
        initToolBar(view, MyApplication.getMyApplication().getSharedPreference().getString("stationName", ""), false);

        layout_option.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (option){
                    layout_correlation.setVisibility(View.GONE);
                    form_option.setImageResource(R.mipmap.expand2);
                    option=false;
                }else {
                    layout_correlation.setVisibility(View.VISIBLE);
                    form_option.setImageResource(R.mipmap.expand);
                    option=true;
                }
            }
        });

        mToolbar.inflateMenu(R.menu.filter_right);//添加菜单menu
        mToolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                myPopupWindow.show();
                return true;
            }
        });


        myPopupWindow = new MyPopup((BaseActivity) getActivity(), R.layout.popgrid);//设置弹出窗口
        ListView gv= (ListView) myPopupWindow.getContentView().findViewById(R.id.pop_gv);
        List<Map<String,String>> list=getlist();
        PopAdapter adapter=new PopAdapter(getActivity(),list);
        gv.setAdapter(adapter);
        gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0:
                        Intent intent = new Intent(getActivity(), AddDataContrastActivity.class);
                        intent.putExtra("stationId", stationId);
                        startActivityForResult(intent, 1000);
                        myPopupWindow.dismiss();
                        break;
                    case 1:
                        Intent intentq = new Intent(getActivity(), AddDataCorrelationActivity.class);
                        intentq.putExtra("stationId", stationId);
                        startActivityForResult(intentq, 1001);
                        myPopupWindow.dismiss();
                        break;
                }
            }
        });
        mTextViewCenter = (TextView) getView().findViewById(R.id.toolbar_title);
        mTextViewCenter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), StationListActivity.class);
                startActivityForResult(intent, StationListActivity.REQUEST_CODE);
            }
        });
        mTextViewCenter.setCompoundDrawablesWithIntrinsicBounds(null, null, getResources().getDrawable(R.mipmap.expand), null);
        mTextViewCenter.setCompoundDrawablePadding(20);
        stationId = MyApplication.getMyApplication().getSharedPreference().getString("stationId", "");
        mChartsContainer = (LinearLayout) getView().findViewById(R.id.charts_container);
        //设置图标layout中的表格影藏
        mChartsContainer.getChildAt(0).findViewById(R.id.param_info).setVisibility(View.GONE);
        LineChartView lineChartView = (LineChartView) mChartsContainer.getChildAt(0).findViewById(R.id.chart);
        lineChartView.emptyHint = "请选择分析条件";
        lineChartView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myPopupWindow.show();
            }
        });
    }
    //LineChartView
    private void addToChart(List<RealTimeData> realTimeDatas, SimpleItem simpleItem, boolean isRight) {
        form_option.setColorFilter(getResources().getColor(R.color.default_text_color));
        if (mChartsContainer.getChildAt(0) == null)
            getActivity().getLayoutInflater().inflate(R.layout.chart_layout, mChartsContainer);
        LineChartView lineChartView0 = (LineChartView) mChartsContainer.getChildAt(0).findViewById(R.id.chart);
        mChartsContainer.getChildAt(0).findViewById(R.id.param_info).setVisibility(View.GONE);
        lineChartView0.addPoints(lineChartView0.convert(realTimeDatas, isRight), simpleItem.getTitle(), simpleItem.getColor(), isRight);
        ((TextView)mChartsContainer.getChildAt(0).findViewById(R.id.indicator)).setText("x方向趋势图");
        ((TextView)mChartsContainer.getChildAt(0).findViewById(R.id.indicator)).setVisibility(View.VISIBLE);


        if (realTimeDatas.get(0).getTypeCode() != 1) {
            if (mChartsContainer.getChildAt(1) == null)
                getActivity().getLayoutInflater().inflate(R.layout.chart_layout, mChartsContainer);
            LineChartView lineChartView1 = (LineChartView) mChartsContainer.getChildAt(1).findViewById(R.id.chart);
            lineChartView1.mIsSimpleDraw = false;
            mChartsContainer.getChildAt(1).findViewById(R.id.param_info).setVisibility(View.GONE);
            lineChartView1.addPoints(lineChartView1.convertY(realTimeDatas, isRight), simpleItem.getTitle(), simpleItem.getColor(), isRight);
            ((TextView)mChartsContainer.getChildAt(1).findViewById(R.id.indicator)).setText("y方向趋势图");


        }
        if (realTimeDatas.get(0).getTypeCode() == 2) {
            if (mChartsContainer.getChildAt(2) == null)
                getActivity().getLayoutInflater().inflate(R.layout.chart_layout, mChartsContainer);
            LineChartView lineChartView2 = (LineChartView) mChartsContainer.getChildAt(2).findViewById(R.id.chart);
            mChartsContainer.getChildAt(2).findViewById(R.id.param_info).setVisibility(View.GONE);
            lineChartView2.addPoints(lineChartView2.convertZ(realTimeDatas, isRight), simpleItem.getTitle(), simpleItem.getColor(), isRight);
            ((TextView)mChartsContainer.getChildAt(2).findViewById(R.id.indicator)).setText("z方向趋势图");

        }

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, final Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != Activity.RESULT_OK)
            return; // do nothing

        if (requestCode == StationListActivity.REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            stationId = data.getStringExtra("stationId");
            mTextViewCenter.setText(data.getStringExtra("stationName"));
            return;
        }

        if (mChartsContainer.getChildAt(1) != null)
            mChartsContainer.removeView(mChartsContainer.getChildAt(1));

        if (mChartsContainer.getChildAt(1) != null)
            mChartsContainer.removeView(mChartsContainer.getChildAt(1));

        final LineChartView lineChartView = (LineChartView) getView().findViewById(R.id.chart);
        lineChartView.getLines().clear();
        lineChartView.mIsSimpleDraw = false;

        if (resultCode == Activity.RESULT_OK) {
            if (progressDialog == null) {
                progressDialog = new ProgressDialog(getActivity());
                progressDialog.setCanceledOnTouchOutside(false);
                progressDialog.setMessage("正在获取数据...");
                progressDialog.show();
            }
        }

        // data contrast
        if (requestCode == 1000 && resultCode == Activity.RESULT_OK) {
            if (mTimer != null)
                mTimer.cancel();

            final ArrayList<SimpleItem> positionItems = (ArrayList<SimpleItem>) data.getSerializableExtra("positionItems");

            if (positionItems.size() > 1) {//多位置
                mTimer = new CountDownTimer(11000, 10000) {
                    @Override
                    public void onTick(long millisUntilFinished) {
                        for (int j = 0; j < mChartsContainer.getChildCount(); j++) {
                            ((LineChartView) mChartsContainer.getChildAt(j).findViewById(R.id.chart)).getLines().clear();
                            ((LineChartView) mChartsContainer.getChildAt(j).findViewById(R.id.chart)).getLinesRight().clear();
                        }
                        String str="";
                        for (final SimpleItem item : positionItems) {
                            getMessage(getHttpService().getRealTimeData(item.getCode(), data.getLongExtra("start", 0), data.getLongExtra("end", System.currentTimeMillis()), 3), new MySubscriber<ComplexData>() {
                                @Override
                                protected void onMyNext(ComplexData complexData) {
                                    List<RealTimeData> realTimeDatas = complexData.getContent();
                                    addToChart(realTimeDatas, item, false);//图表
                                }
                                @Override
                                public void onCompleted() {
                                    super.onCompleted();
                                    if (progressDialog != null) {
                                        progressDialog.dismiss();
                                        progressDialog = null;
                                    }
                                }

                                @Override
                                public void onError(Throwable e) {
                                    super.onError(e);

                                    if (progressDialog != null) {
                                        progressDialog.dismiss();
                                        progressDialog = null;
                                    }
                                }

                                @Override
                                public void onNext(ComplexData complexData) {
                                    if (complexData != null) {
                                        super.onNext(complexData);
                                    } else {
                                        showToastMsg("选择的时间段没有数据");
                                    }
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
                        form_2.setForm("监测位置",str,true);
                        form_5.setForm("时间",sdf.format(new Date(start))+"---"+sdf.format(new Date(end)),false);
                    }
                    @Override
                    public void onFinish() {

                    }
                };
                layout_correlation.setVisibility(View.VISIBLE);
                mTimer.start();
            } else {//多时间
                final ArrayList<String> times = (ArrayList<String>) data.getSerializableExtra("times");
                mTimer = new CountDownTimer(11000, 10000) {
                    @Override
                    public void onTick(long millisUntilFinished) {
                        for (int j = 0; j < mChartsContainer.getChildCount(); j++) {
                            ((LineChartView) mChartsContainer.getChildAt(j).findViewById(R.id.chart)).getLines().clear();
                            ((LineChartView) mChartsContainer.getChildAt(j).findViewById(R.id.chart)).getLinesRight().clear();

                        }
                        String str="";

                        for (final String s : times) {
                            String[] strings = s.split("  ----  ");
                            getMessage(getHttpService().getRealTimeData(positionItems.get(0).getCode(), sdf.parse(strings[0], new ParsePosition(0)).getTime(), sdf.parse(strings[1], new ParsePosition(0)).getTime(), 3), new MySubscriber<ComplexData>() {
                                @Override
                                protected void onMyNext(ComplexData complexData) {
                                    List<RealTimeData> realTimeDatas = complexData.getContent();

                                    if (mChartsContainer.getChildAt(0) == null)
                                        getActivity().getLayoutInflater().inflate(R.layout.chart_layout, mChartsContainer);
                                    LineChartView lineChartView0 = (LineChartView) mChartsContainer.getChildAt(0).findViewById(R.id.chart);
                                    mChartsContainer.getChildAt(0).findViewById(R.id.param_info).setVisibility(View.GONE);
                                    lineChartView0.mIsSimpleDraw = true;
                                    lineChartView0.addPoints(lineChartView0.convert(realTimeDatas, false), s, Color.argb(255, random.nextInt(256), random.nextInt(256), random.nextInt(256)), false);
                                    ((TextView)mChartsContainer.getChildAt(0).findViewById(R.id.indicator)).setText("x方向趋势图");
                                    ((TextView)mChartsContainer.getChildAt(0).findViewById(R.id.indicator)).setVisibility(View.VISIBLE);

                                    if (realTimeDatas.get(0).getTypeCode() != 1) {
                                        if (mChartsContainer.getChildAt(1) == null)
                                            getActivity().getLayoutInflater().inflate(R.layout.chart_layout, mChartsContainer);
                                        LineChartView lineChartView1 = (LineChartView) mChartsContainer.getChildAt(1).findViewById(R.id.chart);
                                        mChartsContainer.getChildAt(1).findViewById(R.id.param_info).setVisibility(View.GONE);
                                        lineChartView1.mIsSimpleDraw = true;

                                        lineChartView1.addPoints(lineChartView1.convertY(realTimeDatas, false), s, Color.argb(255, random.nextInt(256), random.nextInt(256), random.nextInt(256)), false);
                                        ((TextView)mChartsContainer.getChildAt(1).findViewById(R.id.indicator)).setText("y方向趋势图");

                                    }
                                    if (realTimeDatas.get(0).getTypeCode() == 2) {
                                        if (mChartsContainer.getChildAt(2) == null)
                                            getActivity().getLayoutInflater().inflate(R.layout.chart_layout, mChartsContainer);
                                        LineChartView lineChartView2 = (LineChartView) mChartsContainer.getChildAt(2).findViewById(R.id.chart);
                                        mChartsContainer.getChildAt(2).findViewById(R.id.param_info).setVisibility(View.GONE);
                                        lineChartView2.mIsSimpleDraw = true;
                                        ((TextView)mChartsContainer.getChildAt(2).findViewById(R.id.indicator)).setText("z方向趋势图");

                                        lineChartView2.addPoints(lineChartView2.convertZ(realTimeDatas, false), s, Color.argb(255, random.nextInt(256), random.nextInt(256), random.nextInt(256)), false);
                                    }




                                }

                                @Override
                                public void onCompleted() {
                                    super.onCompleted();
                                    if (progressDialog != null) {
                                        progressDialog.dismiss();
                                        progressDialog = null;
                                    }
                                }

                                @Override
                                public void onNext(ComplexData complexData) {
                                    if (complexData != null) {
                                        super.onNext(complexData);
                                    } else {
                                        showToastMsg("选择的时间段没有数据");
                                    }
                                }

                                @Override
                                public void onError(Throwable e) {
                                    super.onError(e);

                                    if (progressDialog != null) {
                                        progressDialog.dismiss();
                                        progressDialog = null;
                                    }
                                }
                            });
                            String time=strings[0]+"---"+strings[1];
                            if (str.equals("")){
                                str+=time;
                            }else {
                                str=str+"\n"+time;
                            }
                        }
                        form_2.setForm("监测位置",positionItems.get(0).getTitle(),true);
                        form_5.setForm("时间",str,false);
                    }
                    @Override
                    public void onFinish() {
                    }
                };
                mTimer.start();
            }
            form_1.setForm("监测因素",data.getStringExtra("title"),false);
            layout_correlation.setVisibility(View.VISIBLE);
            //todo
            form_3.setVisibility(View.GONE);
            form_4.setVisibility(View.GONE);
        }
        // 数据关联

        if (requestCode == 1001 && resultCode == Activity.RESULT_OK) {
            final ArrayList<SimpleItem> positionItems = (ArrayList<SimpleItem>) data.getSerializableExtra("positionItems");
            final ArrayList<SimpleItem> positionItemsCorrelation = (ArrayList<SimpleItem>) data.getSerializableExtra("positionItemsCorrelation");
            if (mTimer != null)
                mTimer.cancel();
            mTimer = new CountDownTimer(11000, 10000) {
                @Override
                public void onTick(long millisUntilFinished) {
                    for (int j = 0; j < mChartsContainer.getChildCount(); j++) {
                        ((LineChartView) mChartsContainer.getChildAt(j).findViewById(R.id.chart)).getLines().clear();
                        ((LineChartView) mChartsContainer.getChildAt(j).findViewById(R.id.chart)).getLinesRight().clear();
                    }
                    for (final SimpleItem item : positionItems) {
                        getMessage(getHttpService().getRealTimeData(item.getCode(), data.getLongExtra("start", 0), data.getLongExtra("end", System.currentTimeMillis()), 3), new MySubscriber<ComplexData>() {
                            @Override
                            protected void onMyNext(ComplexData complexData) {
                                List<RealTimeData> realTimeDatas = complexData.getContent();

                                addToChart(realTimeDatas, item, false);
                                correlationCheck();
                            }

                            @Override
                            public void onCompleted() {
                                super.onCompleted();
                                if (progressDialog != null) {
                                    progressDialog.dismiss();
                                    progressDialog = null;
                                }
                            }

                            @Override
                            public void onError(Throwable e) {
                                super.onError(e);

                                if (progressDialog != null) {
                                    progressDialog.dismiss();
                                    progressDialog = null;
                                }
                            }

                            @Override
                            public void onNext(ComplexData complexData) {
                                if (complexData != null) {
                                    super.onNext(complexData);
                                } else {
                                    showToastMsg("选择的时间段没有数据");
                                }
                            }
                        });
                    }
                    for (final SimpleItem simpleItem : positionItemsCorrelation) {
                        getMessage(getHttpService().getRealTimeData(simpleItem.getCode(), data.getLongExtra("start", 0), data.getLongExtra("end", System.currentTimeMillis()), 3), new MySubscriber<ComplexData>() {
                            @Override
                            protected void onMyNext(ComplexData complexData) {
                                List<RealTimeData> realTimeDatas = complexData.getContent();

                                addToChart(realTimeDatas, simpleItem, true);
                                correlationCheck();
                            }

                            @Override
                            public void onCompleted() {
                                super.onCompleted();
                                if (progressDialog != null) {
                                    progressDialog.dismiss();
                                    progressDialog = null;
                                }

                            }

                            @Override
                            public void onError(Throwable e) {
                                super.onError(e);

                                if (progressDialog != null) {
                                    progressDialog.dismiss();
                                    progressDialog = null;
                                }
                            }



                            @Override
                            public void onNext(ComplexData complexData) {
                                if (complexData != null) {
                                    super.onNext(complexData);
                                } else {
                                    showToastMsg("选择的时间段没有数据");
                                }
                            }
                        });
                    }
                }
                @Override
                public void onFinish() {
                }
            };
            long start= data.getLongExtra("start", 0);
            long end= data.getLongExtra("end", 0);
            String title1="";
            String title2="";
            form_1.setForm("监测因素",data.getStringExtra("title"),false);
            for (SimpleItem sim:positionItems){
                if (title1.equals("")){
                    title1+=sim.getTitle();
                }else {
                    title1=title1+","+sim.getTitle();
                }
            }
            form_2.setForm("监测位置",title1,true);
            form_3.setForm("关联监测因素",data.getStringExtra("titleCorrelation"),false);
            for (SimpleItem sim:positionItemsCorrelation){
                if (title2.equals("")){
                    title2+=sim.getTitle();
                }else {
                    title2=title2+","+sim.getTitle();
                }
            }
            form_3.setVisibility(View.VISIBLE);
            form_4.setVisibility(View.VISIBLE);
            layout_correlation.setVisibility(View.VISIBLE);
            form_4.setForm("关联监测位置",title2,true);
            form_5.setForm("时间",sdf.format(new Date(start))+"---"+sdf.format(new Date(end)),false);
            mTimer.start();
        }
    }

    private void correlationCheck() {
        if (mChartsContainer.getChildCount() > 0) {
            LineChartView v0 = (LineChartView) mChartsContainer.getChildAt(0).findViewById(R.id.chart);
            for (int n = 1; n < mChartsContainer.getChildCount(); n++) {
                LineChartView v = (LineChartView) mChartsContainer.getChildAt(n).findViewById(R.id.chart);
                if (v.getLines().size() != v0.getLines().size()) {
                    v.getLines().clear();
                    v.getLines().addAll(v0.getLines());
                    v.yAxisName = v0.yAxisName;
                    v.invalidate();
                }

                if (v.getLinesRight().size() != v0.getLinesRight().size()) {
                    v.getLinesRight().clear();
                    v.getLinesRight().addAll(v0.getLinesRight());
                    v.yAxisNameRight = v0.yAxisNameRight;
                    v.invalidate();
                }
            }
        }
    }
    public void init(View v){
        form_option= (ImageView) v.findViewById(R.id.form_option);
        layout_correlation= (LinearLayout) v.findViewById(R.id.layout_correlation);
        layout_option= (LinearLayout) v.findViewById(R.id.layout_option);
        form_1= (MyForm) v.findViewById(R.id.form_1);
        form_2= (MyForm) v.findViewById(R.id.form_2);
        form_3= (MyForm) v.findViewById(R.id.form_3);
        form_4= (MyForm) v.findViewById(R.id.form_4);
        form_5= (MyForm) v.findViewById(R.id.form_5);
    }

//    update

}
