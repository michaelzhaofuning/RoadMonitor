package com.sxhxjy.towermonitor.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.sxhxjy.towermonitor.R;
import com.sxhxjy.towermonitor.entity.SimpleItem;

import java.util.List;


public class HomethemeAdapter extends RecyclerView.Adapter<HomethemeAdapter.ViewHolder> implements View.OnClickListener{
	private List<SimpleItem>  list;
	private LayoutInflater inflater;//布局填充器
	private RecyclerView mRecyclerView;
	private OnItemClick_Theme clickLietener;//点击事件接口
	private Context context;
	private int clickTemp = -1;
	//标识选择的Item
	public void setSeclection(int position) {
		clickTemp = position;
	}
	public void setClickLietener(OnItemClick_Theme clickLietener){
		this.clickLietener=clickLietener;
		}

public HomethemeAdapter(Context context, List<SimpleItem> list) {
		this.list = list;
		inflater = LayoutInflater.from(context);
		this.context=context;
		}

@Override
public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		View itemView = inflater.inflate(R.layout.home_theme_item, parent, false);
		itemView.setOnClickListener(this);
		return new ViewHolder(itemView);
//        return new HomeAdapter.ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.home_list_item, parent, false));
		}


@Override
public void onBindViewHolder(final ViewHolder holder, int position) {
	SimpleItem map = list.get(position);
	WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
	int width = wm.getDefaultDisplay().getWidth();
	holder.theme_layout.setMinimumWidth(width / 3);
	holder.name.setText(map.getTitle());
	if (clickTemp == position) {
//		holder.theme_layout.setBackgroundResource(R.drawable.border_color1);
		holder.theme_layout.setEnabled(false);
//		holder.name.setBackgroundResource(R.drawable.placeholder2);

	} else {
//		holder.theme_layout.setBackgroundResource(R.drawable.border_color2);
		holder.theme_layout.setEnabled(true);
//		holder.name.setBackgroundResource(R.drawable.gray_white_selector);
	}

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
		clickLietener.setItemClick_Theme(childAdapterPosition,v);
		}
		}
//ViewHolder
public static class ViewHolder extends RecyclerView.ViewHolder {
	TextView name;
	LinearLayout theme_layout;
	public ViewHolder(View itemView) {
		super(itemView);
		name=(TextView)itemView.findViewById(com.sxhxjy.towermonitor.R.id.tv_home_theme);
		theme_layout= (LinearLayout) itemView.findViewById(com.sxhxjy.towermonitor.R.id.theme_layout);
	}
}
//点击事件接口
public interface OnItemClick_Theme{
	void setItemClick_Theme(int position, View v);

}

}
