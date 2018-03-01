package com.vincent.commonwidget.util

import android.content.Context

/**
 * Created by vincent_tung on 2018/3/1.
 */
class ScreenUtil {

    companion object {

        /**
         *  dp转为px
         */
        fun dpToPx(context: Context?, dp: Float): Float {
            return if (context == null) {
                -1f
            } else dp * context.resources.displayMetrics.density
        }
    }
}