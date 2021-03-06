package com.sxhxjy.towermonitor.base;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.net.Uri;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.sxhxjy.towermonitor.R;
import com.sxhxjy.towermonitor.ui.main.MainActivity;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

/**
 * 2016/11/28
 *
 * @author Michael Zhao
 */

public class SocketService extends Service {

    private Socket socket;
//    private PrintWriter out;
//    private BufferedReader in;
    private DataInputStream in;
    private DataOutputStream out;
    public static int lastAlertNum = 0;

    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            try {
                socket = new Socket(MyApplication.ADDRESS, MyApplication.PORT);
//                    out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())));
                in = new DataInputStream(socket.getInputStream());
                out = new DataOutputStream(socket.getOutputStream());
//                    in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                out.writeUTF(MyApplication.getMyApplication().getSharedPreference().getString("gid", "0"));
                out.flush(); // do NOT forget

            } catch (IOException e) {
                e.printStackTrace();
                return;
            }

            while (!t.isInterrupted()) {
                try {
                    if (in == null)
                        break;
//                        socket.sendUrgentData(7);
                    if (in.available() > 0) {
                        String s = in.readUTF();
                        int num = JSON.parseObject(s).getIntValue("alarmNum");
                        Log.e("socket", s);

                        if (num > 0 && lastAlertNum != num) {
                            sendNtf(num);
                            lastAlertNum = num;
                        }
                    } else {
                        Log.e("socket", "not available  " + in.available());
                    }

                    Thread.sleep(5000);

                } catch (IOException e) {
                    e.printStackTrace();
                    break;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    break;
                }
            }
        }
    };

    private Thread t;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        t = new Thread(runnable);
        t.start();
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        try {
            in.close();
            out.close();
            socket.close();
            t.interrupt();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void sendNtf(int num) {
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("alert", true);
        intent.putExtra("alert_num", num);
        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        PendingIntent p = PendingIntent.getActivity(this, 100,
                intent, PendingIntent.FLAG_ONE_SHOT);

        Notification notification = new Notification.Builder(this)
                .setContentText("您有" +  num + "条警告")
                .setContentTitle(getResources().getString(R.string.app_name))
                .setContentIntent(p)
                .setSmallIcon(R.mipmap.logo)
                .setAutoCancel(true)
                .setVibrate(new long[] {1000, 1000, 1000, 1000})
                // prefer raw, asset lower API
                .setSound(Uri.parse("android.resource://"+getPackageName()+"/raw/alarm"))
                .build();
        notificationManager.notify(1, notification);
    }
}
