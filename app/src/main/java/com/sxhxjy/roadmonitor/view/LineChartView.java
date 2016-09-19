package com.sxhxjy.roadmonitor.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.CountDownTimer;
import android.util.AttributeSet;
import android.view.View;

import com.sxhxjy.roadmonitor.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

/**
 * 2016/9/19
 *
 * @author Michael Zhao
 */
public class LineChartView extends View {
    private static final int DELAY = 500;
    private static final int POINTS_COUNT = 201;
    private static final float OFFSET = 40;
    private Random mRandom = new Random(47);
    private int xAxisLength, yAxisLength;
    private long xStart, xEnd;
    private int yStart, yEnd;

    private Paint mPaint;

    private List<MyPoint> mList = new ArrayList<>(201);

    private float[] points = new float[POINTS_COUNT * 2]; // x0, y0, x1, y1 ...

    public LineChartView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setStrokeCap(Paint.Cap.ROUND);
        mPaint.setColor(getResources().getColor(R.color.colorPrimary));
        mPaint.setStrokeWidth(8);
        new CountDownTimer(100000, DELAY) {
            @Override
            public void onTick(long millisUntilFinished) {
                mList.add(new MyPoint(System.currentTimeMillis(), mRandom.nextInt(100)));
                invalidate();
            }

            @Override
            public void onFinish() {

            }
        }.start();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        xAxisLength = (int) (getMeasuredWidth() - 2 * OFFSET);
        yAxisLength = (int) (getMeasuredHeight() - 2 * OFFSET);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.translate(OFFSET, getMeasuredHeight() - OFFSET);


        xEnd = Collections.max(mList, comparatorX).time;
        xStart = Collections.min(mList, comparatorX).time;
        yEnd = (int) Collections.max(mList, comparatorY).value;
//        yStart = (int) Collections.min(mList, comparatorY).value;
        yStart = 0;
        for (MyPoint myPoint : mList) {
            canvas.drawPoint((float) ((double)(myPoint.time - xStart)) / (xEnd - xStart) * xAxisLength,
                    - (float) ((double)(myPoint.value - yStart)) / (yEnd - yStart) * yAxisLength, mPaint);
        }

    }
    private Comparator<MyPoint> comparatorX =  new Comparator<MyPoint>() {
        @Override
        public int compare(MyPoint lhs, MyPoint rhs) {
            return (int) (lhs.time - rhs.time);
        }
    };

    private Comparator<MyPoint> comparatorY =  new Comparator<MyPoint>() {
        @Override
        public int compare(MyPoint lhs, MyPoint rhs) {
            return (int) (lhs.value - rhs.value);
        }
    };


    public static class MyPoint {
        public MyPoint(long time, long value) {
            this.time = time;
            this.value = value;
        }

        public long time;
        public long value;

    }
}
