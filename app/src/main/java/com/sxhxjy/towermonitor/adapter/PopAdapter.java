package com.sxhxjy.towermonitor.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.sxhxjy.towermonitor.R;

import java.util.List;
import java.util.Map;

/**
 * Created by zm on 2017/1/17.
 */

public class PopAdapter extends BaseAdapter {
    private Context context;
    private List<Map<String,String>> list;

    public PopAdapter(Context context, List<Map<String,String>> list) {
        this.context = context;
        this.list = list;
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
        ViewHolder vh = null;
        View view = null;
        Map<String,String> map = list.get(position);
        if (convertView == null) {
            vh=new ViewHolder();
//                //获取原图item布局和控件
            view = LayoutInflater.from(context).inflate(R.layout.gv_item, null);
            vh.name= (TextView) view.findViewById(R.id.gv_tv);
            vh.oiv= (ImageView) view.findViewById(R.id.gv_iv);
            view.setTag(vh);
            convertView = view;
        } else {
            vh = (ViewHolder) convertView.getTag();
        }
            vh.name.setText(map.get("title").toString());
            vh.oiv.setImageResource(Integer.parseInt(map.get("img")));
        return convertView;
    }

    class ViewHolder {
        TextView name;
        ImageView oiv;
    }
}