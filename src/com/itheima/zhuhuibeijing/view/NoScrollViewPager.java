package com.itheima.zhuhuibeijing.view;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;
/**
 * 不允许滑动的ViewPager
 * @author 刘建阳
 * @date 2016-8-1下午11:35:45
 */
public class NoScrollViewPager extends ViewPager {

	public NoScrollViewPager(Context context) {
		super(context);
	}

	public NoScrollViewPager(Context context, AttributeSet attrs) {
		super(context, attrs);
	}
	
	//事件拦截
	@Override
	public boolean onInterceptTouchEvent(MotionEvent arg0) {
		return false;//不拦截子控件的事件
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent arg0) {
		//重写此方法，触摸时什么都不做，从而实现对滑动事件的禁用
		return true;
	}

}
