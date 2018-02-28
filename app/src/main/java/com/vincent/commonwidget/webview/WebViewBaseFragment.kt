package com.vincent.commonwidget.webview

import android.graphics.Color
import android.os.Bundle
import android.text.TextUtils
import android.view.KeyEvent
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
        /**加载的url*/
        val LOAD_URL = "load_url"
        /**是否显示顶部栏*/
        val IS_SHOW_TOP_BAR = "is_show_top_bar"
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
        val isShown = bundle.getBoolean(IS_SHOW_TOP_BAR,true)
        if (!isShown){
            mNavi.visibility = View.GONE
        }

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
        mWv.setOnKeyListener(object :View.OnKeyListener{
            override fun onKey(v: View?, keyCode: Int, event: KeyEvent?): Boolean {
                if (keyCode == KeyEvent.KEYCODE_BACK && event!!.getRepeatCount() == 0) {
                    if (mWv.canGoBack()) {
                        mWv.goBack();
                    } else {
                        FragmentReplaceHelper.get().popFragments(fragmentManager)
                    }
                    return true;
                }
                return  false;
            }

        })

        return rootLayout;
    }


}