package io.github.trylovecatch.androidscrolldemo;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Scroller;

/**
 * Created by lipeng21 on 2017/3/31.
 */

public class MyView05 extends View{
    private final static String TAG = MyView05.class.getSimpleName();


    private Scroller mScroller;
    private int mLastX;
    private int mLastY;
    private int mInitX;
    private int mInitY;

    public MyView05(Context context) {
        super(context);
        init();
    }

    public MyView05(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MyView05(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }


    private void init(){
        mScroller = new Scroller(getContext());
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        Log.e(TAG, "onFinishInflate");

        mInitX = getLeft();
        mInitY = getTop();
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        Log.e(TAG, "onAttachedToWindow");

        mInitX = getLeft();
        mInitY = getTop();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        Log.e(TAG, "onMeasure");
        mInitX = getLeft();
        mInitY = getTop();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                //如果正在滑动，就强制结束，这样就可以连续向下拖动
                if(!mScroller.isFinished()){
//                    mScroller.abortAnimation();
                    mScroller.forceFinished(true);
                }
                Log.e(TAG, getLeft() + ", " + getTop());
                mLastX = (int)event.getX();
                mLastY = (int)event.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                int tOffsetX = Math.round(event.getX() - mLastX);
                int tOffsetY = Math.round(event.getY() - mLastY);
                offsetLeftAndRight(tOffsetX);
                offsetTopAndBottom(tOffsetY);
                break;
            case MotionEvent.ACTION_UP:
                //实现回弹效果
                mScroller.startScroll(getLeft(), getTop(), -getLeft() + mInitX, -getTop() + mInitY, 2000);
                invalidate();
                break;
        }

        return true;
//        return super.onTouchEvent(event);
    }

    @Override
    public void computeScroll() {
        super.computeScroll();
        if(mScroller.computeScrollOffset()){
            offsetLeftAndRight(mScroller.getCurrX() - getLeft());
            offsetTopAndBottom(mScroller.getCurrY() - getTop());
            invalidate();
        }
    }
}
