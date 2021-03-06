package com.itheima.zhuhuibeijing;

import cn.jpush.android.api.JPushInterface;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.onekeyshare.OnekeyShare;
import cn.sharesdk.onekeyshare.OnekeyShareTheme;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebSettings.TextSize;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

/**
 * 新闻详情页面
 * 
 * @author 刘建阳
 * @date 创建时间：2016-8-4 下午5:14:05
 */
public class NewsDetailActivity extends Activity implements OnClickListener {

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

	private String mUrl;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		requestWindowFeature(Window.FEATURE_NO_TITLE);

		setContentView(R.layout.activity_news_detail);

		ViewUtils.inject(this);

		llControl.setVisibility(View.VISIBLE);
		btnBack.setVisibility(View.VISIBLE);
		btnShare.setVisibility(View.VISIBLE);
		btnMenu.setVisibility(View.INVISIBLE);

		btnBack.setOnClickListener(this);
		btnTextSize.setOnClickListener(this);
		btnShare.setOnClickListener(this);

		mUrl = getIntent().getStringExtra("url");

		mWebView.loadUrl(mUrl);
		// mWebView.loadUrl("http://www.itheima.com");

		WebSettings settings = mWebView.getSettings();
		settings.setBuiltInZoomControls(true);// 显示缩放按钮(wap网页不支持)
		settings.setUseWideViewPort(true);// 支持双击缩放
		settings.setJavaScriptEnabled(true);// 支持js功能

		mWebView.setWebViewClient(new WebViewClient() {
			// 开始加载网页
			@Override
			public void onPageStarted(WebView view, String url, Bitmap favicon) {
				super.onPageStarted(view, url, favicon);
				pbLoading.setVisibility(View.VISIBLE);
			}

			// 网页加载结束
			@Override
			public void onPageFinished(WebView view, String url) {
				super.onPageFinished(view, url);
				pbLoading.setVisibility(View.INVISIBLE);
			}

			// 所有链接跳转都会走此方法
			@Override
			public boolean shouldOverrideUrlLoading(WebView view, String url) {
				view.loadUrl(url);// 在跳转链接时，强制在当前webview中加载
				return super.shouldOverrideUrlLoading(view, url);
			}

		});

		// mWebView.goBack();//跳到上一个页面
		// mWebView.goForward();//跳到下一个页面

		mWebView.setWebChromeClient(new WebChromeClient() {
			@Override
			public void onProgressChanged(WebView view, int newProgress) {
				super.onProgressChanged(view, newProgress);
				// 进度发生变化
				System.out.println("进度：" + newProgress);
			}

			@Override
			public void onReceivedTitle(WebView view, String title) {
				super.onReceivedTitle(view, title);
				// 网页的标题
				System.out.println("网页标题：" + title);
			}

		});

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_back:
			finish();
			break;
		case R.id.btn_textsize:
			// 修改网页字体的大小
			showChooseDialog();
			break;
		case R.id.btn_share:
			showShare();
			break;

		default:
			break;
		}

	}

	private void showShare() {
		ShareSDK.initSDK(this);
		OnekeyShare oks = new OnekeyShare();

		oks.setTheme(OnekeyShareTheme.CLASSIC);

		// 关闭sso授权
		oks.disableSSOWhenAuthorize();

		// 分享时Notification的图标和文字 2.5.9以后的版本不调用此方法
		// oks.setNotification(R.drawable.ic_launcher,
		// getString(R.string.app_name));
		// title标题，印象笔记、邮箱、信息、微信、人人网和QQ空间使用
		oks.setTitle(getString(R.string.ssdk_oks_share));
		// titleUrl是标题的网络链接，仅在人人网和QQ空间使用
		oks.setTitleUrl("http://sharesdk.cn");
		// text是分享文本，所有平台都需要这个字段
		oks.setText("我是分享文本");
		// imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
		// oks.setImagePath("/sdcard/test.jpg");//确保SDcard下面存在此张图片
		// url仅在微信（包括好友和朋友圈）中使用
		oks.setUrl("http://sharesdk.cn");
		// comment是我对这条分享的评论，仅在人人网和QQ空间使用
		oks.setComment("我是测试评论文本");
		// site是分享此内容的网站名称，仅在QQ空间使用
		oks.setSite(getString(R.string.app_name));
		// siteUrl是分享此内容的网站地址，仅在QQ空间使用
		oks.setSiteUrl("http://sharesdk.cn");

		// 启动分享GUI
		oks.show(this);
	}

	private int mTempWhich;// 记录临时选择的字体的大小(点击确定之前)
	private int mCurrentWhich = 2;// 记录当前选择的字体的大小(点击确定之后)

	/**
	 * 展示选择字体大小的弹窗
	 */
	private void showChooseDialog() {
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle("字体设置");
		String[] items = new String[] { "超大号字体", "大号字体", "正常字体", "小号字体",
				"超小号字体" };
		builder.setSingleChoiceItems(items, mCurrentWhich,
				new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						mTempWhich = which;
					}
				});
		builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				// 根据选择的字体来修改网页文字的大小

				WebSettings settings = mWebView.getSettings();

				switch (mTempWhich) {
				case 0:
					// 超大字体
					settings.setTextSize(TextSize.LARGEST);
					// settings.setTextZoom(textZoom);
					break;
				case 1:
					// 大号字体
					settings.setTextSize(TextSize.LARGER);
					break;
				case 2:
					// 正常字体
					settings.setTextSize(TextSize.NORMAL);
					break;
				case 3:
					// 小号字体
					settings.setTextSize(TextSize.SMALLER);
					break;
				case 4:
					// 超小号字体
					settings.setTextSize(TextSize.SMALLEST);
					break;

				default:
					break;
				}

				mCurrentWhich = mTempWhich;
			}
		});
		builder.setNegativeButton("取消", null);
		builder.show();
	}

	@Override
	protected void onResume() {
		super.onResume();
		JPushInterface.onResume(this);
	}

	@Override
	protected void onPause() {
		super.onPause();
		JPushInterface.onPause(this);
	}

}
