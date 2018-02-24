package com.vincent.commonwidget.widget.viewpager;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * 可以设置禁止滑动的 ViewPager(单向禁止：左滑动) 
 *          核心方法：setScrollble() 
 * @author alan 
 * 
 */  
public class CanotSlidingViewpager extends ViewPager {

    /**是否禁止左右滑动，true为禁止，false为不禁止*/
    private boolean noScroll = false;

    public CanotSlidingViewpager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CanotSlidingViewpager(Context context) {
        super(context);
    }

    /**
     *  设置是否不能滑动
     * @param noScroll
     */
    public void setNoScroll(boolean noScroll) {
        this.noScroll = noScroll;
    }

    @Override
    public void scrollTo(int x, int y) {
        super.scrollTo(x, y);
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouchEvent(MotionEvent arg0) {
        if (noScroll)
            return false;
        else
            return super.onTouchEvent(arg0);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent arg0) {
        if (noScroll)
            return false;
        else
            return super.onInterceptTouchEvent(arg0);
    }

    @Override
    public void setCurrentItem(int item, boolean smoothScroll) {
        super.setCurrentItem(item, smoothScroll);
    }

    @Override
    public void setCurrentItem(int item) {
        super.setCurrentItem(item);
    }

}  