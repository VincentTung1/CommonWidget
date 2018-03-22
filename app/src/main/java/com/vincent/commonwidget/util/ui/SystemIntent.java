package com.vincent.commonwidget.util.ui;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.provider.ContactsContract;
import android.provider.MediaStore;

import java.io.File;

/**
 *  调用系统自带的意图
 */

public class SystemIntent {


    /**
     * 拨打电话
     * @param context
     * @param phoneNum  电话号码
     */
    @SuppressLint("MissingPermission")
    public static void callPhone(Context context, String phoneNum){
        Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:"+phoneNum));
        context.startActivity(intent);
    }

    /**
     *  发送短信
     * @param context
     * @param phoneNum
     */
    public static void sendMsg(Context context, String phoneNum){
        Uri smsToUri = Uri.parse("smsto:"+phoneNum+"");
        Intent mIntent = new Intent( Intent.ACTION_SENDTO, smsToUri );
        context.startActivity( mIntent );
    }

    /**
     *  复制号码到剪贴板
     * @param context
     * @param phoneNum
     */
    public static void copy2ClipBoard(Context context, String phoneNum){
        ClipboardManager myClipboard = (ClipboardManager)context.getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData myClip = ClipData.newPlainText("text", phoneNum);
        myClipboard.setPrimaryClip(myClip);
    }

    /**
     * 保存号码到通讯录
     * @param context
     * @param phoneNum
     */
    public static void saveContact(Context context,String phoneNum){
        Intent intent = new Intent(Intent.ACTION_INSERT);
        intent.setType("vnd.android.cursor.dir/person");
        intent.setType("vnd.android.cursor.dir/contact");
        intent.setType("vnd.android.cursor.dir/raw_contact");
        intent.putExtra(ContactsContract.Intents.Insert.PHONE_TYPE, ContactsContract.CommonDataKinds.Phone.TYPE_WORK_MOBILE);
        intent.putExtra(ContactsContract.Intents.Insert.PHONE, phoneNum);
        context.startActivity(intent);
    }

    /**
     *  拍照
     * @param activity
     * @param saveFile  保存文件的位置
     * @param requestCode  请求码
     */
    public static void takePhoto(Activity activity, File saveFile, int requestCode){
        // 设置跳转的系统拍照的activity为：MediaStore.ACTION_IMAGE_CAPTURE ;
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // 并设置拍照的存在方式为外部存储和存储的路径；
        intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(saveFile));
        //跳转到拍照界面;
        activity.startActivityForResult(intent, requestCode);
    }
}
