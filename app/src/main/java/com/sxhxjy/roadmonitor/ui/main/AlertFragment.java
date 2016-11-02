package com.sxhxjy.roadmonitor.ui.main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.TextView;
import android.widget.Toast;

import com.sxhxjy.roadmonitor.R;
import com.sxhxjy.roadmonitor.adapter.AlertListAdapter;
import com.sxhxjy.roadmonitor.adapter.FilterTreeAdapter;
import com.sxhxjy.roadmonitor.adapter.MonitorListAdapter;
import com.sxhxjy.roadmonitor.adapter.SimpleListAdapter;
import com.sxhxjy.roadmonitor.base.BaseActivity;
import com.sxhxjy.roadmonitor.base.BaseFragment;
import com.sxhxjy.roadmonitor.base.BaseListFragment;
import com.sxhxjy.roadmonitor.base.HttpResponse;
import com.sxhxjy.roadmonitor.base.MyApplication;
import com.sxhxjy.roadmonitor.base.MySubscriber;
import com.sxhxjy.roadmonitor.entity.AlertData;
import com.sxhxjy.roadmonitor.entity.AlertTree;
import com.sxhxjy.roadmonitor.entity.MonitorTypeTree;
import com.sxhxjy.roadmonitor.entity.SimpleItem;
import com.sxhxjy.roadmonitor.view.MyPopupWindow;

import java.util.ArrayList;
import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * 2016/9/19
 *
 * @author Michael Zhao
 */
public class AlertFragment extends BaseListFragment<AlertData> {
    /**
     * 警告——fragment页
     */
    private List<SimpleItem> mListLeft = new ArrayList<>();//等级列表
    private List<SimpleItem> mListRight = new ArrayList<>();//时间列表
    private List<FilterTreeAdapter.Group> groups;//抽屉每组的集合
    private SimpleListAdapter mAdapter;//下拉列表适配器
    private TextView mFilterTitleLeft, mFilterTitleRight;//等级列表标题，时间列表标题
    private RecyclerView mFilterList;//下拉列表控件
    private MyPopupWindow myPopupWindow;//弹出窗口
    private FilterTreeAdapter filterTreeAdapter;//抽屉中下拉类表适配器
    private String level,cStype;
    private  long afterTime,beforeTime;
    @Override
    public Observable<HttpResponse<List<AlertData>>> getObservable() {
        FilterTreeAdapter.Group f1=groups.get(0);//告警等级
        FilterTreeAdapter.Group f2=groups.get(1);//设备类型
        FilterTreeAdapter.Group f3=groups.get(2);//状态
        for (SimpleItem s:f1.getList()){
            if (s.isChecked()==true){
                level=s.getId();
            }
        }
        for (SimpleItem s:f2.getList()){
            if (s.isChecked()==true){
                cStype=s.getId();
            }
        }

        for (SimpleItem s:mListRight){
            long time = 0;
            if (s.isChecked()==true){
                String title=s.getTitle();
                if (title.equals("最近一天")){
                    time=86400000;
                }else if (title.equals("最近一周")){
                    time=86400000*7;
                }else if (title.equals("最近一月")){
                    time=86400000*30;
                }
                afterTime= System.currentTimeMillis();
                beforeTime=afterTime-time;
            }
        }
        return getHttpService().getAlertDataList(
                MyApplication.getMyApplication().getSharedPreference().getString("stationId", ""),
                MyApplication.getMyApplication().getSharedPreference().getString("gid", ""),
                level,cStype,beforeTime,afterTime
     );
    }
    @Override
    protected Class<AlertData> getItemClass() {
        return AlertData.class;
    }

    @Override
    protected void init() {
        initToolBar(getView(), "警告", false);
        getActivity().getLayoutInflater().inflate(R.layout.filter_title_alert, mAboveList);

        mFilterTitleLeft = (TextView) getView().findViewById(R.id.filter_left);
        mFilterTitleRight = (TextView) getView().findViewById(R.id.filter_right);

        mListLeft.add(new SimpleItem("", "由高到低", false));
        mListLeft.add(new SimpleItem("", "由低到高", false));

        mListRight.add(new SimpleItem("", "最近一天", false));
        mListRight.add(new SimpleItem("", "最近一周", false));
        mListRight.add(new SimpleItem("", "最近一月", false));
        //等级列表标题点击事件
        mFilterTitleLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAdapter.setListData(mListLeft);
                mAdapter.notifyDataSetChanged();

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
                mAdapter.setListData(mListRight);
                mAdapter.notifyDataSetChanged();
                if (mFilterList.getVisibility() == View.GONE)
                    mFilterList.setVisibility(View.VISIBLE);
                else
                    mFilterList.setVisibility(View.GONE);
            }
        });
        mFilterList = (RecyclerView) getView().findViewById(R.id.filter_list);
        mFilterList.setLayoutManager(new LinearLayoutManager(getActivity()));
        mAdapter = new SimpleListAdapter(this, mListLeft);
        mFilterList.setAdapter(mAdapter);
        mAdapter.setFilterList(mFilterList);
        //列表项的点击事件
        mAdapter.setListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int p = (int) v.getTag();
                for (SimpleItem simpleItem : mAdapter.getListData()) {
                    simpleItem.setChecked(false);
                }
                mAdapter.getListData().get(p).setChecked(true);
                mFilterList.setVisibility(View.GONE);
                if (mAdapter.getListData() == mListLeft) {
                    mFilterTitleLeft.setText(mAdapter.getListData().get(p).getTitle());
                } else {
                    mFilterTitleRight.setText(mAdapter.getListData().get(p).getTitle());
                }
            }
        });
        mToolbar.inflateMenu(R.menu.filter_right);
        mToolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                myPopupWindow.show();
                return true;
            }
        });
        myPopupWindow = new MyPopupWindow((BaseActivity) getActivity(), R.layout.popup_window_right);
        ExpandableListView expandableListView = (ExpandableListView) myPopupWindow.getContentView().findViewById(R.id.expandable_list_view);
        Button confirm = (Button) myPopupWindow.getContentView().findViewById(R.id.confirm);
        groups = new ArrayList<>();
        final List<SimpleItem> mList0 = new ArrayList<>();
        final List<SimpleItem> mList1 = new ArrayList<>();
        final List<SimpleItem> mList2 = new ArrayList<>();
        //抽屉
        FilterTreeAdapter.Group group0 = new FilterTreeAdapter.Group(mList0, "告警等级");
        FilterTreeAdapter.Group group1 = new FilterTreeAdapter.Group(mList1, "设备类型");
        FilterTreeAdapter.Group group2 = new FilterTreeAdapter.Group(mList2, "状态");
        groups.add(group0);
        groups.add(group1);
        groups.add(group2);
        //为抽屉中每个列表循环添加列表项
        getMessage(getHttpService().getAlertTree(), new MySubscriber<AlertTree>() {
            @Override
            protected void onMyNext(AlertTree alertTree) {

                for (AlertTree.AlarmLevelBean alarmLevelBean : alertTree.getAlarmLevel()) {
                    mList0.add(new SimpleItem(alarmLevelBean.getId(), alarmLevelBean.getValue(), false));
                }

                for (AlertTree.AlarmTypeBean alarmTypeBean : alertTree.getAlarmType()) {
                    mList1.add(new SimpleItem(alarmTypeBean.getId(), alarmTypeBean.getValue(), false));
                }
                for (AlertTree.AlarmStateBean alarmStateBean : alertTree.getAlarmState()) {
                    mList2.add(new SimpleItem(alarmStateBean.getId(), alarmStateBean.getValue(), false));
                }
            }
        });


        filterTreeAdapter = new FilterTreeAdapter(groups);
        expandableListView.setAdapter(filterTreeAdapter);
        expandableListView.expandGroup(0);
        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                for (SimpleItem simpleItem : groups.get(groupPosition).getList()) {
                    simpleItem.setChecked(false);
                }
                filterTreeAdapter.mGroups.get(groupPosition).getList().get(childPosition).setChecked(true);
                filterTreeAdapter.notifyDataSetChanged();
                return true;
            }
        });
        //抽屉确定按钮点击事件
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onRefresh();
                myPopupWindow.dismiss();
            }
        });
    }

    @Override
    protected String getCacheKey() {
        return null;
    }
//
    @Override
    protected RecyclerView.Adapter getAdapter() {
        return new AlertListAdapter(this, mList);
    }
}
