package com.rainhowchan.nestscroll.impl;

import android.support.v4.widget.AutoScrollHelper;
import android.support.v7.widget.RecyclerView;

/**
 * Created by Administrator on 2016/11/17.
 */

public class RecyclerViewAutoScrollHelper extends AutoScrollHelper {

    private RecyclerView recyclerView;

    public RecyclerViewAutoScrollHelper(RecyclerView recyclerView) {
        super(recyclerView);
        this.recyclerView = recyclerView;
    }

    @Override
    public void scrollTargetBy(int deltaX, int deltaY) {
        recyclerView.scrollBy(deltaX,deltaY);
    }

    @Override
    public boolean canTargetScrollHorizontally(int direction) {
        return recyclerView.getLayoutManager().canScrollHorizontally();
    }

    @Override
    public boolean canTargetScrollVertically(int direction) {
        return recyclerView.getLayoutManager().canScrollVertically();
    }
}
