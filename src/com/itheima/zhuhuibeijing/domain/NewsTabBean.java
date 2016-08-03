package com.itheima.zhuhuibeijing.domain;

import java.util.ArrayList;

/**
 * 页签详情数据对象
 * @author 刘建阳
 * @date 创建时间：2016-8-3 下午1:47:44
 */
public class NewsTabBean {
	
	
	public NewsTab data;
	
	public class NewsTab{
		public String more;
		public ArrayList<NewsData> news;
		public ArrayList<TopNews> topnews;
	}
	
	//新闻列表对象
	public class NewsData{
		public int id;
		public String listimage;
		public String pubdate;
		public String title;
		public String type;
		public String url;
		
	}
	
	//头条新闻对象
	public class TopNews{
		public int id;
		public String topimage;
		public String pubdate;
		public String title;
		public String type;
		public String url;
	}
	
}
