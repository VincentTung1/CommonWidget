package com.vincent.commonwidget.webview

import android.graphics.Color
import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.LinearLayout
import com.vincent.commonwidget.fragment.TouchNoPassBaseFragment
import com.vincent.commonwidget.helper.FragmentReplaceHelper
import com.vincent.commonwidget.widget.topnavigation.TopNavigation

/**
 *  通用加载网页fragment
 */
abstract class WebViewBaseFragment : TouchNoPassBaseFragment() {

    companion object {
        val LOAD_URL = "load_url"
    }

    private lateinit var mWv: WebView

    private lateinit var mNavi: TopNavigation

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootLayout = LinearLayout(context)
        rootLayout.orientation = LinearLayout.VERTICAL
        val rootLayoutParam = LinearLayout.LayoutParams(-1,-1)
        rootLayout.layoutParams = rootLayoutParam
        rootLayout.setBackgroundColor(Color.WHITE)

        mNavi = TopNavigation(context)
        mNavi.setBackgroundColor(Color.CYAN)
        mNavi.enableBottomLine(true,0)
        mNavi.enableLeftBtn(true, View.OnClickListener {
            FragmentReplaceHelper.get().popFragments(fragmentManager)
        })
        rootLayout.addView(mNavi)

        mWv = WebView(context)
        rootLayout.addView(mWv)


        val bundle = arguments
        val url :String=bundle.getString(LOAD_URL)
        if (!TextUtils.isEmpty(url)) {
            mWv.loadUrl(url)
            mWv.webViewClient = object : WebViewClient(){
                override fun onLoadResource(view: WebView?, url: String?) {
                    super.onLoadResource(view, url)
                    mNavi.setTitle(view!!.title)
                }
            }
        }
        return rootLayout;
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }
}