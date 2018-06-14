package com.vincent.commonwidget.fragment

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.RelativeLayout
import com.vincent.commonwidget.R
import com.vincent.commonwidget.helper.FragmentReplaceHelper
import com.vincent.commonwidget.widget.fllowlike.FllowLikeManager
import com.vincent.commonwidget.widget.topnavigation.TopNavigation

/**
 * Created by vincent_tung on 2018/2/23.
 */
class FirstFragment : BaseFragment() {

    @SuppressLint("ResourceType")
    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val root  = RelativeLayout(context)
        val navi = TopNavigation(context)
        navi.setTitle("标题1")
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
        btn.id = 1
        btn.setText("点击进入事例fragment")
        btn.setOnClickListener {
            val exampleFrag = ExampleFragment()
            val bundle = Bundle()
            bundle.putString("tips","Hello,World!")
            exampleFrag.arguments = bundle
            FragmentReplaceHelper.get().addFragment(fragmentManager,exampleFrag)
        }
        root.addView(btn)
        root.layoutParams = param

        val images = intArrayOf(R.drawable.ic_like_blue, R.drawable.ic_like_green, R.drawable.ic_like_red, R.drawable.ic_like_yellow)
        FllowLikeManager.get().init(context).setDisplayImagesResource(images)

        val imageView  = ImageView(context)
        imageView.setImageResource(R.drawable.ic_like_red)
        val imageViewParam = RelativeLayout.LayoutParams(-2,-2)
        imageViewParam.addRule(RelativeLayout.CENTER_IN_PARENT)
        imageViewParam.addRule(RelativeLayout.BELOW,btn.id)

        val density= resources.displayMetrics.density;
        imageViewParam.topMargin = (10*density).toInt()
        imageView.layoutParams  =imageViewParam

        imageView.setOnClickListener {
            FllowLikeManager.get().bezierPopup(imageView)
        }

        root.addView(imageView)

        return root
    }
}