package com.rainhowchan.nestscroll.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by Administrator on 2016/11/15.
 */

public abstract class BaseRecyclerAdapter<T> extends RecyclerView.Adapter<RecyclerViewHolder> {

    protected Context ctx;
    protected List<T> data;
    protected LayoutInflater mInflater;
    private OnItemClickListener mClickListener;
    private OnItemLongClickListener mLongClickListener;

    protected abstract void bindData(RecyclerViewHolder holder, int position, T data);

    protected abstract int getItemLayoutId(int viewType);

    public BaseRecyclerAdapter(Context ctx, @NonNull List<T> data) {
        this.ctx = ctx;
        this.data = data;
        mInflater = LayoutInflater.from(ctx);
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(final ViewGroup parent, int viewType) {
        final RecyclerViewHolder viewHolder = new RecyclerViewHolder(ctx, mInflater.inflate(getItemLayoutId(viewType), parent, false));
        if (mClickListener != null) {
            viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mClickListener.onItemClick(viewHolder.itemView,viewHolder.getLayoutPosition());
                }
            });
        }
        if (mLongClickListener != null) {
            viewHolder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    mLongClickListener.onItemLongClick(viewHolder.itemView,viewHolder.getLayoutPosition());
                    return true;
                }
            });
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, int position) {
        bindData(holder, position, data.get(position));
    }


    public void setmClickListener(OnItemClickListener mClickListener) {
        this.mClickListener = mClickListener;
    }

    public void setmLongClickListener(OnItemLongClickListener mLongClickListener) {
        this.mLongClickListener = mLongClickListener;
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public void add(int position, T item) {
        data.add(position, item);
        notifyItemInserted(position);
    }

    public void delete(int position) {
        data.remove(position);
        notifyItemRemoved(position);
    }

    public interface OnItemClickListener {
        void onItemClick(View itemView, int position);
    }

    public interface OnItemLongClickListener {
        void onItemLongClick(View itemView, int position);
    }
}
