package com.rainhowchan.nestscroll.view;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by Administrator on 2016/11/23.
 */

public class DrawingWithBezier extends View {

    private Paint mGesturePaint = new Paint();
    private Path mPath = new Path();

    public DrawingWithBezier(Context context, AttributeSet attrs) {
        super(context, attrs);
        mGesturePaint.setAntiAlias(true);
        mGesturePaint.setStyle(Paint.Style.STROKE);
        mGesturePaint.setStrokeWidth(5);
        mGesturePaint.setColor(Color.WHITE);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return true;
    }
}
