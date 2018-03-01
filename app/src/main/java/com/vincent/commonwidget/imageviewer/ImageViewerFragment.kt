package com.vincent.commonwidget.imageviewer

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.vincent.commonwidget.helper.FragmentReplaceHelper
import com.vincent.commonwidget.imageviewer.ImageViewerConst.Companion.IMAGE_PATHS

/**
 *  通用的图片浏览器fragment
 */
class ImageViewerFragment :Fragment() {

    private var mCurrentIndex: Int = 0

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view = ImageViewerView(context)

        val vpParam = ViewGroup.LayoutParams(-1,-1)
        view.layoutParams = vpParam


        val photos = arguments.getStringArrayList(IMAGE_PATHS)

        view.setImagePaths(photos,object :ImageViewerView.OnImagePathClickListener {
            override fun onImageClick() {
                FragmentReplaceHelper.get().popFragments(fragmentManager)
            }

        })

        view.setCurrentItem(mCurrentIndex)

        return view
    }


    /**
     *  设置所有图片路径
     */
    fun setImagePaths(paths :ArrayList<String>){
        val bundle = Bundle()
        bundle.putStringArrayList(IMAGE_PATHS,paths)
        arguments = bundle
    }


    /**
     *  设置当前位置的图片
     */
    fun setCurrentItem(index :Int){
        mCurrentIndex = index
    }
}