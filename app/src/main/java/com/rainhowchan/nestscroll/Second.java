package com.rainhowchan.nestscroll;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.graphics.Palette;
import android.support.v7.graphics.PaletteItem;
import android.support.v7.widget.CardView;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/11/15.
 */

public class Second extends Activity {

    @Bind(R.id.cv_first)
    CardView cvFirst;
    @Bind(R.id.cv_second)
    CardView cvSecond;
    @Bind(R.id.tv_test1)
    TextView tvTest1;
    @Bind(R.id.tv_test2)
    TextView tvTest2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        ButterKnife.bind(this);
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.avator1);

        Palette.generateAsync(bitmap, new Palette.PaletteAsyncListener() {
            @Override
            public void onGenerated(Palette palette) {
                PaletteItem vibrantColor = palette.getVibrantColor();
                cvFirst.setCardBackgroundColor(vibrantColor.getRgb());
                tvTest1.setTextColor(colorBurn(vibrantColor.getRgb()));

            }
        });

        Bitmap bitmap2 = BitmapFactory.decodeResource(getResources(), R.drawable.avator2);
        Palette.generateAsync(bitmap2, new Palette.PaletteAsyncListener() {
            @Override
            public void onGenerated(Palette palette) {
                PaletteItem vibrantColor = palette.getVibrantColor();
                cvSecond.setCardBackgroundColor(vibrantColor.getRgb());
                tvTest2.setTextColor(colorBurn(vibrantColor.getRgb()));
            }
        });

    }

    private int colorBurn(int RGBValues) {
        int alpha = RGBValues >> 24;
        int red = RGBValues >> 16 & 0xFF;
        int green = RGBValues >> 8 & 0xFF;
        int blue = RGBValues & 0xFF;
        red = (int) Math.floor(red * (1 - 0.1));
        green = (int) Math.floor(green * (1 - 0.1));
        blue = (int) Math.floor(blue * (1 - 0.1));
        return Color.rgb(red, green, blue);
    }
}
