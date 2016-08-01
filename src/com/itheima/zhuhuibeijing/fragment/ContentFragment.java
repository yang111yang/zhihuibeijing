package com.itheima.zhuhuibeijing.fragment;

import java.util.ArrayList;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;

import com.itheima.zhuhuibeijing.NoScrollViewPager;
import com.itheima.zhuhuibeijing.R;
import com.itheima.zhuhuibeijing.base.BasePager;
import com.itheima.zhuhuibeijing.base.impl.GovAffairsPager;
import com.itheima.zhuhuibeijing.base.impl.HomePager;
import com.itheima.zhuhuibeijing.base.impl.NewsCenterPager;
import com.itheima.zhuhuibeijing.base.impl.SettingPager;
import com.itheima.zhuhuibeijing.base.impl.SmartServicePager;

public class ContentFragment extends BaseFragment {

	private NoScrollViewPager mViewPager;
	
	private ArrayList<BasePager> mPagers;//五个标签页的集合

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
	}

	@Override
	public View initView() {
		View view = View.inflate(mActivity, R.layout.fragment_content, null);
		mViewPager = (NoScrollViewPager)view.findViewById(R.id.vp_content);
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
			
			pager.initData();//初始化数据
			
			container.addView(view);
			
			return view;
		}
		
		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			container.removeView((View)object);
		}
	}

}
