package com.vincent.commonwidget.widget.linebar;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;

/**
 *   调整文字大小选择器
 */

public class LineBarView extends View {

    Paint mPaint;
    Paint mBallPaint;
    Paint mTextPaint;


    Context mContext;

    private int mTotalLevel = 5;

    /**标准等级*/
    private int mStandardLevel = 2;

    /**当前等级*/
    private int mCurrentLevel = 1;

    private int mBallX = 0;
    private int mBallRadius;

    ArrayList<Integer> intervalXList = new ArrayList<>();

    private int mEndX;


    private OnLineBarCallbackListener mListener;

    public void setOnLineBarCallbackListener(OnLineBarCallbackListener mListener) {
        this.mListener = mListener;
    }


    public void setTotalLevel(int level){
        mTotalLevel = level-1;
    }


    /**
     *  设置标准等级
     * @param standardLevel
     */
    public void setStandardLevel(int standardLevel) {
        mStandardLevel = standardLevel;
    }


    /**
     *  设置当前等级
     * @param level
     */
    public void setCurrentLevel(int level){
        mCurrentLevel = level;
    }


    public LineBarView(Context context) {
        this(context,null);
    }

    public LineBarView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public LineBarView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        mContext = context;

        mPaint = new Paint();
        mPaint.setColor(Color.BLACK);
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.STROKE);


        mTextPaint = new Paint();
        mTextPaint.setColor(Color.BLACK);
        mTextPaint.setTextSize(20);
        mTextPaint.setStrokeWidth(1);
        mTextPaint.setAntiAlias(true);
        mTextPaint.setStyle(Paint.Style.FILL);

        mBallPaint  = new Paint();
        mBallPaint.setColor(Color.WHITE);
        mBallPaint.setAntiAlias(true);
        mBallPaint.setStyle(Paint.Style.FILL);
    }



    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int buttonSize = 30;
        int buttonWidth = buttonSize * getDensity();
        mBallRadius = buttonWidth / 2;


        int startY = getHeight() - 20 * getDensity();
        int startX  = getPaddingLeft() + 2 * mBallRadius;

         mEndX = getWidth() -  2*mBallRadius;

        // 画横线
        canvas.drawLine(startX, startY,getWidth() - 2*mBallRadius,startY,mPaint);


        int interval = (mEndX - startX)/ mTotalLevel;


        intervalXList.clear();

        // 画竖线
        for (int i = 0; i<= mTotalLevel; i++){
            if (i == 0){
                canvas.drawLine(startX,startY - mBallRadius, startX,startY +mBallRadius,mPaint);

                intervalXList.add(startX - 2*mBallRadius);
            }else {
                canvas.drawLine(i*interval+startX,startY - mBallRadius,i*interval + startX,startY +mBallRadius,mPaint);

                intervalXList.add(i*interval + startX - 2*mBallRadius);
            }

        }

        // 画文字

        String text = "A";
        mTextPaint.setTextSize(15 * getDensity());
        canvas.drawText(text,startX -mTextPaint.measureText(text)/2,startY - 2*mBallRadius,mTextPaint);
        mTextPaint.setTextSize(30 * getDensity());
        canvas.drawText(text, mEndX - mTextPaint.measureText(text)/2,startY - 2*mBallRadius,mTextPaint);


        mTextPaint.setTextSize(20 * getDensity());
        for (int i = 0; i <= mTotalLevel; i++) {
            if (i+1 == mStandardLevel){
                canvas.drawText("标准",intervalXList.get(i) + mTextPaint.measureText(text)/2,startY - 2*mBallRadius,mTextPaint);
            }
        }


        // 画球
        if (mCurrentLevel > 1 && mCurrentLevel <=mTotalLevel){
            mBallPaint.setColor(Color.WHITE);
            mBallPaint.setStyle(Paint.Style.FILL);
            canvas.drawCircle(intervalXList.get(mCurrentLevel-1)+startX+mBallX,startY, mBallRadius,mBallPaint);
            mBallPaint.setColor(Color.BLACK);
            mBallPaint.setStyle(Paint.Style.STROKE);
            canvas.drawCircle(intervalXList.get(mCurrentLevel-1)+startX+mBallX,startY, mBallRadius,mBallPaint);
        }else {
            mBallPaint.setColor(Color.WHITE);
            mBallPaint.setStyle(Paint.Style.FILL);
            canvas.drawCircle(startX + mBallX,startY, mBallRadius,mBallPaint);
            mBallPaint.setColor(Color.BLACK);
            mBallPaint.setStyle(Paint.Style.STROKE);
            canvas.drawCircle(startX + mBallX,startY, mBallRadius,mBallPaint);
        }





    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        mCurrentLevel = -1;

        mBallX = (int) event.getX() - 2* mBallRadius;
        if (mBallX < 0 || mBallX > mEndX - 2*mBallRadius){
            return false;
        }else {
            invalidate();
        }

        if (event.getAction() == MotionEvent.ACTION_UP){       //自动修正小球位置

            fixBallLocation();
            invalidate();
        }

        return true;
    }

    private void fixBallLocation() {
        if (intervalXList != null && intervalXList.size()>0){

            for (int i = 0; i < intervalXList.size(); i++) {

                if (i+1 == intervalXList.size()) break;

                int x1 = intervalXList.get(i);
                int x2  = intervalXList.get(i+1);


                if (mBallX > x1 && mBallX <= x2){
                    if (Math.abs(x1 - mBallX) > Math.abs(x2 - mBallX)){
                        mBallX = x2;

                        if (mListener != null){
                            mListener.onSelected(i+2);
                        }
                    }else {
                        mBallX = x1;
                        if (mListener != null){
                            mListener.onSelected(i+1);
                        }
                    }
                }


            }

        }
    }

    private int getDensity(){
        return (int) mContext.getResources().getDisplayMetrics().density;
    }


    public interface OnLineBarCallbackListener{

        void onSelected(int i);
    }
}
