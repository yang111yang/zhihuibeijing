package com.itheima.zhuhuibeijing.base.impl.menu;

import java.util.ArrayList;

import android.app.Activity;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.itheima.zhuhuibeijing.R;
import com.itheima.zhuhuibeijing.base.BaseMenuDetailPager;
import com.itheima.zhuhuibeijing.domain.PhotosBean;
import com.itheima.zhuhuibeijing.domain.PhotosBean.PhotoNews;
import com.itheima.zhuhuibeijing.global.GlobalConstants;
import com.itheima.zhuhuibeijing.utils.CacheUtils;
import com.lidroid.xutils.BitmapUtils;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;
import com.lidroid.xutils.view.annotation.ViewInject;
/**
 * 菜单详情页--组图
 * @author 刘建阳
 * @date 创建时间：2016-8-2 下午3:03:45
 */
public class PhotosMenuDetailPager extends BaseMenuDetailPager implements OnClickListener {


	@ViewInject(R.id.lv_photo)
	private ListView lvPhoto;
	@ViewInject(R.id.gv_photo)
	private GridView gvPhoto;
	private ArrayList<PhotoNews> mNewsList;
	private ImageButton btnPhoto;
	
	public PhotosMenuDetailPager(Activity activity, ImageButton btnPhoto) {
		super(activity);
		btnPhoto.setOnClickListener(this);
		this.btnPhoto = btnPhoto;
		
	}

	@Override
	public View initView() {
//		TextView view = new TextView(mActivity);
//		view.setText("菜单详情页--组图");
//		view.setTextColor(Color.RED);
//		view.setTextSize(22);
//		view.setGravity(Gravity.CENTER);
		View view = View.inflate(mActivity, R.layout.pager_photos_menu_detail, null);
		ViewUtils.inject(this,view);
		return view;
	}

	@Override
	public void initData() {
		
		String cache = CacheUtils.getCache(GlobalConstants.PHOTOS_URL, mActivity);
		
		if (!TextUtils.isEmpty(cache)) {
			processData(cache);
		}
		
		getDataFromServer();
		
	}

	private void getDataFromServer() {
		HttpUtils utils = new HttpUtils();
		utils.send(HttpMethod.GET, GlobalConstants.PHOTOS_URL, new RequestCallBack<String>() {

			@Override
			public void onSuccess(ResponseInfo<String> responseInfo) {
				//请求成功
				String result = responseInfo.result;
				processData(result);
				
				//写缓存
				CacheUtils.setCache(GlobalConstants.PHOTOS_URL, result, mActivity);
			}

			@Override
			public void onFailure(HttpException error, String msg) {
				//请求失败
				error.printStackTrace();
				Toast.makeText(mActivity, msg, Toast.LENGTH_SHORT).show();
			}
		});
	}

	/**
	 *解析json 
	 */
	private void processData(String result) {
		Gson gson = new Gson();
		PhotosBean photoBean = gson.fromJson(result, PhotosBean.class);
		
		mNewsList = photoBean.data.news;
		
		lvPhoto.setAdapter(new PhotoAdapter());
		gvPhoto.setAdapter(new PhotoAdapter());
	}
	
	class PhotoAdapter extends BaseAdapter{

		

		private BitmapUtils mBitmapUtils;

		public PhotoAdapter() {
			mBitmapUtils = new BitmapUtils(mActivity);
			//设置默认显示图片
			mBitmapUtils.configDefaultLoadingImage(R.drawable.pic_item_list_default);
		}

		@Override
		public int getCount() {
			return mNewsList.size();
		}

		@Override
		public PhotoNews getItem(int position) {
			return mNewsList.get(position);
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			ViewHolder holder;
			if (convertView == null) {
				convertView = View.inflate(mActivity, R.layout.list_item_photos, null);
				holder = new ViewHolder();
				holder.ivPic = (ImageView) convertView.findViewById(R.id.iv_pic);
				holder.tvTitle = (TextView) convertView.findViewById(R.id.tv_title);
				convertView.setTag(holder);
			}else {
				holder = (ViewHolder) convertView.getTag();
			}
			
			PhotoNews item = getItem(position);
			
			holder.tvTitle.setText(item.title);
			mBitmapUtils.display(holder.ivPic, item.listimage);
			
			return convertView;
		}
		
	}
	
	static class ViewHolder{
		public ImageView ivPic;
		public TextView tvTitle;
	}
	
	private boolean isListView = true;//组图切换标记

	@Override
	public void onClick(View v) {
		
		if (isListView) {
			lvPhoto.setVisibility(View.VISIBLE);
			gvPhoto.setVisibility(View.GONE);
			
			btnPhoto.setImageResource(R.drawable.icon_pic_grid_type);
			isListView = false;
		}else{
			lvPhoto.setVisibility(View.GONE);
			gvPhoto.setVisibility(View.VISIBLE);
			btnPhoto.setImageResource(R.drawable.icon_pic_list_type);
			isListView = true;
		}
	}
}
