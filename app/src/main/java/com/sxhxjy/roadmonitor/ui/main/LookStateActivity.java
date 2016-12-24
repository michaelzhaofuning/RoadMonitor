package com.sxhxjy.roadmonitor.ui.main;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;


import com.sxhxjy.roadmonitor.R;
import com.sxhxjy.roadmonitor.adapter.LookStateAdapter;
import com.sxhxjy.roadmonitor.base.BaseActivity;
import com.sxhxjy.roadmonitor.base.HttpService;
import com.sxhxjy.roadmonitor.base.MyApplication;
import com.sxhxjy.roadmonitor.entity.States;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LookStateActivity extends BaseActivity {
    private RecyclerView rv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_look_state);
        initToolBar("设备状态", true);
        rv= (RecyclerView) findViewById(R.id.state_recycler);
        getRetrofit();
    }




    private void getRetrofit(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(MyApplication.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        HttpService userBiz = retrofit.create(HttpService.class);
        Call<States> call = userBiz.getGong1(MyApplication.getMyApplication().getSharedPreference().getString("gid",""));

        call.enqueue(new Callback<States>()
        {
            @Override
            public void onResponse(Call<States> call, retrofit2.Response<States> response) {
                if (response.code() != 200) return;
                States states= response.body();

                List<States.DataBean> sd=states.getData();
                StaggeredGridLayoutManager manager=new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);
                rv.setLayoutManager(manager);
                LookStateAdapter adapter=new LookStateAdapter(sd,LookStateActivity.this);
                rv.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<States> call, Throwable t) {

            }
        });
    }
}
