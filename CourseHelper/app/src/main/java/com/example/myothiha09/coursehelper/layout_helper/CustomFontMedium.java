package com.example.myothiha09.coursehelper.layout_helper;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;

/**
 * TextView with Roboto-Medium as default font.
 */

public class CustomFontMedium extends android.support.v7.widget.AppCompatTextView {

  public CustomFontMedium(Context context, AttributeSet attrs, int defStyle) {
    super(context, attrs, defStyle);
    init();
  }

  public CustomFontMedium(Context context, AttributeSet attrs) {
    super(context, attrs);
    init();
  }

  public CustomFontMedium(Context context) {
    super(context);
    init();
  }

  private void init() {
    Typeface tf = Typeface.createFromAsset(getContext().getAssets(), "font/Roboto-Medium.ttf");
    setTypeface(tf, 1);
  }
}
