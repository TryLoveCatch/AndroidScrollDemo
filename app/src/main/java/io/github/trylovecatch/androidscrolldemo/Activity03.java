package io.github.trylovecatch.androidscrolldemo;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 *
 * LayoutParams
 *
 * Created by lipeng21 on 2017/3/28.
 *
 */

public class Activity03 extends Activity{
    private static final String TAG = Activity03.class.getSimpleName();

    private Button mBtn;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity03);

        mBtn = (Button)findViewById(R.id.btn);

        mBtn.setOnTouchListener(new View.OnTouchListener() {
            float tLastX;
            float tLastY;
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()){
                    case MotionEvent.ACTION_DOWN:
                        Log.e(TAG, "按下事件");
                        tLastX = event.getX();
                        tLastY = event.getY();
                        break;
                    case MotionEvent.ACTION_MOVE:
                        Log.e(TAG, "移动事件");
                        int tOffsetX = Math.round(event.getX() - tLastX);
                        int tOffsetY = Math.round(event.getY() - tLastY);
                        ViewGroup.MarginLayoutParams tParams = (ViewGroup.MarginLayoutParams)v.getLayoutParams();
                        tParams.leftMargin = v.getLeft() + tOffsetX;
                        tParams.topMargin = v.getTop() + tOffsetY;
                        v.setLayoutParams(tParams);
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
