package com.itheima.zhuhuibeijing.utils;

import java.lang.ref.SoftReference;
import java.util.HashMap;

import android.graphics.Bitmap;

/**
 * 内存缓存
 * 
 * @author 刘建阳
 * @date 2016-8-12 下午4:40:21
 */
public class MemoryCacheUtils {

	private HashMap<String, SoftReference<Bitmap>> mMemoryCache = new HashMap<String, SoftReference<Bitmap>>();

	/**
	 * 写缓存
	 */
	public void setMemoryCache(String url, Bitmap bitmap) {
		// mMemoryCache.put(url, bitmap);
		SoftReference<Bitmap> soft = new SoftReference<Bitmap>(bitmap);// 使用软引用将bitmap包装起来
		mMemoryCache.put(url, soft);
	}

	/**
	 * 读缓存
	 */
	public Bitmap getMemoryCache(String url) {
		SoftReference<Bitmap> softReference = mMemoryCache.get(url);
		if (softReference != null) {
			Bitmap bitmap = softReference.get();
			return bitmap;
		}
		return null;
	}

}
