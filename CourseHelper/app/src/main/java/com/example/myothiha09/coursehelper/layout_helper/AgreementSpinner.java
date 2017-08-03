package com.example.myothiha09.coursehelper.layout_helper;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ArrayAdapter;
import com.example.myothiha09.coursehelper.R;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;

/**
 * Created by Myo on 8/3/2017.
 */

public class AgreementSpinner extends android.support.v7.widget.AppCompatSpinner {
  HashMap<String, Integer> agreementData = new HashMap<>();

  public AgreementSpinner(Context context) {
    super(context);
    initSpinner();
  }

  public AgreementSpinner(Context context, AttributeSet attrs, int defStyle) {
    super(context, attrs, defStyle);
    initSpinner();
  }

  public AgreementSpinner(Context context, AttributeSet attrs) {
    super(context, attrs);
    initSpinner();
  }

  private void initSpinner() {

    agreementData.put("Strongly Disagree", 100);
    agreementData.put("Disagree", 50);
    agreementData.put("Neutral", 0);
    agreementData.put("Agree", -50);
    agreementData.put("Strongly Agree", -100);
    ArrayList<String> data = new ArrayList<>();
    data.addAll(agreementData.keySet());
    Collections.sort(data, new Comparator<String>() {
      @Override public int compare(String o1, String o2) {
        return agreementData.get(o1) - agreementData.get(o2);
      }
    });
    ArrayAdapter<String> adapter =
        new ArrayAdapter<>(getContext(), R.layout.spinner_custom_layout, data);
    setAdapter(adapter);
    setSelection(2);
  }

  public int getValue(String s) {
    return agreementData.get(s);
  }
}
