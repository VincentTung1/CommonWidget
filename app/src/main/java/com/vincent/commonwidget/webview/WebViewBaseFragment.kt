package com.vincent.commonwidget.webview

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.vincent.commonwidget.fragment.TouchNoPassBaseFragment
import com.vincent.commonwidget.helper.FragmentReplaceHelper
import com.vincent.commonwidget.webview.WebViewBaseConst.Companion.IS_SHOW_TOP_BAR
import com.vincent.commonwidget.webview.WebViewBaseConst.Companion.LOAD_URL

/**
 *  通用加载网页fragment
 */
abstract class WebViewBaseFragment : TouchNoPassBaseFragment() {


    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val bundle = arguments
        val isShown = bundle.getBoolean(IS_SHOW_TOP_BAR,true)
        val url :String=bundle.getString(LOAD_URL)


        val wvBase = WebViewBaseView(context)
        wvBase.setAdapter(object : WebViewBaseView.WebViewBaseViewAdapter{
            override fun isShownTopBar(): Boolean {
                return isShown
            }

            override fun loadUrl(): String {
                return url
            }

            override fun onWebViewBack() {
                FragmentReplaceHelper.get().popFragments(fragmentManager)
            }

        })

        return wvBase;
    }


}