package com.sxhxjy.roadmonitor.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
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
    public static String getX(double dou){
        String dous=Math.abs(dou)+"000000";
        String s="";
        char[] chars = dous.toCharArray();
        for (int i=0;i<4;i++){
            s=s+chars[i];
        }
        if (dou>0){
            return "右移"+s;
        }else if (dou<0){
            return "左移"+s;
        }else {
            return "X无变化";
        }
    }
    public static String getY(double dou){

        String dous=Math.abs(dou)+"000000";
        String s="";
        char[] chars = dous.toCharArray();
        for (int i=0;i<4;i++){
            s=s+chars[i];
        }

        if (dou>0){
            return "下移"+s;
        }else if (dou<0){
            return "上移"+s;
        }else {
            return "Y无变化";
        }
    }

    public static SpannableStringBuilder tvColor(String s,int X1,int X2 ,int Y1,int Y2 ,int i){
        int[] c={Color.parseColor("#ff8800"),Color.parseColor("#aa66cc"),
                Color.parseColor("#0099cc"),Color.parseColor("#1abc9c")};



        SpannableStringBuilder style=new SpannableStringBuilder(s);
        style.setSpan(new ForegroundColorSpan(c[i]),X1,X2,Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        style.setSpan(new ForegroundColorSpan(c[i]),Y1,Y2,Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        return style;
    }
    public static SpannableStringBuilder getXY(double X,double Y,int i){
        final StringBuilder sb = new StringBuilder();
        sb.append("测点"+i+": ");
        String sx="";
        String sy="";
        char[] charx = (Math.abs(X)+"000000").toCharArray();
        char[] chary = (Math.abs(Y)+"000000").toCharArray();
        for (int j=0;j<4;j++){
            sx=sx+charx[j];
            sy=sy+chary[j];
        }
        int X1 =0;
        int X2 = 0;

        String sss="";

        String X_yi="";
        String Y_yi="";
        if (X>0){
            X_yi= "右移"+sx;
        }else if (X<0){
            X_yi= "左移"+sx;
        }else {
            X_yi= "X无变化";
        }
        X1=sb.length();
        X2=X_yi.length()+X1;
        sb.append(X_yi+",");
        int Y1 =0;
        int Y2 =0;

        if (Y>0){
            Y_yi= "下移"+sy;

        }else if (Y<0){
            Y_yi= "上移"+sy;

        }else {
            Y_yi= "Y无变化";
        }
        Y1=sb.length();
        Y2=Y_yi.length()+Y1;
        sb.append(Y_yi);
        return tvColor(sb.toString(),X1,X2,Y1,Y2,i-1);

    }
}
