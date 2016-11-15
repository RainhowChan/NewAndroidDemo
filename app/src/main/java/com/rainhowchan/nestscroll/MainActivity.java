package com.rainhowchan.nestscroll;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.rainhowchan.nestscroll.adapter.BaseRecyclerAdapter;
import com.rainhowchan.nestscroll.adapter.RecyclerViewHolder;
import com.rainhowchan.nestscroll.domain.Book;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends Activity{

    @Bind(R.id.my_recycler_view)
    RecyclerView myRecyclerView;
    private LinearLayoutManager linearLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        myRecyclerView.setHasFixedSize(true);
        linearLayoutManager = new LinearLayoutManager(this);
        myRecyclerView.setLayoutManager(linearLayoutManager);
        BaseRecyclerAdapter<Book> adapter=new BaseRecyclerAdapter<Book>(this,initData()) {

            @Override
            protected void bindData(RecyclerViewHolder holder, int position, Book book) {
                holder.setText(android.R.id.text1, book.getName());
                holder.setText(android.R.id.text2,String.valueOf(book.getPrice()));
            }

            @Override
            protected int getItemLayoutId(int viewType) {
                return android.R.layout.simple_list_item_2;
            }
        };
        myRecyclerView.setAdapter(adapter);

        adapter.setmClickListener(new BaseRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View itemView, int position) {
                Toast.makeText(MainActivity.this, "位置为 " +position, Toast.LENGTH_SHORT).show();
                if (position==0) {
                    startActivity(new Intent(MainActivity.this,Second.class));
                }
            }
        });
    }

    private List<Book> initData() {
        List<Book> books=new ArrayList<>();
        for(int i=0;i<15;i++){
            books.add(new Book("spring"+i,i*10d));
        }
        return books;
    }
}
