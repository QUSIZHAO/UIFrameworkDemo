package com.xbm.android.demo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.xbm.android.demo.activity.DemoFuncActivity;
import com.xbm.android.demo.activity.swipe.SwipeBackTestActivity;
import com.xbm.android.demo.activity.BottomBarActivity;
import com.xbm.android.demo.activity.PullToRefreshActivity;
import com.xbm.android.demo.activity.BannerPracticeActivity;

import static com.xbm.android.demo.utils.Contact.*;

public class MainActivity extends AppCompatActivity implements OnItemClickListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ListView listView = (ListView) findViewById(R.id.demo_list);
        ArrayAdapter adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,
                getResources().getStringArray(R.array.ui_demo_func_list));
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent();
        intent.putExtra("position", position);
        if (position == BottomBarActivity) {
            intent.setClass(MainActivity.this, BottomBarActivity.class);
        } else if (position == SwipeBackTestActivity) {
            intent.setClass(MainActivity.this, SwipeBackTestActivity.class);
        } else if (position == PullToRefreshActivity) {
            intent.setClass(MainActivity.this, PullToRefreshActivity.class);
        } else if (position == BannerPracticeActivity) {
            intent.setClass(MainActivity.this, BannerPracticeActivity.class);
        } else if (position == ExpectMainActivity) {
            intent.setClass(MainActivity.this, ExpectMainActivity.class);
        } else if (position == Meteria_Animations) {
            intent.setClass(MainActivity.this, MeteriaAnimActivity.class);
        } else {
            intent.setClass(MainActivity.this, DemoFuncActivity.class);
        }
        startActivity(intent);
    }
}
