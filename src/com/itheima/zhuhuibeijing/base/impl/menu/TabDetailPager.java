package com.itheima.zhuhuibeijing.base.impl.menu;

import android.app.Activity;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.itheima.zhuhuibeijing.R;
import com.itheima.zhuhuibeijing.base.BaseMenuDetailPager;
import com.itheima.zhuhuibeijing.domain.NewsMenu.NewsTabData;
import com.itheima.zhuhuibeijing.domain.NewsTabBean;
import com.itheima.zhuhuibeijing.global.GlobalConstants;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;
import com.lidroid.xutils.view.annotation.ViewInject;

/**
 * 页签页面对象
 * 
 * @author 刘建阳
 * @date 创建时间：2016-8-2 下午4:27:16
 */
public class TabDetailPager extends BaseMenuDetailPager {

	private NewsTabData mTabData;// 单个页签的网络数据
	private TextView view;

	@ViewInject(R.id.vp_news_menu_detail)
	private ViewPager mViewPager;
	private String mUrl;
	
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
		ViewUtils.inject(mActivity);
		return view;
	}

	@Override
	public void initData() {
//		view.setText(mTabData.title);
		getDataFromServer();
	}

	private void getDataFromServer() {
		HttpUtils utils = new HttpUtils();
		utils.send(HttpMethod.GET, mUrl, new RequestCallBack<String>() {

			@Override
			public void onSuccess(ResponseInfo<String> responseInfo) {
				// 请求成功
				String result = responseInfo.result;
				System.out.println("服务器返回的结果：" + result);

				// Gson
				processData(result);
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
		gson.fromJson(json, NewsTabBean.class);
		
	}

	//头条新闻适配器
	class TopNewsAdapter extends PagerAdapter{

		@Override
		public int getCount() {
			return 0;
		}

		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {
			return false;
		}

		@Override
		public Object instantiateItem(ViewGroup container, int position) {
			return super.instantiateItem(container, position);
		}

		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			super.destroyItem(container, position, object);
		}
		
		
		
	}
	
}
