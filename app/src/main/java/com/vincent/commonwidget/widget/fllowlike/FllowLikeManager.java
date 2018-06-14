package com.vincent.commonwidget.widget.fllowlike;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.graphics.PixelFormat;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static android.content.Context.WINDOW_SERVICE;

/**
 *  展示直播效果的点赞ui管理类
 */

public class FllowLikeManager {

    private WindowManager mWm;
    private WindowManager.LayoutParams mParam;

    private Context mCtxt;

    private static FllowLikeManager INSTANCE = new FllowLikeManager();

    private int[] mImages;

    public static FllowLikeManager get(){
        return INSTANCE;
    }

    public FllowLikeManager init(Context context){
        mCtxt  = context;

        //环境 必须使用  getApplicationContext()！！！ 不然在桌面时无法点击
        mWm = (WindowManager) context.getApplicationContext().getSystemService(WINDOW_SERVICE);
        //类型是TYPE_TOAST，像一个普通的Android Toast一样。这样就不需要申请悬浮窗权限了。
        mParam = new WindowManager.LayoutParams(WindowManager.LayoutParams.TYPE_TOAST);

        //初始化后不首先获得窗口焦点。不妨碍设备上其他部件的点击、触摸事件。
        mParam.flags = WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL
                | WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE
                | WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED;


        mParam.width = WindowManager.LayoutParams.WRAP_CONTENT;
        mParam.height = WindowManager.LayoutParams.WRAP_CONTENT;
        mParam.gravity = Gravity.TOP | Gravity.START;


        mParam.format = PixelFormat.TRANSLUCENT;

        return this;
    }


    /**
     *  普通直线的向上浮动
     * @param view
     */
    public void normalPopup(View view){
        int loc[] = new int[2];
        view.getLocationOnScreen(loc);

        final ImageView iv = new ImageView(mCtxt);
        iv.setLayoutParams(mParam);

        if (view instanceof ImageView){
            iv.setImageDrawable(((ImageView)view).getDrawable());
        }
        iv.measure(0,0);

        mParam.x = loc[0];
        mParam.y = loc[1];
        mWm.addView(iv,mParam);

        final int originalY = mParam.y;


        ValueAnimator anim = ValueAnimator.ofInt(mParam.y,mParam.y - iv.getMeasuredHeight()*2);
        anim.setDuration(1000);
        anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int y = (int) animation.getAnimatedValue();
                mParam.y = y;

                int totalD  =iv.getMeasuredHeight()*2;
                int currD = originalY - y ;

                double currentAlpha = 1- (double) currD / totalD *255;


                iv.getDrawable().setAlpha((int) currentAlpha);


                mWm.updateViewLayout(iv,mParam);
            }
        });

        anim.addListener(new AnimatorListenerAdapter() {

            @Override
            public void onAnimationEnd(Animator animation) {
                mWm.removeView(iv);
            }
        });




        anim.start();
    }


    private List<Path> mPaths = new ArrayList<>();


    /**
     *  贝塞尔曲线弹窗
     * @param view
     */
    public void bezierPopup(View view){
        int loc[] = new int[2];
        view.getLocationOnScreen(loc);

        final ImageView iv = new ImageView(mCtxt);
        iv.setLayoutParams(mParam);

        if (view instanceof ImageView){

            if (mImages != null && mImages.length > 0){
                iv.setImageResource(mImages[new Random().nextInt(mImages.length)]);
            }else {
                iv.setImageDrawable(((ImageView)view).getDrawable());
            }
        }
        iv.measure(0,0);

        mParam.x = loc[0];
        mParam.y = loc[1];
        mWm.addView(iv,mParam);

        final int originalY = mParam.y;

        int destY = mParam.y - iv.getMeasuredHeight()*5;

        initPaths(iv, destY);

        final Path path = mPaths.get(new Random().nextInt(mPaths.size()));


        ValueAnimator anim = ValueAnimator.ofInt(mParam.y,destY);
        anim.setDuration(1000);
        anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int y = (int) animation.getAnimatedValue();
                mParam.y = y;

                int totalD  =iv.getMeasuredHeight()*5;
                int currD = originalY - y ;

                double currentAlpha = 1- (double) currD / totalD *255;



                PathMeasure pm = new PathMeasure(path,false);

                float currDest = (float) ((double) currD / totalD * pm.getLength());
                float currPos[] = new float[2];
                float tan[]  =new float[2];
                pm.getPosTan(currDest,currPos,tan);


                mParam.x = (int) currPos[0];
                mParam.y = (int) currPos[1];


                // 设置透明度
                iv.getDrawable().setAlpha((int) currentAlpha);


                // 设置缩放值
                float scale = 1 - currD * 0.5f / totalD;
                iv.setScaleX(scale);
                iv.setScaleY(scale);

                mWm.updateViewLayout(iv,mParam);
            }
        });

        anim.addListener(new AnimatorListenerAdapter() {

            @Override
            public void onAnimationEnd(Animator animation) {
                mWm.removeView(iv);
            }
        });




        anim.start();
    }

    private void initPaths(ImageView iv, int destY) {
        mPaths.clear();
        Path path1  = new Path();
        path1.moveTo(mParam.x,mParam.y);
        path1.quadTo(mParam.x + iv.getMeasuredWidth() *2 ,mParam.y - iv.getMeasuredHeight()*2,mParam.x,destY);
        mPaths.add(path1);

        Path path2  = new Path();
        path2.moveTo(mParam.x,mParam.y);
        path2.quadTo(mParam.x - iv.getMeasuredWidth() *2 ,mParam.y - iv.getMeasuredHeight()*2,mParam.x,destY);
        mPaths.add(path2);

        Path path3  = new Path();
        path3.moveTo(mParam.x,mParam.y);
        path3.quadTo(mParam.x - iv.getMeasuredWidth() ,mParam.y - iv.getMeasuredHeight()*2,mParam.x,destY);
        mPaths.add(path3);

        Path path4  = new Path();
        path4.moveTo(mParam.x,mParam.y);
        path4.quadTo(mParam.x + iv.getMeasuredWidth() ,mParam.y - iv.getMeasuredHeight()*2,mParam.x,destY);
        mPaths.add(path4);

        Path path5  = new Path();
        path5.moveTo(mParam.x,mParam.y);
        path5.quadTo(mParam.x,mParam.y - iv.getMeasuredHeight()*2,mParam.x,destY);
        mPaths.add(path5);

    }

    /**
     * 设置所有显示的图片资源
     * @param imgs
     */
    public FllowLikeManager setDisplayImagesResource(int[] imgs){
        mImages = imgs;
        return this;
    }
}
