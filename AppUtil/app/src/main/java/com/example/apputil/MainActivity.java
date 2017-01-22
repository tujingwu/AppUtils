package com.example.apputil;

import android.app.Dialog;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;

import com.example.apputil.activity.BadGeViewActivity;
import com.example.apputil.activity.FlowLayoutActivity;
import com.example.apputil.activity.KeyBoardActivity;
import com.example.apputil.appsplash.login.LoginActivity;
import com.example.apputil.baseactivity.BaseActivity;
import com.example.apputil.bottomtab.BottomTabActivity;
import com.example.apputil.customview.guideview.Component;
import com.example.apputil.customview.guideview.Guide;
import com.example.apputil.customview.guideview.GuideBuilder;
import com.example.apputil.customview.guideview.component.MutiComponent;
import com.example.apputil.customview.guideview.component.SimpleComponent;
import com.example.apputil.customview.viewpager.ActivityCardViewPager;
import com.example.apputil.customview.viewpager.ActivityOrientVp;
import com.example.apputil.expandlistview.ExpandableListViewActivity;
import com.example.apputil.picture.AlbumActivity;
import com.example.apputil.refresh.SwipeListViewActivity;
import com.example.apputil.swipelayout.ActivitySlideDelete;
import com.example.apputil.utils.DialogUtils;
import com.example.apputil.utils.NetUtils;
import com.example.apputil.utils.NotifyUtils;
import com.example.apputil.utils.ToastUtils;
import com.example.apputil.version.DownService;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity implements View.OnClickListener {
    private long exitTime = 0;
    @Bind(R.id.btDailog)
    Button btDialog;
    @Bind(R.id.btCancelLogin)
    Button btCancelLogin;
    @Bind(R.id.btNotify)
    Button btNotify;
    @Bind(R.id.btRefresh)
    Button btRefresh;
    @Bind(R.id.btAlbum)
    Button btAlbum;
    @Bind(R.id.btSlideDelete)
    Button btSlideDelete;
    @Bind(R.id.btCustomViewPager)
    Button btCustomViewPager;
    @Bind(R.id.btCardViePager)
    Button btCardViePager;
    @Bind(R.id.btFlowLayout)
    Button btFlowLayout;
    @Bind(R.id.btBadegeview)
    Button btBadegeview;
    @Bind(R.id.btExpandListview)
    Button btExpandListview;
    @Bind(R.id.btKeyBoard)
    Button btKeyBoard;
    @Bind(R.id.btBottomTab)
    Button btBottomTab;

    private int requestCode = (int) SystemClock.uptimeMillis();
    Guide guide;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        //applyKitKatTranslucency();//沉浸式状态栏设置

        // TypefaceUtil.replaceFont(this, "fonts/fzy.ttf");//设置字体颜色样式
        initListener();
        isUpdateApp();

        btDialog.post(new Runnable() {//设置第一次使用app的引导使用
            @Override
            public void run() {
                showGuideView();
            }
        });
    }

    //是否更新app
    private void isUpdateApp() {
        int networkType = NetUtils.getNetworkType(this);
        if (networkType == NetUtils.NETWORK_WIFI) {
            //如果当前网络是wifi类型 就启动服务自动更新app
            startService(new Intent(this, DownService.class));
        }
    }

    @Override
    protected int setContentView() {
        return R.layout.activity_main;
    }

    private void initListener() {
        btDialog.setOnClickListener(this);
        btCancelLogin.setOnClickListener(this);
        btNotify.setOnClickListener(this);
        btRefresh.setOnClickListener(this);
        btAlbum.setOnClickListener(this);
        btSlideDelete.setOnClickListener(this);
        btCustomViewPager.setOnClickListener(this);
        btCardViePager.setOnClickListener(this);
        btFlowLayout.setOnClickListener(this);
        btBadegeview.setOnClickListener(this);
        btExpandListview.setOnClickListener(this);
        btKeyBoard.setOnClickListener(this);
        btBottomTab.setOnClickListener(this);
    }

    private void showDialog() {
        Dialog dialog = DialogUtils.setDialog(this, R.layout.loading_dialog);
        dialog.show();
    }


    //按两次返回键提示“再按一次退出程序”
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            exit();
            return false;
        }
        return super.onKeyDown(keyCode, event);
    }

    public void exit() {
        if ((System.currentTimeMillis() - exitTime) > 2000) {
            ToastUtils.show(this, "再按一次退出程序");
            exitTime = System.currentTimeMillis();
        } else {
            finish();
            System.exit(0);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btDailog://显示dialog
                showDialog();
                break;
            case R.id.btCancelLogin: //退出登陆
                Intent logoutIntent = new Intent(MainActivity.this, LoginActivity.class);
                logoutIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(logoutIntent);
                finish();
                //在退出前 清空需要清除的缓存或数据
                break;
            case R.id.btNotify://弹出通知
                Intent intent = new Intent(this, LoginActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                PendingIntent pIntent = PendingIntent.getActivity(this, requestCode, intent, PendingIntent.FLAG_UPDATE_CURRENT);
                int smallIcon = R.mipmap.ic_launcher;
                String ticker = "新通知";
                String title = "标题";
                String content = "通知内容";
                new NotifyUtils(this, 1)
                        .notify_normal_singline(pIntent,
                                smallIcon, ticker,
                                title, content, true,
                                false, false);
                break;
            case R.id.btRefresh:
                Intent intent1 = new Intent(this, SwipeListViewActivity.class);
                startActivity(intent1);
                break;
            case R.id.btAlbum:
                startActivity(new Intent(this, AlbumActivity.class));
                break;

            case R.id.btSlideDelete://侧滑
                startActivity(new Intent(this, ActivitySlideDelete.class));
                break;
            case R.id.btCustomViewPager://垂直卡片viewpager
                startActivity(new Intent(this, ActivityOrientVp.class));
                break;
            case R.id.btCardViePager://卡片viewpager
                startActivity(new Intent(this, ActivityCardViewPager.class));
                break;
            case R.id.btFlowLayout://流式布局
                startActivity(new Intent(this, FlowLayoutActivity.class));
                break;
            case R.id.btBadegeview://消息红点
                startActivity(new Intent(this, BadGeViewActivity.class));
                break;
            case R.id.btExpandListview://二级列表listview
                startActivity(new Intent(this, ExpandableListViewActivity.class));
                break;
            case R.id.btKeyBoard://聊天表情键盘
                startActivity(new Intent(this, KeyBoardActivity.class));
                break;
            case R.id.btBottomTab://底部栏tab
                startActivity(new Intent(this, BottomTabActivity.class));
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        stopService(new Intent(this, DownService.class));
    }


    //=====================下半部分是第一次引导使用提示======================
    //详情:https://github.com/binIoter/GuideView
    public void showGuideView() {
        GuideBuilder builder = new GuideBuilder();
        builder.setTargetView(btDialog)//把控件id传进来就行(按钮 线性布局等都可以)
                .setAlpha(150)
                .setHighTargetCorner(20)
                .setHighTargetPadding(10)
                .setOverlayTarget(false)
                .setOutsideTouchable(false);
        builder.setOnVisibilityChangedListener(new GuideBuilder.OnVisibilityChangedListener() {
            @Override
            public void onShown() {
            }

            @Override
            public void onDismiss() {
                showGuideView2();
            }
        });

        builder.addComponent(new SimpleComponent());
        guide = builder.createGuide();
        guide.setShouldCheckLocInWindow(false);
        guide.show(MainActivity.this);//在那个avtivity显示
    }

    public void showGuideView2() {
        final GuideBuilder builder1 = new GuideBuilder();
        builder1.setTargetView(btFlowLayout)//这里也是传个控件id
                .setAlpha(150)
                .setHighTargetGraphStyle(Component.CIRCLE)
                .setOverlayTarget(false)
                .setExitAnimationId(android.R.anim.fade_out)
                .setOutsideTouchable(false);
        builder1.setOnVisibilityChangedListener(new GuideBuilder.OnVisibilityChangedListener() {
            @Override
            public void onShown() {
            }

            @Override
            public void onDismiss() {
            }
        });

        builder1.addComponent(new MutiComponent());
        Guide guide = builder1.createGuide();
        guide.setShouldCheckLocInWindow(false);
        guide.show(MainActivity.this);
    }
}
