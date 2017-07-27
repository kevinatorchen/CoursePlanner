package com.example.myothiha09.coursehelper.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import com.example.myothiha09.coursehelper.R;
import com.example.myothiha09.coursehelper.fragment.NameFragment;
import com.example.myothiha09.coursehelper.fragment.SchoolFragment;

public class RegisterActivity extends AppCompatActivity {
  public static SharedPreferences prefs;

  /**
   * A static method to start this activity more conveniently
   *
   * @param context the context this Activity was started from
   */
  public static void start(Context context) {
    Intent intent = new Intent(context, RegisterActivity.class);
    context.startActivity(intent);
  }

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_register);
  }


}
