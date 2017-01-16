package com.sxhxjy.roadmonitor.ui.main;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.TimePicker;

import com.sxhxjy.roadmonitor.R;
import com.sxhxjy.roadmonitor.base.BaseActivity;
import com.sxhxjy.roadmonitor.base.MonitorPosition;
import com.sxhxjy.roadmonitor.base.MyApplication;
import com.sxhxjy.roadmonitor.base.MySubscriber;
import com.sxhxjy.roadmonitor.entity.MonitorTypeTree;
import com.sxhxjy.roadmonitor.entity.SimpleItem;
import com.sxhxjy.roadmonitor.view.MyLinearLayout;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Random;

/**
 * 2016/9/29
 *
 * @author Michael Zhao
 */

public class AddDataCorrelationActivity extends BaseActivity {
    /**
     * 数据关联页
     */
    private String[] aLocation;
    private List<SimpleItem> mLocationList = new ArrayList<>();
    private List<SimpleItem> mTypeList = new ArrayList<>();
    private String[] aType;
    private ArrayList<SimpleItem> positionItems = new ArrayList<>();
    private ArrayList<SimpleItem> positionItemsCorrelation = new ArrayList<>();
    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.CHINA);
    private String title, titleCorrelation;
    private String startTime, endTime;
    private String stationId;
    private MyLinearLayout station, stationCorrelation;
    private MyLinearLayout location, locationCorrelation;
    private int[] colors;
    private Random random = new Random();
    private boolean gettingMsg;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_data_correlation_activity);
        initToolBar("数据关联", true);
        stationId = getIntent().getStringExtra("stationId");
        colors = new int[]{
                Color.BLACK,
                getResources().getColor(android.R.color.holo_red_light),
                getResources().getColor(android.R.color.holo_blue_bright),
                getResources().getColor(android.R.color.holo_orange_dark),
                getResources().getColor(android.R.color.holo_green_dark),
                getResources().getColor(android.R.color.holo_purple),
                getResources().getColor(R.color.colorPrimaryDark),
                Color.argb(255, random.nextInt(256), random.nextInt(256), random.nextInt(256))
        };

        mToolbar.inflateMenu(R.menu.confirm_right);
        mToolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if (positionItems.isEmpty() || positionItemsCorrelation.isEmpty() || startTime == null || endTime == null) {
                    showToastMsg("请完善分析条件");
                    return false;
                }

                //Bundle传值
                Bundle b = new Bundle();
                b.putSerializable("positionItems", positionItems);
                b.putSerializable("positionItemsCorrelation", positionItemsCorrelation);
                b.putString("title", title);
                b.putString("titleCorrelation", titleCorrelation);
                b.putLong("start", simpleDateFormat.parse(startTime, new ParsePosition(0)).getTime());
                b.putLong("end", simpleDateFormat.parse(endTime, new ParsePosition(0)).getTime());
                Intent data = new Intent();
                data.putExtras(b);
                setResult(RESULT_OK, data);
                finish();
                return true;
            }
        });


    }

    public void monitorType(final View view) {
        if (stationId == null || gettingMsg) return;
        if (mTypeList.isEmpty()) {
            getMessage(getHttpService().getMonitorTypeTree(stationId), new MySubscriber<List<MonitorTypeTree>>() {
                @Override
                protected void onMyNext(List<MonitorTypeTree> monitorTypeTrees) {
                    mTypeList.clear();
                    for (MonitorTypeTree monitorTypeTree : monitorTypeTrees) {
                        if (monitorTypeTree.getChildrenPoint() != null) {
                            for (MonitorTypeTree.ChildrenPointBean childrenPointBean : monitorTypeTree.getChildrenPoint()) {
                                mTypeList.add(new SimpleItem(childrenPointBean.getId(), childrenPointBean.getName(), false));
                            }
                        }

                    }

                    aType = new String[mTypeList.size()];
                    for (int i = 0; i < mTypeList.size(); i++) {
                        aType[i] = mTypeList.get(i).getTitle();
                    }

                    showDialogType(view);
                }
                @Override
                public void onCompleted() {
                    super.onCompleted();
                    gettingMsg = false;
                }
                @Override
                public void onStart() {
                    super.onStart();
                    gettingMsg = true;
                }

                @Override
                public void onError(Throwable e) {
                    super.onError(e);
                    gettingMsg = false;
                }
            });
        } else {
            for (SimpleItem item : mTypeList) {
                item.setChecked(false);
            }
            showDialogType(view);
        }
    }

    public void monitorLocation(final View view) {
        if (view.getId() != R.id.correlation_position) {
            location = (MyLinearLayout) view;
        } else {
            locationCorrelation = (MyLinearLayout) view;
        }
        if (gettingMsg) return;
        if (!mLocationList.isEmpty()) {
            showDialogPosition(view);
        } else {
            final Random random = new Random();
            for (SimpleItem item :mTypeList) {
                if (item.isChecked()) {
                    getMessage(getHttpService().getPositions(item.getId(), stationId), new MySubscriber<List<MonitorPosition>>() {
                        @Override
                        protected void onMyNext(List<MonitorPosition> monitorPositions) {
                            aLocation = new String[monitorPositions.size()];
                            int i = 0;
                            int colorIndex = 0;
                            mLocationList.clear();
                            for (MonitorPosition position : monitorPositions) {
                                SimpleItem simpleItem = new SimpleItem(position.getId(), position.getName(), false);
                                simpleItem.setCode(position.code);
                                simpleItem.setColor(Color.argb(255, random.nextInt(256), random.nextInt(256), random.nextInt(256)));
                                mLocationList.add(simpleItem);
                                aLocation[i++] = position.getName();
                            }
                            showDialogPosition(view);
                        }
                        @Override
                        public void onCompleted() {
                            super.onCompleted();
                            gettingMsg = false;
                        }
                        @Override
                        public void onStart() {
                            super.onStart();
                            gettingMsg = true;
                        }

                        @Override
                        public void onError(Throwable e) {
                            super.onError(e);
                            gettingMsg = false;
                        }
                    });
                }
            }
        }
    }

    private void showDialogType(final View view) {
        new AlertDialog.Builder(AddDataCorrelationActivity.this).setTitle("选择监测因素").setSingleChoiceItems(aType, 0, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                MyLinearLayout myLinearLayout = (MyLinearLayout) view;
                myLinearLayout.setContent(aType[which]);
                mTypeList.get(which).setChecked(true);
                if (view.getId() == R.id.correlation_type) {
                    titleCorrelation = mTypeList.get(which).getTitle();
                } else {
                    title = mTypeList.get(which).getTitle();
                }
                dialog.dismiss();
                if (view.getId() == R.id.correlation_type) {
                    if (locationCorrelation != null)
                        locationCorrelation.setContent("");
                } else {
                    if (location != null)
                        location.setContent("");
                }
                mLocationList.clear();
            }
        }).create().show();
    }

    private void showDialogPosition(final View view) {
        final boolean[] aTypeChecked = new boolean[mLocationList.size()];

        new AlertDialog.Builder(AddDataCorrelationActivity.this).setTitle("选择监测点位置").setMultiChoiceItems(aLocation, aTypeChecked, new DialogInterface.OnMultiChoiceClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                mLocationList.get(which).setChecked(isChecked);
            }
        }).setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (view.getId() == R.id.correlation_position)
                    positionItemsCorrelation.clear();
                else
                    positionItems.clear();

                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < aTypeChecked.length; i++) {
                    if (aTypeChecked[i]) {
                        sb.append(aLocation[i]).append("  ");
                        if (view.getId() == R.id.correlation_position)
                            positionItemsCorrelation.add(mLocationList.get(i));
                        else
                            positionItems.add(mLocationList.get(i));
                    }
                }
                MyLinearLayout myLinearLayout = (MyLinearLayout) view;
                myLinearLayout.setContent(sb.toString());
            }
        }).create().show();
    }

    public void timeStart(View view) {
        chooseTime((MyLinearLayout) view);
    }

    public void timeEnd(View view) {
        chooseTime((MyLinearLayout) view);
    }

    public void chooseTime(final MyLinearLayout myLinearLayout) {
        final StringBuilder sb = new StringBuilder();
        Date date = new Date(System.currentTimeMillis());
        Calendar c = Calendar.getInstance();
        c.setTimeInMillis(System.currentTimeMillis());
        int year = c.get(Calendar.YEAR);
        new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                sb.append(year);
                sb.append("-");
                sb.append(monthOfYear + 1);
                sb.append("-");
                sb.append(dayOfMonth);
                sb.append("  ");
                new TimePickerDialog(AddDataCorrelationActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        sb.append(hourOfDay < 10 ? "0" + hourOfDay : hourOfDay);
                        sb.append(":");
                        sb.append(minute < 10 ? "0" + minute : minute);
                        myLinearLayout.setContent(sb.toString());

                        if (myLinearLayout.getId() == R.id.start_time)
                            startTime = sb.toString();
                        else
                            endTime = sb.toString();
                    }
                }, 0, 0, true).show();
            }
        }, year, date.getMonth(), date.getDate()).show();

    }

    public void chooseStation(View view) {

        Intent intent = new Intent(this, StationListActivity.class);
        startActivityForResult(intent, StationListActivity.REQUEST_CODE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == StationListActivity.REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            stationId = data.getStringExtra("stationId");
            station.setContent(data.getStringExtra("stationName"));
            mTypeList.clear();
            mLocationList.clear();
            positionItems.clear();
            positionItemsCorrelation.clear();
            ViewGroup v = (ViewGroup) findViewById(R.id.container);
            for (int i = 0; i < v.getChildCount(); i++) {
                if (v.getChildAt(i) instanceof MyLinearLayout) {
                    MyLinearLayout linearLayout = (MyLinearLayout) v.getChildAt(i);
                    if (linearLayout.getId() != R.id.station) {
                        linearLayout.setContent("");
                    }
                }


            }

        }
    }
}
