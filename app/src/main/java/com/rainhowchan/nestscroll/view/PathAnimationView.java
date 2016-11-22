package com.rainhowchan.nestscroll.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * Created by Administrator on 2016/11/22.
 */

public class PathAnimationView extends View {
    private final Context context;
    private Paint mPaint;// 画笔
    private Path mPath;// 路径对象

    private int screenW, screenH;// 屏幕宽高  
    private float x, y;// 路径初始坐标
    private float initScreenW;// 屏幕初始宽度
    private float initX;// 初始X轴坐标
    private float transX, moveX;// 画布移动的距离

    private boolean isCanvasMove;// 画布是否需要平移


    public PathAnimationView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        setLayerType(LAYER_TYPE_SOFTWARE, null);//关闭硬件加速
        initPaint();
    }

    private void initPaint() {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG | Paint.DITHER_FLAG);
        mPaint.setColor(Color.GREEN);
        mPaint.setStrokeWidth(10);
        mPaint.setStrokeCap(Paint.Cap.ROUND);
        mPaint.setStrokeJoin(Paint.Join.ROUND);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setShadowLayer(7, 0, 0, Color.GREEN);
        mPath = new Path();
        transX = 0;
        isCanvasMove = false;
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
         /*
         * 获取屏幕宽高
         */
        screenW = w;
        screenH = h;

        /*
         * 设置起点坐标
         */
        x = 0;
        y = (screenH / 2) /*+ (screenH / 4) + (screenH / 10)*/;

        // 屏幕初始宽度
        initScreenW = screenW;

        // 初始X轴坐标
        initX = ((screenW / 2) + (screenW / 4));

        moveX = (screenW / 24);
        mPath.moveTo(x, y);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawColor(Color.BLACK);

        mPath.lineTo(x, y);

        // 向左平移画布
        canvas.translate(-transX, 0);

        // 计算坐标
        calCoors();

        // 绘制路径
        canvas.drawPath(mPath, mPaint);
        invalidate();
    }

    private void calCoors() {
        if (isCanvasMove) {
            transX += 4;
        }

        if (x < initX) {
            x += 8;
        } else {
            if (x < initX + moveX) {
                x += 2;
                y -= 8;
            } else {
                if (x < initX + (moveX * 2)) {
                    x += 2;
                    y += 14;
                } else {
                    if (x < initX + (moveX * 3)) {
                        x += 2;
                        y -= 12;
                    } else {
                        if (x < initX + (moveX * 4)) {
                            x += 2;
                            y += 6;
                        } else {
                            if (x < initScreenW) {
                                x += 8;
                            } else {
                                isCanvasMove = true;
                                initX = initX + initScreenW;
                            }
                        }
                    }
                }
            }
        }


    }
}
