package com.itheima.zhuhuibeijing;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.Window;
import android.view.WindowManager;

import cn.jpush.android.api.JPushInterface;

import com.itheima.zhuhuibeijing.fragment.ContentFragment;
import com.itheima.zhuhuibeijing.fragment.LeftMenuFragment;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.jeremyfeinstein.slidingmenu.lib.app.SlidingFragmentActivity;

/**
 * 主页面
 * 
 * @author 刘建阳
 * @date 创建时间：2016-8-1 下午2:28:21
 */
public class MainActivity extends SlidingFragmentActivity {

	private static final String TAG_LEFT_MENU = "TAG_LEFT_MENU";
	private static final String TAG_CONTENT = "TAG_CONTENT";

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		requestWindowFeature(Window.FEATURE_NO_TITLE);

		setContentView(R.layout.activity_main);

		setBehindContentView(R.layout.activity_left_menu);

		SlidingMenu slidingMenu = getSlidingMenu();
		slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
		// slidingMenu.setBehindOffset(200);

		WindowManager wm = getWindowManager();
		int width = wm.getDefaultDisplay().getWidth();
		slidingMenu.setBehindOffset(width * 200 / 320);

		initFragment();

	}

	/**
	 * 初始化fragment
	 */
	private void initFragment() {
		FragmentManager fm = getSupportFragmentManager();
		FragmentTransaction transaction = fm.beginTransaction();// 开始事务
		transaction.replace(R.id.fl_left_menu, new LeftMenuFragment(),
				TAG_LEFT_MENU);// 参数1：帧布局容器的id,参数2：要替换的fragment 参数3：标记
		transaction.replace(R.id.fl_main, new ContentFragment(), TAG_CONTENT);
		transaction.commit();

		// Fragment content = fm.findFragmentByTag(TAG_CONTENT);

	}

	// 获取侧边栏fragment对象
	public LeftMenuFragment getLeftMenuFragment() {
		FragmentManager fm = getSupportFragmentManager();
		LeftMenuFragment fragment = (LeftMenuFragment) fm
				.findFragmentByTag(TAG_LEFT_MENU);
		return fragment;
	}

	// 获取侧边栏fragment对象
	public ContentFragment getContentFragment() {
		FragmentManager fm = getSupportFragmentManager();
		ContentFragment fragment = (ContentFragment) fm
				.findFragmentByTag(TAG_CONTENT);
		return fragment;
	}

	@Override
	protected void onResume() {
		super.onResume();
		JPushInterface.onResume(this);
	}

	@Override
	protected void onPause() {
		super.onPause();
		JPushInterface.onPause(this);
	}

}
