package com.rainhowchan.nestscroll;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.rainhowchan.nestscroll.view.CustomView;
import com.rainhowchan.nestscroll.view.RoundProgress;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/11/18.
 */
public class ViewPageProActivity extends AppCompatActivity {

    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.nv)
    NavigationView nv;
    @Bind(R.id.drawer_view)
    DrawerLayout drawerView;
    @Bind(R.id.rp)
    RoundProgress rp;
    private int progress;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewpagepro);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        setupDrawerContent(nv);

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (progress <= 100) {
                    try {
                        progress++;
                        rp.setProgress(progress);
                        Thread.sleep(300);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

            }
        }).start();

    }

    private void setupDrawerContent(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    private MenuItem mPreMenuItem;

                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        if (mPreMenuItem != null) mPreMenuItem.setChecked(false);
                        menuItem.setChecked(true);
                        drawerView.closeDrawers();
                        mPreMenuItem = menuItem;
                        return true;
                    }
                });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_drawer, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
//            drawerView.openDrawer(GravityCompat.START);
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
