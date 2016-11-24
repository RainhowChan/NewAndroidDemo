package com.rainhowchan.nestscroll.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * Created by Administrator on 2016/11/24.
 */

public class BlurImageView extends ImageView {

    private Paint mPaint;

    public BlurImageView(Context context, AttributeSet attrs) {
        super(context, attrs);

        initPaint();

    }

    private void initPaint() {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(Color.parseColor("#a0000000"));
        mPaint.setStyle(Paint.Style.FILL);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawRect(0,0,getWidth(),getHeight(),mPaint);
    }
}
