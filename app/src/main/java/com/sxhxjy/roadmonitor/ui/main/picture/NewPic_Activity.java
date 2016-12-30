package com.sxhxjy.roadmonitor.ui.main.picture;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.sxhxjy.roadmonitor.R;
import com.sxhxjy.roadmonitor.base.BaseActivity;
import com.sxhxjy.roadmonitor.base.MyApplication;
import com.sxhxjy.roadmonitor.entity.RecordResult;
import com.sxhxjy.roadmonitor.util.Picassos;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class NewPic_Activity extends BaseActivity {
    private ListView lv_newpic;
    private List<RecordResult.DataBean.ContentBean.NewPicListBean> list;
    private  RecordResult.DataBean.ContentBean contentBean;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_pic);
        contentBean=(RecordResult.DataBean.ContentBean) getIntent().getSerializableExtra("data");
        initToolBar("对比图", true);
        TextView toolbarRight = (TextView) mToolbar.findViewById(R.id.toolbar_right);
        toolbarRight.setText("查看图表");
        toolbarRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(NewPic_Activity.this, ChartActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("data",contentBean);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
        list=contentBean.getNewPicList();
        lv_newpic= (ListView) findViewById(R.id.lv_newpic);
        myAdapter ada=new myAdapter();
        lv_newpic.setAdapter(ada);
        lv_newpic.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(NewPic_Activity.this, PhotoViewActivity.class);
                Bundle b = new Bundle();
                b.putInt("pos", position);
                b.putSerializable("result", contentBean);
                i.putExtras(b);
                startActivity(i);
            }
        });
    }



    class myAdapter extends BaseAdapter {
        private LayoutInflater inflater;//布局填充器
        public myAdapter(){
            inflater=(LayoutInflater)NewPic_Activity.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int i) {
            return list.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(final int i, View view, ViewGroup viewGroup) {
            NewPicViewHolder newvh=null;
            if (view == null) {
                newvh=new NewPicViewHolder();
                view=inflater.inflate(R.layout.newlist_item,null);
                newvh.niv= (ImageView)view.findViewById(R.id.niv);
                newvh.ntv1= (TextView) view.findViewById(R.id.ntv1);
                newvh.ntv2= (TextView) view.findViewById(R.id.ntv2);
                newvh.ntv3= (TextView) view.findViewById(R.id.ntv3);
                newvh.ntv4=(TextView) view.findViewById(R.id.ntv4);
                newvh.ntv5= (TextView) view.findViewById(R.id.ntv5);
                view.setTag(newvh);
            }else {
            newvh= (NewPicViewHolder) view.getTag();
        }
            //为对比图控件赋值
            RecordResult.DataBean.ContentBean.NewPicListBean np=list.get(i);
            String PicUrl=(MyApplication.BASE_URL_Img+np.getComPicPathPress()).replace("\\", "/");
            Picassos.getImg(NewPic_Activity.this,PicUrl,newvh.niv);
            newvh.ntv1.setText(Picassos.getXY(np.getX1Change(),np.getY1Change(),1));
            newvh.ntv2.setText(Picassos.getXY(np.getX2Change(),np.getY2Change(),2));
            newvh.ntv3.setText(Picassos.getXY(np.getX3Change(),np.getY3Change(),3));
            newvh.ntv4.setText(Picassos.getXY(np.getX4Change(),np.getY4Change(),4));
//坐标
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.CHINA);
            long time = np.getSaveTime();
            newvh.ntv5.setText(""+sdf.format(new Date(time)));
            return view;
        }
        class NewPicViewHolder{
            TextView ntv1,ntv2,ntv3,ntv4,ntv5;
            ImageView niv;
        }
    }
}
