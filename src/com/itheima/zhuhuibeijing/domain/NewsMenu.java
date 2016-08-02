package com.itheima.zhuhuibeijing.domain;

import java.util.ArrayList;

/**
 * 分类信息的对象封装
 * 
 * 使用Gson解析时，对象书写技巧
 * 1.逢{}创建对象，逢[]创建集合ArrayList
 * 2.所有字段名称要与json返回的字段高度一致
 * 
 * @author 刘建阳
 * @date 创建时间：2016-8-2 上午10:21:53
 */
public class NewsMenu {

	public int retcode;
	public ArrayList<Integer> extend;
	public ArrayList<NewsMenuData> data;
	
	//侧边栏菜单的对象
	public class NewsMenuData{
		
		public int id;
		public String title;
		public int type;
		public ArrayList<NewsTabData> children;
		
		@Override
		public String toString() {
			return "NewsMenuData [title=" + title + ", children=" + children
					+ "]";
		}
		
		
		
	}

	//页签的对象
	public class NewsTabData{
		
		public int id;
		public String title;
		public int type;
		public String url;
		
		@Override
		public String toString() {
			return "NewsTabData [title=" + title + "]";
		}
		
		
		
	}

	@Override
	public String toString() {
		return "NewsMenu [data=" + data + "]";
	}
	
	
	
}
