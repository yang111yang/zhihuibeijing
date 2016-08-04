package com.itheima.zhuhuibeijing;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
/**
 * 新闻详情页面
 * @author 刘建阳
 * @date 创建时间：2016-8-4 下午5:14:05
 */
public class NewsDetailActivity extends Activity {

	@ViewInject(R.id.ll_control)
	private LinearLayout llControl;
	@ViewInject(R.id.btn_back)
	private ImageButton btnBack;
	@ViewInject(R.id.btn_textsize)
	private ImageButton btnTextSize;
	@ViewInject(R.id.btn_share)
	private ImageButton btnShare;
	@ViewInject(R.id.btn_menu)
	private ImageButton btnMenu;
	@ViewInject(R.id.wv_news_detail)
	private WebView mWebView;
	@ViewInject(R.id.pb_loading)
	private ProgressBar pbLoading;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_news_detail);
		
		ViewUtils.inject(this);
		
		llControl.setVisibility(View.VISIBLE);
		btnBack.setVisibility(View.VISIBLE);
		btnShare.setVisibility(View.VISIBLE);
		btnMenu.setVisibility(View.INVISIBLE);
		
		mWebView.loadUrl("http://www.itheima.com");
		
		WebSettings settings = mWebView.getSettings();
		settings.setBuiltInZoomControls(true);//显示缩放按钮
		settings.setUseWideViewPort(true);//支持双击缩放
		settings.setJavaScriptEnabled(true);//支持js功能
		
		mWebView.setWebViewClient(new WebViewClient(){
			//开始加载网页
			@Override
			public void onPageStarted(WebView view, String url, Bitmap favicon) {
				super.onPageStarted(view, url, favicon);
				pbLoading.setVisibility(View.VISIBLE);
			}
			//网页加载结束
			@Override
			public void onPageFinished(WebView view, String url) {
				super.onPageFinished(view, url);
				pbLoading.setVisibility(View.INVISIBLE);
			}
			
			//所有链接跳转都会走此方法
			@Override
			public boolean shouldOverrideUrlLoading(WebView view, String url) {
				view.loadUrl(url);//在跳转链接时，强制在当前webview中加载
				return super.shouldOverrideUrlLoading(view, url);
			}
			
		});
		
//		mWebView.goBack();//跳到上一个页面
//		mWebView.goForward();//跳到下一个页面
		
		mWebView.setWebChromeClient(new WebChromeClient(){
			@Override
			public void onProgressChanged(WebView view, int newProgress) {
				super.onProgressChanged(view, newProgress);
				//进度发生变化
				System.out.println("进度："+newProgress);
			}
			
			@Override
			public void onReceivedTitle(WebView view, String title) {
				super.onReceivedTitle(view, title);
				//网页的标题
				System.out.println("网页标题："+title);
			}
			
		});
	}
}
