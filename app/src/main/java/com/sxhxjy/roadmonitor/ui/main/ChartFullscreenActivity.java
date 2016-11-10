package com.sxhxjy.roadmonitor.ui.main;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.sxhxjy.roadmonitor.R;
import com.sxhxjy.roadmonitor.base.BaseActivity;
import com.sxhxjy.roadmonitor.view.LineChartView;

import java.util.ArrayList;

/**
 * 2016/11/2
 *
 * @author Michael Zhao
 */

public class ChartFullscreenActivity extends Activity {
    private static boolean hinted;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chart_full_screen_activity);

        LineChartView lineChartView = (LineChartView) findViewById(R.id.chart);
        lineChartView.setMyLines(LineChartView.lineChartView.getLines());
        lineChartView.setMyLinesRight(LineChartView.lineChartView.getLinesRight());

        lineChartView.yAxisName = LineChartView.lineChartView.yAxisName;
        lineChartView.yAxisNameRight = LineChartView.lineChartView.yAxisNameRight;
        lineChartView.setChartInFullscreen(true);
        lineChartView.mIsSimpleDraw = LineChartView.lineChartView.mIsSimpleDraw;
        lineChartView.invalidate();
        if (!hinted) {
            Toast.makeText(this, "亲~ 双击可以退出哦", Toast.LENGTH_SHORT).show();
            hinted = true;
        }
    }
}
