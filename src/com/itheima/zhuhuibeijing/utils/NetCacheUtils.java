package com.itheima.zhuhuibeijing.utils;

import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.widget.ImageView;

/**
 * 网络缓存
 * 
 * @author 刘建阳
 * 时间：2016-8-12下午2:45:37
 */
public class NetCacheUtils {

	public void getBitmapFromNet(ImageView imageView, String url) {
		//AsyncTask 异步封装的工具，可以实现异步请求和主界面更新（对线程池+handler的封装）
		new BitmapTask().execute(imageView,url);//启动AsyncTask
	}
	/**
	 * 三个泛型的意义：
	 * 第一个泛型：doInBackground的参数类型
	 * 第二个泛型：onProgressUpdate的参数类型
	 * 第三个泛型：onPostExecute的参数类型及doInBackground的返回值类型
	 * @author 刘建阳
	 * @date 2016-8-12 下午3:06:09
	 */
	class BitmapTask extends AsyncTask<Object, Integer, Bitmap>{

		//1.预加载，运行在主线程
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			System.out.println("onPreExecute");
		}

		//2.正在加载，运行在子线程（核心方法），可以直接异步请求
		@Override
		protected Bitmap doInBackground(Object... params) {
			System.out.println("doInBackground");
			ImageView imageView = (ImageView) params[0];
			String url = (String) params[1];
			
			//开始下载图片
			
			return null;
		}

		//3.更新进度，运行在主线程	
		@Override
		protected void onProgressUpdate(Integer... values) {
			super.onProgressUpdate(values);
			System.out.println("onProgressUpdate");
		}

		//4.加载结束，运行在主线程（核心方法），可以直接更新UI
		@Override
		protected void onPostExecute(Bitmap result) {
			super.onPostExecute(result);
			System.out.println("onPostExecute");
		}
		
		
		
	}

}
