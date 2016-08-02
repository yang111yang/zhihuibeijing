package com.itheima.zhuhuibeijing.base;

import android.app.Activity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.TextView;
import com.itheima.zhuhuibeijing.R;
/**
 * 五个标签页的基类
 * @author 刘建阳
 * @date 2016-8-1下午10:49:49
 */
public class BasePager {
	
	public Activity mActivity;//这个activity就是MainActivity
	public TextView tvTitle;
	public ImageButton btnMenu;
	public FrameLayout flContent;//空的帧布局对象，需要动态添加数据

	public View mRootView;
	
	
	public BasePager(Activity activity) {
		mActivity = activity;
		mRootView = initView();
	}

	//初始化布局
	public View initView(){
		View view = View.inflate(mActivity, R.layout.base_pager, null);
		tvTitle = (TextView) view.findViewById(R.id.tv_title);
		btnMenu = (ImageButton) view.findViewById(R.id.btn_menu);
		flContent = (FrameLayout) view.findViewById(R.id.fl_content);
		return view;
		
	}
	
	//初始化数据
	public void initData(){
		
	}
	
}
