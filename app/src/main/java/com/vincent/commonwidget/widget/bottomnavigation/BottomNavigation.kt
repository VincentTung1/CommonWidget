package com.vincent.commonwidget.widget.bottomnavigation

import android.content.Context
import android.util.AttributeSet
import android.view.Gravity
import android.widget.LinearLayout
import android.widget.RadioGroup
import com.vincent.commonwidget.widget.bottomnavigation.entity.BottomNavigationItemConfig
import com.vincent.commonwidget.widget.bottomnavigation.item.BottomNavigationItem

/**
 *  通用底部导航栏
 */
class BottomNavigation : RadioGroup {

    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)


    init {
        orientation = LinearLayout.HORIZONTAL

    }


    fun addItem(config : BottomNavigationItemConfig){
        val item= BottomNavigationItem(context)
        item.setNormalRes(config.mNormalRes)
        item.setHighLightRes(config.mHighlightRes)
        item.setTitle(config.mTitle)
        item.setTitleNormalColor(config.mTitleNormalColor)
        item.setTitleHighlightColor(config.mTitleHighLightColor)
        item.gravity = Gravity.CENTER

        val params = RadioGroup.LayoutParams(0,-2,1.0F)
        item.layoutParams = params

        addView(item)
    }
}