package com.itheima.zhuhuibeijing.base.impl.menu;

import java.util.ArrayList;

import android.app.Activity;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;

import com.itheima.zhuhuibeijing.R;
import com.itheima.zhuhuibeijing.base.BaseMenuDetailPager;
import com.itheima.zhuhuibeijing.domain.NewsMenu.NewsTabData;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

/**
 * 菜单详情页--新闻
 * 
 * @author 刘建阳
 * @date 创建时间：2016-8-2 下午3:00:52
 */
public class NewsMenuDetailPager extends BaseMenuDetailPager {

	@ViewInject(R.id.vp_news_menu_detail)
	private ViewPager mViewPager;
	
	private ArrayList<NewsTabData> mTabData;//页签网络数据
	
	private ArrayList<TabDetailPager> mPagers;

	public NewsMenuDetailPager(Activity activity, ArrayList<NewsTabData> children) {
		super(activity);
		mTabData = children;
	}

	@Override
	public View initView() {
		View view = View.inflate(mActivity, R.layout.pager_news_menu_detail,
				null);
		ViewUtils.inject(this, view);
		return view;
	}

	
	@Override
	public void initData() {
		mPagers = new ArrayList<TabDetailPager>();
		
		//初始化页签
		for (int i = 0; i < mTabData.size(); i++) {
			TabDetailPager tabDetailPager = new TabDetailPager(mActivity, mTabData.get(i));
			mPagers.add(tabDetailPager);
		}
		
		mViewPager.setAdapter(new NewsMenuDetailAdapter());
	}
	
	class NewsMenuDetailAdapter extends PagerAdapter {

		@Override
		public int getCount() {
			return mPagers.size();
		}

		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {
			return arg0 == arg1;
		}

		@Override
		public Object instantiateItem(ViewGroup container, int position) {
			
			TabDetailPager pager = mPagers.get(position);
			View view = pager.mRootView;
			container.addView(view);
			pager.initData();
			return view;
		}

		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			container.removeView((View) object);
		}

	}

}
