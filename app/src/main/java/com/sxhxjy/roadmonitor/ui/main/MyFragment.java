package com.sxhxjy.roadmonitor.ui.main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sxhxjy.roadmonitor.R;
import com.sxhxjy.roadmonitor.base.BaseFragment;
import com.sxhxjy.roadmonitor.base.MyApplication;
import com.sxhxjy.roadmonitor.base.UpdateUtil;
import com.sxhxjy.roadmonitor.view.MyLinearLayout;

/**
 * 2016/9/26
 *
 * @author Michael Zhao
 */

public class MyFragment extends BaseFragment {
    /**\
     * 我的——fragment页
     */
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.my_fragment, null);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initToolBar(view, "我的", false);

        MyLinearLayout version = (MyLinearLayout) view.findViewById(R.id.version);
        version.setContent(UpdateUtil.getVersion(getActivity()));
        version.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UpdateUtil.update(getActivity(), MyApplication.BASE_URL + "user/changeVersion");
            }
        });
    }


}
