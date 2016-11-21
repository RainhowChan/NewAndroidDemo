package com.rainhowchan.nestscroll.view;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorMatrix;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;

import com.rainhowchan.nestscroll.util.MeasureUtil;

/**
 * Created by Administrator on 2016/11/21.
 */

public class CustomView extends View implements Runnable{
    private Context context;
    private AttributeSet attrs;
    private Paint paint = new Paint();
    private DisplayMetrics screenSize;
    private int radiu=200;
    private ColorMatrix colorMatrix = new ColorMatrix(new float[]{
            -1, 0, 0, 1, 0,
            0, -1, 0, 1, 1,
            0, 0, -1, 1, 1,
            0, 0, 0, 1, 0,
    });

    public void setRadiu(int radiu) {
        this.radiu = radiu;
        invalidate();//重绘
    }

    public CustomView(Context context) {
        super(context);
        this.context = context;
        initPaint();
    }

  /*  public CustomView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        this.attrs = attrs;
        initPaint();
    }*/

    public CustomView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        this.attrs = attrs;
        initPaint();
    }

    private void init() {
      /*  TypedArray typedArray = context.obtainStyledAttributes(new int[]{R.styleable.CustomView_view_src});
        drawable = typedArray.getDrawable(0);
        typedArray.recycle();
        BitmapDrawable bd= (BitmapDrawable) drawable;
        if(bd!=null)
        bitmap = bd.getBitmap();*/
    }

    /**
     * Paint.Style.STROKE：描边
     * Paint.Style.FILL_AND_STROKE：描边并填充
     * Paint.Style.FILL：填充
     */
    private void initPaint() {
        paint.setStyle(Paint.Style.STROKE);
        screenSize = MeasureUtil.getScreenSize((Activity) context);
        /*paint.setColor(Color.LTGRAY);
        paint.setStrokeWidth(50);*/
//        dip2px()
    }

    @Override
    protected void onDraw(Canvas canvas) {

//        paint.setAntiAlias(true);
//        paint.setColorFilter(new ColorMatrixColorFilter(colorMatrix));
//        paint.setColorFilter(new LightingColorFilter(0xff55ffff,0x00000000));
//        paint.setColorFilter(new PorterDuffColorFilter(Color.RED, PorterDuff.Mode.ADD));
        /*paint.setColor(Color.argb(255, 255, 128, 103));
        canvas.drawCircle(240,300,200,paint);*/
//        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.kale);
//        canvas.drawBitmap(bitmap, 240, 600, paint);

        canvas.drawCircle(screenSize.widthPixels / 2, screenSize.heightPixels / 2, radiu, paint);
    }

    @Override
    public void run() {
        while (true) {
            try {
                if (radiu <= 200) {
                    radiu += 10;
                    postInvalidate();
                } else {
                    radiu = 0;
                }
                Thread.sleep(40);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /* 根据手机的分辨率从 dp 的单位 转成为 px(像素) */
    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

}
