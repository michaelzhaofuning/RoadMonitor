package com.sxhxjy.roadmonitor.ui.main;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.TimePicker;

import com.sxhxjy.roadmonitor.R;
import com.sxhxjy.roadmonitor.base.BaseActivity;
import com.sxhxjy.roadmonitor.base.MonitorPosition;
import com.sxhxjy.roadmonitor.base.MyApplication;
import com.sxhxjy.roadmonitor.base.MySubscriber;
import com.sxhxjy.roadmonitor.entity.MonitorTypeTree;
import com.sxhxjy.roadmonitor.entity.SimpleItem;
import com.sxhxjy.roadmonitor.util.ActivityUtil;
import com.sxhxjy.roadmonitor.view.DeleteView;
import com.sxhxjy.roadmonitor.view.MyLinearLayout;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

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
    private String code;
//    private ArrayList<>
    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.CHINA);
    private String title;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_data_contrast_activity);
        initToolBar("数据对比", true);

        mToolbar.inflateMenu(R.menu.confirm_right);
        mToolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                Bundle b = new Bundle();
                DeleteView myLinearLayout = (DeleteView) timeContent.getChildAt(0);
                String[] strings = myLinearLayout.getContent().split("  ----  ");
                b.putString("code", code);
                b.putString("title", title);
                b.putLong("start", simpleDateFormat.parse(strings[0], new ParsePosition(0)).getTime());
                b.putLong("end", simpleDateFormat.parse(strings[1], new ParsePosition(0)).getTime());

                Intent data = new Intent();
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
        if (mTypeList.isEmpty()) {
            getMessage(getHttpService().getMonitorTypeTree(MyApplication.getMyApplication().getSharedPreference().getString("gid","")), new MySubscriber<List<MonitorTypeTree>>() {
                @Override
                protected void onMyNext(List<MonitorTypeTree> monitorTypeTrees) {
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

                    new AlertDialog.Builder(AddDataContrastActivity.this).setTitle("选择监测因素").setSingleChoiceItems(aType, 0, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            MyLinearLayout myLinearLayout = (MyLinearLayout) view;
                            myLinearLayout.setContent(aType[which]);
                            mTypeList.get(which).setChecked(true);
                            title = mTypeList.get(which).getTitle();
                            dialog.dismiss();
                        }
                    }).create().show();


                }
            });
        } else {
            new AlertDialog.Builder(AddDataContrastActivity.this).setTitle("选择监测因素").setSingleChoiceItems(aType, 0, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    MyLinearLayout myLinearLayout = (MyLinearLayout) view;
                    myLinearLayout.setContent(aType[which]);
                    mTypeList.get(which).setChecked(true);
                    dialog.dismiss();
                }
            }).create().show();
        }
    }

    public void monitorLocation(final View view) {
        if (!mLocationList.isEmpty()) {
            showDialogPosition(view);
        } else {
            for (SimpleItem item :mTypeList) {
                if (item.isChecked()) {
                    getMessage(getHttpService().getPositions(item.getId(), MyApplication.getMyApplication().getSharedPreference().getString("gid", "")), new MySubscriber<List<MonitorPosition>>() {
                        @Override
                        protected void onMyNext(List<MonitorPosition> monitorPositions) {
                            aLocation = new String[monitorPositions.size()];
                            int i = 0;
                            for (MonitorPosition position : monitorPositions) {
                                mLocationList.add(new SimpleItem(position.getId(), position.getName(), false));
                                aLocation[i++] = position.getName();
                            }
                            showDialogPosition(view);
                        }
                    });
                }
            }
        }
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
                int checked = 0;
                for (int i = 0; i < aTypeChecked.length; i++) {
                    if (aTypeChecked[i]) {
                        checked++;
                        sb.append(aLocation[i]).append("  ");
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

    public void timeStart(View view) {
        chooseTime((MyLinearLayout) view);
    }

    public void timeEnd(View view) {
        chooseTime((MyLinearLayout) view);
    }

    public StringBuilder chooseTime(final MyLinearLayout myLinearLayout) {
        final StringBuilder sb = new StringBuilder();

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
                    }
                }, 0, 0, true).show();
            }
        }, 2016, 0, 1).show();

        return sb;
    }

    public void addTime(View view) {
        final StringBuilder sb = new StringBuilder();

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
                        sb.append("  ----  ");
                        new DatePickerDialog(AddDataContrastActivity.this, new DatePickerDialog.OnDateSetListener() {
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
                                        timeContent.addView(new DeleteView(AddDataContrastActivity.this, sb.toString(), timeContent));                              }
                                }, 0, 0, true).show();
                            }
                        }, 2016, 0, 1).show();
                        showToastMsg("请选择结束时间");
                    }
                }, 0, 0, true).show();
            }
        }, 2016, 0, 1).show();
        showToastMsg("请选择开始时间");
    }
}
