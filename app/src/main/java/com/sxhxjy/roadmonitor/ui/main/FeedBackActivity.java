package com.sxhxjy.roadmonitor.ui.main;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.sxhxjy.roadmonitor.R;
import com.sxhxjy.roadmonitor.base.BaseActivity;
import com.sxhxjy.roadmonitor.base.MyApplication;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class FeedBackActivity extends BaseActivity {
    private EditText E_Mail_feed,feedtext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed_back);
        initToolBar("意见反馈",true);
        E_Mail_feed= (EditText) findViewById(R.id.E_Mail_feed);
        feedtext= (EditText) findViewById(R.id.feedtext);
    }

    public void feeds(View v){
        if (feed()){
            getFeedFile();
            Toast.makeText(this,"感谢您的反馈意见，我们将为您不断改进！",Toast.LENGTH_SHORT).show();
        }
    }
    public boolean feed(){
        if (feedtext.getText().toString().length()==0){
            Toast.makeText(this,"请输入您的宝贵意见",Toast.LENGTH_SHORT).show();
            return false;
        }else if (feedtext.getText().toString().length()<10){
            Toast.makeText(this,"最少输入10个字",Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
    public void getFeedFile(){
        try {
            String path = this.getFilesDir().getPath();//获得文件目录
            File file = new File(path, "feed.txt");//文件实例化参数目录，文件名
            if (file.exists()) {//如果文件存在
                String info =feedtext.getText().toString();
                FileOutputStream fos =FeedBackActivity.this.openFileOutput("feed.txt", this.MODE_APPEND);
                fos.write(info.getBytes());//开始写
                fos.close();//
            }else {
                setFeedFile();
            }
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    public void setFeedFile(){
        try {
            FileOutputStream fos =FeedBackActivity.this.openFileOutput("feed.txt", this.MODE_PRIVATE);
            String info = MyApplication.getMyApplication().getSharedPreference().getString("account","null")
                    + "##" + E_Mail_feed.getText().toString()+"##"+feedtext.getText().toString();//写入的格式
            fos.write(info.getBytes());//开始写
            fos.close();//
            Log.i("ooooo", "保存成功");//LOG提示
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void getFeedTxt(){

    }
}
