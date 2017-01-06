package com.sxhxjy.roadmonitor.adapter;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sxhxjy.roadmonitor.R;
import com.sxhxjy.roadmonitor.entity.States;

import java.util.List;

/**
 * Created by zm on 2016/12/23.
 */

public class LookStateAdapter extends RecyclerView.Adapter<LookStateAdapter.MyViewHolder> {
    private List<States.DataBean> list;
    private Context cotent;
    private int colors;
    public LookStateAdapter(List<States.DataBean> list, Context c){
        this.cotent=c;
        this.list=list;
    }
    //开启初始化控件内部类
    @Override
    public LookStateAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new LookStateAdapter.MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.stateitem, parent, false));

    }
    //控件赋值及事件处理
    @TargetApi(Build.VERSION_CODES.M)
    @Override
    public void onBindViewHolder(LookStateAdapter.MyViewHolder holder, int position) {
        holder.itemView.setTag(position);
        holder.name.setText(list.get(position).getOrgName()+">"+list.get(position).getPointName()+">"+list.get(position).getStationName());
        String states="";

        if (list.get(position).getStationState() == null) {
            states = "故障";
            colors = R.color.color2;
        } else {
            switch (list.get(position).getStationState()) {
                case "0":
                    states = "正常";
                    colors = R.color.colorPrimary;
                    break;
                case "1":
                  /*  states = "停电";
                    colors = R.color.color1;
                    break;*/
                case "2":
                    states = "异常";
                    colors = R.color.color2;
                    break;
            }
        }
        holder.state.setText(states);
//        holder.stateTime.setText(list.get(position).getSaveTime());
        holder.state.setBackgroundColor(cotent.getResources().getColor(colors));;



    }
    //返回集合长度（item总数）
    @Override
    public int getItemCount() {
        return list != null ? list.size() : 0;
    }
    class MyViewHolder extends RecyclerView.ViewHolder{
        TextView name,state,stateTime;
        public MyViewHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.unit_Name);
            state = (TextView) itemView.findViewById(R.id.unit_State);
            stateTime= (TextView) itemView.findViewById(R.id.stateTime);
        }
    }
}
