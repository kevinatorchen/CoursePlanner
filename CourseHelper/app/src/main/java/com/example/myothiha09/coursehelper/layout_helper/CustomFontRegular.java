package com.example.myothiha09.coursehelper.layout_helper;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;

/**
 * TextView with Roboto-Regular as default font
 */

public class CustomFontRegular extends android.support.v7.widget.AppCompatTextView {

  public CustomFontRegular(Context context, AttributeSet attrs, int defStyle) {
    super(context, attrs, defStyle);
    init();
  }

  public CustomFontRegular(Context context, AttributeSet attrs) {
    super(context, attrs);
    init();
  }

  public CustomFontRegular(Context context) {
    super(context);
    init();
  }

  private void init() {
    Typeface tf = Typeface.createFromAsset(getContext().getAssets(), "font/Roboto-Regular.ttf");
    setTypeface(tf, 1);
  }
}
