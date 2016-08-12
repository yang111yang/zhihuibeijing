package com.itheima.zhuhuibeijing.utils;

import android.widget.ImageView;
/**
 * 自定义三级缓存图片加载工具
 * @author 刘建阳
 *
 * 时间：2016-8-12下午2:42:41
 */
public class MyBitmapUtils {
	

	private NetCacheUtils mNetCacheUtils;

	public MyBitmapUtils() {
		mNetCacheUtils = new NetCacheUtils();
	}

	public void display(ImageView imageView, String url) {
		//先从内存里面加载图片，速度最快，不浪费流量
		//其次从本地（sdcard）中加载图片，速度快，不浪费流量
		//最后从网络下载图片，速度慢，浪费流量	
		
		mNetCacheUtils.getBitmapFromNet(imageView,url);
		
		
	}


}
