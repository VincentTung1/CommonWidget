package com.vincent.commonwidget.imageviewer

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.FragmentActivity
import android.view.ViewGroup

/**
 *   通用的图片浏览器Activity
 */
class ImageViewerActivity : FragmentActivity() {

    companion object {
        fun start(activity: Activity, paths:ArrayList<String>,currentIndex : Int){
            val intent = Intent(activity,ImageViewerActivity::class.java)
            intent.putExtra(ImageViewerConst.IMAGE_PATHS,paths)
            intent.putExtra(ImageViewerConst.CURRENT_INDEX,currentIndex)
            activity.startActivity(intent)
            activity.overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out)
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val view = ImageViewerView(this)

        val vpParam = ViewGroup.LayoutParams(-1,-1)
        view.layoutParams = vpParam


        val photos = intent.getStringArrayListExtra(ImageViewerConst.IMAGE_PATHS)

        view.setImagesPath(photos,object :ImageViewerView.OnImageClickListener{
            override fun onImageClick() {
                finish()
            }

        })

        val index = intent.getIntExtra(ImageViewerConst.CURRENT_INDEX,0)
        view.setCurrentItem(index)

        setContentView(view)
    }


}