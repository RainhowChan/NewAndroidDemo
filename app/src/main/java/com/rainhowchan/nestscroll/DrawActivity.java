package com.rainhowchan.nestscroll;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import com.rainhowchan.nestscroll.view.CircleView;
import com.rainhowchan.nestscroll.view.CustomView;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/11/21.
 */
public class DrawActivity extends Activity {
    @Bind(R.id.cv)
    CircleView cv;
    private int radiu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_draw);
        ButterKnife.bind(this);

//        new Thread(cv).start();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
