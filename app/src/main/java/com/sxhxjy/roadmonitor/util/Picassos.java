package com.sxhxjy.roadmonitor.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.sxhxjy.roadmonitor.R;

/**
 * Created by zm on 2016/12/14.
 */

public class Picassos {


    public static void getImg(Context context, String path, final ImageView iv){
        Picasso picasso= Picasso.with(context);
        picasso.load(path).placeholder(R.drawable.placeholder).error(R.drawable.placeholder)
                .config(Bitmap.Config.RGB_565)
                .into(iv);
    }
    public static void getImg1(Context context, String path, final ImageView iv){
        Picasso picasso= Picasso.with(context);
        picasso.load(path).placeholder(R.drawable.placeholder).error(R.drawable.placeholder)
                .resize(200,200)
                .config(Bitmap.Config.RGB_565)
                .into(iv);
    }
    public static String getdouble(double dou){
        String dous=dou+"000000";
        String s="";
        char[] chars = dous.toCharArray();
        for (int i=0;i<4;i++){
            s=s+chars[i];
        }
        return s;
    }
}
