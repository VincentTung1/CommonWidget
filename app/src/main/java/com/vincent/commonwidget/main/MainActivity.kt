package com.vincent.commonwidget.main

import android.graphics.Color
import com.vincent.commonwidget.R
import com.vincent.commonwidget.widget.bottomnavigation.entity.BottomNavigationItemConfig

class MainActivity : MainBaseActivity() {

 override fun getDefaultSelectItem(): Int {
    return 1
 }

 override fun getBottomNaviItems(): ArrayList<BottomNavigationItemConfig> {

       var configs = ArrayList<BottomNavigationItemConfig>()
        var config1 = BottomNavigationItemConfig()
        config1.mTitle = "标题1"
        config1.mNormalRes = R.mipmap.ic_launcher
        config1.mHighlightRes = R.mipmap.ic_launcher_round
        config1.mTitleNormalColor = Color.BLACK
        config1.mTitleHighLightColor = Color.BLUE
        configs.add(config1)

        var config2 = BottomNavigationItemConfig()
        config2.mTitle = "标题2"
        config2.mNormalRes = R.mipmap.ic_launcher
        config2.mHighlightRes = R.mipmap.ic_launcher_round
        config2.mTitleNormalColor = Color.BLACK
        config2.mTitleHighLightColor = Color.BLUE
        configs.add(config2)

        var config3 = BottomNavigationItemConfig()
        config3.mTitle = "标题3"
        config3.mNormalRes = R.mipmap.ic_launcher
        config3.mHighlightRes = R.mipmap.ic_launcher_round
        config3.mTitleNormalColor = Color.BLACK
        config3.mTitleHighLightColor = Color.BLUE
        configs.add(config3)

       return  configs
    }


}
