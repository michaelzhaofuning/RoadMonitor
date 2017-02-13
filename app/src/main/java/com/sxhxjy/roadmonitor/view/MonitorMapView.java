package com.sxhxjy.roadmonitor.view;

import android.animation.Animator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.sxhxjy.roadmonitor.R;
import com.sxhxjy.roadmonitor.entity.Monitor;
import com.sxhxjy.roadmonitor.entity.Station;

import java.util.ArrayList;
import java.util.List;

/**
 * 2017/2/13
 *
 * @author Michael Zhao
 */

public class MonitorMapView extends ImageView {
    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private Bitmap backBitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.navigation_icon);
    private ArrayList<Station> monitors = new ArrayList();

    public MonitorMapView(Context context) {
        super(context);
    }

    public MonitorMapView(Context context, AttributeSet attrs) {
        super(context, attrs);

    }

    public MonitorMapView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setMonitors(List<Station> monitors) {
        this.monitors.addAll(monitors);
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawBitmap(backBitmap, 0, 0, paint);

        paint.setColor(Color.RED);
        paint.setTextSize(20);

        BitmapDrawable drawable = (BitmapDrawable) getDrawable();
        Bitmap bitmap = drawable.getBitmap();
        for (Station monitor : monitors) {
            int x = monitor.getPicLeft() / bitmap.getWidth() * getMeasuredWidth();
            int y = monitor.getPicTop() / bitmap.getHeight() * getMaxHeight();
            canvas.drawCircle(x, y, 50, paint);
            canvas.drawText(monitor.getName(), x, y + 30f, paint);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();
        int action = event.getAction();
        if (x < 100 && y < 100 && action == MotionEvent.ACTION_UP) {
            animate().alpha(0).setListener(new Animator.AnimatorListener() {
                @Override
                public void onAnimationStart(Animator animation) {

                }

                @Override
                public void onAnimationEnd(Animator animation) {
                    // global
                    if (getAlpha() == 0)
                      setVisibility(GONE);
                }

                @Override
                public void onAnimationCancel(Animator animation) {

                }

                @Override
                public void onAnimationRepeat(Animator animation) {

                }
            }).start();
        }

        if (monitors.size() == 0) return true;

        BitmapDrawable drawable = (BitmapDrawable) getDrawable();
        Bitmap bitmap = drawable.getBitmap();

        Station minx = monitors.get(0);
        float mindx = 1000000f;
        for (int i = 1; i < monitors.size(); i++) {
            Station monitor = monitors.get(i);
            int a = monitor.getPicLeft() / bitmap.getWidth() * getMeasuredWidth();
            if (Math.abs(x - a) < mindx) {
                minx = monitor;
                mindx = Math.abs(x - a);
            }


        }

        Station miny = monitors.get(0);
        float mindy = 1000000f;
        for (int i = 1; i < monitors.size(); i++) {
            Station monitor = monitors.get(i);
            int b = monitor.getPicTop() / bitmap.getHeight() * getMaxHeight();
            if (Math.abs(y - b) < mindy) {
                miny = monitor;
                mindy = Math.abs(y - b);
            }


        }

        if (mindx > mindy) {
            gotoMonitor(miny);
        } else {
            gotoMonitor(minx);
        }

        return true;
    }

    private void gotoMonitor(Station station) {
        Toast.makeText(getContext(), station.getName(), Toast.LENGTH_LONG).show();
    }
}
