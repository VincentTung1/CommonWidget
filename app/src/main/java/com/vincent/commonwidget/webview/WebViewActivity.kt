package com.vincent.commonwidget.webview

import android.app.Activity
import android.content.Intent

/**
 * Created by vincent_tung on 2018/2/28.
 */
class WebViewActivity :WebViewBaseActivity() {

    companion object {

        fun start(context: Activity, url:String, isShownTopBar:Boolean){
            val intent = Intent(context,WebViewActivity::class.java)
            intent.putExtra(LOAD_URL,url)
            intent.putExtra(IS_SHOW_TOP_BAR,isShownTopBar)
            context.startActivity(intent)
            context.overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out)
        }
    }
}