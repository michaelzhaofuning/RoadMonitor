package com.sxhxjy.roadmonitor.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.sxhxjy.roadmonitor.R;
import com.sxhxjy.roadmonitor.entity.HomeTheme;

import java.util.List;

/**
 * Created by zm on 2016/12/29.
 */

public class HomeAdapter  extends RecyclerView.Adapter<HomeAdapter.ViewHolder> implements View.OnClickListener{
    private List<HomeTheme.DataBean> list;
    private LayoutInflater inflater;//布局填充器
    private RecyclerView mRecyclerView;
    private HomeAdapter.OnItemClickLietener clickLietener;//点击事件接口
    private Context context;
    private int []img={R.drawable.pic,R.drawable.theme01,R.drawable.theme02,R.drawable.theme03 };
    public void setClickLietener(HomeAdapter.OnItemClickLietener clickLietener){
        this.clickLietener=clickLietener;
    }

    public HomeAdapter(Context context, List<HomeTheme.DataBean> list) {
        this.list = list;
        inflater = LayoutInflater.from(context);
        this.context=context;
    }

    @Override
    public HomeAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = inflater.inflate(R.layout.home_list_item, parent, false);
        itemView.setOnClickListener(this);
        return new HomeAdapter.ViewHolder(itemView);
//        return new HomeAdapter.ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.home_list_item, parent, false));
    }


    @Override
    public void onBindViewHolder(HomeAdapter.ViewHolder holder, int position) {
        HomeTheme.DataBean map=list.get(position);
        WindowManager wm = (WindowManager)context.getSystemService(Context.WINDOW_SERVICE);
        int width = wm.getDefaultDisplay().getWidth();
        holder.home_theme.setMinimumHeight(width/5);
        holder.home_theme.setMinimumWidth(width/4);
//        holder.home_list_item.setMinimumWidth((width/4)-10);
        holder.iv.setImageResource(img[position]);
        if (position==0)
            holder.name.setText(map.getName());
        else
            holder.name.setText(map.getName()+"  优");
    }

    @Override
    public int getItemCount() {
        return list != null ? list.size() : 0;
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        mRecyclerView=recyclerView;
    }

    @Override
    public void onClick(View v) {
        int childAdapterPosition = mRecyclerView.getChildAdapterPosition(v);
        if (clickLietener!=null) {
            clickLietener.setItemClickListener(childAdapterPosition);
        }
    }
    //ViewHolder
    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView iv;
        TextView name;
        RelativeLayout home_list_item;
        LinearLayout home_theme;
        public ViewHolder(View itemView) {
            super(itemView);
            home_theme= (LinearLayout) itemView.findViewById(R.id.home_theme);
            name=(TextView)itemView.findViewById(R.id.tv_home_item);
            home_list_item= (RelativeLayout) itemView.findViewById(R.id.home_list_item);
            iv= (ImageView) itemView.findViewById(R.id.theme_img);
        }
    }
    //点击事件接口
    public interface OnItemClickLietener{
        void setItemClickListener(int position);
    }

}
