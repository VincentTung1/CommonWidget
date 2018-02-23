package com.vincent.commonwidget.main

import android.graphics.Color
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.RelativeLayout
import com.vincent.commonwidget.widget.bottomnavigation.BottomNavigation
import com.vincent.commonwidget.widget.bottomnavigation.entity.BottomNavigationItemConfig

/**
 * Created by vincent_tung on 2018/2/9.
 */
abstract class MainBaseActivity :AppCompatActivity(){

    /**根布局视图*/
    lateinit var mRootLayout : RelativeLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        initViews()
    }

     fun initViews() {

        mRootLayout = RelativeLayout(this)
        val params = RelativeLayout.LayoutParams(-1,-1)
        mRootLayout.layoutParams = params
        setContentView(mRootLayout)

        val bottomNavi  = BottomNavigation(this)
        val paramsBottom = RelativeLayout.LayoutParams(-1,-2)
        paramsBottom.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM,RelativeLayout.TRUE)
        bottomNavi.layoutParams = paramsBottom
        bottomNavi.setBackgroundColor(Color.CYAN)

        mRootLayout.addView(bottomNavi)

         var bottomItems = getBottomNaviItems()

         for (item in bottomItems){
             bottomNavi.addItem(item)
         }

         val index= getDefaultSelectItem()
         if (index != 0){
             bottomNavi.check(bottomNavi.getChildAt(index).id)
         }else{
             bottomNavi.check(bottomNavi.getChildAt(0).id)
         }
     }


    /**
     *  设置底部导航栏各项
     */
    abstract fun getBottomNaviItems() : ArrayList<BottomNavigationItemConfig>


    /**
     *  设置默认选中项
     */
    abstract fun getDefaultSelectItem():Int
}