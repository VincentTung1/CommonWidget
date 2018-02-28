package com.vincent.commonwidget.widget.topnavigation

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.view.View
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import com.vincent.commonwidget.R

/**
 *  通用导航栏
 */
class TopNavigation : RelativeLayout {

    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    private lateinit var mLeftBtn: ImageView

    private lateinit var mTitle: TextView

    /**导航栏默认高度*/
    private var mDefaultHeight = 40f


    init {
       initViews()
   }



    private fun initViews() {
        mLeftBtn = ImageView(context)
        mLeftBtn.setImageResource(R.drawable.ic_back_button)
        mLeftBtn.visibility = View.GONE
        val mLeftParam = RelativeLayout.LayoutParams(-2,-2)
        mLeftParam.setMargins(10,0,0,0)
        mLeftParam.addRule(RelativeLayout.CENTER_VERTICAL)
        mLeftBtn.layoutParams = mLeftParam
        addView(mLeftBtn)

        mTitle = TextView(context)
        val mTitleParam = RelativeLayout.LayoutParams(-2,-2)
        mTitleParam.addRule(RelativeLayout.CENTER_IN_PARENT)
        mTitle.layoutParams = mTitleParam
        mTitle.textSize = 28f
        addView(mTitle)


        setHeight(mDefaultHeight)


    }


     private fun dpToPx(context: Context?, dp: Float): Float {
        return if (context == null) {
            -1f
        } else dp * context.resources.displayMetrics.density
    }

    /**
     *  设置导航栏高度
     */
    fun setHeight(dp : Float){
        val px= dpToPx(context!!,dp).toInt()
        val naviParam = RelativeLayout.LayoutParams(-1,px)
        layoutParams = naviParam
    }


    /**
     *  是否需要显示左边按钮
     */
    fun enableLeftBtn(enable : Boolean,titleClickListener: OnClickListener){
        if (enable){
            mLeftBtn.visibility = View.VISIBLE
        }else{
            mLeftBtn.visibility = View.GONE
        }
        mLeftBtn.setOnClickListener(titleClickListener)
    }

    /**
     *  是否添加底部黑色线
     */
    fun enableBottomLine(enable: Boolean,color : Int){
        if (enable){
            val line = View(context)
            val lineParam = RelativeLayout.LayoutParams(-1,2);
            lineParam.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM)
            line.layoutParams = lineParam
            if (color>0){
                line.setBackgroundColor(color)
            }else{
                line.setBackgroundColor(Color.GRAY)
            }
            addView(line)
        }
    }

    /**
     *  设置左边按钮图标
     */
    fun setLeftBtnIcon(icon :Int){
        mLeftBtn.setImageResource(icon)
    }

    /**
     *  设置中间标题
     */
    fun setTitle(title :String){
        mTitle.setText(title)
    }

    /**
     *  设置中间标题颜色
     */
    fun setTitleColor(color : Int){
        mTitle.setTextColor(color)
    }

}