package com.itheima.zhuhuibeijing.base.impl.menu;

import java.util.ArrayList;

import android.app.Activity;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.itheima.zhuhuibeijing.R;
import com.itheima.zhuhuibeijing.base.BaseMenuDetailPager;
import com.itheima.zhuhuibeijing.domain.NewsMenu.NewsTabData;
import com.itheima.zhuhuibeijing.domain.NewsTabBean;
import com.itheima.zhuhuibeijing.domain.NewsTabBean.TopNews;
import com.itheima.zhuhuibeijing.global.GlobalConstants;
import com.itheima.zhuhuibeijing.utils.CacheUtils;
import com.itheima.zhuhuibeijing.view.TopNewsViewPager;
import com.lidroid.xutils.BitmapUtils;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.viewpagerindicator.CirclePageIndicator;

/**
 * 页签页面对象
 * 
 * @author 刘建阳
 * @date 创建时间：2016-8-2 下午4:27:16
 */
public class TabDetailPager extends BaseMenuDetailPager {

	private NewsTabData mTabData;// 单个页签的网络数据
	
	private TextView view;

	@ViewInject(R.id.vp_top_news)
	private TopNewsViewPager mViewPager;
	
	@ViewInject(R.id.tv_title)
	private TextView tvTitle;
	
	@ViewInject(R.id.indicator)
	private CirclePageIndicator indicator;
	
	private String mUrl;
	
	private ArrayList<TopNews> mTopnews;
	
	private TopNewsAdapter topNewsAdapter;
	
	public TabDetailPager(Activity activity, NewsTabData newsTabData) {
		super(activity);
		mTabData = newsTabData;
		
		mUrl = GlobalConstants.SERVER_URL + mTabData.url;
	}

	@Override
	public View initView() {
//		view = new TextView(mActivity);
//		// view.setText(mTabData.title);//此处空指针
//		view.setTextColor(Color.RED);
//		view.setTextSize(22);
//		view.setGravity(Gravity.CENTER);

		View view = View.inflate(mActivity, R.layout.pager_tab_detail, null);
		ViewUtils.inject(this,view);
		return view;
	}

	@Override
	public void initData() {
//		view.setText(mTabData.title);
		
		String cache = CacheUtils.getCache(mUrl, mActivity);
		if (!TextUtils.isEmpty(cache)) {
			processData(cache);
		}
		
		
		getDataFromServer();
	}

	private void getDataFromServer() {
		HttpUtils utils = new HttpUtils();
		utils.send(HttpMethod.GET, mUrl, new RequestCallBack<String>() {

			@Override
			public void onSuccess(ResponseInfo<String> responseInfo) {
				// 请求成功
				String result = responseInfo.result;

				// Gson
				processData(result);
				
				//写缓存
				CacheUtils.setCache(mUrl, result, mActivity);
			}

			@Override
			public void onFailure(HttpException error, String msg) {
				// 请求失败
				error.printStackTrace();
				Toast.makeText(mActivity, msg, Toast.LENGTH_SHORT)
						.show();
			}
		});
	}

	protected void processData(String json) {
		Gson gson = new Gson();
		NewsTabBean newsTabBean = gson.fromJson(json, NewsTabBean.class);
		
		//头条新闻填充数据
		mTopnews = newsTabBean.data.topnews;
		
		if (mTopnews != null) {
			topNewsAdapter = new TopNewsAdapter();
			mViewPager.setAdapter(topNewsAdapter);
			
			indicator.setViewPager(mViewPager);
	        indicator.setSnap(true);//快照方式展示
			
	        //事件要设置给indicator
	        indicator.setOnPageChangeListener(new OnPageChangeListener() {
				
				@Override
				public void onPageSelected(int arg0) {
					TopNews topNews = mTopnews.get(arg0);
					tvTitle.setText(topNews.title);
				}
				
				@Override
				public void onPageScrolled(int arg0, float arg1, int arg2) {
					
				}
				
				@Override
				public void onPageScrollStateChanged(int arg0) {
					
				}
			});
			
			//更新第一个头条新闻标题
			tvTitle.setText(mTopnews.get(0).title);
			
			indicator.onPageSelected(0);//默认让第一个选中(解决页面销毁后重新初始化时，Indicator仍然保留上次位置的bug)
		}
		
	}

	//头条新闻适配器
	class TopNewsAdapter extends PagerAdapter{

		private BitmapUtils mBitmapUtils;

		public TopNewsAdapter(){
			mBitmapUtils = new BitmapUtils(mActivity);
			
			mBitmapUtils.configDefaultLoadingImage(R.drawable.topnews_item_default);
		}
		
		
		@Override
		public int getCount() {
			return mTopnews.size();
		}

		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {
			return arg0 == arg1;
		}

		@Override
		public View instantiateItem(ViewGroup container, int position) {
			ImageView view = new ImageView(mActivity);
			view.setImageResource(R.drawable.topnews_item_default);
			view.setScaleType(ScaleType.FIT_XY);//设置图片缩放方式，宽高填充父控件 
			
			String imageUrl = mTopnews.get(position).topimage;
			
			//下载图片-->将图片设置给ImageView-->避免内存溢出-->缓存
			//BitmapUtils--XUtils
			mBitmapUtils.display(view, imageUrl);
			
			container.addView(view);
			return view;
		}

		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			container.removeView((View)object);
		}
		
		
		
	}
	
}
