package com.example.myothiha09.coursehelper.controller;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.myothiha09.coursehelper.R;

public class RegisterActivity extends AppCompatActivity {
    public static SharedPreferences prefs;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        prefs = this.getSharedPreferences(
                getPackageName(), Context.MODE_PRIVATE);
        checkFirstTimeUser();
        getSupportFragmentManager().beginTransaction().replace(R.id.frame_container, new NameFragment()).commit();
    }
    public void checkFirstTimeUser() {
        boolean firstTime = prefs.getBoolean("FirstTimeUser", true);
        if (!firstTime) {
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
            finish();
        }
    }
    public void onClick(View v) {
        if (v.getId() == R.id.nextButtonFromName) {
            prefs.edit().putString("StudentName",
                    ((EditText)findViewById(R.id.stuName)).getText().toString()).commit();
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.setCustomAnimations(R.anim.pop_enter, R.anim.pop_exit);
            ft.replace(R.id.frame_container, new SchoolFragment()).commit();
        } else if (v.getId() == R.id.nextButtonFromSchool) {
            prefs.edit().putString("UniName",
                    ((EditText)findViewById(R.id.uniName)).getText().toString()).commit();
            prefs.edit().putBoolean("FirstTimeUser", false).commit();
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
            overridePendingTransition(R.anim.pop_enter, R.anim.pop_exit);
            finish();
        }
    }
}
