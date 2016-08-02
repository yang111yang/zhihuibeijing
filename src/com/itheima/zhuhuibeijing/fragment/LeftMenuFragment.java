package com.itheima.zhuhuibeijing.fragment;

import java.util.ArrayList;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.itheima.zhuhuibeijing.MainActivity;
import com.itheima.zhuhuibeijing.R;
import com.itheima.zhuhuibeijing.domain.NewsMenu.NewsMenuData;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

public class LeftMenuFragment extends BaseFragment {

	@ViewInject(R.id.lv_list)
	private ListView lv_list;
	
	private ArrayList<NewsMenuData> mNewsMenuData;//侧边栏网络数据对象
	
	private int mCurrentPos;//被选中的item的位置

	private LeftMenuAdapter mAdapter;
	
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
		mAdapter = new LeftMenuAdapter();
		lv_list.setAdapter(mAdapter);
		
		lv_list.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				mCurrentPos = position;
				mAdapter.notifyDataSetChanged();//刷新ListView
				
				//收起侧边栏
				toggle();
			}
		});
		
	}

	/**
	 * 打开或关闭侧边栏
	 */
	protected void toggle() {
		MainActivity mainUI = (MainActivity) mActivity;
		SlidingMenu slidingMenu = mainUI.getSlidingMenu();
		slidingMenu.toggle();//如果当前状态是开，调用后就关，反之亦然
		
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
			
			View view = View.inflate(mActivity, R.layout.list_item_left_menu, null);
			TextView tvMenu = (TextView) view.findViewById(R.id.tv_menu);
			
			NewsMenuData item = getItem(position);
			tvMenu.setText(item.title);
			
			if (position == mCurrentPos) {
				//被选中
				tvMenu.setEnabled(true);
			}else{
				//未选中
				tvMenu.setEnabled(false);
			}
			
			
			return view;
		}
		
	}
	
}
