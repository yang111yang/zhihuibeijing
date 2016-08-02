package com.itheima.zhuhuibeijing.base.impl.menu;

import android.app.Activity;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.itheima.zhuhuibeijing.base.BaseMenuDetailPager;
/**
 * 菜单详情页--互动
 * @author 刘建阳
 * @date 创建时间：2016-8-2 下午3:04:14
 */
public class InteractMenuDetailPager extends BaseMenuDetailPager {

	public InteractMenuDetailPager(Activity activity) {
		super(activity);
	}

	@Override
	public View initView() {
		TextView view = new TextView(mActivity);
		view.setText("菜单详情页--互动");
		view.setTextColor(Color.RED);
		view.setTextSize(22);
		view.setGravity(Gravity.CENTER);
		
		return view;
	}

}
