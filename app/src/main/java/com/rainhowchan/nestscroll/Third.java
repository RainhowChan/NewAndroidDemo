package com.rainhowchan.nestscroll;

import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v4.widget.ContentLoadingProgressBar;
import android.support.v4.widget.ListViewAutoScrollHelper;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/11/16.
 */
public class Third extends AppCompatActivity {

    @Bind(R.id.toolBar)
    Toolbar toolBar;
    @Bind(R.id.lv)
    ListView lv;
    private ContentLoadingProgressBar pb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);
        ButterKnife.bind(this);

        toolBar.setTitle("我的第三个界面");
        toolBar.setTitleTextAppearance(this, R.style.WindowTitle);
//        toolBar.setNavigationIcon(R.drawable.ic_back_white_64);
        setSupportActionBar(toolBar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        initDate();
    }

    private void initDate() {
        Cursor cursor = getContentResolver().query(ContactsContract.Contacts.CONTENT_URI, null, null, null, null);
        if (cursor != null) {
            startManagingCursor(cursor);
        }
        lv.setAdapter(new SimpleCursorAdapter(this,android.R.layout.simple_list_item_2,cursor,new String[]{ContactsContract.PhoneLookup.DISPLAY_NAME,ContactsContract.PhoneLookup._ID},new int[]{android.R.id.text1,android.R.id.text2}));
        stopManagingCursor(cursor);

        ListViewAutoScrollHelper scrollHelper = new ListViewAutoScrollHelper(lv);
        scrollHelper.setActivationDelay(100);
        lv.setOnTouchListener(scrollHelper);
        scrollHelper.setEnabled(true);
        scrollHelper.setRampUpDuration(20);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(Third.this,"位置  "+position,Toast.LENGTH_SHORT).show();
            }
        });
    }
}
