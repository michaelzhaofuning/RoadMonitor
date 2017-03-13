package com.sxhxjy.towermonitor.base;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;


import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 *
 * http://124.163.206.250:8080/road-monitor.apk
 *
 *
 * Extends application to add some methods
 *
 * //////////////////////////////////////////////////////////////////////////////
 * 1. IP !!!! 2.app name, Logo 3.flash.jpg
 *
 * If you want to change package name, do NOT forget to change Build.gradle
 * //////////////////////////////////////////////////////////////////////////////
 *
 * @author Michael Zhao
 */
public class MyApplication extends Application {
    private static MyApplication app;

    // socket Hostname in socket service
    public static final int PORT = 8000;

    //    private static final String ADDRESS = "192.168.1.172";

    public static String ADDRESS = "124.163.206.250";
//    public static final String ADDRESS = "124.163.206.251";


    public static String BASE_IP = "101.201.141.139:8170";
//    public static String BASE_IP = "192.168.1.172:8088";

    public static String BASE_URL = "http://"+ BASE_IP + "/IronTowerPro/webapp/";

//    public static String BASE_URL = "http://"+ BASE_IP + "/AirDefencePro/web/";

    public static String BASE_URL_Img = "http://"+ BASE_IP+"/";
//    public static final String BASE_URL = "http://192.168.1.172:8088/ClearPro/web/";
    private HttpService httpService;

    public static MyApplication getMyApplication() {
        return app;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        app = this;
//        Fresco.initialize(this);
        initHttp();

    }

    public void initHttp() {
        // refresh static field
//        BASE_URL = "http://"+ BASE_IP + "/ClearPro/web/";
//        BASE_URL_Img = "http://"+ BASE_IP+"/";

        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(logging);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(httpClient.build())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        httpService = retrofit.create(HttpService.class);



        Intent intent = new Intent(this, SocketService.class);
        stopService(intent);

        startService(intent);
    }

    public SharedPreferences getSharedPreference() {
        return MyApplication.getMyApplication().getSharedPreferences("app", Context.MODE_PRIVATE);
    }

    public HttpService getHttpService() {
        return httpService;
    }

    public void exit() {
        // close database
        CacheManager.getInstance().closeDB();
        System.exit(0);
//        Process.killProcess(Process.myPid());
    }
}
