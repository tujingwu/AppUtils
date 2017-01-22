package com.example.apputil.swipelayout;

import android.os.Bundle;
import android.widget.ListView;

import com.example.apputil.R;
import com.example.apputil.baseactivity.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by tujingwu on 2016/12/10.
 * 侧滑
 * https://github.com/daimajia/AndroidSwipeLayout
 */
public class ActivitySlideDelete extends BaseActivity {
    @Bind(R.id.listView)
    ListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        initData();
    }

    @Override
    protected int setContentView() {
        return R.layout.activity_slide_delete;
    }

    private void initData() {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 15; i++) {
            list.add("item:" + i);
        }
        ListViewAdapter listViewAdapter = new ListViewAdapter(this, list);
        mListView.setAdapter(listViewAdapter);
    }

}
