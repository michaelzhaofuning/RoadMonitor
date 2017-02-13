package com.sxhxjy.roadmonitor.ui.main;

import android.app.DownloadManager;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import com.sxhxjy.roadmonitor.R;
import com.sxhxjy.roadmonitor.base.BaseActivity;
import com.sxhxjy.roadmonitor.base.CacheManager;
import com.sxhxjy.roadmonitor.base.MyApplication;
import com.sxhxjy.roadmonitor.base.MySubscriber;
import com.sxhxjy.roadmonitor.entity.LoginData;
import com.sxhxjy.roadmonitor.util.ActivityUtil;
import com.sxhxjy.roadmonitor.util.MD5_X;
import com.sxhxjy.roadmonitor.util.Utils;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


/**
 * Log in
 *
 * adb push C:\Users\dell\Desktop\yiwu\trunk\Ewuexchange\app\app-debug.apk /data/local/tmp/com.efulink.ewuexchange
 *
 * adb shell pm install -r "/data/local/tmp/com.efulink.ewuexchange"
 *
 * @author Michael Zhao
 */
public class LoginActivity extends BaseActivity implements View.OnClickListener {
    /**
     * 登录页
     */
    public ImageView imageViewQQ;
    public ImageView imageViewWechat;
    public ImageView imageViewWeibo;
    private Context mContext;
    private Button mLogin;
    private TextView mReg, mForget;
    private EditText mUser, mPassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(getResources().getColor(android.R.color.transparent));
            getWindow().getDecorView().setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        }

        initView();
    }

    private void initView() {
        mContext= this;
        mUser = (EditText) findViewById(R.id.user);
        mPassword = (EditText) findViewById(R.id.password);
        mLogin = (Button) findViewById(R.id.login);
        mLogin.setOnClickListener(this);
    }

    private boolean isPhoneValid() {
        if (Utils.checkPhone(mUser.getText().toString())) {
            return true;
        } else {
            Handler h = new Handler();
            h.postDelayed(new Runnable() {
                @Override
                public void run() {
                    mUser.setError("手机号不正确");
                    mUser.requestFocus();
                }
            }, 300); // must delay or focus change TOO FAST !!!!!!
            // what the fuck !!!!!!

            return false;
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.login:
                OkHttpClient okHttpClient = new OkHttpClient.Builder().build();
                Request request = new Request.Builder()
//                        .url("http://124.163.206.250:8080/ClearPro/web/webuser/appLogin?account="+mUser.getText().toString()+"&password="+MD5_X.md5(mPassword.getText().toString().getBytes()).toLowerCase())
                        .url("http://192.168.1.172:8088/ClearPro/web/webuser/appLogin?account="+mUser.getText().toString()+"&password="+MD5_X.md5(mPassword.getText().toString().getBytes()).toLowerCase())
                        .get().build();
                okHttpClient.newCall(request).enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                showToastMsg("网络连接失败");
                            }
                        });

                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        if (response.code() != 200) return;
                        String result = response.body().string();

                        final JSONObject jsonObject = JSON.parseObject(result);
                        if (!jsonObject.getString("resultCode").equals("200")) {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    showToastMsg(jsonObject.getString("resultMessage"));

                                }
                            });
                            return;
                        }
                        String data = jsonObject.getJSONObject("data").toJSONString();
                        LoginData loginData = JSON.parseObject(data, LoginData.class);

                        // init http
                        MyApplication.BASE_IP = "http://"+"192.168.1.172:8088";
//                        MyApplication.BASE_IP = "http://"+loginData.getUser().getPriUserGroup().serverIp + ":" + loginData.getUser().getPriUserGroup().serverPort;
                        MyApplication.ADDRESS = loginData.getUser().getPriUserGroup().serverIp;
                        MyApplication.getMyApplication().initHttp();

                        CacheManager.getInstance().set("login", new Gson().toJson(loginData));
                        MyApplication.getMyApplication().getSharedPreference().edit().putString("account", loginData.getUser().getAccount()).apply();
                        MyApplication.getMyApplication().getSharedPreference().edit().putString("uid", loginData.getUser().getId()).apply();
                        MyApplication.getMyApplication().getSharedPreference().edit().putString("gid", loginData.getUser().getGid()).apply();
                        ActivityUtil.startActivityForResult(LoginActivity.this, StationListActivity.class, null, 111);
                        finish();
                    }
                });


                break;
        }
    }



    private boolean isValid() {
        if (Utils.checkPhone(mUser.getText().toString()))
            if (Utils.checkPwd(mPassword.getText().toString()))
                return true;
            else {
                mPassword.setError("请输入合法的密码");
                mPassword.requestFocus();
                return false;
            }
        else {
            mUser.setError("请输入正确的手机号");
            mUser.requestFocus();
            return false;
        }
    }

    @Override
    public void onBackPressed() {
        MyApplication.getMyApplication().exit();
    }

}
