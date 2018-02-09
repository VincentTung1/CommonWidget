package com.vincent.commonwidget.welcome

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AppCompatActivity
import android.view.ViewGroup
import android.widget.ImageView


abstract class WelcomeBaseActivity : AppCompatActivity() {



    lateinit var mCenterImg : ImageView;

    /**背景图片*/
    private var mResId = -1


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initViews()
        initData()
    }

    private fun initData() {
       mResId = getResId()
        if (mResId != -1){
            mCenterImg.setBackgroundResource(mResId)
        }

        if (getActivityToSkip()!= null){
            var sec = 0
            if (getSkipSecs()>0){
               sec = getSkipSecs()
            }

            Handler().postDelayed(Runnable {

                val activity : Activity? = getActivityToSkip()
                val clazz = activity!!::class.java
                startActivity(Intent(this,clazz))
                finish()
            },sec*1000L)
        }
    }

    fun initViews(){
        mCenterImg = ImageView(this)
        val params = ViewGroup.LayoutParams(-1,-1)
        mCenterImg.layoutParams =params
        setContentView(mCenterImg)
    }

    /**
     *  返回背景图片资源
     */
    abstract fun getResId(): Int;

    /**
     *  返回跳转界面时间
     */
    abstract fun getSkipSecs(): Int


    /**
     *  返回需要跳转的界面
     */
    abstract fun getActivityToSkip(): Activity?
}
