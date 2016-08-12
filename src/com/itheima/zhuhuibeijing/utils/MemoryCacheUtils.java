package com.itheima.zhuhuibeijing.utils;

import android.graphics.Bitmap;
import android.support.v4.util.LruCache;

/**
 * 内存缓存
 * 
 * @author 刘建阳
 * @date 2016-8-12 下午4:40:21
 */
public class MemoryCacheUtils {

//	private HashMap<String, SoftReference<Bitmap>> mMemoryCache = new HashMap<String, SoftReference<Bitmap>>();

	private LruCache<String, Bitmap> mMemoryCache;
	
	public MemoryCacheUtils() {
		//LruCache 可以将最近最少使用的对象回收掉，从而保证内存不会超出范围
		//Lru:least recentlly used 最近最少使用的算法
		
		Runtime.getRuntime().maxMemory();
		
//		mMemoryCache = new LruCache<String, Bitmap>(10MB);
	}

	/**
	 * 写缓存
	 */
	public void setMemoryCache(String url, Bitmap bitmap) {
		// mMemoryCache.put(url, bitmap);
//		SoftReference<Bitmap> soft = new SoftReference<Bitmap>(bitmap);// 使用软引用将bitmap包装起来
//		mMemoryCache.put(url, soft);
	}

	/**
	 * 读缓存
	 */
	public Bitmap getMemoryCache(String url) {
//		SoftReference<Bitmap> softReference = mMemoryCache.get(url);
//		if (softReference != null) {
//			Bitmap bitmap = softReference.get();
//			return bitmap;
//		}
		return null;
	}

}
