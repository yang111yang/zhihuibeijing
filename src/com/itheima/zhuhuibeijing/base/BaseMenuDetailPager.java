package com.itheima.zhuhuibeijing.base;

import android.app.Activity;
import android.view.View;

/**
 * 菜单详情页基类
 * @author 刘建阳
 * @date 创建时间：2016-8-2 下午2:54:22
 */
public abstract class BaseMenuDetailPager {

	public Activity mActivity;
	public View mRootView;
	
	
	public BaseMenuDetailPager(Activity activity) {
		mActivity = activity;
		mRootView = initView();
	}

	//初始化布局，必须有子类实现
	public abstract View initView();
	
	public void initData(){
		
	}
	
}
