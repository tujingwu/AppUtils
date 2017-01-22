package com.example.apputil.recyclerviewdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.apputil.R;
import com.example.apputil.recyclerviewdemo.adapter.RvAdapter;
import com.example.apputil.recyclerviewdemo.divider.DividerItemDecoration;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recyclerview);

        RecyclerView rv = (RecyclerView) findViewById(R.id.rv);
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 55; i++) {
            list.add("item:" + i);
        }

        RvAdapter rvAdapter = new RvAdapter(this, list);

        // LinearLayoutManager llm = new LinearLayoutManager(this);
        GridLayoutManager glm = new GridLayoutManager(this, 3);
        glm.setOrientation(LinearLayoutManager.HORIZONTAL);

        //rv.addItemDecoration(new RecycleViewDivider(this, LinearLayoutManager.HORIZONTAL, 1, Color.RED));
        //设置自定义的分割线 R.drawable.recyclerview_divider
        DividerItemDecoration did = new DividerItemDecoration(this, R.drawable.recyclerview_divider);
        // did.setDrawBorderTopAndBottom(true);//画上和下的线
        // did.setDrawBorderLeftAndRight(true);//画左和右的线
        // did.setOnlySetItemOffsetsButNoDraw(true);//设置不画分割线 但是留出分割线的路径
        rv.addItemDecoration(did);
        rv.setLayoutManager(glm);
        rv.setAdapter(rvAdapter);

    }
}
