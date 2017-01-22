package com.example.apputil.refresh;

import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.chanven.lib.cptr.loadmore.ILoadMoreViewFactory;
import com.example.apputil.R;

/**
 * Created by tujingwu on 2016/11/25.
 */
public class Foot implements ILoadMoreViewFactory{

    @Override
    public ILoadMoreView madeLoadMoreView() {
        return new LoadMoreHelper();
    }

    private class LoadMoreHelper implements ILoadMoreView {

        protected View footerView;
        protected TextView footerTv;
        protected ProgressBar footerBar;

        protected View.OnClickListener onClickRefreshListener;

        @Override
        public void init(FootViewAdder footViewHolder, View.OnClickListener onClickRefreshListener) {
            footerView = footViewHolder.addFootView(R.layout.loading_dialog);
            //footerTv = (TextView) footerView.findViewById(com.chanven.lib.cptr.R.id.loadmore_default_footer_tv);
           // footerBar = (ProgressBar) footerView.findViewById(com.chanven.lib.cptr.R.id.loadmore_default_footer_progressbar);
            this.onClickRefreshListener = onClickRefreshListener;
            showNormal();
        }

        @Override
        public void showNormal() {
           // footerTv.setText("点击加载更多");
           // footerTv.setTextColor(Color.RED);
           // footerBar.setVisibility(View.GONE);
            footerView.setOnClickListener(onClickRefreshListener);
        }

        @Override
        public void showLoading() {
            //footerTv.setText("正在加载中...");
           //// footerTv.setTextColor(Color.RED);
          //  footerBar.setVisibility(View.VISIBLE);
            footerView.setOnClickListener(null);
        }

        @Override
        public void showFail(Exception exception) {
            //footerTv.setText("加载失败，点击重新");
           // footerTv.setTextColor(Color.RED);
           // footerBar.setVisibility(View.GONE);
            footerView.setOnClickListener(onClickRefreshListener);
        }

        @Override
        public void showNomore() {
           // footerTv.setText("已经加载完毕");
           // footerTv.setTextColor(Color.RED);
            //footerBar.setVisibility(View.GONE);
            footerView.setOnClickListener(null);
        }

        @Override
        public void setFooterVisibility(boolean isVisible) {
            footerView.setVisibility(isVisible ? View.VISIBLE : View.GONE);
        }
    }
}
