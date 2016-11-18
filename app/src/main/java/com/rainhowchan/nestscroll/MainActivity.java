package com.rainhowchan.nestscroll;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Toast;

import com.rainhowchan.nestscroll.adapter.BaseRecyclerAdapter;
import com.rainhowchan.nestscroll.adapter.RecyclerViewHolder;
import com.rainhowchan.nestscroll.domain.Book;
import com.rainhowchan.nestscroll.impl.RecyclerViewAutoScrollHelper;
import com.rainhowchan.nestscroll.util.RecycleViewDivider;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @Bind(R.id.my_recycler_view)
    RecyclerView myRecyclerView;
    @Bind(R.id.toolBar)
    Toolbar toolBar;
    @Bind(R.id.drawer)
    DrawerLayout drawer;
    private LinearLayoutManager linearLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        initToolBar();
        initDrawerLayout();

        myRecyclerView.setHasFixedSize(true);
        linearLayoutManager = new LinearLayoutManager(this);
        myRecyclerView.setLayoutManager(linearLayoutManager);
        BaseRecyclerAdapter<Book> adapter = new BaseRecyclerAdapter<Book>(this, initData()) {

            @Override
            protected void bindData(RecyclerViewHolder holder, int position, Book book) {
                holder.setText(R.id.text1, book.getName());
                holder.setText(R.id.text2, String.valueOf(book.getPrice()));
            }

            @Override
            protected int getItemLayoutId(int viewType) {
                return R.layout.listview_item_main;
            }
        };
        RecyclerViewAutoScrollHelper helper = new RecyclerViewAutoScrollHelper(myRecyclerView);
        myRecyclerView.setOnTouchListener(helper);
        helper.setEnabled(true);
        myRecyclerView.setAdapter(adapter);
        myRecyclerView.addItemDecoration(new RecycleViewDivider(this,LinearLayoutManager.HORIZONTAL,10, Color.GRAY));
        adapter.setmClickListener(new BaseRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View itemView, int position) {
                Toast.makeText(MainActivity.this, "位置为 " + position, Toast.LENGTH_SHORT).show();
                if (position == 0) {
                    startActivity(new Intent(MainActivity.this, Second.class));
                } else if (position == 1) {
                    startActivity(new Intent(MainActivity.this,Third.class));
                } else if (position == 2) {
                    startActivity(new Intent(MainActivity.this,MyNestedScrollView.class));
                } else if (position == 3) {
                    startActivity(new Intent(MainActivity.this,NestedScrollViewDemo.class));
                } else if (position == 4) {
                    startActivity(new Intent(MainActivity.this,ViewPageProActivity.class));
                }
            }
        });
    }

    @Override
    public boolean onMenuOpened(int featureId, Menu menu) {
        if (featureId == Window.FEATURE_ACTION_BAR && menu != null) {
            if (menu.getClass().getSimpleName().equals("MenuBuilder")) {
                try {
                    Method m = menu.getClass().getDeclaredMethod("setOptionalIconsVisible", Boolean.TYPE);
                    m.setAccessible(true);
                    m.invoke(menu, true);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return super.onMenuOpened(featureId, menu);
    }

    private void initDrawerLayout() {
        ActionBarDrawerToggle drawerToggle = new ActionBarDrawerToggle(this, drawer, toolBar, R.string.drawer_open, R.string.drawer_close);
        drawerToggle.syncState();
        drawer.setDrawerListener(drawerToggle);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main,menu);
        return true;
    }

    private void initToolBar() {

//        toolBar.setSubtitle("哈哈");
        toolBar.setTitleTextAppearance(this, R.style.WindowTitle);

        toolBar.inflateMenu(R.menu.main);
        toolBar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getOrder()) {
                    case 80:
                        Toast.makeText(MainActivity.this, "您点击了 80", Toast.LENGTH_SHORT).show();
                        break;
                    case 90:
                        Toast.makeText(MainActivity.this, "您点击了 90", Toast.LENGTH_SHORT).show();
                        break;
                    case 100:
                        Toast.makeText(MainActivity.this, "您点击了 100", Toast.LENGTH_SHORT).show();
                        break;
                }
                return false;
            }
        });
        /*setSupportActionBar(toolBar);
        ActionBar actionBar = getSupportActionBar();*/
        toolBar.setTitle("RecyclerView演示");
//        actionBar.setLogo(R.drawable.ic_launcher);
    }

    private List<Book> initData() {
        List<Book> books = new ArrayList<>();
        for (int i = 0; i < 15; i++) {
            books.add(new Book("spring" + i, i * 10d));
        }
        return books;
    }
}
