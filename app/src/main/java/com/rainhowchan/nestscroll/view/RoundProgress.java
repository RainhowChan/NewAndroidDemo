package com.rainhowchan.nestscroll.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.media.Image;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.rainhowchan.nestscroll.R;

/**
 * Created by Administrator on 2016/11/24.
 */

public  class RoundProgress extends ImageView {

    private Paint mPaint;
    private Paint paint;
    private int roundColor;
    private int roundProgressColor;
    private int textColor;
    private float textSize;
    private float roundWidth;

    private int progress = 0;

    public RoundProgress(Context context) {
        this(context, null);
    }

    public RoundProgress(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RoundProgress(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray mTypedArray = context.obtainStyledAttributes(attrs, R.styleable.roundProgress);
        roundColor = mTypedArray.getColor(R.styleable.roundProgress_roundColor, Color.RED);
        roundProgressColor = mTypedArray.getColor(R.styleable.roundProgress_roundProgressColor, Color.GREEN);
        textColor = mTypedArray.getColor(R.styleable.roundProgress_percentTextColor, Color.GREEN);
        int backgroundColor = mTypedArray.getColor(R.styleable.roundProgress_roundBackground, Color.LTGRAY);
        textSize = mTypedArray.getDimension(R.styleable.roundProgress_percentTextSize, 15);
        roundWidth = mTypedArray.getDimension(R.styleable.roundProgress_roundWidth, 5);
        mTypedArray.recycle();
        paint = new Paint();
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(backgroundColor);
        mPaint.setStyle(Paint.Style.FILL);

    }

    @Override
    protected void onDraw(Canvas canvas) {

        if (progress <100) {
            canvas.drawRect(0,0,getWidth(),getHeight(),mPaint);
            paint.setAntiAlias(true);
            paint.setStrokeWidth(roundWidth);
            paint.setColor(roundColor);
            paint.setStyle(Paint.Style.STROKE);
            int center = getWidth()/2;
            int radius = (int) (center - roundWidth / 2)*3/5;
            canvas.drawCircle(center, center, radius, paint);

            paint.setStrokeWidth(0);
            paint.setColor(textColor);
            paint.setTextSize(textSize);
            //        paint.setFakeBoldText(true);
            float textWidth = paint.measureText(progress + " %");
            canvas.drawText(progress + " %", center - textWidth / 2, center + textSize / 2, paint);

            paint.setColor(roundProgressColor);
            paint.setStrokeWidth(roundWidth);
            paint.setStrokeCap(Paint.Cap.ROUND);
            int sweepAngle = progress * 360 / 100;
            canvas.drawArc(new RectF(center - radius, center - radius, center + radius, center + radius), 0, sweepAngle, false, paint);
        }else{
            super.onDraw(canvas);
        }

    }

    public void setProgress(int progress) {
        this.progress = progress;
        if (progress > 100) {
            this.progress = 100;
        } else
            postInvalidate();
    }
}
