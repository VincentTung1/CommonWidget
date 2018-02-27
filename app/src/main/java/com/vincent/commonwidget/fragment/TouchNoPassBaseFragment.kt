package com.vincent.commonwidget.fragment

import android.os.Bundle
import android.view.MotionEvent
import android.view.View

/**
 *  防止触摸穿透的fragment
 */
abstract class TouchNoPassBaseFragment : BaseFragment(),View.OnTouchListener{

    override fun onTouch(v: View?, event: MotionEvent?): Boolean {
        return true
    }


    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        view!!.setOnTouchListener(this)
        super.onViewCreated(view, savedInstanceState)
    }
}