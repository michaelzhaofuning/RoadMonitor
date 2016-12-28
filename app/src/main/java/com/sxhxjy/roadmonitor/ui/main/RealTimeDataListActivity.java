package com.sxhxjy.roadmonitor.ui.main;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Fragment;
import android.app.TimePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ExpandableListView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.sxhxjy.roadmonitor.R;
import com.sxhxjy.roadmonitor.adapter.FilterTreeAdapter;
import com.sxhxjy.roadmonitor.adapter.RealTimeDataListAdapter;
import com.sxhxjy.roadmonitor.adapter.SimpleListAdapter;
import com.sxhxjy.roadmonitor.base.BaseActivity;
import com.sxhxjy.roadmonitor.base.BaseListFragment;
import com.sxhxjy.roadmonitor.base.HttpResponse;
import com.sxhxjy.roadmonitor.base.MySubscriber;
import com.sxhxjy.roadmonitor.entity.AlertData;
import com.sxhxjy.roadmonitor.entity.AlertTree;
import com.sxhxjy.roadmonitor.entity.RealTimeData;
import com.sxhxjy.roadmonitor.entity.SimpleItem;
import com.sxhxjy.roadmonitor.view.MyPopupWindow;
import com.sxhxjy.roadmonitor.view.NumDrawable;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;

import rx.Observable;

/**
 * 2016/9/26
 *
 * 实时数据
 */

public class RealTimeDataListActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.base_activity);
        Fragment f = new RealTimeDataListFragment();
        getFragmentManager().beginTransaction()
                .add(R.id.container, f).commit();
        initToolBar("实时数据", true);
    }

    public static class RealTimeDataListFragment extends BaseListFragment<RealTimeData> {
        private CountDownTimer countDownTimer;

        private List<SimpleItem> mListLeft = new ArrayList<>();//等级列表
        private SimpleListAdapter mSimpleListAdapter;//下拉列表适配器
        private TextView mFilterTitleLeft, mFilterTitleRight,mFilterTitledefault;//等级列表标题，时间列表标题
        private RecyclerView mFilterList;//下拉列表控件
        private String  timeCode;
        private ArrayList<RealTimeData> rdlist;

        @Override
        public Observable<HttpResponse<List<RealTimeData>>> getObservable() {
            mAdapter.notifyDataSetChanged();
            return null;
        }
        @Override
        public void onDetach() {
            super.onDetach();
            countDownTimer.cancel();
        }
        @Override
        protected Class<RealTimeData> getItemClass() {
            return RealTimeData.class;
        }
        @Override
        protected void init() {
            mPullRefreshLoadLayout.enableRefresh(false);
            countDownTimer = new CountDownTimer(9999999999L, MonitorFragment.interval) {
                @Override
                public void onTick(long millisUntilFinished) {
                    if (mAdapter != null)
                        mAdapter.notifyDataSetChanged();
                }

                @Override
                public void onFinish() {

                }
            };

            rdlist=new ArrayList<>();
            //set集合保存的是引用不同地址的对象
            Set<String> ts = new HashSet<>();
            List<String> slist=new ArrayList<>();
            for (RealTimeData rd:MonitorFragment.mRealTimes){
                if (rd.getCode().equals(MonitorFragment.mRealTimes.get(0).getCode())){
                    rdlist.add(rd);
                }
                if (!rd.getCode().equals("")&&rd.getCode()!=null){
                    if(ts.add(rd.getCode())){
                        slist.add(rd.getName() + "   " + rd.getCode());
                    }
                    }
            }
            mList = rdlist;
            countDownTimer.start();

            getActivity().getLayoutInflater().inflate(R.layout.filter_title_alert, mAboveList);

            mFilterTitleLeft = (TextView) getView().findViewById(R.id.filter_left);
            mFilterTitleLeft.setText("监测点");
            mFilterTitleRight = (TextView) getView().findViewById(R.id.filter_right);
            mFilterTitledefault = (TextView) getView().findViewById(R.id.filter_default);
            for (int i=0;i<slist.size();i++){
                mListLeft.add(new SimpleItem("", slist.get(i), false));
            }

            //等级列表标题点击事件
            mFilterTitleLeft.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mSimpleListAdapter.setListData(mListLeft);
                    mSimpleListAdapter.notifyDataSetChanged();

                    if (mFilterList.getVisibility() == View.GONE)
                        mFilterList.setVisibility(View.VISIBLE);
                    else
                        mFilterList.setVisibility(View.GONE);
                }
            });
            //时间列表标题点击事件
            mFilterTitleRight.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    final StringBuilder sb = new StringBuilder();
                    final Date date = new Date(System.currentTimeMillis());
                new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        sb.append(String.format("%s-%s-%s",year+"",
                                (monthOfYear+1 < 10 ? "0" + monthOfYear+1 : monthOfYear+1+""),
                                (dayOfMonth < 10 ? "0" + dayOfMonth : dayOfMonth+"")));

                        new TimePickerDialog(getActivity(), new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                                sb.append(" "+ (hourOfDay < 10 ? "0" + hourOfDay : hourOfDay)+":"+(minute < 10 ? "0" + minute : minute));
                                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm");
                                try {

                                    long millionSeconds = sdf.parse(sb.toString()).getTime();//毫秒
                                    long time =mList.get(0).getSaveTime();

                                    if (millionSeconds>=time) return;
                                for (int i=1;i<mList.size();i++){
                                    if (millionSeconds>=mList.get(i).getSaveTime()){
                                        Log.e("test",  ""+mList.size() + "   " + mRecyclerView.getAdapter().getItemCount());
                                        linearLayoutManager.scrollToPositionWithOffset(i-1, 0);
                                        break;
                                    }
                                }
                            } catch (ParseException e) {
                                    e.printStackTrace();
                                }
                                }
                                //0,0指的是时间，true表示是否为24小时，true为24小时制
                        },00,00,true).show();
                    }
                },2016,date.getMonth(), date.getDate()).show();
            }
            });
            mFilterTitledefault.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mFilterTitleLeft.setText("监测点");
                    mFilterList.setVisibility(View.GONE);
                    for (SimpleItem simpleItem : mSimpleListAdapter.getListData()) {
                        simpleItem.setChecked(false);
                    }
                    timeCode = null;
                    mAdapter.notifyDataSetChanged();
                    linearLayoutManager.scrollToPositionWithOffset(0, 0);
                }
            });
            mFilterList = (RecyclerView) getView().findViewById(R.id.filter_list);
            mFilterList.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    if (event.getAction() == MotionEvent.ACTION_DOWN)
                    mFilterList.setVisibility(View.GONE);
                    return false;
                }
            });
            mFilterList.setLayoutManager(new LinearLayoutManager(getActivity()));
            mSimpleListAdapter = new SimpleListAdapter(this, mListLeft);
            mFilterList.setAdapter(mSimpleListAdapter);
            mSimpleListAdapter.setFilterList(mFilterList);
            //列表项的点击事件
            mSimpleListAdapter.setListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int p = (int) v.getTag();
                    for (SimpleItem simpleItem : mSimpleListAdapter.getListData()) {
                        simpleItem.setChecked(false);
                    }
                    mSimpleListAdapter.getListData().get(p).setChecked(true);
                    mFilterList.setVisibility(View.GONE);
                    if (mSimpleListAdapter.getListData() == mListLeft) {
                        mFilterTitleLeft.setText(mSimpleListAdapter.getListData().get(p).getTitle());
                        for (SimpleItem s:mListLeft){
                            if (s.isChecked()) {
                                String title=s.getTitle();
                                rdlist.clear();
                                for (int i=0;i<MonitorFragment.mRealTimes.size();i++){
                                    if (title.equals(MonitorFragment.mRealTimes.get(i).getName() + "   " +MonitorFragment.mRealTimes.get(i).getCode())){
                                        rdlist.add(MonitorFragment.mRealTimes.get(i));
                                    }
                                }
                            }
                        }
                        mAdapter.notifyDataSetChanged();

                    } else {
//                        mFilterTitleRight.setText(mSimpleListAdapter.getListData().get(p).getTitle());
                        onRefresh();
                    }
                }
            });
        }

        @Override
        protected String getCacheKey() {
            return null;
        }

        @Override
        protected RecyclerView.Adapter getAdapter() {
            return new RealTimeDataListAdapter(this, mList);
        }
    }


}
