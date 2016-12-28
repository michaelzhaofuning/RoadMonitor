package com.sxhxjy.roadmonitor.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.sxhxjy.roadmonitor.R;
import com.sxhxjy.roadmonitor.base.MyApplication;
import com.sxhxjy.roadmonitor.entity.RecordResult;
import com.sxhxjy.roadmonitor.util.Picassos;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Created by zm on 2016/12/14.
 */

public class OldAdapter extends BaseAdapter {
    private Context context;
    private List<RecordResult.DataBean.ContentBean> list;
    public OldAdapter(Context context, List<RecordResult.DataBean.ContentBean> list){
        this.context=context;
        this.list=list;
    }
    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);

    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        OldPicViewHolder oldvh=null;
        View OldPicView = null;
        final RecordResult.DataBean.ContentBean rd=list.get(position);
            if (convertView == null) {
                oldvh=new OldPicViewHolder();
//                //获取原图item布局和控件
                OldPicView = LayoutInflater.from(context).inflate(R.layout.oldlist_item, null);
                oldvh.oiv= (ImageView) OldPicView.findViewById(R.id.oiv);
                oldvh.id= (TextView) OldPicView.findViewById(R.id.devId);
                oldvh.otime= (TextView) OldPicView.findViewById(R.id.otime);
                OldPicView.setTag(oldvh);
                convertView = OldPicView;
            } else {
                oldvh= (OldPicViewHolder) convertView.getTag();
            }
            //为原图控件赋值
            String PicUrl=(MyApplication.BASE_URL_Img+rd.getOldPicDetail().getPicPathPress()).replace("\\", "/");
            Picassos.getImg1(context,PicUrl,oldvh.oiv);
            oldvh.id.setText("设备编号："+rd.getOldPicDetail().getDevId());
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.CHINA);
            long time =rd.getNewPicList().get(0).getSaveTime();
            oldvh.otime.setText("最新比对时间："+sdf.format(new Date(time)));
        return convertView;
    }
    class OldPicViewHolder{
        TextView id,otime;
        ImageView oiv;
    }


}
