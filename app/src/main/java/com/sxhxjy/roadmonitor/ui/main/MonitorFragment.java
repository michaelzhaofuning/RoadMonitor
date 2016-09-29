package com.sxhxjy.roadmonitor.ui.main;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;

import com.sxhxjy.roadmonitor.R;
import com.sxhxjy.roadmonitor.adapter.FilterTreeAdapter;
import com.sxhxjy.roadmonitor.adapter.SimpleListAdapter;
import com.sxhxjy.roadmonitor.base.BaseActivity;
import com.sxhxjy.roadmonitor.base.BaseFragment;
import com.sxhxjy.roadmonitor.entity.SimpleItem;
import com.sxhxjy.roadmonitor.util.ActivityUtil;
import com.sxhxjy.roadmonitor.view.MyPopupWindow;

import java.util.ArrayList;
import java.util.List;

/**
 * 2016/9/26
 *
 * @author Michael Zhao
 */

public class MonitorFragment extends BaseFragment implements View.OnClickListener {

    private String stationId = "40288164568be6a401568bf1e5100000";
    private TextView mTextViewCenter;
    private ImageView mImageViewLeft;
    private List<SimpleItem> mListLeft = new ArrayList<>();
    private List<SimpleItem> mListRight = new ArrayList<>();
    private SimpleListAdapter mAdapter;
    private TextView mFilterTitleLeft, mFilterTitleRight;
    private RecyclerView mFilterList;
    private MyPopupWindow myPopupWindow;
    private FilterTreeAdapter filterTreeAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.monitor_fragment, null);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initToolBar(getView(), getArguments().getString("stationName"), false);
        mTextViewCenter = (TextView) getView().findViewById(R.id.toolbar_title);
        mImageViewLeft = (ImageView) getView().findViewById(R.id.toolbar_left);
        mImageViewLeft.setVisibility(View.VISIBLE);
        mImageViewLeft.setImageResource(R.mipmap.history);
        mImageViewLeft.setOnClickListener(this);
        mTextViewCenter.setOnClickListener(this);
        mTextViewCenter.setCompoundDrawablesWithIntrinsicBounds(null, null, getResources().getDrawable(R.mipmap.expand), null);
        mTextViewCenter.setCompoundDrawablePadding(20);

        mFilterTitleLeft = (TextView) view.findViewById(R.id.filter_left);
        mFilterTitleRight = (TextView) view.findViewById(R.id.filter_right);

        mListLeft.add(new SimpleItem("", "南中环桥", false));
        mListLeft.add(new SimpleItem("", "祥云桥", false));
        mListLeft.add(new SimpleItem("", "漪汾桥", false));
        mListRight.add(new SimpleItem("", "最近一天", false));
        mListRight.add(new SimpleItem("", "最近一周", false));
        mListRight.add(new SimpleItem("", "最近一月", false));

        mFilterTitleLeft.setOnClickListener(this);

        mFilterTitleRight.setOnClickListener(this);

        mFilterList = (RecyclerView) view.findViewById(R.id.filter_list);
        mFilterList.setLayoutManager(new LinearLayoutManager(getActivity()));
        mAdapter = new SimpleListAdapter(this, mListLeft);
        mFilterList.setAdapter(mAdapter);
        mAdapter.setFilterList(mFilterList);

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

        List<FilterTreeAdapter.Group> groups = new ArrayList<>();
        List<SimpleItem> mList0 = new ArrayList<>();
        mList0.add(new SimpleItem("", "温度检测", true));
        mList0.add(new SimpleItem("", "温度检测", false));
        mList0.add(new SimpleItem("", "温度检测", false));
        List<SimpleItem> mList1 = new ArrayList<>();
        mList1.add(new SimpleItem("", "位移检测", false));
        mList1.add(new SimpleItem("", "伸缩检测", false));
        List<SimpleItem> mList2 = new ArrayList<>();
        mList2.add(new SimpleItem("", "应变检测", false));
        mList2.add(new SimpleItem("", "受力检测", false));
        mList2.add(new SimpleItem("", "受力检测", false));
        mList1.add(new SimpleItem("", "挠度检测", false));
        FilterTreeAdapter.Group group0 = new FilterTreeAdapter.Group(mList0, "环境主题");
        FilterTreeAdapter.Group group1 = new FilterTreeAdapter.Group(mList1, "变形主题");
        FilterTreeAdapter.Group group2 = new FilterTreeAdapter.Group(mList2, "应变主题");
        groups.add(group0);
        groups.add(group1);
        groups.add(group2);

        filterTreeAdapter = new FilterTreeAdapter(groups);
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
                myPopupWindow.dismiss();
                filterTreeAdapter.notifyDataSetChanged();
                return true;
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == StationListActivity.REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            stationId = data.getStringExtra("stationId");
            mTextViewCenter.setText(data.getStringExtra("stationName"));
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
                mAdapter.setListData(mListLeft);
                mAdapter.notifyDataSetChanged();

                if (mFilterList.getVisibility() == View.GONE)
                    mFilterList.setVisibility(View.VISIBLE);
                else
                    mFilterList.setVisibility(View.GONE);
                break;
            case R.id.filter_right:
                mAdapter.setListData(mListRight);
                mAdapter.notifyDataSetChanged();

                if (mFilterList.getVisibility() == View.GONE)
                    mFilterList.setVisibility(View.VISIBLE);
                else
                    mFilterList.setVisibility(View.GONE);

                break;
        }
    }
}
