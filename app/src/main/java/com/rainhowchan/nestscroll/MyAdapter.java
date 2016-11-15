package com.rainhowchan.nestscroll;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.rainhowchan.nestscroll.domain.Book;

import java.util.List;

/**
 * Created by Administrator on 2016/11/15.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    private final List<Book> books;

    public MyAdapter(@NonNull List<Book> books) {
        this.books = books;
    }

    @Override
    public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(android.R.layout.simple_list_item_2,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyAdapter.ViewHolder holder, int position) {
        Book book = books.get(position);
        holder.textView1.setText(book.getName());
        holder.textView2.setText(String.valueOf(book.getPrice()));
    }

    @Override
    public int getItemCount() {
        return books.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
         TextView textView1;
         TextView textView2;
         ViewHolder(View view) {
            super(view);
            this.textView1= (TextView) view.findViewById(android.R.id.text1);
            this.textView2= (TextView) view.findViewById(android.R.id.text2);
        }
    }
}
