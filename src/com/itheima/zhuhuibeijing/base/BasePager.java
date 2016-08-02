package com.itheima.zhuhuibeijing.base;

import android.app.Activity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.TextView;

import com.itheima.zhuhuibeijing.MainActivity;
import com.itheima.zhuhuibeijing.R;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
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
		
		btnMenu.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				toggle();
			}
		});
		
		
		
		return view;
		
	}
	
	//初始化数据
	public void initData(){
		
	}
	
	
	/**
	 * 打开或关闭侧边栏
	 */
	protected void toggle() {
		MainActivity mainUI = (MainActivity) mActivity;
		SlidingMenu slidingMenu = mainUI.getSlidingMenu();
		slidingMenu.toggle();//如果当前状态是开，调用后就关，反之亦然
		
	}
	
}
