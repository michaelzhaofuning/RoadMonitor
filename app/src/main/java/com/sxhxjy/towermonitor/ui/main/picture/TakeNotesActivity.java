package com.sxhxjy.towermonitor.ui.main.picture;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.sxhxjy.towermonitor.R;
import com.sxhxjy.towermonitor.adapter.OldAdapter;
import com.sxhxjy.towermonitor.base.BaseActivity;
import com.sxhxjy.towermonitor.base.MyApplication;
import com.sxhxjy.towermonitor.entity.RecordResult;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class TakeNotesActivity extends BaseActivity {
    private LinearLayout layout_notes,rlayout_notes;
    private TextView tv;
    private GridView lv;
//    public String getPath= "http://124.163.206.251:8088/AirDefencePro/web/picCompare/findChanges";
    public String getPath= MyApplication.BASE_URL+"picCompare/findChanges";
    private OldAdapter adapter;
    private String result;
    private List<RecordResult.DataBean.ContentBean> data;
    Handler handler=new Handler(){
        //接受消息的线程
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 0:
                    Gsons();
                    break;
                case 1:
                    Toast.makeText(TakeNotesActivity.this,"网络连接失败",Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_take_notes);
        initToolBar("比对记录", true);
        initView();
        getOkHttp();

    }

    public void Gsons(){
        RecordResult record= JSON.parseObject(result,RecordResult.class);
        data=record.getData().getContent();
        adapter=new OldAdapter(this,data);
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                RecordResult.DataBean.ContentBean rc=data.get(position);
                Intent intent=new Intent(TakeNotesActivity.this,NewPic_Activity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("data",rc);
                intent.putExtras(bundle);
                TakeNotesActivity.this.startActivity(intent);
            }
        });
    }
    private void getOkHttp() {
        showWaitingDialog(null);
        new OkHttpClient().newCall(new Request.Builder()
//                .post(new FormBody.Builder().build())
                .url(getPath)
                .build()).enqueue(new Callback() {
            @Override//请求失败
            public void onFailure(Call call, IOException e) {
                Log.e("take notes", call.toString() + e);
                dismissWaitingDialog();
            }
            @Override//请求成功
            public void onResponse(Call call, Response response) throws IOException {
                dismissWaitingDialog();
                Log.i("aaaaaaaa",response.code()+"------code=pic");
                String code=response.code()+"";
                if (code.equals("200")){
                    result=response.body().string();
                    Message message = new Message();
                    message.what = 0;
                    message.obj = result;
                    handler.sendMessage(message);
                }else {
                    Message message = new Message();
                    message.what = 1;
                    message.obj = code;
                    handler.sendMessage(message);
                }
            }
        });
    }
    private void initView() {
        lv= (GridView) findViewById(R.id.lv);
        layout_notes= (LinearLayout) findViewById(R.id.layout_notes);
        rlayout_notes= (LinearLayout) findViewById(R.id.rlayout_notes);
        tv= (TextView) findViewById(R.id.NoData);
    }
}
