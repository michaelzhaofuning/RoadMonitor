package com.sxhxjy.roadmonitor.view;

import android.content.Context;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.view.WindowManager;
import android.widget.PopupWindow;

import com.sxhxjy.roadmonitor.R;
import com.sxhxjy.roadmonitor.base.BaseActivity;

/**
 * Created by zm on 2017/1/17.
 */

public class MyPopup extends PopupWindow implements PopupWindow.OnDismissListener {
    /**
     * 弹出窗口
     */
    protected BaseActivity mActivity;
    protected View view;



    public MyPopup(BaseActivity activity, int viewId) {
        super((int) (150 * activity.getResources().getDisplayMetrics().density), ViewGroup.LayoutParams.WRAP_CONTENT);
        mActivity = activity;
        setAnimationStyle(R.style.popup_window_anim);//设置动画
        setFocusable(true);
        setOutsideTouchable(true);
        setBackgroundDrawable(mActivity.getResources().getDrawable(android.R.color.white));
        setOnDismissListener(this);
        view = activity.getLayoutInflater().inflate(viewId, null);
        setContentView(view);
    }


    @Override
    public void onDismiss() {
        WindowManager.LayoutParams params = mActivity.getWindow().getAttributes();
        params.alpha = 1;
        mActivity.getWindow().setAttributes(params);
    }

    public void show() {
        show(mActivity.findViewById(android.R.id.content));
    }

    public void show(View parentView) {
        int dip=(int) mActivity.getResources().getDisplayMetrics().density;
        showAtLocation(parentView, Gravity.TOP, 100*dip,82*dip );//设置位置(左右上下)
        WindowManager.LayoutParams params = mActivity.getWindow().getAttributes();
        params.alpha = 0.6f;
        mActivity.getWindow().setAttributes(params);
    }



    }
