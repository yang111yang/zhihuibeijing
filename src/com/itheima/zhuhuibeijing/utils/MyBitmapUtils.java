package com.itheima.zhuhuibeijing.utils;

import com.itheima.zhuhuibeijing.R;

import android.graphics.Bitmap;
import android.widget.ImageView;

/**
 * 自定义三级缓存图片加载工具
 * 
 * @author 刘建阳
 * 
 *         时间：2016-8-12下午2:42:41
 */
public class MyBitmapUtils {

	private NetCacheUtils mNetCacheUtils;
	private LocalCacheUtils mLocalCacheUtils;

	public MyBitmapUtils() {
		mLocalCacheUtils = new LocalCacheUtils();
		mNetCacheUtils = new NetCacheUtils(mLocalCacheUtils);
	}

	public void display(ImageView imageView, String url) {
		// 设置默认图片
		imageView.setImageResource(R.drawable.pic_item_list_default);

		// 先从内存里面加载图片，速度最快，不浪费流量
		// 其次从本地（sdcard）中加载图片，速度快，不浪费流量
		Bitmap bitmap = mLocalCacheUtils.getLocalCache(url);
		if (bitmap != null) {
			imageView.setImageBitmap(bitmap);
			System.out.println("从本地加载图片啦...");
			return;
		}
		// 最后从网络下载图片，速度慢，浪费流量
		mNetCacheUtils.getBitmapFromNet(imageView, url);

	}

}
