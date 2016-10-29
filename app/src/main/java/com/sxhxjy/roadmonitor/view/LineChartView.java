package com.sxhxjy.roadmonitor.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathEffect;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.os.CountDownTimer;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

import com.sxhxjy.roadmonitor.R;
import com.sxhxjy.roadmonitor.entity.RealTimeData;

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
    private static final int DELAY = 1000;
    private static final int POINTS_COUNT = 24;
    private static final int OFFSET = 60;
    private static final int OFFSET_LEGEND = 40;
    private static final int LEGEND_WIDTH= 70;
    private static final int LEGEND_HEIGHT = 35;

    private static final float OFFSET_SCALE = 8;
    private static final float SPLIT_TO = 5;

    private static final int ALERT_VALUE = 80;
    private Random mRandom = new Random(47);
    private int xAxisLength, yAxisLength;
    private long xStart, xEnd;
    private float yStart, yEnd;
    private float firstPointX, nextPointX, firstPointY, nextPointY;

    private long BASE_TIME = System.currentTimeMillis();

    private int[] colors = {Color.BLUE, Color.GREEN, Color.YELLOW, Color.CYAN};



    private Paint mPaint;
    private Path mPath = new Path();

    private PathEffect mPathEffect = new DashPathEffect(new float[] {8, 8}, 0);

    private List<MyLine> myLines = new ArrayList<>();


    private RectF rectF = new RectF();



    public LineChartView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setDither(true);
        mPath.setFillType(Path.FillType.WINDING);

        // fake data
        /*new CountDownTimer(1000000, DELAY) {
            @Override
            public void onTick(long millisUntilFinished) {
                if (mList.size() == POINTS_COUNT)
                    mList.remove(0);
                mList.add(new MyPoint(System.currentTimeMillis(), mRandom.nextInt(100)));

                invalidate();
            }

            @Override
            public void onFinish() {

            }
        }.start();*/
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        xAxisLength = getMeasuredWidth() - 2 * OFFSET;
        yAxisLength = getMeasuredHeight() - 2 * OFFSET - OFFSET_LEGEND;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(getResources().getColor(R.color.white));

        if (myLines.isEmpty()) {
            mPaint.setTextSize(70);
            mPaint.setColor(Color.GRAY);
            String emptyHint = "请选择数据源";
            float width = mPaint.measureText(emptyHint);
            canvas.drawText(emptyHint, getMeasuredWidth() / 2 - width / 2, getMeasuredHeight() / 2, mPaint);
            return;
        }

        canvas.translate(OFFSET, getMeasuredHeight() - OFFSET - OFFSET_LEGEND);


        xStart = System.currentTimeMillis() + 1000*3600*60;
        yEnd = -10000f;

        for (MyLine line : myLines) {
            xEnd = Math.max(Collections.max(line.points, comparatorX).time, xEnd);
            xStart = Math.min(Collections.min(line.points, comparatorX).time, xStart);
            yEnd = Math.max(Collections.max(line.points, comparatorY).value, yEnd);
            yStart = Math.min(Collections.min(line.points, comparatorY).value, yStart);
//            yStart = 0;
        }

        mPaint.setTextSize(20);

        // draw point and line
        mPaint.setStrokeCap(Paint.Cap.ROUND);
        mPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        for (MyLine line : myLines) {
            for (MyPoint myPoint : line.points) {
                firstPointX = nextPointX;
                firstPointY = nextPointY;
                nextPointX = (float) (((double) (myPoint.time - xStart)) / (xEnd - xStart) * xAxisLength);
                nextPointY = -(float) (((double) (myPoint.value - yStart)) / (yEnd - yStart) * yAxisLength);

                mPaint.setColor(line.color);
                mPaint.setStrokeWidth(4);

                if (line.points.indexOf(myPoint) != 0) // do not draw line when draw first point !
                    canvas.drawLine(firstPointX, firstPointY, nextPointX, nextPointY, mPaint);

                mPaint.setStrokeWidth(8);

                if (myPoint.value > ALERT_VALUE) {
                    mPaint.setColor(getResources().getColor(android.R.color.holo_red_light));
                }

                canvas.drawPoint(nextPointX, nextPointY, mPaint);


                // draw x
                mPaint.setColor(getResources().getColor(R.color.default_color));
                mPaint.setStrokeWidth(2);
                mPaint.setTextAlign(Paint.Align.CENTER);

                canvas.drawLine(nextPointX, 0, nextPointX, -OFFSET_SCALE, mPaint);
                mPaint.setStrokeWidth(1);
                canvas.drawText((myPoint.time - BASE_TIME) / 1000 + " s", nextPointX, OFFSET_SCALE * 4, mPaint);

            }
        }

        // draw y
        mPaint.setColor(getResources().getColor(R.color.default_color));
        mPaint.setStrokeWidth(2);
        canvas.drawLine(0, 0, 0, - yAxisLength, mPaint);
        canvas.drawLine(0, 0, xAxisLength, 0, mPaint);

        mPaint.setTextAlign(Paint.Align.RIGHT);
        canvas.drawText(yStart + "", - OFFSET_SCALE, 0, mPaint);
        for (int j = 0; j < SPLIT_TO; j++) {
            float y = yStart + (yEnd - yStart) / SPLIT_TO * (j + 1);
            float yInView = (y - yStart) / (yEnd - yStart) * yAxisLength;
            yInView = -yInView; // reverse

            mPaint.setStrokeWidth(1);
            canvas.drawText(y + "", - OFFSET_SCALE, yInView, mPaint);
            mPaint.setStrokeWidth(2);
            canvas.drawLine(0, yInView, OFFSET_SCALE, yInView, mPaint);

        }

        // draw alert line
        mPaint.setColor(getResources().getColor(android.R.color.holo_red_light));
        mPaint.setPathEffect(mPathEffect);
        mPath.reset();
        float alertY = - (float) (((double)(ALERT_VALUE - yStart)) / (yEnd - yStart) * yAxisLength);
        mPath.moveTo(0, alertY);
        mPath.lineTo(xAxisLength, alertY);

        canvas.drawPath(mPath, mPaint);
        mPaint.setPathEffect(null);

        mPaint.setTextAlign(Paint.Align.LEFT);
        canvas.drawText(ALERT_VALUE + "", xAxisLength + OFFSET_SCALE, alertY - OFFSET_SCALE, mPaint);

        // draw legend
        rectF.setEmpty();
        for (MyLine myLine : myLines) {
            mPaint.setColor(myLine.color);
            rectF.top = OFFSET;
            rectF.bottom = rectF.top + OFFSET_LEGEND - 20;
            rectF.right = rectF.left + OFFSET_LEGEND * 3;
            canvas.drawRoundRect(rectF, 2, 2, mPaint);
            rectF.left += rectF.width() + OFFSET_LEGEND * 4;
            mPaint.setColor(getResources().getColor(R.color.default_text_color));
            mPaint.setStrokeWidth(0.1f);
            mPaint.setTextAlign(Paint.Align.LEFT);
            canvas.drawText(myLine.name, rectF.right + 15, rectF.bottom, mPaint);
        }

    }

    public void addPoints(List<MyPoint> points, String s, int color) {
        myLines.add(new MyLine(s, points, color)); // TODO cost memory
        invalidate();
    }

    public static List<MyPoint> convert(List<RealTimeData> list) {

        List<MyPoint> points = new ArrayList<>();
        for (RealTimeData realTimeData : list) {
            if (points.size() == POINTS_COUNT)
                points.remove(0);
            points.add(new MyPoint(realTimeData.getSaveTime(), (float) realTimeData.getX()));
        }
        return points;
    }

    public static List<MyPoint> convertY(List<RealTimeData> list) {

        List<MyPoint> points = new ArrayList<>();
        for (RealTimeData realTimeData : list) {
            if (points.size() == POINTS_COUNT)
                points.remove(0);
            points.add(new MyPoint(realTimeData.getSaveTime(), (float) realTimeData.getY()));
        }
        return points;
    }

    public static List<MyPoint> convertZ(List<RealTimeData> list) {

        List<MyPoint> points = new ArrayList<>();
        for (RealTimeData realTimeData : list) {
            if (points.size() == POINTS_COUNT)
                points.remove(0);
            points.add(new MyPoint(realTimeData.getSaveTime(), (float) realTimeData.getZ()));
        }
        return points;
    }

    public List<MyLine> getLines() {
        return myLines;
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
            if (lhs.value - rhs.value > 0)
                return 1;
            if (lhs.value - rhs.value < 0)
                return -1;
            if (lhs.value - rhs.value == 0)
                return 0;
            return 0;
        }
    };


    public static class MyPoint {
        MyPoint(long time, float value) {
            this.time = time;
            this.value = value;
        }

        long time;
        float value;

    }

    public static class MyLine {
        MyLine(String name, List<MyPoint> points, int color) {
            this.name = name;
            this.points = points;
            this.color = color;
        }

        String name;
        int color;
        List<MyPoint> points;
    }
}
