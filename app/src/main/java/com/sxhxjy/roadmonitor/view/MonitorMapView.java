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
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.sxhxjy.roadmonitor.R;
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
    private ArrayList<Station> monitors = new ArrayList<>();

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
        this.monitors.clear();
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

        if (monitors.size() == 0) return;

        for (Station monitor : monitors) {
            float x = monitor.getPicLeft() * 1f / bitmap.getWidth() * getMeasuredWidth();
            float y = monitor.getPicTop() * 1f / bitmap.getHeight() * getMeasuredHeight();
            canvas.drawCircle(x, y, 8, paint);
            canvas.drawText(monitor.getName(), x, y + 40f, paint);
        }

        showMonitorInfo(monitors.get(0));
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();
        int action = event.getAction();
        if (x < 100 && y < 100 && action == MotionEvent.ACTION_UP) {
            final View p = ((View)getParent());
            p.animate().alpha(0).setListener(new Animator.AnimatorListener() {
                @Override
                public void onAnimationStart(Animator animation) {

                }

                @Override
                public void onAnimationEnd(Animator animation) {
                    // global
                    if (p.getAlpha() == 0)
                      p.setVisibility(GONE);
                }

                @Override
                public void onAnimationCancel(Animator animation) {

                }

                @Override
                public void onAnimationRepeat(Animator animation) {

                }
            }).start();
            return true;
        }

        if (monitors.size() == 0 || action != MotionEvent.ACTION_UP) return true;

        BitmapDrawable drawable = (BitmapDrawable) getDrawable();
        Bitmap bitmap = drawable.getBitmap();

        Station min = monitors.get(0);
        float d = 1000000f;
        for (int i = 1; i < monitors.size(); i++) {
            Station monitor = monitors.get(i);
            float a = monitor.getPicLeft() * 1f / bitmap.getWidth() * getMeasuredWidth();
            float b = monitor.getPicTop() * 1f / bitmap.getHeight() * getMeasuredHeight();
            if (Math.abs(x - a) + Math.abs(y - b) < d) {
                min = monitor;
                d = Math.abs(x - a) + Math.abs(y - b);
            }
        }

        showMonitorInfo(min);

        return true;
    }

    private void showMonitorInfo(Station station) {
        final ViewGroup p = (ViewGroup) getParent();
        TextView monitorName = (TextView) p.findViewById(R.id.monitor_name);
        monitorName.setText(station.getName() + " (" + station.getCode() + ")");
        p.findViewById(R.id.get_chart_data).setTag(station);
    }
}
