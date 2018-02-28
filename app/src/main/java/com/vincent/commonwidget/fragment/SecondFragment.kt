package com.vincent.commonwidget.fragment

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.RelativeLayout
import com.vincent.commonwidget.helper.FragmentReplaceHelper
import com.vincent.commonwidget.webview.WebViewActivity
import com.vincent.commonwidget.webview.WebViewBaseFragment
import com.vincent.commonwidget.webview.WebViewFragment
import com.vincent.commonwidget.widget.topnavigation.TopNavigation

/**
 * Created by vincent_tung on 2018/2/23.
 */
class SecondFragment : BaseFragment() {

    @SuppressLint("ResourceType")
    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val root  = RelativeLayout(context)
        val navi = TopNavigation(context)
        navi.setTitle("标题2")
        navi.setBackgroundColor(Color.CYAN)
        navi.enableBottomLine(true,0)
        root.addView(navi)

        val btnF = Button(context)
        val param = RelativeLayout.LayoutParams(-1,-1)

        val btnFParam = RelativeLayout.LayoutParams(-2,-2)
        btnFParam.addRule(RelativeLayout.CENTER_IN_PARENT)
        btnF.id  = 1111
        btnF.layoutParams = btnFParam
        btnF.textSize = 25f
        btnF.setText("点击进入事例网页fragment")
        btnF.setOnClickListener {
            val webViewFrag = WebViewFragment()
            val bundle = Bundle()
            bundle.putString(WebViewBaseFragment.LOAD_URL,"http://www.baidu.com")
            webViewFrag.arguments = bundle
            FragmentReplaceHelper.get().addFragment(fragmentManager,webViewFrag)
        }
        root.addView(btnF)
        val btnA = Button(context)

        val btnAParam = RelativeLayout.LayoutParams(-2,-2)
        btnAParam.addRule(RelativeLayout.BELOW,btnF.id)
        btnAParam.addRule(RelativeLayout.CENTER_IN_PARENT)
        btnA.layoutParams = btnAParam
        btnA.textSize = 25f
        btnA.setText("点击进入事例网页Activity")
        btnA.setOnClickListener {
           WebViewActivity.start(activity,"http://wap.qq.com",true)
        }
        root.addView(btnA)


        root.layoutParams = param
        return root
    }
}