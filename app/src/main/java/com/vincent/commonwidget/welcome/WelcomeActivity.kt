package com.vincent.commonwidget.welcome

import android.app.Activity
import com.vincent.commonwidget.MainActivity
import com.vincent.commonwidget.R

/**
 *  通用欢迎界面
 */
class WelcomeActivity : WelcomeBaseActivity() {

    override fun getSkipSecs(): Int {
        return  3;
    }

    override fun getActivityToSkip(): Activity{
        return MainActivity()
    }

    override fun getResId(): Int {
       return R.drawable.bg_welcome_page_zhengxin;
    }
}