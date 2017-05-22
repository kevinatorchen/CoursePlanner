package com.example.myothiha09.coursehelper.model;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;

/**
 * Created by Myo on 5/17/2017.
 */

public class CustomFont extends android.support.v7.widget.AppCompatTextView {

    public CustomFont(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    public CustomFont(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CustomFont(Context context) {
        super(context);
        init();
    }

    public void init() {
        Typeface tf = Typeface.createFromAsset(getContext().getAssets(), "font/myFont.TTF");
        setTypeface(tf ,1);
    }
}
