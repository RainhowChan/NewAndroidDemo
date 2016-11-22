package com.rainhowchan.nestscroll.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ComposePathEffect;
import android.graphics.CornerPathEffect;
import android.graphics.DashPathEffect;
import android.graphics.DiscretePathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathDashPathEffect;
import android.graphics.PathEffect;
import android.graphics.SumPathEffect;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Administrator on 2016/11/22.
 */

public class MaskFilterView extends View {
    private Context context;
    private Paint mPaint;
    private static final int RECT_SIZE = 800;
    private int mPhase;//偏移值
    private Path mPath;
    private PathEffect[] mEffects;


    public MaskFilterView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        setLayerType(LAYER_TYPE_SOFTWARE,null);//关闭硬件加速
        initPaint();
    }

    private void initPaint() {
        mPaint = new Paint();
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(5);
        mPaint.setColor(Color.DKGRAY);

        mPath = new Path();
        mPath.moveTo(0,0);

        for(int i=0;i<=30;i++) {
            mPath.lineTo(i * 30, (float) (Math.random() * 100));
        }

        mEffects = new PathEffect[7];

        mEffects[0] = null;
        mEffects[1] = new CornerPathEffect(10);
        mEffects[2] = new DiscretePathEffect(3.0F, 8.0F);
        mEffects[3] = new DashPathEffect(new float[] { 20, 10,5,10 }, mPhase);
        Path path = new Path();
        path.addCircle(0, 0, 3, Path.Direction.CCW);
        mEffects[4] = new PathDashPathEffect(path, 12, mPhase, PathDashPathEffect.Style.ROTATE);
        mEffects[5] = new ComposePathEffect(mEffects[2], mEffects[4]);
        mEffects[6] = new SumPathEffect(mEffects[4], mEffects[1]);
    }
    @Override
    protected void onDraw(Canvas canvas) {
        for (int i = 0; i < mEffects.length; i++) {
            mPaint.setPathEffect(mEffects[i]);
            canvas.drawPath(mPath, mPaint);

            // 每绘制一条将画布向下平移250个像素
            canvas.translate(0, 250);
        }
        mPhase += 1;
        invalidate();
    }
}
