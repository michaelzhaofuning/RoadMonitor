package com.sxhxjy.roadmonitor.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * 2016/11/16
 *
 * @author Michael Zhao
 */

public class ContrastView extends ImageView {
    public Bitmap contrastBitmap;
    public float ratioX, ratioY;
    private static int OFFSET = 300;
    private static int OFFSET_INVIEW = 200;
    private Paint paint;
    private Rect src;
    private Rect dst;

    public ContrastView(Context context) {
        super(context);
    }

    public ContrastView(Context context, AttributeSet attrs) {
        super(context, attrs);
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setStrokeWidth(6);
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(Color.RED);
    }

    public void setContrast(Bitmap bitmap, float ratioX, float ratioY) {
        contrastBitmap = bitmap;
        this.ratioX = ratioX / contrastBitmap.getWidth();
        this.ratioY = ratioY / contrastBitmap.getHeight();
        if (contrastBitmap == null) return;

        final int x = (int) (this.ratioX * contrastBitmap.getWidth());
        final int y = (int) (this.ratioY * contrastBitmap.getHeight());
        final int xInView = (int) (this.ratioX * getMeasuredWidth());
        final int yInView = (int) (this.ratioY * getMeasuredHeight());

        src = new Rect(x - OFFSET, y - OFFSET, x + OFFSET, y + OFFSET);
        final int left, top;
        if (this.ratioX > 0.5f)
            left = xInView - OFFSET_INVIEW;
        else
            left = xInView + OFFSET_INVIEW;
        if (this.ratioY > 0.5f)
            top = yInView - OFFSET_INVIEW;
        else
            top = yInView + OFFSET_INVIEW;

        dst = new Rect(left, top, left + OFFSET_INVIEW, top + OFFSET_INVIEW);
        invalidate();
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);


        if (contrastBitmap != null) {
            canvas.drawRect(dst, paint);
            canvas.drawBitmap(contrastBitmap, src, dst, null);
        }
    }
}
