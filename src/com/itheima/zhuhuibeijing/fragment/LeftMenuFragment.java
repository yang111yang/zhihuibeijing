package com.itheima.zhuhuibeijing.fragment;

import java.util.ArrayList;

import com.itheima.zhuhuibeijing.R;
import com.itheima.zhuhuibeijing.domain.NewsMenu.NewsMenuData;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;

public class LeftMenuFragment extends BaseFragment {

	@ViewInject(R.id.lv_list)
	private ListView lv_list;
	
	private ArrayList<NewsMenuData> mNewsMenuData;//侧边栏网络数据对象
	
	@Override
	public void initData() {
	}

	@Override
	public View initView() {
		View view = View.inflate(mActivity, R.layout.fragment_left_menu, null);

		ViewUtils.inject(this,view); //注入view和事件
		return view;
		
	}
	
	//给侧边栏设置数据
	public void setMenuData(ArrayList<NewsMenuData> data){
		//更新页面
		mNewsMenuData = data;
	}

	class LeftMenuAdapter extends BaseAdapter{

		@Override
		public int getCount() {
			return mNewsMenuData.size();
		}

		@Override
		public NewsMenuData getItem(int position) {
			return mNewsMenuData.get(position);
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			
			
			return null;
		}
		
	}
	
}
