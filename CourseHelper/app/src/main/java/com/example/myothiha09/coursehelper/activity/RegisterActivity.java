package com.example.myothiha09.coursehelper.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.example.myothiha09.coursehelper.R;

public class RegisterActivity extends AppCompatActivity {

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
    ButterKnife.bind(this);
  }

  @OnClick(R.id.submit) void submit() {
    finish();
  }
  @OnClick(R.id.cancel) void cancel() {
    finish();
  }
}
