package com.rainhowchan.nestscroll;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import com.rainhowchan.nestscroll.util.RxUtil;

/**
 * Created by Administrator on 2016/12/7.
 */
public class RXActivity extends Activity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx);
    }

    public void show(View view) {
        RxUtil.range();
    }

}
