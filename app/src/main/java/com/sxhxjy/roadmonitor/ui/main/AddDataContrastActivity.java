package com.sxhxjy.roadmonitor.ui.main;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.TimePicker;

import com.sxhxjy.roadmonitor.R;
import com.sxhxjy.roadmonitor.base.BaseActivity;
import com.sxhxjy.roadmonitor.base.MonitorPosition;
import com.sxhxjy.roadmonitor.base.MySubscriber;
import com.sxhxjy.roadmonitor.entity.MonitorTypeTree;
import com.sxhxjy.roadmonitor.entity.SimpleItem;
import com.sxhxjy.roadmonitor.view.DeleteView;
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

public class AddDataContrastActivity extends BaseActivity {

    private View addTimeMultiple;
    private View addTimeSingle;
    private LinearLayout timeContent;
    private String[] aLocation;
    private List<SimpleItem> mLocationList = new ArrayList<>();
    private List<SimpleItem> mTypeList = new ArrayList<>();
    private String[] aType;
    private ArrayList<SimpleItem> positionItems = new ArrayList<>();
//    private ArrayList<>
    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.CHINA);
    private String title;
    private String startTime;
    private String endTime;
    public int startDay; //

    private String stationId;
    private MyLinearLayout station;
    private MyLinearLayout location;

    private int[] colors;
    private Random random = new Random();
    private boolean gettingMsg = false;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_data_contrast_activity);
        initToolBar("数据对比", true);
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

                if (positionItems.isEmpty()) {
                    showToastMsg("请完善分析条件");
                    return false;
                } else if (positionItems.size() > 1) {
                    if (startTime == null || endTime == null)      {
                        showToastMsg("请完善分析条件");
                        return false;
                    }

                } else {
                    if (timeContent.getChildCount() == 0) {
                        showToastMsg("请完善分析条件");
                        return false;
                    }
                }

                Bundle b = new Bundle();

                b.putString("title", title);
                b.putSerializable("positionItems", positionItems);
                if (positionItems.size() > 1) {
                    b.putLong("start", simpleDateFormat.parse(startTime, new ParsePosition(0)).getTime());
                    b.putLong("end", simpleDateFormat.parse(endTime, new ParsePosition(0)).getTime());
                } else {
                    ArrayList<String> times = new ArrayList<String>();
                    for (int i = 0; i < timeContent.getChildCount(); i++) {
                        DeleteView myLinearLayout = (DeleteView) timeContent.getChildAt(i);
                        times.add(myLinearLayout.getContent());
                    }
                    b.putStringArrayList("times", times);

                }

                Intent data = new Intent();
                data.putExtras(b);
                setResult(RESULT_OK, data);
                finish();
                return true;
            }
        });

        addTimeMultiple = findViewById(R.id.add_time_multiple);
        timeContent = (LinearLayout) findViewById(R.id.container);
        addTimeSingle = findViewById(R.id.add_time_single);
    }

    public void monitorType(final View view) {
        if (stationId == null || gettingMsg) return;
        if (mTypeList.isEmpty()) {
            getMessage(getHttpService().getMonitorTypeTree(stationId), new MySubscriber<List<MonitorTypeTree>>() {
                @Override
                protected void onMyNext(List<MonitorTypeTree> monitorTypeTrees) {
                    mTypeList.clear();
                    for (MonitorTypeTree monitorTypeTree : monitorTypeTrees) {
                        mTypeList.add(new SimpleItem(monitorTypeTree.getId(), monitorTypeTree.getName(), false));

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
        location = (MyLinearLayout) view;
        if (gettingMsg) return;
        if (!mLocationList.isEmpty()) {
            showDialogPosition(view);
        } else {
            for (SimpleItem item :mTypeList) {
                if (item.isChecked()) {
                    getMessage(getHttpService().getPositions(item.getId(), stationId), new MySubscriber<List<MonitorPosition>>() {
                        @Override
                        protected void onMyNext(List<MonitorPosition> monitorPositions) {
                            mLocationList.clear();
                            aLocation = new String[monitorPositions.size()];
                            int i = 0;
                            int colorIndex = 0;
                            for (MonitorPosition position : monitorPositions) {
                                SimpleItem simpleItem = new SimpleItem(position.getId(), position.getName(), false);
                                simpleItem.setCode(position.code);

                                simpleItem.setColor(colors[colorIndex++ % colors.length]);


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
        new AlertDialog.Builder(AddDataContrastActivity.this).setTitle("选择监测因素").setSingleChoiceItems(aType, 0, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                MyLinearLayout myLinearLayout = (MyLinearLayout) view;
                myLinearLayout.setContent(aType[which]);
                mTypeList.get(which).setChecked(true);
                title = mTypeList.get(which).getTitle();
                mLocationList.clear();
                if (location != null) {
                    location.setContent("");
                }
                dialog.dismiss();
            }
        }).create().show();
    }


    private void showDialogPosition(final View view) {
        final boolean[] aTypeChecked = new boolean[mLocationList.size()];

        new AlertDialog.Builder(AddDataContrastActivity.this).setTitle("选择监测点位置").setMultiChoiceItems(aLocation, aTypeChecked, new DialogInterface.OnMultiChoiceClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                mLocationList.get(which).setChecked(isChecked);
            }
        }).setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                StringBuilder sb = new StringBuilder();
                positionItems.clear();
                int checked = 0;
                for (int i = 0; i < aTypeChecked.length; i++) {
                    if (aTypeChecked[i]) {
                        checked++;
                        sb.append(aLocation[i]).append("  ");
                        positionItems.add(mLocationList.get(i));
                    }
                }
                MyLinearLayout myLinearLayout = (MyLinearLayout) view;
                myLinearLayout.setContent(sb.toString());
                if (checked > 1) {
                    addTimeSingle.setVisibility(View.VISIBLE);
                    addTimeMultiple.setVisibility(View.GONE);
                } else {
                    addTimeSingle.setVisibility(View.GONE);
                    addTimeMultiple.setVisibility(View.VISIBLE);
                }
            }
        }).create().show();
    }

    public void chooseStation(View view) {
        station = (MyLinearLayout) view;
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
//            positionItemsCorrelation.clear();
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



    public void timeStart(View view) {
        chooseTime((MyLinearLayout) view);
    }

    public void timeEnd(View view) {
        chooseTime((MyLinearLayout) view);
    }

    public StringBuilder chooseTime(final MyLinearLayout myLinearLayout) {
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
                new TimePickerDialog(AddDataContrastActivity.this, new TimePickerDialog.OnTimeSetListener() {
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

        return sb;
    }

    public void addTime(View view) {
        final StringBuilder sb = new StringBuilder();
        final Date date = new Date(System.currentTimeMillis());
        final Calendar c = Calendar.getInstance();
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
                startDay = dayOfMonth;
                sb.append("  ");
                new TimePickerDialog(AddDataContrastActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        sb.append(hourOfDay < 10 ? "0" + hourOfDay : hourOfDay);
                        sb.append(":");
                        sb.append(minute < 10 ? "0" + minute : minute);
                        sb.append("  ----  ");
                        new DatePickerDialog(AddDataContrastActivity.this, new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                sb.append(year);
                                sb.append("-");
                                sb.append(monthOfYear + 1);
                                if (dayOfMonth < startDay) {
                                    showToastMsg("不能小于开始时间，请重新选择！");
                                } else {
                                sb.append("-");
                                sb.append(dayOfMonth);
                                sb.append("  ");
                                new TimePickerDialog(AddDataContrastActivity.this, new TimePickerDialog.OnTimeSetListener() {
                                    @Override
                                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                                        sb.append(hourOfDay < 10 ? "0" + hourOfDay : hourOfDay);
                                        sb.append(":");
                                        sb.append(minute < 10 ? "0" + minute : minute);
                                        timeContent.addView(new DeleteView(AddDataContrastActivity.this, sb.toString(), timeContent));
                                    }
                                }, 0, 0, true).show();
                                }
                            }
                        }, c.get(Calendar.YEAR), date.getMonth(), date.getDate()).show();

                        showToastMsg("请选择结束时间");
                    }
                }, 0, 0, true).show();
            }
        }, year, date.getMonth(), date.getDate()).show();
        showToastMsg("请选择开始时间");
    }
}
