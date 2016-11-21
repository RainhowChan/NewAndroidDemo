package com.rainhowchan.nestscroll.util;

import android.app.Activity;
import android.content.Context;
import android.util.DisplayMetrics;
import android.view.Display;

/**
 * Created by Administrator on 2016/11/21.
 */

public class MeasureUtil {
    public static DisplayMetrics getScreenSize(Activity activity) {
        DisplayMetrics outMetrics = new DisplayMetrics();
        Display defaultDisplay = activity.getWindow().getWindowManager().getDefaultDisplay();
        defaultDisplay.getMetrics(outMetrics);
        return outMetrics;
    }

    /* 根据手机的分辨率从 dp 的单位 转成为 px(像素) */
    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }
}
