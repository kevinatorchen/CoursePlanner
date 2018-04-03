package com.example.myothiha09.coursehelper.layout_helper;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;

/**
 * TextView with Roboto-Light as default font
 * Created by Myo on 5/17/2017.
 */

public class CustomFontLight extends android.support.v7.widget.AppCompatTextView {

  public CustomFontLight(Context context, AttributeSet attrs, int defStyle) {
    super(context, attrs, defStyle);
    init();
  }

  public CustomFontLight(Context context, AttributeSet attrs) {
    super(context, attrs);
    init();
  }

  public CustomFontLight(Context context) {
    super(context);
    init();
  }

  private void init() {
    Typeface tf = Typeface.createFromAsset(getContext().getAssets(), "font/Roboto-Light.ttf");
    setTypeface(tf, 1);
  }
}
