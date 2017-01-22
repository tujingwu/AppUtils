package com.example.apputil.emoticonskeyboard.common;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.GridView;
import android.widget.RelativeLayout;

import com.example.apputil.R;
import com.example.apputil.emoticonskeyboard.adapter.AppsAdapter;
import com.example.apputil.emoticonskeyboard.bean.AppBean;

import java.util.ArrayList;

public class SimpleAppsGridView extends RelativeLayout {

    protected View view;

    public SimpleAppsGridView(Context context) {
        this(context, null);
    }

    public SimpleAppsGridView(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(R.layout.view_keyboard_apps, this);
        init();
    }

    protected void init(){
        GridView gv_apps = (GridView) view.findViewById(R.id.gv_apps);
        ArrayList<AppBean> mAppBeanList = new ArrayList<>();
        mAppBeanList.add(new AppBean(R.mipmap.ic_launcher, "图片"));
        mAppBeanList.add(new AppBean(R.mipmap.ic_launcher, "拍照"));
        mAppBeanList.add(new AppBean(R.mipmap.ic_launcher, "视频"));
        mAppBeanList.add(new AppBean(R.mipmap.ic_launcher, "空间"));
        mAppBeanList.add(new AppBean(R.mipmap.ic_launcher, "联系人"));
        mAppBeanList.add(new AppBean(R.mipmap.ic_launcher, "文件"));
        mAppBeanList.add(new AppBean(R.mipmap.ic_launcher, "位置"));
        AppsAdapter adapter = new AppsAdapter(getContext(), mAppBeanList);
        gv_apps.setAdapter(adapter);
    }
}
