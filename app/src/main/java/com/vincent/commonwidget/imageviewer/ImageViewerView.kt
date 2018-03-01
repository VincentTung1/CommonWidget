package com.vincent.commonwidget.imageviewer

import android.content.Context
import android.graphics.Color
import android.net.Uri
import android.support.v4.view.PagerAdapter
import android.support.v4.view.ViewPager
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import android.widget.TextView
import com.vincent.commonwidget.util.ScreenUtil
import com.vincent.commonwidget.widget.photoview.PhotoView
import java.io.File


/**
 * Created by vincent_tung on 2018/2/28.
 */
class ImageViewerView :RelativeLayout {

    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    init {
        initViews()
        initListeners()
    }

    private lateinit var mVp: ViewPager

    private lateinit var mCurrent: TextView

    private fun initViews() {
        mVp = ViewPager(context)
        mVp.setBackgroundColor(Color.BLACK)
        val vpParam = RelativeLayout.LayoutParams(-1,-1)
        mVp.layoutParams = vpParam
        addView(mVp)

        mCurrent = TextView(context)
        val currentParam = RelativeLayout.LayoutParams(-2,-2)
        currentParam.addRule(RelativeLayout.ALIGN_PARENT_RIGHT)
        currentParam.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM)
        currentParam.setMargins(
                0,0,
                ScreenUtil.dpToPx(context,10f).toInt(),
                ScreenUtil.dpToPx(context,10f).toInt())
        mCurrent.textSize = ScreenUtil.dpToPx(context,28f)
        mCurrent.layoutParams = currentParam
        mCurrent.setTextColor(Color.WHITE)
        addView(mCurrent)
    }

    private fun initListeners() {
        mVp.setOnPageChangeListener(object :ViewPager.OnPageChangeListener{
            override fun onPageScrollStateChanged(state: Int) {
            }

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
            }

            override fun onPageSelected(position: Int) {
                mCurrent.setText("${mVp.currentItem+1}/${mVp.adapter.count}")
            }

        })
    }

    /**
     *  设置图片所有路径
     */
    fun setImagesPath(paths:ArrayList<String>,listener : OnImageClickListener?){
        mVp.adapter = object : PagerAdapter() {
            override fun isViewFromObject(view: View?, `object`: Any?): Boolean {
                return  view==`object`
            }

            override fun getCount(): Int {
               return paths.size
            }

            override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
                container.removeView(`object` as View)
            }

            override fun getItemPosition(`object`: Any): Int {
                return POSITION_NONE
            }

            override fun instantiateItem(container: ViewGroup, position: Int): Any {
                val uri = paths.get(position)
                val photoView = PhotoView(context)
                photoView.layoutParams = ViewGroup.LayoutParams(-1,-1)
                photoView.setImageURI(Uri.fromFile(File(uri)))
                container.addView(photoView)
                photoView.setOnClickListener {
                    listener!!.onImageClick()
                }
                return photoView
            }
        }
        mCurrent.setText("1/${mVp.adapter.count}")

    }

    /**
     *  设置当前位置的图片
     */
    fun setCurrentItem(index : Int){
        if (index >= 0 && index < mVp.adapter.count){
            mVp.currentItem = index
        }
    }

    interface OnImageClickListener{
        fun onImageClick();
    }
}

