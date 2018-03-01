package com.vincent.commonwidget.webview

import android.os.Bundle
import android.support.v4.app.FragmentActivity
import com.vincent.commonwidget.webview.WebViewBaseConst.Companion.IS_SHOW_TOP_BAR
import com.vincent.commonwidget.webview.WebViewBaseConst.Companion.LOAD_URL

/**
 *  通用加载网页Activity
 */
abstract class WebViewBaseActivity : FragmentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        val bundle = intent
        val isShown = bundle.getBooleanExtra(IS_SHOW_TOP_BAR,true)

        val url :String=bundle.getStringExtra(LOAD_URL)

        val wvBase = WebViewBaseView(this)
        wvBase.setAdapter(object : WebViewBaseView.WebViewBaseViewAdapter{
            override fun isShownTopBar(): Boolean {
                return isShown
            }

            override fun loadUrl(): String {
                return url
            }

            override fun onWebViewBack() {
               finish()
            }

        })

        setContentView(wvBase)
    }

}