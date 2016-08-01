package com.itheima.zhuhuibeijing.fragment;

import com.itheima.zhuhuibeijing.R;

import android.view.View;

public class ContentFragment extends BaseFragment {

	@Override
	public View initData() {
		return null;
	}

	@Override
	public View initView() {
		View view = View.inflate(mActivity, R.layout.fragment_content, null);
		return view;
	}

}
