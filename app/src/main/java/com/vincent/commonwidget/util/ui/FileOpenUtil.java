package com.vincent.commonwidget.util.ui;

import android.app.Activity;
import android.content.Intent;

import com.vincent.commonwidget.util.IntentUtil;

import java.io.File;

/**
 *  打开文件工具类
 */

public class FileOpenUtil {

    /**
     * 打开文件
     * @param openfilePath
     */
    public static void openFile(Activity activity,String openfilePath) {
        if (!new File(openfilePath).exists()){
            ToastUtil.Short(activity,"文件不存在，不能打开文件！");
            return;
        }

        Intent intent=null;
        String filePath = openfilePath.toLowerCase();
        if (filePath.endsWith(".doc") || filePath.endsWith(".docx")){
            intent = IntentUtil.getWordFileIntent(filePath);
        }else if (filePath.endsWith(".ppt") || filePath.endsWith(".pptx")){
            intent=IntentUtil.getPptFileIntent(filePath);
        }else if(filePath.endsWith(".html")){
            intent=IntentUtil.getHtmlFileIntent(filePath);
        }else if(filePath.endsWith(".txt")){
            intent=IntentUtil.getTextFileIntent(filePath,false);
        }else if(filePath.endsWith(".xls")||filePath.endsWith(".xlsx")){
            intent=IntentUtil.getExcelFileIntent(filePath);
        }else if(filePath.endsWith(".zip")||filePath.endsWith(".rar")){
            intent=IntentUtil.getZipFileIntent(filePath);
        }else {
            ToastUtil.Short(activity,"文件已下载成功，但类型不支持打开浏览！");
            return;
        }



        activity.startActivity(intent);
    }
}
