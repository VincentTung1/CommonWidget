package com.vincent.commonwidget.fragment

import android.graphics.Color
import android.os.Bundle
import android.os.Environment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.RelativeLayout
import com.vincent.commonwidget.helper.FragmentReplaceHelper
import com.vincent.commonwidget.imageviewer.ImageViewerFragment
import com.vincent.commonwidget.widget.topnavigation.TopNavigation
import java.io.File

/**
 * Created by vincent_tung on 2018/2/23.
 */
class ThirdFragment : BaseFragment() {
    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val root  = RelativeLayout(context)
        val navi = TopNavigation(context)
        navi.setTitle("标题3")
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
        btn.setText("点击进入图片浏览器fragment")
        btn.setOnClickListener {
            val imageFrag = ImageViewerFragment()
            val photos = ArrayList<String>()

            val dir :String= Environment.getExternalStorageDirectory().absolutePath + File.separator
            for (i in 1..3){
                photos.add("${dir}p${i}.jpg")
            }

            imageFrag.setImagePaths(photos)
            FragmentReplaceHelper.get().addFragment(fragmentManager,imageFrag)
        }
        root.addView(btn)
        root.layoutParams = param
        return root
    }
}