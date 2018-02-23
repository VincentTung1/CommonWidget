package com.vincent.commonwidget.widget.bottomnavigation.item

import android.content.Context
import android.util.AttributeSet
import android.widget.RadioButton

/**
 * Created by vincent_tung on 2018/2/10.
 */
class BottomNavigationItem : RadioButton{

    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)


    private var mNormalRes : Int? = 0

    private var mHighlightRes : Int? = 0

    private var mTitle : String? =""

    private var mTitleNormalColor : Int? = 0

    private var mTitleHighlightColor : Int? =0



    init {
        setOnCheckedChangeListener { buttonView, isChecked ->
            run {
                if (isChecked) {
                    val drawable = context.getDrawable(mHighlightRes!!)
                    setCompoundDrawablesWithIntrinsicBounds(null, drawable, null, null);
                    setTextColor(mTitleHighlightColor!!)
                } else {
                    val drawable = context.getDrawable(mNormalRes!!)
                    setCompoundDrawablesWithIntrinsicBounds(null, drawable, null, null);
                    setTextColor(mTitleNormalColor!!)
                }
            }
        }
    }

    /**
     *  设置普通状态时的图标
     */
    fun setNormalRes(resId :Int){
        mNormalRes = resId
        buttonDrawable = null
        val drawable = context.getDrawable(mNormalRes!!)
        setCompoundDrawablesWithIntrinsicBounds(null, drawable, null, null);
    }

    /**
     *  设置高亮状态时的图标
     */
    fun setHighLightRes(resId : Int){
        mHighlightRes = resId
    }

    /**
     *  设置标题
     */
    fun setTitle(title :String){
        mTitle =title
        setText(title)
    }

    /**
     *  设置普通状态时的标题颜色
     */
    fun setTitleNormalColor(color :Int){
       mTitleNormalColor = color
        setTextColor(mTitleNormalColor!!)
    }

    /**
     *  设置高亮状态时的标题颜色
     */
    fun setTitleHighlightColor(color :Int){
       mTitleHighlightColor = color
    }


}