package com.itheima.zhuhuibeijing.utils;

import android.content.Context;
/**
 * dp和px相互转换工具类
 * @author 刘建阳
 * @date 2016-8-15 上午9:42:37
 */
public class DensityUtils {
	
	
	public static int dip2px(float dip, Context ctx){
		float density = ctx.getResources().getDisplayMetrics().density;
		int px = (int) (dip*density + 0.5);//+0.5可以实现四舍五入
		return px;
	}
	
	public static float px2dip(int px, Context ctx){
		float density = ctx.getResources().getDisplayMetrics().density;
		float dp = px/density;//+0.5可以实现四舍五入
		return dp;
	}
	
	
	
}
