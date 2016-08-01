package com.itheima.zhuhuibeijing.base.impl;

import android.app.Activity;
import android.graphics.Color;
import android.view.Gravity;
import android.widget.TextView;

import com.itheima.zhuhuibeijing.base.BasePager;
/**
 * 新闻中心
 * @author 刘建阳
 * @date 2016-8-1下午10:52:58
 */
public class NewsCenterPager extends BasePager {

	public NewsCenterPager(Activity activity) {
		super(activity);
	}
	
	@Override
	public void initData() {
		//要给帧布局填充布局对象
		TextView view = new TextView(mActivity);
		view.setText("新闻中心");
		view.setTextColor(Color.RED);
		view.setTextSize(22);
		view.setGravity(Gravity.CENTER);
		
		flContent.addView(view);
		
		//修改页面标题
		tvTitle.setText("新闻");
	}

}
