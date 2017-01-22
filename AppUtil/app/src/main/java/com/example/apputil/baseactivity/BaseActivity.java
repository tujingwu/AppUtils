package com.example.apputil.baseactivity;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;

import com.example.apputil.R;
import com.example.apputil.utils.TypefaceUtil;
import com.readystatesoftware.systembartint.SystemBarTintManager;

/**
 * Created by tujingwu on 2016/11/16.
 */
public abstract class BaseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(setContentView());
        //去除title
        // requestWindowFeature(Window.FEATURE_NO_TITLE);
        //去掉Activity上面的状态栏
       /* getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);*/
        applyKitKatTranslucency();
        TypefaceUtil.replaceFont(this, "fonts/fzy.ttf");//设置字体颜色样式
    }

    //布局文件ID
    protected abstract int setContentView();

    //------------------------------下部分是沉浸式状态栏设置---------------------------//
    public void applyKitKatTranslucency() {
        // KitKat translucent navigation/status bar.
        // 4.4及以上版本开启
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            setTranslucentStatus(true);
            SystemBarTintManager mTintManager = new SystemBarTintManager(this);
            mTintManager.setStatusBarTintEnabled(true);

            mTintManager.setStatusBarTintResource(R.color.colorAppBar);//通知栏所需颜色
        }

    }

    @TargetApi(19)
    private void setTranslucentStatus(boolean on) {
        Window win = getWindow();
        WindowManager.LayoutParams winParams = win.getAttributes();
        final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
        if (on) {
            winParams.flags |= bits;
        } else {
            winParams.flags &= ~bits;
        }
        win.setAttributes(winParams);
    }
}
