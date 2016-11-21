package com.rainhowchan.nestscroll.view;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;

import com.rainhowchan.nestscroll.util.MeasureUtil;

/**
 * Created by Administrator on 2016/11/21.
 */

public class CircleView extends View implements Runnable{

    private Paint paint;
    private Context context;
    private int innearCircle;//内圆半径
    private int ringWidth;//圆环宽度
    private int centerWidth;
    private int centerHeight;
    private RectF rect;
    private int startAngle=0;

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

    public void setStartAngle(int startAngle) {
        this.startAngle = startAngle;
    }

    private void initPaint() {
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setStyle(Paint.Style.STROKE);
        innearCircle = MeasureUtil.dip2px(context, 83);
        ringWidth = MeasureUtil.dip2px(context, 10);
        DisplayMetrics screenSize = MeasureUtil.getScreenSize((Activity) context);
        int width = screenSize.widthPixels;
        int height = screenSize.heightPixels;
        centerWidth=width / 2;
        centerHeight=height/2;
        rect = new RectF(centerWidth - (innearCircle + 1 + ringWidth / 2), centerHeight - (innearCircle + 1 + ringWidth / 2),
                centerWidth - (innearCircle + 1 + ringWidth / 2), centerHeight - (innearCircle + 1 + ringWidth / 2));
    }

    @Override
    protected void onDraw(Canvas canvas) {

        paint.setARGB(255,138,43,226);
        paint.setStrokeWidth(2);
        canvas.drawCircle(centerWidth, centerHeight, innearCircle , paint);

        paint.setARGB(255, 138, 43, 0);
        paint.setStrokeWidth(ringWidth);
        canvas.drawCircle(centerWidth, centerHeight, innearCircle + 1 + ringWidth / 2, paint);

        //绘制外圆
        this.paint.setARGB(255, 138, 43, 226);
        this.paint.setStrokeWidth(2);
        canvas.drawCircle(centerWidth, centerHeight, innearCircle + ringWidth+1, this.paint);


        canvas.drawArc(rect,190+startAngle,90,false,paint);
        canvas.drawArc(rect, 0+startAngle, 90, false, paint);

        paint.setARGB(30, 127, 255, 212);
        canvas.drawArc(rect, 90+startAngle, 90, false, paint);
        canvas.drawArc(rect, 270+startAngle, 90, false, paint);
    }


    @Override
    public void run() {
        while (true) {
            startAngle+=10;
            if(startAngle == 180)
                startAngle = 0;
            try {
                Thread.sleep(50);
                postInvalidate();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
}
