package com.example.apputil.activity;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.apputil.R;
import com.example.apputil.customview.FlowLayout;

/**
 * Created by tujingwu on 2017/1/4.
 * 流式布局界面
 */
public class FlowLayoutActivity extends AppCompatActivity implements View.OnClickListener {

    FlowLayout flowLayout;

    String[] texts = new String[]{
            "good", "bad", "understand", "it is a good day !",
            "how are you", "ok", "fine", "name", "momo",
            "lankton", "lan", "flowlayout demo", "soso"
    };

    int length;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flowlayout);
        length = texts.length;
        flowLayout = (FlowLayout) findViewById(R.id.flowlayout);
        findViewById(R.id.btn_add_random).setOnClickListener(this);
        findViewById(R.id.btn_relayout1).setOnClickListener(this);
        findViewById(R.id.btn_remove_all).setOnClickListener(this);
        findViewById(R.id.btn_relayout2).setOnClickListener(this);
        findViewById(R.id.btn_specify_line).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_add_random:
                int ranHeight = dip2px(this, 30);
                ViewGroup.MarginLayoutParams lp = new ViewGroup.MarginLayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ranHeight);
                lp.setMargins(dip2px(this, 10), 0, dip2px(this, 10), 0);
                TextView tv = new TextView(this);
                tv.setPadding(dip2px(this, 15), 0, dip2px(this, 15), 0);
                tv.setTextColor(Color.parseColor("#FF3030"));
                tv.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16);
                int index = (int) (Math.random() * length);
                tv.setText(texts[index]);
                tv.setGravity(Gravity.CENTER_VERTICAL);
                tv.setLines(1);
                tv.setBackgroundResource(R.drawable.bg_tag);
                flowLayout.addView(tv, lp);
                break;
            case R.id.btn_remove_all:
                flowLayout.removeAllViews();//清除所有view
                break;
            case R.id.btn_relayout1:
                flowLayout.relayoutToCompress();// 是通过对子View重新排序， 使得它们能够更合理的挤占空间，
                break;
            case R.id.btn_relayout2://对齐， 不会改变子View的顺序， 也不会起到压缩的作用
                flowLayout.relayoutToAlign();
                break;
            case R.id.btn_specify_line://截断
                flowLayout.specifyLines(3);
                break;
            default:
                break;
        }
    }

    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }
}
