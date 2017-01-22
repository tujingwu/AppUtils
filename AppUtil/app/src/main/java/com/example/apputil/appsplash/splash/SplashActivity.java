package com.example.apputil.appsplash.splash;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageView;

import com.example.apputil.R;
import com.example.apputil.appsplash.guide.GuideActivity;
import com.example.apputil.appsplash.login.LoginActivity;
import com.example.apputil.comfigmanager.acache.ACacheKey;
import com.example.apputil.utils.ACache;

/**
 * Created by tujingwu on 2016/11/15.
 */
public class SplashActivity extends AppCompatActivity {
    private ImageView splashImg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
    }

    private void initView() {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash);
        splashImg = (ImageView) this.findViewById(R.id.img);
        AlphaAnimation anima = new AlphaAnimation(0.3f, 1.0f);
        anima.setDuration(3000);// 设置动画显示时间
        splashImg.startAnimation(anima);
        anima.setAnimationListener(new AnimationImpl());
       /* AnimationSet aset_3=new AnimationSet(true);
        ScaleAnimation aa_3=new ScaleAnimation(1, 0.1f, 1, 0.1f,Animation.RELATIVE_TO_SELF,1f,Animation.RELATIVE_TO_SELF,1f);
        aa_3.setDuration(3000);
        aset_3.addAnimation(aa_3);
        splashImg.startAnimation(aset_3);
        aa_3.setAnimationListener(new AnimationImpl());*/
       /* Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                startActivity(new Intent(SplashActivity.this, GuideActivity.class));
                finish();
            }
        }, 3000);*/
    }

    private class AnimationImpl implements Animation.AnimationListener {

        @Override
        public void onAnimationStart(Animation animation) {
            splashImg.setBackgroundResource(R.mipmap.splash);
        }

        @Override
        public void onAnimationEnd(Animation animation) {
            skip(); // 动画结束后跳转到别的页面
        }

        @Override
        public void onAnimationRepeat(Animation animation) {

        }

    }

    private void skip() {
        String isFirst = ACache.get(this).getAsString(ACacheKey.Key_isFirst_Time_Enter);
        if (isFirst == null) {
            ACache.get(this).put(ACacheKey.Key_isFirst_Time_Enter, "Yes");
            startActivity(new Intent(this, GuideActivity.class));
            finish();
        } else {
            //  Log.e("ddd","登陆界面");
            startActivity(new Intent(this, LoginActivity.class));
            finish();
        }

    }
}
