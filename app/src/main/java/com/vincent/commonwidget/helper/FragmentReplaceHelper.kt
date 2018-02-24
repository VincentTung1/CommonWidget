package com.vincent.commonwidget.helper

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager

/**
 * Created by vincent_tung on 2018/2/23.
 */
class FragmentReplaceHelper {
    private var currentFragment = Fragment()

    private val mManager: FragmentManager? = null

    /**
     * 展示Fragment
     */
    private fun showFragment(fragment: Fragment) {
//        if (currentFragment !== fragment) {
//            val transaction = mManager.beginTransaction()
//            transaction.hide(currentFragment)
//            currentFragment = fragment
//            if (!fragment.isAdded()) {
//                transaction.add(R.id.layout_main_fragment, fragment).show(fragment).commit()
//            } else {
//                transaction.show(fragment).commit()
//            }
//        }
    }
}