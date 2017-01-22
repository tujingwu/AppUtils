package com.example.apputil.customview.viewpager;

import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.apputil.R;
import com.example.apputil.baseactivity.BaseActivity;
import com.example.apputil.customview.viewpager.transformer.AlphaTrasf;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by tujingwu on 2016/12/27.
 */
public class ActivityCardViewPager extends BaseActivity {
    @Bind(R.id.viewPager)
    ViewPager mViewPager;

    private List<Integer> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // setContentView(R.layout.activity_cardviewpager);
        ButterKnife.bind(this);
        initViewPager();
    }

    @Override
    protected int setContentView() {
        return R.layout.activity_cardviewpager;
    }

    private void initViewPager() {
        mViewPager.setPageMargin(80);
        mViewPager.setOffscreenPageLimit(3);//表示三个界面之间来回切换都不会重新加载


        list = new ArrayList<>();
        list.add(R.mipmap.login);
        list.add(R.mipmap.splash);
        list.add(R.mipmap.uoko_guide_background_1);
        list.add(R.mipmap.uoko_guide_background_2);
        list.add(R.mipmap.uoko_guide_background_3);
        list.add(R.mipmap.splash);

        VpAdapter vpAdapter = new VpAdapter();
        mViewPager.setAdapter(vpAdapter);
        mViewPager.setPageTransformer(false, new AlphaTrasf());
    }


    class VpAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            ImageView imageView = new ImageView(getApplication());
            imageView.setImageResource(list.get(position));
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            container.addView(imageView);
            return imageView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            //super.destroyItem(container, position, object);
            container.removeView((View) object);
        }
    }
}
