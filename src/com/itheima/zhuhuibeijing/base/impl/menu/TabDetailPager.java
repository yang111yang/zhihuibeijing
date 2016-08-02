package com.itheima.zhuhuibeijing.base.impl.menu;

import android.app.Activity;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.itheima.zhuhuibeijing.base.BaseMenuDetailPager;
import com.itheima.zhuhuibeijing.domain.NewsMenu.NewsTabData;

/**
 * 页签页面对象
 * 
 * @author 刘建阳
 * @date 创建时间：2016-8-2 下午4:27:16
 */
public class TabDetailPager extends BaseMenuDetailPager {

	private NewsTabData mTabData;// 单个页签的网络数据
	private TextView view;

	public TabDetailPager(Activity activity, NewsTabData newsTabData) {
		super(activity);
		mTabData = newsTabData;
	}

	@Override
	public View initView() {
		view = new TextView(mActivity);
		// view.setText(mTabData.title);//此处空指针
		view.setTextColor(Color.RED);
		view.setTextSize(22);
		view.setGravity(Gravity.CENTER);

		return view;
	}

	@Override
	public void initData() {
		view.setText(mTabData.title);
	}

}
