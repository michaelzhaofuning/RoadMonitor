package com.sxhxjy.roadmonitor.ui.main;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TimePicker;

import com.sxhxjy.roadmonitor.R;
import com.sxhxjy.roadmonitor.adapter.FilterTreeAdapter;
import com.sxhxjy.roadmonitor.adapter.SimpleListAdapter;
import com.sxhxjy.roadmonitor.base.BaseActivity;
import com.sxhxjy.roadmonitor.base.BaseFragment;
import com.sxhxjy.roadmonitor.base.MonitorPosition;
import com.sxhxjy.roadmonitor.base.MyApplication;
import com.sxhxjy.roadmonitor.base.MySubscriber;
import com.sxhxjy.roadmonitor.base.ParamInfo;
import com.sxhxjy.roadmonitor.entity.ComplexData;
import com.sxhxjy.roadmonitor.entity.MonitorTypeTree;
import com.sxhxjy.roadmonitor.entity.RealTimeData;
import com.sxhxjy.roadmonitor.entity.SimpleItem;
import com.sxhxjy.roadmonitor.ui.main.picture.TakeNotesActivity;
import com.sxhxjy.roadmonitor.util.ActivityUtil;
import com.sxhxjy.roadmonitor.util.MyCountDownTimer;
import com.sxhxjy.roadmonitor.view.LineChartView;
import com.sxhxjy.roadmonitor.view.MyPopupWindow;

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

public class MonitorFragment extends BaseFragment implements View.OnClickListener {
    /**
     * 检测项目fragment
     */
    public List<FilterTreeAdapter.Group> groupsOfFilterTree = new ArrayList<>();
    public MyCountDownTimer mTimer;
    public static ArrayList<RealTimeData> mRealTimes = new ArrayList<>();
    public int startDay; //
    private String stationId;
    private TextView mTextViewCenter;
    private ImageView mImageViewLeft;
    private List<SimpleItem> mListLeft = new ArrayList<>();
    private List<SimpleItem> mListRight = new ArrayList<>();
    private SimpleListAdapter mAdapter;
    private TextView mFilterTitleLeft, mFilterTitleRight;
    private RecyclerView mFilterList;
    private MyPopupWindow myPopupWindow;
    private FilterTreeAdapter filterTreeAdapter;
    private Random random = new Random(47);
    private String codeId;
    private String timeId = "0";
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.CHINA);
    private ProgressDialog progressDialog;
    private LinearLayout mChartsContainer;
    private String positionId;
    private boolean paramsGeted;
    private boolean isFirstProgressDialog = true;
    public static final long interval = 30000;
    // filter item clicked
    private View.OnClickListener simpleListListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            int p = (int) v.getTag();

            if (mAdapter.isMultipleChoice()) {
                // button clicked
                if (p == mAdapter.getListData().size()) {
                    StringBuilder sb = new StringBuilder();
                    for (SimpleItem simpleItem : mAdapter.getListData()) {
                        if (simpleItem.isChecked()) {
                            sb.append(simpleItem.getTitle() + "...");
                            break;
                        }
                    }
                    if (TextUtils.isEmpty(sb.toString())) {
                        showToastMsg("请至少选择一个位置！");
                        return;
                    }
                    mFilterTitleLeft.setText(sb.toString());
                    mFilterList.setVisibility(View.GONE);

                    getChartData();

                    return;
                } else {
                    mAdapter.getListData().get(p).setChecked(!mAdapter.getListData().get(p).isChecked());
                }
            } else { // time filter
                for (SimpleItem simpleItem : mAdapter.getListData()) {
                    simpleItem.setChecked(false);
                }
                if (p == 3) {// pick time
                    addTime(mAdapter);
                }
                mAdapter.getListData().get(p).setChecked(true);
                timeId = mAdapter.getListData().get(p).getId();
                mFilterList.setVisibility(View.GONE);
                if (p != 3)
                    getChartData();
            }


            if (mAdapter.getListData() != mListLeft) {
                mFilterTitleRight.setText(mAdapter.getListData().get(p).getTitle());
            }

            mAdapter.notifyDataSetChanged();
        }
    };

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        return inflater.inflate(R.layout.monitor_fragment, null);
    }

    @Override
    protected void loadOnce() {
        super.loadOnce();
        // * entrance *
        if (groupsOfFilterTree.isEmpty()) getTypeTree(); // getTypeTree
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initToolBar(getView(), getArguments().getString("stationName"), false);
        stationId = getArguments().getString("stationId");
        cacheStation(stationId, getArguments().getString("stationName"));


        mTextViewCenter = (TextView) getView().findViewById(R.id.toolbar_title);
        mImageViewLeft = (ImageView) getView().findViewById(R.id.toolbar_left);
        mImageViewLeft.setVisibility(View.VISIBLE);
        mImageViewLeft.setImageResource(R.mipmap.history);
        mImageViewLeft.setOnClickListener(this);
        mTextViewCenter.setOnClickListener(this);
        mTextViewCenter.setCompoundDrawablesWithIntrinsicBounds(null, null, getResources().getDrawable(R.mipmap.expand), null);
        mTextViewCenter.setCompoundDrawablePadding(20);
        mChartsContainer = (LinearLayout) getView().findViewById(R.id.charts_container);


        mFilterTitleLeft = (TextView) view.findViewById(R.id.filter_left);
        mFilterTitleRight = (TextView) view.findViewById(R.id.filter_right);

        mListRight.add(new SimpleItem("0", "最近一天", true));
        mListRight.add(new SimpleItem("1", "最近一周", false));
        mListRight.add(new SimpleItem("2", "最近一月", false));
        mListRight.add(new SimpleItem("3", "其它", false));

        mFilterTitleLeft.setOnClickListener(this);
        mFilterTitleRight.setOnClickListener(this);

        mFilterList = (RecyclerView) view.findViewById(R.id.filter_list);

        mFilterList.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN)
                    mFilterList.setVisibility(View.GONE);
                return false;
            }
        });
        mFilterList.setLayoutManager(new LinearLayoutManager(getActivity()));
        mAdapter = new SimpleListAdapter(this, mListLeft);
        mFilterList.setAdapter(mAdapter);
        mAdapter.setFilterList(mFilterList);

        mAdapter.setListener(simpleListListener);


        mToolbar.inflateMenu(R.menu.filter_right);
        mToolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if (groupsOfFilterTree.isEmpty()) getTypeTree();
                myPopupWindow.show();

                if (mFilterList.getVisibility() == View.VISIBLE)
                    mFilterList.setVisibility(View.GONE);
                return true;
            }
        });



/**
 * 弹出窗口
 */

        myPopupWindow = new MyPopupWindow((BaseActivity) getActivity(), R.layout.popup_window_right);

        ExpandableListView expandableListView = (ExpandableListView) myPopupWindow.getContentView().findViewById(R.id.expandable_list_view);
        Button confirm = (Button) myPopupWindow.getContentView().findViewById(R.id.confirm);
        Button reset = (Button) myPopupWindow.getContentView().findViewById(R.id.reset);
        TextView pic = (TextView) myPopupWindow.getContentView().findViewById(R.id.pic);
        pic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityUtil.startActivityForResult(getActivity(), TakeNotesActivity.class);
                myPopupWindow.dismiss();
            }
        });


        confirm.setVisibility(View.GONE);
        reset.setVisibility(View.GONE);

        filterTreeAdapter = new FilterTreeAdapter(groupsOfFilterTree);
        expandableListView.setAdapter(filterTreeAdapter);
        expandableListView.expandGroup(0);
        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                for (FilterTreeAdapter.Group group : filterTreeAdapter.mGroups) {
                    for (SimpleItem simpleItem : group.getList()) {
                        simpleItem.setChecked(false);
                    }
                }
                filterTreeAdapter.mGroups.get(groupPosition).getList().get(childPosition).setChecked(true);
//                codeId = filterTreeAdapter.mGroups.get(groupPosition).getList().get(childPosition).getId();
                myPopupWindow.dismiss();
                filterTreeAdapter.notifyDataSetChanged();
                getLocation(groupPosition, childPosition);

                return true;
            }
        });

        if (mChartsContainer.getChildCount() == 0) {
            getView().findViewById(R.id.empty).setVisibility(View.VISIBLE);
        } else {
            getView().findViewById(R.id.empty).setVisibility(View.GONE);
        }

    }

    private void getChartData() {
        isFirstProgressDialog = true;
        paramsGeted = false;
        mRealTimes.clear();

        if (mTimer != null)
            mTimer.cancel();

        for (SimpleItem simpleItem : mListLeft) {
            simpleItem.setColor(Color.argb(255, random.nextInt(256), random.nextInt(256), random.nextInt(256)));
            simpleItem.setShouldGetDiffer(false);
        }

        if (!mListRight.get(3).isChecked()) // 不是'其它'时间段
            timeId = "0"; // to get the full data

        for (int j = 0; j < mChartsContainer.getChildCount(); j++) {
            ((LineChartView) mChartsContainer.getChildAt(j).findViewById(R.id.chart)).getLines().clear();
        }

        if (mChartsContainer.getChildAt(1) != null)
            mChartsContainer.removeView(mChartsContainer.getChildAt(1));

        if (mChartsContainer.getChildAt(1) != null)
            mChartsContainer.removeView(mChartsContainer.getChildAt(1));

        mTimer = new MyCountDownTimer(999999999, interval) {
            @Override
            public void onTick(long millisUntilFinished) {

                for (final SimpleItem simpleItem : mListLeft) {
                    if (simpleItem.isChecked()) {
                        codeId = simpleItem.getId();
                        String[] strings = null;
                        long start = System.currentTimeMillis();
                        long end = System.currentTimeMillis() + 1000 * 3600 * 24;

                        if (timeId.equals("3") && mListRight.get(3).isChecked()) {
                            strings = mListRight.get(3).getTitle().split("  ---- \n ");
                            if (strings.length != 2) {
                                timeId = "0";
                            } else {
                                start = sdf.parse(strings[0], new ParsePosition(0)).getTime();
                                end = sdf.parse(strings[1], new ParsePosition(0)).getTime();
                            }
                        }

                        if (simpleItem.isShouldGetDiffer() && mChartsContainer.getChildAt(0) != null) {
                            LineChartView v = (LineChartView) mChartsContainer.getChildAt(0).findViewById(R.id.chart);
                            if (v != null && !v.getLines().isEmpty()) {
                                LineChartView.MyLine line = v.getLines().get(0);
                                start = line.points.get(line.points.size() - 1).time + 1000; // next second
                                end = System.currentTimeMillis();
                            }
                            if (!timeId.equals("3")) timeId = "3";
                        }


                        getMessage(getHttpService().getRealTimeData(simpleItem.getCode(), start, end, Integer.parseInt(timeId)), new MySubscriber<ComplexData>() {
                            @Override
                            public void onStart() {
                                super.onStart();
                                if (progressDialog == null && isFirstProgressDialog && getActivity() != null) {
                                    progressDialog = new ProgressDialog(getActivity());
                                    progressDialog.setCanceledOnTouchOutside(false);
                                    progressDialog.setMessage("正在获取数据...");
                                    progressDialog.show();
                                    isFirstProgressDialog = false;
                                }
                            }

                            @Override
                            protected void onMyNext(ComplexData complexData) {
                                List<RealTimeData> realTimeDatas = complexData.getContent();
                                mRealTimes.addAll(0, realTimeDatas);


                                if (mChartsContainer.getChildAt(0) == null)
                                    getActivity().getLayoutInflater().inflate(R.layout.chart_layout, mChartsContainer);
                                LineChartView lineChartView0 = (LineChartView) mChartsContainer.getChildAt(0).findViewById(R.id.chart);

                                // true we appended data to line,
                                // false we NOT found the line to add then we add a new line
                                boolean handled = false;

                                for (LineChartView.MyLine myLine : lineChartView0.getLines()) {
                                    if (realTimeDatas.get(0).getName().equals(myLine.name)) {
                                        // we found the line to add
                                        myLine.points.addAll(lineChartView0.convert(realTimeDatas, false));
                                        handled = true;
                                    }
                                }

                                if (!handled) {
                                    lineChartView0.addPoints(lineChartView0.convert(realTimeDatas, false), simpleItem.getTitle(), simpleItem.getColor(), false);

                                    ((TextView) mChartsContainer.getChildAt(0).findViewById(R.id.min)).setText(complexData.getXmin() + "");
                                    ((TextView) mChartsContainer.getChildAt(0).findViewById(R.id.max)).setText(complexData.getXmax() + "");
                                }


                                if (realTimeDatas.get(0).getTypeCode() != 1) {
                                    if (mChartsContainer.getChildAt(1) == null)
                                        getActivity().getLayoutInflater().inflate(R.layout.chart_layout, mChartsContainer);
                                    LineChartView lineChartView1 = (LineChartView) mChartsContainer.getChildAt(1).findViewById(R.id.chart);


                                    handled = false;

                                    for (LineChartView.MyLine myLine : lineChartView1.getLines()) {
                                        if (realTimeDatas.get(0).getName().equals(myLine.name)) {
                                            // we found the line to add
                                            myLine.points.addAll(lineChartView1.convertY(realTimeDatas, false));
                                            handled = true;
                                        }
                                    }

                                    if (!handled) {
                                        lineChartView1.addPoints(lineChartView1.convertY(realTimeDatas, false), simpleItem.getTitle(), simpleItem.getColor(), false);


                                        ((TextView) mChartsContainer.getChildAt(1).findViewById(R.id.min)).setText(complexData.getYmin() + "");
                                        ((TextView) mChartsContainer.getChildAt(1).findViewById(R.id.max)).setText(complexData.getYmax() + "");
                                    }

                                }
                                if (realTimeDatas.get(0).getTypeCode() == 2) {
                                    if (mChartsContainer.getChildAt(2) == null)
                                        getActivity().getLayoutInflater().inflate(R.layout.chart_layout, mChartsContainer);
                                    LineChartView lineChartView2 = (LineChartView) mChartsContainer.getChildAt(2).findViewById(R.id.chart);


                                    handled = false;

                                    for (LineChartView.MyLine myLine : lineChartView2.getLines()) {
                                        if (realTimeDatas.get(0).getName().equals(myLine.name)) {
                                            // we found the line to add
                                            myLine.points.addAll(lineChartView2.convertZ(realTimeDatas, false));
                                            handled = true;
                                        }
                                    }

                                    if (!handled) {
                                        lineChartView2.addPoints(lineChartView2.convertZ(realTimeDatas, false), simpleItem.getTitle(), simpleItem.getColor(), false);


                                        ((TextView) mChartsContainer.getChildAt(2).findViewById(R.id.min)).setText(complexData.getZmin() + "");
                                        ((TextView) mChartsContainer.getChildAt(2).findViewById(R.id.max)).setText(complexData.getZmax() + "");
                                    }
                                }

                                if (!paramsGeted) {
                                    paramsGeted = true;
                                    getParamInfo();
                                }
                            }

                            @Override
                            public void onError(Throwable e) {
                                super.onError(e);

                                if (!paramsGeted) {
                                    paramsGeted = true;
                                    getParamInfo();
                                }

                                if (progressDialog != null) {
                                    progressDialog.dismiss();
                                    progressDialog = null;
                                }

                                // error occurred,
//                                simpleItem.setShouldGetDiffer(true);


                            }

                            @Override
                            public void onCompleted() {
                                if (getView() != null) {// logout getview is null
                                    if (getView().findViewById(R.id.empty) != null) {
                                        if (mChartsContainer.getChildCount() == 0) {
                                            getView().findViewById(R.id.empty).setVisibility(View.VISIBLE);
                                        } else {
                                            getView().findViewById(R.id.empty).setVisibility(View.GONE);
                                        }
                                    }

                                    if (progressDialog != null) {
                                        progressDialog.dismiss();
                                        progressDialog = null;
                                    }
                                }

                                for (int i = 0; i < mChartsContainer.getChildCount(); i++) {
                                    View v = mChartsContainer.getChildAt(i).findViewById(R.id.chart);
                                    v.invalidate();
                                }
                            }
                        });

                        // we sent the request to get the full data !
                        // then get the differ
                        simpleItem.setShouldGetDiffer(true);
                    }
                }
            }

            @Override
            public void onFinish() {
                Log.e("monitorTimer", "onFinish called !");
            }
        };
        mTimer.start();
    }

    //获得条件请求数据
    private void getLocation(int groupPosition, int childPosition) {
        getMessage(getHttpService().getPositions(filterTreeAdapter.mGroups.get(groupPosition).getList().get(childPosition).getId(), MyApplication.getMyApplication().getSharedPreference().getString("stationId", "")), new MySubscriber<List<MonitorPosition>>() {
            @Override
            protected void onMyNext(List<MonitorPosition> monitorPositions) {
                mListLeft.clear();
                int i = 0;
                for (MonitorPosition position : monitorPositions) {
                    SimpleItem simpleItem = new SimpleItem(position.getId(), position.getName(), i++ == 0);
                    simpleItem.setCode(position.code);
                    mListLeft.add(simpleItem);
                    if (simpleItem.isChecked()) mFilterTitleLeft.setText(simpleItem.getTitle());

                    getChartData();

                }
            }
        });
    }

    private void getTypeTree() {
        getMessage(getHttpService().getMonitorTypeTree(MyApplication.getMyApplication().getSharedPreference().getString("stationId", "")), new MySubscriber<List<MonitorTypeTree>>() {
            @Override
            protected void onMyNext(List<MonitorTypeTree> monitorTypeTrees) {
                int i = 0;

                for (MonitorTypeTree monitorTypeTree : monitorTypeTrees) {
                    List<SimpleItem> list = new ArrayList<SimpleItem>();
                    if (monitorTypeTree.getChildrenPoint() != null) {
                        for (MonitorTypeTree.ChildrenPointBean childrenPointBean : monitorTypeTree.getChildrenPoint()) { // first click
                            list.add(new SimpleItem(childrenPointBean.getId(), childrenPointBean.getName(), i++ == 0));
                        }
                    }
                    FilterTreeAdapter.Group group = new FilterTreeAdapter.Group(list, monitorTypeTree.getName());
                    groupsOfFilterTree.add(group);
                }
                filterTreeAdapter.notifyDataSetChanged();

                for (int j = 0; j < groupsOfFilterTree.size(); j++) {
                    for (int k = 0; k < groupsOfFilterTree.get(j).getList().size(); k++) {
                        if (groupsOfFilterTree.get(j).getList().get(k).isChecked()) {
                            getLocation(j, k);
                        }
                    }
                }


            }
        });
    }

    //首页点击修改主题
    public void changeMonitor(int position) {
        for (FilterTreeAdapter.Group group : filterTreeAdapter.mGroups) {
            for (SimpleItem simpleItem : group.getList()) {
                simpleItem.setChecked(false);
            }
        }
        if (groupsOfFilterTree.size() == 0) return;
        if (groupsOfFilterTree.get(position).getList().size() > 0) {
            groupsOfFilterTree.get(position).getList().get(0).setChecked(true);
            getLocation(position, 0);
            mAdapter.notifyDataSetChanged();
        }
    }

    private void getParamInfo() {
        getMessage(getHttpService().getParamInfo(codeId), new MySubscriber<ParamInfo>() {
            @Override
            protected void onMyNext(ParamInfo paramInfo) {


                if (mChartsContainer.getChildAt(0) != null) {
                    View view = mChartsContainer.getChildAt(0);
                    ((TextView) view.findViewById(R.id.position)).setText(paramInfo.getCode());

                    ((TextView) view.findViewById(R.id.threshold1)).setText(paramInfo.getxOneThreshold());
                    ((TextView) view.findViewById(R.id.threshold2)).setText(paramInfo.getxTwoThreshold());
                    ((TextView) view.findViewById(R.id.threshold3)).setText(paramInfo.getxThreeThreshold());
                    ((TextView) view.findViewById(R.id.threshold4)).setText(paramInfo.getxFourThreshold());
                }

                if (mChartsContainer.getChildAt(1) != null) {
                    View view = mChartsContainer.getChildAt(1);
                    ((TextView) view.findViewById(R.id.position)).setText(paramInfo.getCode());
//                    ((TextView) view.findViewById(R.id.min)).setText(paramInfo.getYmin() + "");
//                    ((TextView) view.findViewById(R.id.max)).setText(paramInfo.getYmax() + "");
                    ((TextView) view.findViewById(R.id.threshold1)).setText(paramInfo.getyOneThreshold());
                    ((TextView) view.findViewById(R.id.threshold2)).setText(paramInfo.getyTwoThreshold());
                    ((TextView) view.findViewById(R.id.threshold3)).setText(paramInfo.getyThreeThreshold());
                    ((TextView) view.findViewById(R.id.threshold4)).setText(paramInfo.getyFourThreshold());
                }

                if (mChartsContainer.getChildAt(2) != null) {
                    View view = mChartsContainer.getChildAt(2);
                    ((TextView) view.findViewById(R.id.position)).setText(paramInfo.getCode());
//                    ((TextView) view.findViewById(R.id.min)).setText(paramInfo.getZmin() + "");
//                    ((TextView) view.findViewById(R.id.max)).setText(paramInfo.getZmax() + "");
                    ((TextView) view.findViewById(R.id.threshold1)).setText(paramInfo.getzOneThreshold());
                    ((TextView) view.findViewById(R.id.threshold2)).setText(paramInfo.getzTwoThreshold());
                    ((TextView) view.findViewById(R.id.threshold3)).setText(paramInfo.getzThreeThreshold());
                    ((TextView) view.findViewById(R.id.threshold4)).setText(paramInfo.getzFourThreshold());
                }
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == StationListActivity.REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            stationId = data.getStringExtra("stationId");
            mTextViewCenter.setText(data.getStringExtra("stationName"));
            cacheStation(stationId, data.getStringExtra("stationName"));
            groupsOfFilterTree.clear();
            getTypeTree();
        }
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.toolbar_title:
                Intent intent = new Intent(getActivity(), StationListActivity.class);
                startActivityForResult(intent, StationListActivity.REQUEST_CODE);
                break;
            case R.id.toolbar_left:
                ActivityUtil.startActivityForResult(getActivity(), RealTimeDataListActivity.class);
                break;

            case R.id.filter_left:
                if (groupsOfFilterTree.isEmpty()) {
                    showToastMsg("请先选择监测类型");
                    return;
                }
                mAdapter.setListData(mListLeft);
                mAdapter.setMultipleChoice(true);
                mAdapter.notifyDataSetChanged();


                if (mFilterList.getVisibility() == View.GONE)
                    mFilterList.setVisibility(View.VISIBLE);
                else
                    mFilterList.setVisibility(View.GONE);
                break;
            case R.id.filter_right:
                mAdapter.setListData(mListRight);
                mAdapter.setMultipleChoice(false);
                mAdapter.notifyDataSetChanged();


                if (mFilterList.getVisibility() == View.GONE)
                    mFilterList.setVisibility(View.VISIBLE);
                else
                    mFilterList.setVisibility(View.GONE);

                break;
        }
    }

    public void cacheStation(String stationId, String stationName) {
        MyApplication.getMyApplication().getSharedPreference().edit().putString("stationId", stationId).apply();
        MyApplication.getMyApplication().getSharedPreference().edit().putString("stationName", stationName).apply();
    }

    public void addTime(final SimpleListAdapter adapter) {
        final StringBuilder sb = new StringBuilder();
        final Date date = new Date(System.currentTimeMillis());
        new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                sb.append(year);
                sb.append("-");
                sb.append(monthOfYear + 1);
                startDay = dayOfMonth;
                sb.append("-");
                sb.append(dayOfMonth);
                sb.append("  ");
                new TimePickerDialog(getActivity(), new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        sb.append(hourOfDay < 10 ? "0" + hourOfDay : hourOfDay);
                        sb.append(":");
                        sb.append(minute < 10 ? "0" + minute : minute);
                        sb.append("  ---- \n ");
                        new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                sb.append(year);
                                sb.append("-");
                                sb.append(monthOfYear + 1);
                                if (dayOfMonth < startDay) {
                                    showToastMsg("不能小于开始时间，请重新选择！");
                                    timeId = "0";
                                } else {
                                    sb.append("-");
                                    sb.append(dayOfMonth);
                                    sb.append("  ");
                                    new TimePickerDialog(getActivity(), new TimePickerDialog.OnTimeSetListener() {
                                        @Override
                                        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                                            sb.append(hourOfDay < 10 ? "0" + hourOfDay : hourOfDay);
                                            sb.append(":");
                                            sb.append(minute < 10 ? "0" + minute : minute);
                                            adapter.getListData().get(3).setTitle(sb.toString());
                                            mFilterTitleRight.setText(sb.toString());
                                            getChartData();
                                        }
                                    }, 0, 0, true).show();
                                }
                            }
                        }, 2016, date.getMonth(), date.getDate()).show();
                        showToastMsg("请选择结束时间");
                    }
                }, 0, 0, true).show();
            }
        }, 2016, date.getMonth(), date.getDate()).show();
        showToastMsg("请选择开始时间");
    }
}
