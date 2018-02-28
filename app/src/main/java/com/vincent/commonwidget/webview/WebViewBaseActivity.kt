package com.vincent.commonwidget.webview

import android.graphics.Color
import android.os.Bundle
import android.support.v4.app.FragmentActivity
import android.text.TextUtils
import android.view.KeyEvent
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.LinearLayout
import com.vincent.commonwidget.widget.topnavigation.TopNavigation

/**
 *  通用加载网页Activity
 */
abstract class WebViewBaseActivity : FragmentActivity() {

    companion object {
        /**加载的url*/
        val LOAD_URL = "load_url"
        /**是否显示顶部栏*/
        val IS_SHOW_TOP_BAR = "is_show_top_bar"

    }


    private lateinit var mWv: WebView

    private lateinit var mNavi: TopNavigation


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val rootLayout = LinearLayout(this)
        rootLayout.orientation = LinearLayout.VERTICAL
        val rootLayoutParam = LinearLayout.LayoutParams(-1,-1)
        rootLayout.layoutParams = rootLayoutParam
        rootLayout.setBackgroundColor(Color.WHITE)


        mNavi = TopNavigation(this)
        mNavi.setBackgroundColor(Color.CYAN)
        mNavi.enableBottomLine(true,0)
        mNavi.enableLeftBtn(true, View.OnClickListener {
            finish()
        })
        rootLayout.addView(mNavi)

        mWv = WebView(this)
        rootLayout.addView(mWv)


        val bundle = intent
        val isShown = bundle.getBooleanExtra(IS_SHOW_TOP_BAR,true)
        if (!isShown){
            mNavi.visibility = View.GONE
        }

        val url :String=bundle.getStringExtra(LOAD_URL)
        if (!TextUtils.isEmpty(url)) {
            mWv.loadUrl(url)
            mWv.webViewClient = object : WebViewClient(){
                override fun onLoadResource(view: WebView?, url: String?) {
                    super.onLoadResource(view, url)
                    mNavi.setTitle(view!!.title)
                }
            }
        }
        mWv.setOnKeyListener(object : View.OnKeyListener{
            override fun onKey(v: View?, keyCode: Int, event: KeyEvent?): Boolean {
                if (keyCode == KeyEvent.KEYCODE_BACK && event!!.getRepeatCount() == 0) {
                    if (mWv.canGoBack()) {
                        mWv.goBack();
                    } else {
                        finish()
                    }
                    return true;
                }
                return  false;
            }

        })

        setContentView(rootLayout)
    }

}