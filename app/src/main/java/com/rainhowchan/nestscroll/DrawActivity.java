package com.rainhowchan.nestscroll;

import android.app.Activity;
import android.os.Bundle;

import com.rainhowchan.nestscroll.view.CircleView;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/11/21.
 */
public class DrawActivity extends Activity {
    @Bind(R.id.cv)
    CircleView cv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_draw);
        ButterKnife.bind(this);

        new Thread(cv).start();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
