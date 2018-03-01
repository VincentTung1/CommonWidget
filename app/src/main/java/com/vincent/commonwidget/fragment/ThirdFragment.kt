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
import com.vincent.commonwidget.imageviewer.ImageViewerActivity
import com.vincent.commonwidget.imageviewer.ImageViewerFragment
import com.vincent.commonwidget.widget.topnavigation.TopNavigation
import java.io.File
import java.util.*

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

        val btnF = Button(context)
        val param = RelativeLayout.LayoutParams(-1,-1)
        root.layoutParams = param

        val btnFParam = RelativeLayout.LayoutParams(-2,-2)
        btnFParam.addRule(RelativeLayout.CENTER_IN_PARENT)
        btnF.id = Random().nextInt()
        btnF.layoutParams = btnFParam
        btnF.textSize = 25f
        btnF.setText("点击进入图片浏览器fragment")
        btnF.setOnClickListener {
            val imageFrag = ImageViewerFragment()
            val photos = ArrayList<String>()

            val dir :String= Environment.getExternalStorageDirectory().absolutePath + File.separator
            for (i in 1..3){
                photos.add("${dir}p${i}.jpg")
            }

            imageFrag.setImagePaths(photos)
            FragmentReplaceHelper.get().addFragment(fragmentManager,imageFrag)
        }
        root.addView(btnF)


        val btnAParam = RelativeLayout.LayoutParams(-2,-2)
        btnAParam.addRule(RelativeLayout.CENTER_IN_PARENT)
        btnAParam.addRule(RelativeLayout.BELOW,btnF.id)
        val btnA = Button(context)
        btnA.layoutParams = btnAParam
        btnA.textSize = 25f
        btnA.setText("点击进入图片浏览器Activity")
        btnA.setOnClickListener {
            val photos = ArrayList<String>()

            val dir :String= Environment.getExternalStorageDirectory().absolutePath + File.separator
            for (i in 1..3){
                photos.add("${dir}p${i}.jpg")
            }

            ImageViewerActivity.start(activity,photos)
        }
        root.addView(btnA)

        return root
    }
}