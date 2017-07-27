package com.example.myothiha09.coursehelper.activity;

/**
 * Created by Myo on 7/27/2017.
 */

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnTextChanged;
import com.example.myothiha09.coursehelper.R;
import com.example.myothiha09.coursehelper.model.Model;
import com.example.myothiha09.coursehelper.model.Student;
import com.example.myothiha09.coursehelper.util.Constants;
import com.google.gson.Gson;

public class LoginActivity extends AppCompatActivity {
  @BindView(R.id.titleBox) LinearLayout titleBox;
  @BindView(R.id.email) EditText email;
  @BindView(R.id.password) EditText password;
  @BindView(R.id.loginButton) Button loginButton;
  @BindView(R.id.registerButton) Button registerButton;
  @BindView(R.id.authCheckerText) TextView authChecker;
  @BindView(R.id.progressBar) ProgressBar progressBar;
  @BindView(R.id.forgot_password) Button forgetPasswordButton;
  private SharedPreferences prefs;

  /**
   * A static method to start this activity more conveniently
   *
   * @param context the context this Activity was started from
   */

  public static void start(Context context) {
    Intent intent = new Intent(context, LoginActivity.class);
    context.startActivity(intent);
  }

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_login);
    prefs = getSharedPreferences(getPackageName(), MODE_PRIVATE);
    checkFirstTimeUser();
    ButterKnife.bind(this);
  }

  public void checkFirstTimeUser() {
    boolean firstTime = prefs.getBoolean(Constants.FIRST_TIME_TAG, true);
    if (!firstTime) {
      MainActivity.start(this);
      finish();
    }
  }

  /**
   * Start ForgotPasswordActivity when user click forgot password.
   */
  @OnClick(R.id.forgot_password) void onForgotPassword() {

  }

  /**
   * Go to main screen if user successfully logged in.
   */
  @OnClick(R.id.loginButton) public void onLoginClick() {
    if (checkAuthentication()) {
      prefs.edit().putBoolean(Constants.FIRST_TIME_TAG, false).apply();
      Gson gson = new Gson();
      String studentJson = gson.toJson(new Student("Myo Thiha", "Georgia Tech"));
      prefs.edit().putString(Constants.STUDENT_TAG, studentJson).apply();
      MainActivity.start(this);
    }
  }

  /**
   * Listen for text change to reset the text that shows if authentication failed.
   */
  @OnTextChanged({ R.id.email, R.id.password }) void onTextChange() {
    authChecker.setVisibility(View.INVISIBLE);
  }

  /**
   * Check if the email and password are valid or not.
   *
   * @return true if valid false if invalid
   */

  private boolean checkAuthentication() {
    authChecker.setVisibility(View.INVISIBLE);
    progressBar.setVisibility(View.VISIBLE);
    return true;
  }

  private void disableView() {
    getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
        WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
  }

  private void enableView() {
    getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
  }

  /**
   * Go to registration screen when user click Register button.
   */
  @OnClick(R.id.registerButton) public void registerButton() {
    RegisterActivity.start(getApplicationContext());
    finish();
  }
}