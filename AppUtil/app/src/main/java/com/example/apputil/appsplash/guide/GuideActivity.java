package com.example.apputil.appsplash.guide;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.apputil.R;
import com.example.apputil.appsplash.login.LoginActivity;

import java.util.Arrays;

import cn.bingoogolapple.bgabanner.BGABanner;

/**
 * Created by tujingwu on 2016/11/15.
 */
public class GuideActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView mSkipTv;
    private Button mEnterBtn;
    private BGABanner mBanner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        initListener();
        initViewPager();
    }

    private void initListener() {
        mSkipTv.setOnClickListener(this);
        mEnterBtn.setOnClickListener(this);
    }

    private void initView() {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_guide);
        mBanner = (BGABanner) this.findViewById(R.id.banner);
        mSkipTv = (TextView) findViewById(R.id.tv_guide_skip);
        mEnterBtn = (Button) findViewById(R.id.btn_guide_enter);
    }

    private void initViewPager() {
        // 初始化方式1：通过传入数据模型并结合Adapter的方式初始化
        mBanner.setAdapter(new BGABanner.Adapter() {
            @Override
            public void fillBannerItem(BGABanner banner, View view, Object model, int position) {
                ((ImageView) view).setImageResource((int) model);
            }
        });
        mBanner.setData(Arrays.asList(R.mipmap.uoko_guide_background_1, R.mipmap.uoko_guide_background_2, R.mipmap.uoko_guide_background_3), null);


        //一进来就隐藏Buttom按钮
        mEnterBtn.setVisibility(View.GONE);
        // 监听页码切换事件，控制「跳过按钮」和「进入主界面的显示与隐藏」
        mBanner.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (position == 2) {
                    mSkipTv.setVisibility(View.GONE);
                    mEnterBtn.setVisibility(View.VISIBLE);
                } else {
                    mSkipTv.setVisibility(View.VISIBLE);
                    mEnterBtn.setVisibility(View.GONE);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        // 在界面可见时给背景Banner设置一个白色背景，避免滑动过程中两个Banner都设置透明度后能看到Launcher
        mBanner.setBackgroundResource(android.R.color.white);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btn_guide_enter || view.getId() == R.id.tv_guide_skip) {
            startActivity(new Intent(this, LoginActivity.class));
            finish();
        }
    }
}
