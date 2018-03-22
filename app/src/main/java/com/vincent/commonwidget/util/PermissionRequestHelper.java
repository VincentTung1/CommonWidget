package com.vincent.commonwidget.util;

import android.Manifest;
import android.app.Activity;
import android.support.v4.app.ActivityCompat;

/**
 *  请求权限工具类
 */

public class PermissionRequestHelper {

    /**默认请求的权限*/
    private static String[] DEFAULT_PERMISSIONS =  new String[]{Manifest.permission.ACCESS_COARSE_LOCATION,Manifest.permission.ACCESS_FINE_LOCATION};

    /**默认请求权限码*/
    private static int DEFAULT_REQUEST_CODE = 0;


    /**
     * 请求默认需要的权限
     * @param activity
     */
    public static void requestDefaultPermissions(Activity activity){
        ActivityCompat.requestPermissions(activity,
                DEFAULT_PERMISSIONS,
                DEFAULT_REQUEST_CODE);
    }

    /**
     * 请求自定义权限
     * @param activity
     * @param permissions  权限数组
     */
    public static void requestCustomPermissions(Activity activity,String[] permissions){
        requestCustomPermissions(activity,permissions,DEFAULT_REQUEST_CODE);
    }

    /**
     * 请求自定义权限
     * @param activity
     * @param permissions  权限数组
     * @param requestCode   权限请求码
     */
    public static void requestCustomPermissions(Activity activity,String[] permissions,int requestCode){
        ActivityCompat.requestPermissions(activity,
                permissions,
                requestCode);
    }
}
