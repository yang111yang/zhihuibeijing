package com.itheima.zhuhuibeijing.domain;

import java.util.ArrayList;

public class PhotosBean {

	public PhotosData data;

	public class PhotosData {
		public String more;
		public ArrayList<PhotoNews> news;
	}

	public class PhotoNews {
		public int id;
		public String listimage;
		public String title;
	}

}
