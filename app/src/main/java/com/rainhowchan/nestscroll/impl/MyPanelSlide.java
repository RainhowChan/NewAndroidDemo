package com.rainhowchan.nestscroll.impl;

import android.content.Context;
import android.support.v4.widget.SlidingPaneLayout;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

/**
 * Created by Administrator on 2016/11/17.
 */

public class MyPanelSlide extends SlidingPaneLayout {
    private Context context;
    private View fullView;
    private View partialView;

    public MyPanelSlide(Context context) {
        super(context);
        this.context = context;
        init();
    }

    public MyPanelSlide(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        init();
    }

    public MyPanelSlide(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.context = context;
        init();
    }

    private void init() {
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        if(getChildCount()<1) return;
        View panel = getChildAt(0);
        if(!(panel instanceof ViewGroup))return;
        ViewGroup viewGroup= (ViewGroup) panel;
        if(viewGroup.getChildCount()!=2) return;
        fullView = viewGroup.getChildAt(0);
        partialView = viewGroup.getChildAt(1);
        super.setPanelSlideListener(listener);
    }

    private SimplePanelSlideListener listener=new SimplePanelSlideListener(){
        @Override
        public void onPanelSlide(View panel, float slideOffset) {
            super.onPanelSlide(panel, slideOffset);
            if(fullView==null||partialView==null)return;
            partialView.setVisibility(isOpen() ? View.GONE : View.VISIBLE);
            partialView.setAlpha(1 - slideOffset);
        }
    };

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        if(partialView!=null)
            partialView.setVisibility(isOpen()?GONE:VISIBLE);
    }
}
