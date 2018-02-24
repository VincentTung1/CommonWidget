package com.vincent.commonwidget.fragment

import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import android.widget.TextView

/**
 * Created by vincent_tung on 2018/2/23.
 */
class FirstFragment : BaseFragment() {

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val tv = TextView(context)
        val param = RelativeLayout.LayoutParams(-1,-1);
        tv.textSize = 25f
        tv.gravity = Gravity.CENTER
        tv.layoutParams = param
        tv.setText("内容1")

        return tv
    }
}