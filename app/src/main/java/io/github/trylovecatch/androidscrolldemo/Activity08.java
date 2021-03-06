package io.github.trylovecatch.androidscrolldemo;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.widget.ViewDragHelper;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

/**
 *
 * offsetLeftAndRight offsetTopAndBottom
 * Created by lipeng21 on 2017/3/28.
 *
 */

public class Activity08 extends Activity{
    private static final String TAG = Activity08.class.getSimpleName();

    private Button mBtn;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity02);

        mBtn = (Button)findViewById(R.id.btn);

        mBtn.setOnTouchListener(new View.OnTouchListener() {
            float tLastX;
            float tLastY;
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()){
                    case MotionEvent.ACTION_DOWN:
                        tLastX = event.getX();
                        tLastY = event.getY();
                        Log.e(TAG, "按下事件: " + tLastX + ", " + tLastY);
                        break;
                    case MotionEvent.ACTION_MOVE:
                        int tOffsetX = Math.round(event.getX() - tLastX);
                        int tOffsetY = Math.round(event.getY() - tLastY);
                        Log.e(TAG, "移动事件: " + tOffsetX + ", " + tOffsetY + ", " + (v.getTranslationX() + tOffsetX)
                            + ", " + (v.getTranslationY() + tOffsetY));

                        v.setTranslationX(v.getTranslationX() + tOffsetX);
                        v.setTranslationY(v.getTranslationY() + tOffsetY);
                        break;
                    case MotionEvent.ACTION_UP:
                        Log.e(TAG, "松开事件");
                        Log.e(TAG, "left: " + mBtn.getLeft() + ", right: " + mBtn.getRight() + ", top: " + mBtn.getTop()
                                + ", bottom: " + mBtn.getBottom());
                        break;
                }

                return false;
            }
        });

        mBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e(TAG, "onClick");
            }
        });

    }



}
