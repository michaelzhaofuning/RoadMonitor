package com.sxhxjy.roadmonitor.ui.main;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;
import com.sxhxjy.roadmonitor.R;
import com.sxhxjy.roadmonitor.adapter.FilterTreeAdapter;
import com.sxhxjy.roadmonitor.adapter.HomeAdapter;
import com.sxhxjy.roadmonitor.adapter.HomethemeAdapter;
import com.sxhxjy.roadmonitor.base.BaseFragment;
import com.sxhxjy.roadmonitor.base.CacheManager;
import com.sxhxjy.roadmonitor.base.HttpResponse;
import com.sxhxjy.roadmonitor.base.MyApplication;
import com.sxhxjy.roadmonitor.base.MySubscriber;
import com.sxhxjy.roadmonitor.entity.GroupTree;
import com.sxhxjy.roadmonitor.entity.HomeTheme;
import com.sxhxjy.roadmonitor.entity.LoginData;
import com.sxhxjy.roadmonitor.entity.MonitorHome;
import com.sxhxjy.roadmonitor.entity.MonitorTower;
import com.sxhxjy.roadmonitor.entity.SimpleItem;
import com.sxhxjy.roadmonitor.entity.Station;
import com.sxhxjy.roadmonitor.ui.main.picture.TakeNotesActivity;
import com.sxhxjy.roadmonitor.view.MonitorMapView;
import com.tencent.mapsdk.raster.model.BitmapDescriptorFactory;
import com.tencent.mapsdk.raster.model.LatLng;
import com.tencent.mapsdk.raster.model.Marker;
import com.tencent.mapsdk.raster.model.MarkerOptions;
import com.tencent.tencentmap.mapsdk.map.MapView;
import com.tencent.tencentmap.mapsdk.map.TencentMap;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * 2016/9/26
 *  BPUBZ-HB3RQ-5SB5M-GLB4U-2A4QF-E7FT7
 * @author Michael Zhao
 */

public class HomeFragment extends BaseFragment implements HomeAdapter.OnItemClickLietener
        ,HomethemeAdapter.OnItemClick_Theme{
    /**\
     * 首页——fragment页
     */
    private TextView hide;
    private MapView mapview;
    private MonitorMapView monitorMapView;
    private LinearLayout monitorContainer;
    private String path= MyApplication.BASE_URL + "points/findAppRootPoint?groupId=4028812c57b6993b0157b6aca4410004";
    private OkHttpClient okHttpClient;
    private Request request;
    private RecyclerView lv_home;
    private RecyclerView rv_place;
    private List<SimpleItem> mList;
    private  SimpleItem simpleItem;
    private HomethemeAdapter adapter;
    private LoginData loginData;
    private TencentMap tencentMap;
    private ArrayList<Station> stations = new ArrayList<>();
    private LoginData.UserGroupsBean groupsBean = null; // marker group


    private LinearLayout layout_theme;
    Handler handler=new Handler(){
        //接受消息的线程
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 0:
                    layout_theme.setVisibility(View.GONE);
                    hide.setVisibility(View.VISIBLE);
                    hide.setText("网络连接失败，点击刷新");
                    hide.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            layout_theme.setVisibility(View.VISIBLE);
                            hide.setVisibility(View.GONE);
                            getplace();
                        }
                    });

                    break;
            }
        }
    };

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.home_fragment, null);
    }
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initToolBar(view, "首页", false);
        init(view);
//        getplace();
        monitorMapView = (MonitorMapView) view.findViewById(R.id.monitor_map);
        monitorContainer = (LinearLayout) view.findViewById(R.id.monitor_container);

        // get chart data by id
        monitorContainer.findViewById(R.id.get_chart_data).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Station station = (Station) v.getTag();
                if (station != null) {

                    MonitorFragment monitorFragment = ((MonitorFragment) ((MainActivity)getActivity()).fragments.get(1));
                    monitorFragment.cacheStation(groupsBean.getId(),groupsBean.getName());
                    monitorFragment.setIsFirst(); // load once Not called
                    monitorFragment.getChartDataById(station);
                    ((MainActivity)getActivity()).selectedBar(1);


                }


            }
        });
        mapview.onCreate(savedInstanceState);
        tencentMap = mapview.getMap();
        loginData = new Gson().fromJson(CacheManager.getInstance().get("login"), LoginData.class);

        if (loginData != null) {
            for (final LoginData.UserGroupsBean groupsBean : loginData.getUserGroups()) {
                // root
                if (groupsBean.getParentid().equals("0")) continue;

                LatLng latLng = new LatLng(groupsBean.getLatitude(), groupsBean.getLongitude());
                tencentMap.setCenter(latLng);
                tencentMap.setZoom(6);


                Marker marker = tencentMap.addMarker(new MarkerOptions()
                        .position(latLng)
                        .snippet(groupsBean.getDescription())
                        .title(groupsBean.getName() + "\n")
                        .anchor(0.5f, 0.5f)
                        .tag(groupsBean) // groupID
                        .icon(BitmapDescriptorFactory
                                .defaultMarker())
                        .draggable(true));
                marker.showInfoWindow();
            }
           /* tencentMap.setOnMarkerClickListener(new TencentMap.OnMarkerClickListener() {
                @Override
                public boolean onMarkerClick(Marker marker) {
                    final LoginData.UserGroupsBean groupsBean = (LoginData.UserGroupsBean) marker.getTag();
                    HomeFragment.this.groupsBean = groupsBean;
                    // we show monitor map
                    getMessage(getHttpService().getStations(groupsBean.getId()), new MySubscriber<List<Station>>() {
                        @Override
                        protected void onMyNext(List<Station> stations) {
                            HomeFragment.this.stations.clear();
                            HomeFragment.this.stations.addAll(stations);
                            monitorMapView.setMonitors(stations);

                            Picasso picasso = Picasso.with(getActivity());
                            picasso.load(MyApplication.BASE_URL_Img+groupsBean.getJgwPic().replace('\\','/'))
                                    .config(Bitmap.Config.RGB_565)
                                    .into(monitorMapView, new com.squareup.picasso.Callback() {
                                        @Override
                                        public void onSuccess() {
                                            monitorContainer.setVisibility(View.VISIBLE);
                                            monitorContainer.animate()
                                                    .alpha(1f).start();
                                        }

                                        @Override
                                        public void onError() {
                                            showToastMsg("加载传感器实景图失败...");
                                        }
                                    });
                        }
                    });

                    return false;
                }
            });*/
            tencentMap.setOnInfoWindowClickListener(new TencentMap.OnInfoWindowClickListener() {
                @Override
                public void onInfoWindowClick(Marker marker) {
                    final LoginData.UserGroupsBean groupsBean = (LoginData.UserGroupsBean) marker.getTag();
                    HomeFragment.this.groupsBean = groupsBean;

                    // we show monitor map
                    getMessage(getHttpService().getStations(groupsBean.getId()), new MySubscriber<List<Station>>() {
                        @Override
                        protected void onMyNext(List<Station> stations) {
                            HomeFragment.this.stations.clear();
                            HomeFragment.this.stations.addAll(stations);
                            monitorMapView.setMonitors(stations);

                            Picasso picasso = Picasso.with(getActivity());
                            picasso.load(MyApplication.BASE_URL_Img+groupsBean.getJgwPic().replace('\\','/'))
                                    .config(Bitmap.Config.RGB_565)
                                    .into(monitorMapView, new com.squareup.picasso.Callback() {
                                        @Override
                                        public void onSuccess() {
                                            monitorContainer.setVisibility(View.VISIBLE);
                                            monitorContainer.animate()
                                                    .alpha(1f).start();
                                        }

                                        @Override
                                        public void onError() {
                                            showToastMsg("加载传感器实景图失败...");
                                        }
                                    });
                        }
                    });

                }
            });
        }


        final TableLayout tableLayoutMonitor = (TableLayout) view.findViewById(R.id.table_monitors);
        getMessage(getHttpService().getHomeMonitors("", MyApplication.getMyApplication().getSharedPreference().getString("stationId", "")
        ), new MySubscriber<List<MonitorHome>>() {
            @Override
            protected void onMyNext(List<MonitorHome> monitorHomes) {
                for (MonitorHome monitorHome : monitorHomes) {
                    TableRow v = (TableRow) getActivity().getLayoutInflater().inflate(R.layout.table_item_monitor,null);
                    ((TextView)v.getChildAt(0)).setText(monitorHome.getCname());
                    ((TextView)v.getChildAt(1)).setText(monitorHome.getCount());
                    ((TextView)v.getChildAt(2)).setText(monitorHome.getAlarmCount());
                    ((TextView)v.getChildAt(3)).setText(monitorHome.getRunHour());
                    tableLayoutMonitor.addView(v);
                }
            }
        });

        final TableLayout tableLayoutTower = (TableLayout) view.findViewById(R.id.table_towers);
        getMessage(getHttpService().getHomeTowers(MyApplication.getMyApplication().getSharedPreference().getString("stationId", "")
        ), new MySubscriber<List<MonitorTower>>() {
            @Override
            protected void onMyNext(List<MonitorTower> monitorHomes) {
                for (MonitorTower monitorHome : monitorHomes) {
                    TableRow v = (TableRow) getActivity().getLayoutInflater().inflate(R.layout.table_item_tower,null);
                    ((TextView)v.getChildAt(0)).setText(monitorHome.getTypeName());
                    ((TextView)v.getChildAt(1)).setText(monitorHome.getTypeNum());
                    ((TextView)v.getChildAt(2)).setText(monitorHome.getAlarmNum());
                    tableLayoutTower.addView(v);
                }
            }
        });














    }

    public void getplace() {
        getHttpService().getGroups(MyApplication.getMyApplication().getSharedPreference().getString("gid","4")).subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .map(new Func1<HttpResponse<List<GroupTree>>, List<GroupTree>>() {
                    @Override
                    public List<GroupTree> call(HttpResponse<List<GroupTree>> listHttpResponse) {
                        return listHttpResponse.getData();
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<GroupTree>>() {
                    @Override
                    public void onCompleted() {


                    }

                    @Override
                    public void onError(Throwable e) {
                        Message message = new Message();
                        message.what = 0;
//                        message.obj = result;
                        handler.sendMessage(message);
                    }

                    @Override
                    public void onNext(List<GroupTree> data) {
                        mList=new ArrayList<SimpleItem>();
                        if (data != null) {
                            List<GroupTree> container = new ArrayList<GroupTree>();

                            findGroup(data, container);

                            for (GroupTree groupTree : container) {
                                SimpleItem item = new SimpleItem();
                                item.setTitle(groupTree.name);
                                item.setId(groupTree.id);
                                mList.add(item);
                            }

                            simpleItem = new SimpleItem(container.get(0).id, container.get(0).name, false);
                            LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
                            layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
                            rv_place.setLayoutManager(layoutManager);
                            adapter=new HomethemeAdapter(getActivity(),mList);
                            rv_place.setAdapter(adapter);
                            adapter.setClickLietener(HomeFragment.this);
                            adapter.setSeclection(0);
                            getOkHttp();
                        }
                    }
                });
    }
    /**
     *点击地点
     */



    public static void findGroup(List<GroupTree> d, List<GroupTree> container) {
        for (GroupTree groupTree : d) {
            if (groupTree.childrenGroup == null) {
                container.add(groupTree);
            } else {
                findGroup(groupTree.childrenGroup, container);
            }
        }
    }


    @Override
    public void setItemClick_Theme(int position,View v) {
        simpleItem=mList.get(position);
        if (loginData != null) {
            for (LoginData.UserGroupsBean groupsBean : loginData.getUserGroups()) {
                if (simpleItem.getId().equals(groupsBean.getId())) {
                    LatLng latLng = new LatLng(groupsBean.getLatitude(), groupsBean.getLongitude());
                    tencentMap.setCenter(latLng);
                    tencentMap.setZoom(9);
                }

            }
        }

        adapter.setSeclection(position);
        adapter.notifyDataSetChanged();
        getOkHttp();
    }

    /**
     *点击主题
     */

//    @Override
//    public void setItemClickListener(int position) {

//    }


    @Override
    public void setItemClickListener(int position) {
        if (position==0){
            startActivity(new Intent(getActivity(), TakeNotesActivity.class));
        } else{
            MonitorFragment monitorFragment = ((MonitorFragment) ((MainActivity)getActivity()).fragments.get(1));
            monitorFragment.cacheStation(simpleItem.getId(),simpleItem.getTitle());
            monitorFragment.setIsFirst(); // load once Not called
            monitorFragment.changeMonitor(position-1);
            ((MainActivity)getActivity()).selectedBar(1);
        }
    }

    private void getOkHttp() {
        new OkHttpClient().newCall(new Request.Builder()
//                .post(new FormBody.Builder().build())
                .url(path)
                .build()).enqueue(new Callback() {
            @Override//请求失败
            public void onFailure(Call call, IOException e) {
//                Toast.makeText(getActivity(),"请求失败",Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.code()!=200) return;
                final String result=response.body().string();//拿到json数据
                if (getActivity() == null) return;

                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        json(result);
                        Log.i("oooooooooo",result);
                    }
                });
            }
        });
    }
    public void json(String s){
        final HomeTheme theme= JSON.parseObject(s,HomeTheme.class);
        List<HomeTheme.DataBean> list=new ArrayList<>();
        HomeTheme.DataBean hd=new HomeTheme.DataBean();
        hd.setName("图像监测");
        list.add(hd);
        if (theme.getData() == null) return;
        for (HomeTheme.DataBean hds:theme.getData()){
            list.add(hds);
        }

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        lv_home.setLayoutManager(layoutManager);
        HomeAdapter adapter=new HomeAdapter(getActivity(),list);
        lv_home.setAdapter(adapter);
        adapter.setClickLietener(this);
    }
    public void init(View view){
        lv_home= (RecyclerView) view.findViewById(R.id.list_home);
        rv_place= (RecyclerView) view.findViewById(R.id.place_rv);
        mapview = (MapView) view.findViewById(R.id.map_view);
        hide= (TextView) view.findViewById(R.id.home_hide);
        layout_theme= (LinearLayout) view.findViewById(R.id.layout_theme);
    }

    @Override
    public void onDestroy() {
        mapview.onDestroy();
        super.onDestroy();
    }
    @Override
    public void onPause() {
        mapview.onPause();
        super.onPause();
    }
    @Override
    public void onResume() {
        mapview.onResume();
        super.onResume();
    }
    @Override
    public void onStop() {
        mapview.onStop();
        super.onStop();
    }


}
