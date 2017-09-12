package io.github.trylovecatch.androidscrolldemo;

import android.app.Activity;
import android.os.Bundle;

/**
 *
 * Scroller
 *
 * Created by lipeng21 on 2017/3/28.
 *
 */

public class Activity05 extends Activity{
    private static final String TAG = Activity05.class.getSimpleName();

    private MyView05 mView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity05);

        mView = (MyView05)findViewById(R.id.myView);

    }



}
