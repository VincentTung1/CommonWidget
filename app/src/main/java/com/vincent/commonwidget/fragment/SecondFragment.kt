package com.vincent.commonwidget.fragment

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.RelativeLayout
import com.vincent.commonwidget.helper.FragmentReplaceHelper
import com.vincent.commonwidget.webview.WebViewBaseFragment
import com.vincent.commonwidget.webview.WebViewFragment
import com.vincent.commonwidget.widget.topnavigation.TopNavigation

/**
 * Created by vincent_tung on 2018/2/23.
 */
class SecondFragment : BaseFragment() {

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val root  = RelativeLayout(context)
        val navi = TopNavigation(context)
        navi.setTitle("标题2")
        navi.setBackgroundColor(Color.CYAN)
//        navi.enableLeftBtn(true, View.OnClickListener {
//            Toast.makeText(context,"已点击返回按钮！",0).show()
//        })
        navi.enableBottomLine(true,0)
        root.addView(navi)

        val btn = Button(context)
        val param = RelativeLayout.LayoutParams(-1,-1)

        val btnParam = RelativeLayout.LayoutParams(-2,-2)
        btnParam.addRule(RelativeLayout.CENTER_IN_PARENT)
        btn.layoutParams = btnParam
        btn.textSize = 25f
        btn.setText("点击进入事例网页fragment")
        btn.setOnClickListener {
            val webViewFrag = WebViewFragment()
            val bundle = Bundle()
            bundle.putString(WebViewBaseFragment.LOAD_URL,"http://www.baidu.com")
            webViewFrag.arguments = bundle
            FragmentReplaceHelper.get().addFragment(fragmentManager,webViewFrag)
        }
        root.addView(btn)
        root.layoutParams = param
        return root
    }
}