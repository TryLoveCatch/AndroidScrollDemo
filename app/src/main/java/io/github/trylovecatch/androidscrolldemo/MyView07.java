package io.github.trylovecatch.androidscrolldemo;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.view.MotionEventCompat;
import android.support.v4.widget.ViewDragHelper;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;

/**
 * Created by lipeng21 on 2017/3/31.
 */

public class MyView07 extends LinearLayout{
    private final static String TAG = MyView07.class.getSimpleName();


    private ViewDragHelper mHelp;
    private int mLastX;
    private int mLastY;
    private int mInitX;
    private int mInitY;

    public MyView07(Context context) {
        super(context);
        init();
    }

    public MyView07(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MyView07(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }


    private void init(){
        mHelp = ViewDragHelper.create(this, new ViewDragHelper.Callback() {
            @Override
            public boolean tryCaptureView(View child, int pointerId) {
                return true;
            }

            @Override
            public int clampViewPositionHorizontal(View child, int left, int dx) {
                Log.e(TAG, "left: " + left);
                return left;
            }

            @Override
            public int clampViewPositionVertical(View child, int top, int dy) {
                Log.e(TAG, "top: " + top);
                return top;
            }

//            @Override
//            public void onViewReleased(View releasedChild, float xvel, float yvel) {
//                super.onViewReleased(releasedChild, xvel, yvel);
//            }
        });
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        Log.e(TAG, "onFinishInflate");
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        Log.e(TAG, "onAttachedToWindow");
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        Log.e(TAG, "onMeasure");
        mInitX = getLeft();
        mInitY = getTop();
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        final int action = MotionEventCompat.getActionMasked(ev);
        if (action == MotionEvent.ACTION_CANCEL || action == MotionEvent.ACTION_UP) {
            mHelp.cancel();
            return false;
        }
        Log.e(TAG, "onInterceptTouchEvent");
        return mHelp.shouldInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.e(TAG, "onTouchEvent");
        mHelp.processTouchEvent(event);
        return true;
    }

//    @Override
//    public void computeScroll() {
//        super.computeScroll();
//        if(mHelp.continueSettling(true)){
//            ViewCompat.postInvalidateOnAnimation(this);
//        }
//    }
}
