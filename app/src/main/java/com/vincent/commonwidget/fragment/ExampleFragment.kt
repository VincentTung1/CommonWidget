package com.vincent.commonwidget.fragment

import android.graphics.Color
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import android.widget.TextView
import android.widget.Toast
import com.vincent.commonwidget.helper.FragmentReplaceHelper

/**
 * Created by vincent_tung on 2018/2/26.
 */
class ExampleFragment : TouchNoPassBaseFragment() {


    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val root  = RelativeLayout(context)
        val tv = TextView(context)
        tv.setBackgroundColor(Color.BLUE)
        val param = RelativeLayout.LayoutParams(-1,-1);
        tv.textSize = 25f
        tv.gravity = Gravity.CENTER
        tv.layoutParams = param
        tv.setText("事例fragment,轻触返回")
        tv.isClickable = true
        tv.setOnClickListener {
            FragmentReplaceHelper.get().popFragments(fragmentManager)
        }
        root.addView(tv)
        return root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val bundle :Bundle?= arguments
        val tips= bundle!!.getString("tips","")
        Toast.makeText(context,tips, Toast.LENGTH_SHORT).show()

    }
}