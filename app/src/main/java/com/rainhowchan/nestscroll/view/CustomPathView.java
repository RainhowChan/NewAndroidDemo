package com.rainhowchan.nestscroll.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Administrator on 2016/11/22.
 */

public class CustomPathView extends View {

    private Paint paint;

    private Path path;
    private Path path1;
    public CustomPathView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initPaint();
    }

    public CustomPathView(Context context) {
        super(context);
        initPaint();
    }

    private void initPaint() {
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(5);
        paint.setColor(Color.RED);
        RectF outRect=new RectF(50,50,500,500);
        RectF innerRect = new RectF(30, 30, 200, 200);
        path = new Path();
        path.arcTo(outRect,0,90);
        path1 = new Path();
        path1.arcTo(innerRect,180,270);
    }
    @Override
    protected void onDraw(Canvas canvas) {

        canvas.drawPath(path,paint);
        canvas.drawPath(path1,paint);
    }
}
