package com.rainhowchan.nestscroll.view;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;

import com.rainhowchan.nestscroll.util.MeasureUtil;

/**
 * Created by Administrator on 2016/11/21.
 */

public class CircleView extends View {

    private Paint paint;
    private Context context;
    private int innearCircle;//内圆半径
    private int ringWidth;//圆环宽度
    private int width;
    private int height;

    public CircleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        initPaint();
    }

    public CircleView(Context context) {
        super(context);
        this.context = context;
        initPaint();
    }

    private void initPaint() {
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setStyle(Paint.Style.STROKE);
        innearCircle = MeasureUtil.dip2px(context, 83);
        ringWidth = MeasureUtil.dip2px(context, 10);
        DisplayMetrics screenSize = MeasureUtil.getScreenSize((Activity) context);
        width = screenSize.widthPixels;
        height = screenSize.heightPixels;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        paint.setARGB(255,138,43,226);
        paint.setStrokeWidth(2);
        canvas.drawCircle(width / 2, height / 2, innearCircle , paint);

        paint.setARGB(255, 138, 43, 0);
        paint.setStrokeWidth(ringWidth);
        canvas.drawCircle(width / 2, height / 2, innearCircle + 1 + ringWidth / 2, paint);

        //绘制外圆
        this.paint.setARGB(255, 138, 43, 226);
        this.paint.setStrokeWidth(2);
        canvas.drawCircle(width / 2, height / 2, innearCircle + ringWidth, this.paint);
    }


}
