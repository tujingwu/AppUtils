package com.example.apputil.customview.badgeview;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.widget.ImageView;

public class BadgeImageView extends ImageView implements IBadgeViewImpl{

	private IBadgeView mBadgeView;
	
	public BadgeImageView(Context context, AttributeSet attrs) {
		super(context, attrs);
		mBadgeView=new IBadgeView(this, context,attrs);
	}
	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		super.onSizeChanged(w, h, oldw, oldh);
		mBadgeView.onSizeChanged(w, h);
	}
	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		mBadgeView.draw(canvas);
	}
	@Override
	public IBadgeView setBadgeCount(String count) {
		return mBadgeView.setCount(count);
	}
	@Override
	public IBadgeView setBadgeShown(boolean isShowBadge) {
		return mBadgeView.setShown(isShowBadge);
	}
	@Override
	public IBadgeView setBadgeColor(int color) {
		return mBadgeView.setColor(color);
	}
}