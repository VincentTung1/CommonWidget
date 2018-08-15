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

    private int mDefalutLevel = 5;

    private int mBallX = 0;
    private int mBallRadius;

    ArrayList<Integer> intervalXList = new ArrayList<>();

    private int mEndX;


    private OnLineBarCallbackListener mListener;

    public void setOnLineBarCallbackListener(OnLineBarCallbackListener mListener) {
        this.mListener = mListener;
    }


    public void setTotalLevel(int level){
        mDefalutLevel = level-1;
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


        int startY = getHeight() - 20*getDensity();
        int startX  = getPaddingLeft() + 2*mBallRadius;

         mEndX = getWidth() -  2*mBallRadius;

        // 画横线
        canvas.drawLine(startX, startY,getWidth() - 2*mBallRadius,startY,mPaint);


        int interval = (mEndX - startX)/ mDefalutLevel;


        intervalXList.clear();

        // 画竖线
        for (int i = 0; i<= mDefalutLevel;i++){
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
        mTextPaint.setTextSize(15);
        canvas.drawText(text,startX -mTextPaint.measureText(text)/2,startY - 2*mBallRadius,mTextPaint);
        mTextPaint.setTextSize(30);
        canvas.drawText(text, mEndX - mTextPaint.measureText(text)/2,startY - 2*mBallRadius,mTextPaint);


        // 画球
        mBallPaint.setColor(Color.WHITE);
        mBallPaint.setStyle(Paint.Style.FILL);
        canvas.drawCircle(startX + mBallX,startY, mBallRadius,mBallPaint);
        mBallPaint.setColor(Color.BLACK);
        mBallPaint.setStyle(Paint.Style.STROKE);
        canvas.drawCircle(startX + mBallX,startY, mBallRadius,mBallPaint);



    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {

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
