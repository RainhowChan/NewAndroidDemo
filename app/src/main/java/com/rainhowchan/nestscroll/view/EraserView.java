package com.rainhowchan.nestscroll.view;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.rainhowchan.nestscroll.R;
import com.rainhowchan.nestscroll.util.MeasureUtil;

/**
 * Created by Administrator on 2016/11/23.
 */

public class EraserView extends View {

    private static final float MIN_MOVE_DIS = 5;//最小绘制距离
    private Context context;
    private int widthPixels;
    private int heightPixels;
    private Paint paint;
    private Bitmap fgBitmap;
    private Canvas mCanvas;
    private Bitmap bgBitmap;
    private Path mPath;

    public EraserView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        calcuScreen();
        init();
    }

    private void calcuScreen() {
        DisplayMetrics screenSize = MeasureUtil.getScreenSize((Activity) context);
        widthPixels = screenSize.widthPixels;
        heightPixels = screenSize.heightPixels;
    }

    private void init() {
        mPath = new Path();
        paint = new Paint(Paint.ANTI_ALIAS_FLAG|Paint.DITHER_FLAG);
        paint.setARGB(128, 255, 0, 0);

        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeJoin(Paint.Join.ROUND);
        paint.setStrokeCap(Paint.Cap.ROUND);
        paint.setStrokeWidth(50);
        fgBitmap = Bitmap.createBitmap(widthPixels, heightPixels, Bitmap.Config.ARGB_8888);

        mCanvas = new Canvas(fgBitmap);
        mCanvas.drawColor(0xBA808080);
        bgBitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.bizi);
        bgBitmap = Bitmap.createScaledBitmap(bgBitmap, widthPixels, heightPixels, true);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawBitmap(bgBitmap,0,0,null);

        canvas.drawBitmap(fgBitmap, 0, 0, null);

        mCanvas.drawPath(mPath, paint);
    }

    private float preX;
    private float preY;
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x=event.getX();
        float y=event.getY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mPath.reset();
                mPath.moveTo(x, y);
                preX=x;
                preY=y;
                break;
            case MotionEvent.ACTION_MOVE:
                float dx = Math.abs(x - preX);
                float dy = Math.abs(x - preY);
                if (dx >= MIN_MOVE_DIS || dy >= MIN_MOVE_DIS) {
                    mPath.quadTo(preX, preY, (x + preX) / 2, (y + preY) / 2);
                    preX = x;
                    preY = y;
                }
                break;
        }
        invalidate();
        return true;
    }
}
