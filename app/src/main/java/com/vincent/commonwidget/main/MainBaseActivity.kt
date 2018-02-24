package com.vincent.commonwidget.main

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.widget.RelativeLayout
import com.vincent.commonwidget.widget.bottomnavigation.BottomNavigation
import com.vincent.commonwidget.widget.bottomnavigation.entity.BottomNavigationItemConfig
import com.vincent.commonwidget.widget.viewpager.CanotSlidingViewpager
import java.util.*

/**
 * Created by vincent_tung on 2018/2/9.
 */
abstract class MainBaseActivity :AppCompatActivity(){

    /**根布局视图*/
    lateinit var mRootLayout : RelativeLayout

    private var currentFragment = Fragment()

    private val mManager: FragmentManager? = supportFragmentManager

    /**内容布局的Id*/
    private var mContentLayoutId: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        initViews()
    }



    @SuppressLint("ResourceType")
     fun initViews() {

        mRootLayout = RelativeLayout(this)
        val params = RelativeLayout.LayoutParams(-1,-1)
        mRootLayout.layoutParams = params
        setContentView(mRootLayout)


        //添加底部导航栏
        val bottomNavi  = BottomNavigation(this)
        bottomNavi.id = Random().nextInt()
        val paramsBottom = RelativeLayout.LayoutParams(-1,-2)
        paramsBottom.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM,RelativeLayout.TRUE)
        bottomNavi.layoutParams = paramsBottom
        bottomNavi.setBackgroundColor(Color.CYAN)

        mRootLayout.addView(bottomNavi)

         val bottomItems = getBottomNaviItems()

         for (item in bottomItems){
             bottomNavi.addItem(item)
         }

         val index= getDefaultSelectItem()
         if (index != 0){
             bottomNavi.check(bottomNavi.getChildAt(index).id)
         }else{
             bottomNavi.check(bottomNavi.getChildAt(0).id)
         }

         //添加所有fragment
         val vp = CanotSlidingViewpager(this)
         val fragmentParams = RelativeLayout.LayoutParams(-1,-2)
         fragmentParams.addRule(RelativeLayout.ABOVE,bottomNavi.id)
         fragmentParams.addRule(RelativeLayout.ALIGN_PARENT_TOP)
         fragmentParams.addRule(RelativeLayout.ALIGN_TOP,bottomNavi.id)
         vp.layoutParams = fragmentParams
         vp.setBackgroundColor(Color.TRANSPARENT)
         mContentLayoutId = Random().nextInt(1000)
         vp.id = mContentLayoutId as Int


        val fragments = getMainFragments()
        if (fragments.size>0) {
            vp.adapter =  MyPagerAdapter(fragments,mManager!!)
        }else{
            throw RuntimeException("请设置ViewPager所需要的Fragment！")
        }
         vp.offscreenPageLimit = fragments.size  // 设置viewpager缓存view 的个数

         mRootLayout.addView(vp)


        //设置默认显示的 fragment
        val frg = getDefaultShownFragment()
        if (frg != null) {
            val index= fragments.indexOf(frg)
            vp.currentItem = index
        }

        bottomNavi.setOnCheckedChangeListener { group, checkedId ->
            run {
                vp.currentItem = checkedId - 1
            }
        }

        vp.addOnPageChangeListener(object : ViewPager.OnPageChangeListener{
            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
            }

            override fun onPageScrollStateChanged(state: Int) {
            }

            override fun onPageSelected(position: Int) {
                bottomNavi.check(position+1)
            }

        })
    }


    class MyPagerAdapter(val fragments : ArrayList<Fragment>,fragmentManager: FragmentManager) : FragmentPagerAdapter(fragmentManager){

        override fun getItem(position: Int): Fragment {
           return fragments[position]
        }

        override fun getCount(): Int {
           return fragments.size
        }


    }


    /**
     *  展示Fragment
     */
     fun showFragment(fragment: Fragment) {
        if (currentFragment !== fragment) {
            val transaction = mManager!!.beginTransaction()
            transaction.hide(currentFragment)
            currentFragment = fragment
            if (!fragment.isAdded()) {
                transaction.remove(fragment).commit()
                transaction.add(mContentLayoutId!!, fragment).show(fragment).commit()
            } else {
                transaction.show(fragment).commit()
            }
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


    /**
     *  初始化主界面所有fragment
     */
    abstract fun getMainFragments(): ArrayList<Fragment>

    /**
     *  设置默认显示的fragment
     */
    abstract fun getDefaultShownFragment() : Fragment?
}



