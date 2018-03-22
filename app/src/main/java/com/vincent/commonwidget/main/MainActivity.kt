package com.vincent.commonwidget.main

import android.graphics.Color
import android.support.v4.app.Fragment
import com.vincent.commonwidget.R
import com.vincent.commonwidget.fragment.FirstFragment
import com.vincent.commonwidget.fragment.SecondFragment
import com.vincent.commonwidget.fragment.ThirdFragment
import com.vincent.commonwidget.widget.bottomnavigation.entity.BottomNavigationItemConfig

class MainActivity : MainBaseActivity() {

    override fun enabledScroll(): Boolean {
       return true
    }

    private lateinit var first: FirstFragment

    override fun getDefaultSelectItem(): Int {
        return 0
    }

    override fun getBottomNaviItems(): ArrayList<BottomNavigationItemConfig> {

        val configs = ArrayList<BottomNavigationItemConfig>()
        val config1 = BottomNavigationItemConfig()
        config1.mId = 1
        config1.mTitle = "标题1"
        config1.mNormalRes = R.mipmap.ic_launcher
        config1.mHighlightRes = R.mipmap.ic_launcher_round
        config1.mTitleNormalColor = Color.BLACK
        config1.mTitleHighLightColor = Color.BLUE
        configs.add(config1)

        val config2 = BottomNavigationItemConfig()
        config2.mId = 2
        config2.mTitle = "标题2"
        config2.mNormalRes = R.mipmap.ic_launcher
        config2.mHighlightRes = R.mipmap.ic_launcher_round
        config2.mTitleNormalColor = Color.BLACK
        config2.mTitleHighLightColor = Color.BLUE
        configs.add(config2)

        val config3 = BottomNavigationItemConfig()
        config3.mId = 3
        config3.mTitle = "标题3"
        config3.mNormalRes = R.mipmap.ic_launcher
        config3.mHighlightRes = R.mipmap.ic_launcher_round
        config3.mTitleNormalColor = Color.BLACK
        config3.mTitleHighLightColor = Color.BLUE
        configs.add(config3)

        return configs
    }


    override fun getMainFragments(): java.util.ArrayList<Fragment> {

        val frags = ArrayList<Fragment>()

        first = FirstFragment()
        frags.add(first)

        val second = SecondFragment()
        frags.add(second)

        val third = ThirdFragment()
        frags.add(third)

        return frags
    }

    override fun getDefaultShownFragment() : Fragment{
        return  first
    }
}
