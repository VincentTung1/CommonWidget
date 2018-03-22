package com.vincent.commonwidget.util;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * SharePreference工具类
 * @author Vincent_Tung
 *
 */
public class SharePreferenceUtil {

	public static final String FileName ="vPref";  //Vincent's SharedPreferences



	/**
	 * 设置String串缓存
	 * @param context
	 * @param key
	 * @param value
	 */
	public static void setStringPref(Context context,String key ,String value){
		SharedPreferences sp = context.getSharedPreferences(FileName,0);
		sp.edit().putString(key, value).commit();
	}

	/**
	 * 获取String缓存
	 * @param context
	 * @param key
	 * @return
	 */
	public static String getStringPref(Context context,String key){
		SharedPreferences sp = context.getSharedPreferences(FileName,0);
		return sp.getString(key, null);
	}
	/**
	 * 设置int缓存
	 * @param context
	 * @param key
	 * @param value
	 */
	public static void setIntPref(Context context,String key ,int value){
		SharedPreferences sp = context.getSharedPreferences(FileName,0);
		sp.edit().putInt(key, value).commit();
	}

	/**
	 * 获取int缓存
	 * @param context
	 * @param key
	 * @return
	 */
	public static int getIntPref(Context context,String key){
		SharedPreferences sp = context.getSharedPreferences(FileName,0);
		return sp.getInt(key, 0);
	}
	/**
	 * 设置boolean缓存
	 * @param context
	 * @param key
	 * @param value
	 */
	public static void setBooleanPref(Context context,String key ,boolean value){
		SharedPreferences sp = context.getSharedPreferences(FileName,0);
		sp.edit().putBoolean(key, value).commit();
	}

	/**
	 * 获取boolean缓存
	 * @param context
	 * @param key
	 * @return
	 */
	public static boolean getBooleanPref(Context context,String key){
		SharedPreferences sp = context.getSharedPreferences(FileName,0);
		return sp.getBoolean(key, false);
	}

}
