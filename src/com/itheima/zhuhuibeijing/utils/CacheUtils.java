package com.itheima.zhuhuibeijing.utils;

import android.content.Context;

/**
 * 网络缓存的工具类
 * @author 刘建阳
 * @date 创建时间：2016-8-2 上午11:09:13
 */
public class CacheUtils {

	/**
	 * 以url为key，以json为value，保存在本地
	 * @param url
	 * @param json
	 */
	public static void setCache(String url, String json, Context ctx){
		//也可以用文件缓存，以MD5(url)为文件名，以json为文件的内容
		PrefUtils.setString(ctx, url, json);
	}
	/**
	 * 获取缓存
	 * @param url
	 * @param ctx
	 * @return 
	 */
	public static String getCache(String url, Context ctx){
		//文件缓存：查找有没有一个叫做MD5(url)的文件，有的话，说明缓存
		return PrefUtils.getString(ctx, url, null);
	}
	
}
