package com.rainhowchan.nestscroll.adapter;

import android.content.Context;
import android.support.annotation.DrawableRes;
import android.support.annotation.IdRes;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Administrator on 2016/11/15.
 */

public class RecyclerViewHolder extends RecyclerView.ViewHolder {

    private Context ctx;
    private SparseArray<View> mViews;

    public RecyclerViewHolder(Context ctx, View view) {
        super(view);
        this.ctx = ctx;
        mViews = new SparseArray<>();
    }

    @SuppressWarnings("unchecked")
    private <T extends View> T findViewById(int viewId) {
        View view = mViews.get(viewId);
        if (view == null) {
            view = itemView.findViewById(viewId);
            mViews.put(viewId, view);
        }
        return ((T) view);
    }

    public <T extends View> T getView(int viewId,Class<T> clazz){
        return findViewById(viewId);
    }

    public TextView getTextView(int viewId){
        return getView(viewId,TextView.class);
    }

    public ImageView getImageView(int viewId){
        return getView(viewId,ImageView.class);
    }

    public EditText getEditView(int viewId){
        return getView(viewId,EditText.class);
    }

    public RecyclerViewHolder setText(int viewId, String value) {
        getTextView(viewId).setText(value);
        return this;
    }

    public RecyclerViewHolder setImageResource(int viewId, @DrawableRes int imageSource) {
        getImageView(viewId).setImageResource(imageSource);
        return this;
    }

    public RecyclerViewHolder setBackGroud(int viewId, @DrawableRes int imageSource) {
        getView(viewId,View.class).setBackgroundResource(imageSource);
        return this;
    }

    public RecyclerViewHolder setClickListener(int viewId, View.OnClickListener listener) {
        View view = findViewById(viewId);
        view.setOnClickListener(listener);
        return this;
    }

}
