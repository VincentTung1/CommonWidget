package com.vincent.commonwidget.helper;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

/**
 * Created by vincent_tung on 2018/2/27.
 */

public class FragmentReplaceHelper {

    private static int mRootLayoutId = 0;

    private static class FragmentReplaceHolder{
        private static FragmentReplaceHelper INSTANCE = new FragmentReplaceHelper();
    }

    public static FragmentReplaceHelper get(){
        return FragmentReplaceHolder.INSTANCE;
    }

    public void setRootLayoutId(int rootLayoutId){
        mRootLayoutId = rootLayoutId;
    }

    public int getRootLayoutId(){
        return mRootLayoutId;
    }

    /**
     * 添加并显示fragment
     * @param fragmentManager
     * @param fragment
     */
    public void addFragment(FragmentManager fragmentManager, Fragment fragment){
        if (mRootLayoutId<=0) throw new RuntimeException("请先设置显示fragment的rootLayoutId！");
        FragmentUtils.addFragment(fragmentManager,fragment, mRootLayoutId,false,true);
    }

    /**
     *
     * @param fm
     */
    public void popFragments(FragmentManager fm){
        FragmentUtils.popFragments(fm);
    }
}
