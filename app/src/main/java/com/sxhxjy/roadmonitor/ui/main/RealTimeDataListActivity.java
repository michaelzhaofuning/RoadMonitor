package com.sxhxjy.roadmonitor.ui.main;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;

import com.sxhxjy.roadmonitor.R;
import com.sxhxjy.roadmonitor.adapter.RealTimeDataListAdapter;
import com.sxhxjy.roadmonitor.base.BaseActivity;
import com.sxhxjy.roadmonitor.base.BaseListFragment;
import com.sxhxjy.roadmonitor.base.HttpResponse;
import com.sxhxjy.roadmonitor.entity.RealTimeData;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import rx.Observable;

/**
 * 2016/9/26
 *
 * @author Michael Zhao
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
            mList = MonitorFragment.monitorFragment.mRealTimes;
            countDownTimer.start();
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
