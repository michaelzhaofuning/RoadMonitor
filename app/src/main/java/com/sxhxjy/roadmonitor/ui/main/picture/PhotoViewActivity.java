package com.sxhxjy.roadmonitor.ui.main.picture;

import android.app.Activity;
import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;
import com.sxhxjy.roadmonitor.R;
import com.sxhxjy.roadmonitor.base.MyApplication;
import com.sxhxjy.roadmonitor.entity.RecordResult;
import com.sxhxjy.roadmonitor.view.ContrastView;

import java.io.File;
import java.io.IOException;

/**
 * 2016/11/11
 *
 * @author Michael Zhao
 *         比对结果
 *         Put intent extras:
 *
 *  result: ContentBean
 *  pos: int
 *
 */

public class PhotoViewActivity extends Activity {
    public ContrastView photoView;
    private int pos = 0;
    private float ratioX = 0;
    private float ratioY = 0;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.photo_view_activity);
        initToolBar("对比结果", true);
        photoView = (ContrastView) findViewById(R.id.photo_view);
        TextView textView = (TextView) findViewById(R.id.info);
        RecordResult.DataBean.ContentBean contrastResult = (RecordResult.DataBean.ContentBean) getIntent().getSerializableExtra("result");
        pos = getIntent().getIntExtra("pos", 0);

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("请稍候...");
        progressDialog.show();

        if (contrastResult != null) {
            if (contrastResult.getOldPicDetail().getMoniter1X() == null) {
                Toast.makeText(this, "数据为空", Toast.LENGTH_SHORT).show();
                finish();
                return;
            }
//            textView.setText("x变化:          " + contrastResult.getXChange() + "px" + "              y变化:            " + contrastResult.getYChange() + "px\n\n变化阈值：     " + contrastResult.getChangeThreshold());
            ratioX = Float.parseFloat(contrastResult.getOldPicDetail().getMoniter1X());
            ratioY = Float.parseFloat(contrastResult.getOldPicDetail().getMoniter1Y());

//            if (getIntent().getStringExtra("from") != null) {
//                showOld(false, null, new MyCallback(false, null, TakePictureActivity.PATH + "picture_contrast.jpg"), TakePictureActivity.PATH + "picture.jpg");
//                return;
//            }
final String rawOld = contrastResult.getOldPicDetail().getPicDirFilename().replace('\\','/');
final String rawNew = contrastResult.getNewPicList().get(pos).getComPicPath().replace('\\','/');
            // swap
            final String urlNew = MyApplication.BASE_URL_Img + "uploadPhotos"+rawOld.substring(rawOld.indexOf('/', 6));
            final String urlOld = MyApplication.BASE_URL_Img + "uploadPhotos"+rawNew.substring(rawNew.indexOf('/', 6));
            showOld(true, urlOld, new MyCallback(true, urlNew, null), null);
        }
    }

    public void showOld(boolean isUrl, final String oldPic, Callback callback, final String path) {
        Picasso picasso = Picasso.with(this);
        if (isUrl) {
            picasso.load(oldPic).config(Bitmap.Config.RGB_565).into(photoView, callback);
        } else {
            picasso.load(new File(path)).config(Bitmap.Config.RGB_565).into(photoView, callback);
        }

//      .load("http://i1.hdslb.com/video/c7/c7d913dccc8bf4f5daf54c692b80f4b4.jpg")
    }

    public void initToolBar(String title, boolean canBack) {
        Toolbar mToolbar = (Toolbar) findViewById(R.id.tool_bar);
        if (mToolbar != null) {
            mToolbar.setVisibility(View.VISIBLE);
            mToolbar.setBackgroundResource(R.color.colorPrimary);
            TextView mTitle = (TextView) mToolbar.findViewById(R.id.toolbar_title);
            mToolbar.setTitleTextColor(Color.WHITE);
            if (title != null && mTitle != null) mTitle.setText(title);
            mToolbar.setTitle("");
            if (canBack) {
                mToolbar.setNavigationIcon(R.mipmap.navigation_icon);
                mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onBackPressed();
                    }
                });
            }
        }
    }

    private class MyCallback implements Callback {
        private String url, path;
        private boolean isUrl;


        public MyCallback(boolean isUrl, final String url, final String path) {
            this.isUrl = isUrl;
            if (isUrl) this.url = url;
            else this.path = path;
        }

        @Override
        public void onSuccess() {
            Log.e("picasso", "onSuccess !");

            // width and height of imageView is measured
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        final Bitmap bitmap;
                        if (isUrl) {
                            bitmap = Picasso.with(PhotoViewActivity.this)
                                    .load(url).config(Bitmap.Config.RGB_565).get();
                        } else {
                            bitmap = Picasso.with(PhotoViewActivity.this)
                                    .load(new File(path)).config(Bitmap.Config.RGB_565).get();
                        }
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                photoView.setContrast(bitmap, ratioX, ratioY);
                                if (progressDialog != null) {
                                    progressDialog.dismiss();
                                    progressDialog = null;
                                }
                            }
                        });
                    } catch (IOException e) {
                        e.printStackTrace();
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(PhotoViewActivity.this, "加载图片失败", Toast.LENGTH_LONG).show();
                                if (progressDialog != null) {
                                    progressDialog.dismiss();
                                    progressDialog = null;
                                }
                            }
                        });
                    }
                }
            }).start();
        }

        @Override
        public void onError() {
            Log.e("picasso", "onError !");
            Toast.makeText(PhotoViewActivity.this, "加载图片失败", Toast.LENGTH_LONG).show();
            if (progressDialog != null) {
                progressDialog.dismiss();
            }
        }
    }
}
