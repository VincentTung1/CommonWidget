package com.vincent.commonwidget.webview

import android.content.Context
import android.graphics.Color
import android.text.TextUtils
import android.util.AttributeSet
import android.view.KeyEvent
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.LinearLayout
import com.vincent.commonwidget.widget.topnavigation.TopNavigation

/**
 * Created by vincent_tung on 2018/3/1.
 */
class WebViewBaseView : LinearLayout {

    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)



    private lateinit var mWv: WebView

    private lateinit var mNavi: TopNavigation

    lateinit var mAdapter: WebViewBaseViewAdapter

    /**
     *  设置网页适配器
     */
    fun setAdapter(adapter: WebViewBaseViewAdapter){
        mAdapter = adapter
        initViews()
    }

    private fun initViews() {
        orientation = LinearLayout.VERTICAL
        val rootLayoutParam = LinearLayout.LayoutParams(-1,-1)
        layoutParams = rootLayoutParam
        setBackgroundColor(Color.WHITE)


        mNavi = TopNavigation(context)
        mNavi.setBackgroundColor(Color.CYAN)
        mNavi.enableBottomLine(true,0)
        mNavi.enableLeftBtn(true, View.OnClickListener {
            mAdapter.onWebViewBack()
        })
        addView(mNavi)

        mWv = WebView(context)
        addView(mWv)


        val isShown = mAdapter.isShownTopBar()
        if (!isShown){
            mNavi.visibility = View.GONE
        }

        val url :String=mAdapter.loadUrl()
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
                        mAdapter.onWebViewBack()
                    }
                    return true;
                }
                return  false;
            }

        })
    }

    interface WebViewBaseViewAdapter {

        /**是否显示顶部栏*/
        fun isShownTopBar() : Boolean

        /**设置网页加载的url*/
        fun loadUrl() : String

        /**监听网页关闭时的回调*/
        fun onWebViewBack()
    }

}