package com.sxhxjy.roadmonitor.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.sxhxjy.roadmonitor.R;

/**
 * Created by zm on 2017/1/17.
 */

public class MyForm extends LinearLayout {
    private TextView title,content;
    private LinearLayout layout;
    private View fv;

    public MyForm(Context context) {
        super(context);
    }

    public MyForm(Context context, AttributeSet attrs) {
        super(context, attrs);
        //找到对应XML给获得控件 1上下问 2获得XML
        LayoutInflater.from(getContext()).inflate(R.layout.form_item, this);
        title= (TextView) findViewById(R.id.form_title);
        content= (TextView) findViewById(R.id.form_content);
        layout= (LinearLayout) findViewById(R.id.form_layout);
        fv=findViewById(R.id.fv);
    }

    public MyForm(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setForm(String titles,String contents,boolean flag){
        fv.setBackgroundColor(getResources().getColor(R.color.grey_text_color));
        title.setText(titles);
        content.setText(contents);
        if (flag){
            layout.setBackgroundColor(getResources().getColor(R.color.colorPrimaryTranslucent));
            title.setTextColor(getResources().getColor(R.color.white));
            content.setTextColor(getResources().getColor(R.color.white));
        }else {
            layout.setBackgroundColor(getResources().getColor(R.color.white));
            title.setTextColor(getResources().getColor(R.color.black));
            content.setTextColor(getResources().getColor(R.color.black));
        }
    }

}
