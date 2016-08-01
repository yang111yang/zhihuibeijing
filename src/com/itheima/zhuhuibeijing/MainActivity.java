package com.itheima.zhuhuibeijing;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.Window;
import com.itheima.zhuhuibeijing.fragment.ContentFragment;
import com.itheima.zhuhuibeijing.fragment.LeftMenuFragment;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.jeremyfeinstein.slidingmenu.lib.app.SlidingFragmentActivity;
/**
 * 主页面
 * @author 刘建阳
 * @date 创建时间：2016-8-1 下午2:28:21
 */
public class MainActivity extends SlidingFragmentActivity {
	
	
	private static final String TAG_LEFT_MENU = null;
	private static final String TAG_CONTENT = null;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		
		setContentView(R.layout.activity_main);
		
		setBehindContentView(R.layout.activity_left_menu);
		
		SlidingMenu slidingMenu = getSlidingMenu();
		slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
		slidingMenu.setBehindOffset(200);
		
		initFragment();
		
	}

	/**
	 * 初始化fragment
	 */
	private void initFragment(){
		FragmentManager fm = getSupportFragmentManager();
		FragmentTransaction transaction = fm.beginTransaction();//开始事务 
		transaction.replace(R.id.fl_left_menu,new LeftMenuFragment(),TAG_LEFT_MENU);//参数1：帧布局容器的id,参数2：要替换的fragment 参数3：标记 
		transaction.replace(R.id.fl_main, new ContentFragment(),TAG_CONTENT);
		transaction.commit();
		
//		Fragment content = fm.findFragmentByTag(TAG_CONTENT);
		
	}
}
