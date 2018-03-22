package com.vincent.commonwidget.util.ui;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;

/**
 * Created by vincent_tung on 2018/1/18.
 */

public class DialogManager {

   private AlertDialog mDialog;

   private static class DialogLoader{
       private static DialogManager INSTANCE  = new DialogManager();
   }

   public static DialogManager get(){
        return DialogLoader.INSTANCE;
    }


    /**
     * 初始化带有进度效果的对话框
     * @param context
     * @param title
     * @param message
     * @return
     */
    public AlertDialog initProgressDialog(Context context,String title,String message){
       mDialog = new ProgressDialog(context);
       mDialog.setTitle(title);
       mDialog.setCancelable(false);
       mDialog.setMessage(message);
       return mDialog;
    }


    /**
     * 初始化带有选择提示的对话框
     * @param context
     * @param title
     * @param message
     * @param positiveMessage
     * @param positivelistener
     * @param negativeMessage
     * @param negativelistener
     * @return
     */
    public AlertDialog initAlertDialog(Context context,String title,String message,
                                       String positiveMessage, DialogInterface.OnClickListener positivelistener,
                                       String negativeMessage, DialogInterface.OnClickListener negativelistener){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        if (title != null) {
            builder.setTitle(title);
        }
        if (message != null) {
            builder.setMessage(message);
        }
        if (positivelistener != null) {
            builder.setPositiveButton(positiveMessage, positivelistener);
        }
        if (negativelistener != null) {
            builder.setNegativeButton(negativeMessage, negativelistener);
        }

        mDialog= builder.create();
        return mDialog;
    }


    /**
     *  初始化多选提示框
     * @return
     */
    public AlertDialog initMultiChoiceDialog(Context context, CharSequence[] items, DialogInterface.OnClickListener listener){
        return mDialog = new AlertDialog.Builder(context).
                setItems(items, listener).create();
    }

    public DialogManager changeTitle(String title){
        if (mDialog != null) {
            mDialog.setTitle(title);
        }
        return DialogLoader.INSTANCE;
    }

    /**
     * 改变对话框内容信息
     * @param msg
     */
    public DialogManager changeMessage(String msg){
        if (mDialog != null) {
            mDialog.setMessage(msg);
        }
        return DialogLoader.INSTANCE;
    }

    public void show(){
        if (mDialog != null) {
            mDialog.show();
        }
    }


    public void dismiss(){
        if (mDialog != null && mDialog.isShowing()) {
            mDialog.dismiss();
        }
    }


}
