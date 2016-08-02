package com.itheima.zhuhuibeijing.fragment;

import java.util.ArrayList;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;

import com.itheima.zhuhuibeijing.MainActivity;
import com.itheima.zhuhuibeijing.NoScrollViewPager;
import com.itheima.zhuhuibeijing.R;
import com.itheima.zhuhuibeijing.base.BasePager;
import com.itheima.zhuhuibeijing.base.impl.GovAffairsPager;
import com.itheima.zhuhuibeijing.base.impl.HomePager;
import com.itheima.zhuhuibeijing.base.impl.NewsCenterPager;
import com.itheima.zhuhuibeijing.base.impl.SettingPager;
import com.itheima.zhuhuibeijing.base.impl.SmartServicePager;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;

public class ContentFragment extends BaseFragment {

	private NoScrollViewPager mViewPager;
	
	private ArrayList<BasePager> mPagers;//五个标签页的集合
	
	private RadioGroup rgGroup;

	@Override
	public void initData() {
		mPagers = new ArrayList<BasePager>();
		//添加五个标签页
		mPagers.add(new HomePager(mActivity));
		mPagers.add(new NewsCenterPager(mActivity));
		mPagers.add(new SmartServicePager(mActivity));
		mPagers.add(new GovAffairsPager(mActivity));
		mPagers.add(new SettingPager(mActivity));
		
		mViewPager.setAdapter(new ContentAdapter());
		mViewPager.setOnPageChangeListener(new OnPageChangeListener() {
			
			@Override
			public void onPageSelected(int position) {
				mPagers.get(position).initData();
				
				if (position == 0 || position == 4) {
					//首页和设置页面要禁用侧边栏
					setSlidingMenuEnable(false);
				}else{
					//其他页面开启侧边栏
					setSlidingMenuEnable(true);
				}
			}
			
			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
				
			}
			
			@Override
			public void onPageScrollStateChanged(int arg0) {
				
			}
		});
		
		//手动加载第一页数据
		mPagers.get(0).initData();
		//首页禁用侧边栏
		setSlidingMenuEnable(false);
		
		
		//底栏标签切换监听
		rgGroup.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				switch (checkedId) {
				case R.id.rb_home:
					//首页
//					mViewPager.setCurrentItem(0);
					mViewPager.setCurrentItem(0, false);//参数2：表示是否具有滑动动画
					break;
				case R.id.rb_news:
					//新闻中心
					mViewPager.setCurrentItem(1, false);
					break;
				case R.id.rb_smart:
					//智慧服务
					mViewPager.setCurrentItem(2, false);
					break;
				case R.id.rb_gov:
					//政务
					mViewPager.setCurrentItem(3, false);
					break;
				case R.id.rb_setting:
					//设置
					mViewPager.setCurrentItem(4, false);
					break;
				}
			}
		});
		
	}

	/**
	 * 开启或禁用侧边栏
	 * @param enable
	 */
	protected void setSlidingMenuEnable(boolean enable) {
		//获取侧边栏对象
		MainActivity mainUI = (MainActivity) mActivity;
		SlidingMenu slidingMenu = mainUI.getSlidingMenu();
		if (enable) {
			slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
		}else{
			slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_NONE);
		}
	}

	@Override
	public View initView() {
		View view = View.inflate(mActivity, R.layout.fragment_content, null);
		mViewPager = (NoScrollViewPager)view.findViewById(R.id.vp_content);
		rgGroup = (RadioGroup) view.findViewById(R.id.rg_group);
		return view;
	}
	
	class ContentAdapter extends PagerAdapter{

		@Override
		public int getCount() {
			return mPagers.size();
		}

		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {
			return arg0 == arg1;
		}
		
		@SuppressWarnings("deprecation")
		@Override
		public Object instantiateItem(ViewGroup container, int position) {
			BasePager pager = mPagers.get(position);
			View view = pager.mRootView;//获取当前页面对象的布局
			
			//pager.initData();//初始化数据,ViewPager会默认加载下一个页面，为了节省流量和性能，不要在此处调用初始化方法
			
			container.addView(view);
			
			return view;
		}
		
		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			container.removeView((View)object);
		}
	}
	
	/**
	 * 获取新闻中心页面
	 */
	public NewsCenterPager getNewsCenterPager(){
		NewsCenterPager pager = (NewsCenterPager) mPagers.get(1);
		return pager;
	}
	

}
