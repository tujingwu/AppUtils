package com.example.apputil.bottomtab;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;

import com.example.apputil.R;
import com.example.apputil.bottomtab.fragment.Frg_Left;
import com.example.apputil.bottomtab.fragment.Frg_Right;
import com.example.apputil.customview.MyRadioButton;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by tujingwu on 2017/1/18.
 * 常用底部栏例子
 */
public class BottomTabActivity extends AppCompatActivity implements View.OnClickListener {

    @Bind(R.id.fragmentLayout)
    RelativeLayout fragmentLayout;
    @Bind(R.id.rg)
    RadioGroup rg;
    @Bind(R.id.tab_a)
    MyRadioButton tab_a;
    @Bind(R.id.tab_b)
    MyRadioButton tab_b;
    @Bind(R.id.tab_c)
    MyRadioButton tab_c;

    private Frg_Left leftFrg;
    private Frg_Right rightFrg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_tab);
        ButterKnife.bind(this);
        initListner();
    }

    //设置tab 图片 字体选中的样式监听
    private void initListner() {
        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                for (int i = 0; i < group.getChildCount(); i++) {
                    if (i != 1) {//不是中间按钮
                        if (checkedId == group.getChildAt(i).getId())
                            ((MyRadioButton) (group.getChildAt(i))).setChecked(true);
                        else
                            ((MyRadioButton) (group.getChildAt(i))).setChecked(false);
                    }
                }
            }
        });

        tab_a.setOnClickListener(this);
        tab_c.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tab_a:
                tabA();
                break;
            case R.id.tab_c:
                tabC();
                break;
        }
    }

    private void tabA() {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        hideFragment(ft);
        if (leftFrg == null) {
            leftFrg = new Frg_Left();
            ft.add(R.id.fragmentLayout, leftFrg);
        } else {
            ft.show(leftFrg);
        }

        ft.commit();
    }

    private void tabC() {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        hideFragment(ft);
        if (rightFrg == null) {
            rightFrg = new Frg_Right();
            ft.add(R.id.fragmentLayout, rightFrg);
        } else {
            ft.show(rightFrg);
        }

        ft.commit();
    }

    //隐藏Fragment
    private void hideFragment(FragmentTransaction transaction) {
        if (leftFrg != null) {
            transaction.hide(leftFrg);
        }
        if (rightFrg != null) {
            transaction.hide(rightFrg);
        }
    }
}
