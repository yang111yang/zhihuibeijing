package com.itheima.zhuhuibeijing;

import cn.jpush.android.api.JPushInterface;

import com.itheima.zhuhuibeijing.utils.PrefUtils;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.widget.RelativeLayout;

/**
 * 闪屏页面
 * 
 * @author 刘建阳
 * @date 创建时间：2016-8-1 上午11:34:01
 */
public class SplashActivity extends Activity {

	private RelativeLayout rl_root;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		requestWindowFeature(Window.FEATURE_NO_TITLE);

		setContentView(R.layout.activity_splash);

		rl_root = (RelativeLayout) findViewById(R.id.rl_root);

		// 旋转动画
		RotateAnimation rotateAnimation = new RotateAnimation(0, 360,
				Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF,
				0.5f);
		rotateAnimation.setDuration(1000);// 动画时间
		rotateAnimation.setFillAfter(true);// 保持动画结束时的状态

		// 缩放动画
		ScaleAnimation scaleAnimation = new ScaleAnimation(0, 1, 0, 1,
				Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF,
				0.5f);
		scaleAnimation.setDuration(1000);// 动画时间
		scaleAnimation.setFillAfter(true);// 保持动画结束时的状态

		// 渐变动画
		AlphaAnimation alphaAnimation = new AlphaAnimation(0, 1);
		alphaAnimation.setDuration(1000);// 动画时间
		alphaAnimation.setFillAfter(true);// 保持动画结束时的状态

		// 动画集合
		AnimationSet set = new AnimationSet(true);
		set.addAnimation(rotateAnimation);
		set.addAnimation(scaleAnimation);
		set.addAnimation(alphaAnimation);

		// 启动动画
		rl_root.startAnimation(set);

		// 监听动画
		set.setAnimationListener(new AnimationListener() {

			@Override
			public void onAnimationStart(Animation animation) {

			}

			@Override
			public void onAnimationRepeat(Animation animation) {

			}

			@Override
			public void onAnimationEnd(Animation animation) {
				// 动画结束
				// 如果是第一次进入, 跳新手引导
				// 否则跳主页面
				boolean isFirstEnter = PrefUtils.getBoolean(
						SplashActivity.this, "is_first_enter", true);

				Intent intent;
				if (isFirstEnter) {
					// 新手引导
					intent = new Intent(getApplicationContext(),
							GuideActivity.class);
				} else {
					// 主页面
					intent = new Intent(getApplicationContext(),
							MainActivity.class);
				}

				startActivity(intent);

				finish();// 结束当前页面
			}
		});

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
