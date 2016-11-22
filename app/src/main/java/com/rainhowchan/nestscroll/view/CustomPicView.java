package com.rainhowchan.nestscroll.view;

import android.content.Context;
import android.graphics.AvoidXfermode;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.LightingColorFilter;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.util.AttributeSet;
import android.view.View;

import com.rainhowchan.nestscroll.R;

/**
 * Created by Administrator on 2016/11/22.
 */

public class CustomPicView extends View {
    private Paint paint;
    private Bitmap bitmap;
    private AvoidXfermode avoidXfermode;

    public CustomPicView(Context context) {
        super(context);
        initPaint();
    }

    public boolean checked;

    public CustomPicView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initPaint();
    }

    private void initPaint() {
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.avator1);
        /*final ColorMatrix colorMatrix = new ColorMatrix(new float[]{
                0, 0, 1, 0, 0,
                0, 1, 0, 0, 0,
                1, 0, 0, 0, 0,
                0, 0, 0, 1, 0,
        });*/
        paint.setColorFilter(new LightingColorFilter(0xffff00ff, 0x000000));
        avoidXfermode = new AvoidXfermode(0xffffffff, 0, AvoidXfermode.Mode.TARGET);

        setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!checked) {
                    checked = true;
                    paint.setColorFilter(null);
                }else{
                    checked = false;
                    paint.setColorFilter(new PorterDuffColorFilter(Color.GREEN, PorterDuff.Mode.LIGHTEN));
                }
                invalidate();
            }
        });
    }

    @Override
    protected void onDraw(Canvas canvas) {
        paint.setXfermode(avoidXfermode);
        canvas.drawBitmap(bitmap, 50, 50, paint);
    }
}
