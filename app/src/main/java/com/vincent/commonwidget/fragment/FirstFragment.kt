package com.vincent.commonwidget.fragment

import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.RelativeLayout
import com.vincent.commonwidget.helper.FragmentReplaceHelper

/**
 * Created by vincent_tung on 2018/2/23.
 */
class FirstFragment : BaseFragment() {

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val root  = RelativeLayout(context)
        val btn = Button(context)
        val param = RelativeLayout.LayoutParams(-2,-2);
        btn.textSize = 25f


        btn.setText("点击进入事例fragment")
        btn.setOnClickListener {
            val exampleFrag = ExampleFragment()
            val bundle = Bundle()
            bundle.putString("tips","Hello,World!")
            exampleFrag.arguments = bundle
            FragmentReplaceHelper.get().addFragment(fragmentManager,exampleFrag)
        }
        root.gravity = Gravity.CENTER
        root.addView(btn)
        root.layoutParams = param
        return root
    }
}