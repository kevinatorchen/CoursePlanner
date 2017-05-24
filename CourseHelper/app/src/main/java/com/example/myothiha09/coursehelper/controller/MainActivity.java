package com.example.myothiha09.coursehelper.controller;

import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;
import com.example.myothiha09.coursehelper.R;
import com.example.myothiha09.coursehelper.model.Course;
import com.example.myothiha09.coursehelper.model.Model;
import com.example.myothiha09.coursehelper.model.Student;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private Student student;
    private NavigationView navigationView;
    int index = 0;
    int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Model.getInstance(); //to intialize lists and student
        index = Model.student.getCoursesList().size();
        student = Model.student;
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        View layout = navigationView.getHeaderView(0);
        ((TextView) layout.findViewById(R.id.headerName)).
                setText(student.getName());
        ((TextView) layout.findViewById(R.id.headerUni)).
                setText(student.getUniversity());
        getSupportFragmentManager().beginTransaction().replace(R.id.container, new AddClass()).commit();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            FragmentManager fm = getSupportFragmentManager();
            if (fm.getBackStackEntryCount() > 0) {
                fm.popBackStack();
            } else {
                if (count == 1) super.onBackPressed();
                count++;
                Boast.makeText(getApplicationContext(), "Press back again to exit", Toast.LENGTH_SHORT).show();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        count = 0;
                    }
                }, 2000);
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            finishAffinity();
            return true;
        } else if (id == R.id.action_reset) {
            createDialogBox().show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_select_classes) {
            getSupportFragmentManager().beginTransaction().replace(R.id.container, new AddClass()).addToBackStack(null).commit();
        } else if (id == R.id.nav_schedules) {
            getSupportFragmentManager().beginTransaction().replace(R.id.container, new ScheduleOverviewRecycler()).addToBackStack(null).commit();
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    /**
     * Creates the dialog box for alert function. It also tells permission information.
     * @return nothing. Just prompts a dialog box.
     */
    private android.app.AlertDialog createDialogBox() {
        android.app.AlertDialog.Builder myDialogBuilder = new android.app.AlertDialog.Builder(this);
        myDialogBuilder.setTitle("Are you sure?");
        myDialogBuilder.setMessage("You will lose all your data!!!");
        myDialogBuilder.setIcon(R.mipmap.ic_launcher);


        myDialogBuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        myDialogBuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                RegisterActivity.prefs.edit().clear().commit();
                finishAffinity();
                System.exit(1);
            }
        });
        return myDialogBuilder.create();
    }
    public void onClickMain(View v) {
        if (v.getId() == R.id.addClass) {
            if (student.getCoursesList().size() >= 10) {
                Boast.makeText(getApplicationContext(), "You cannot have more than 10 classes", Toast.LENGTH_LONG).show();
            }
            else {
                showCategoryChooser();
            }
        } else if (v.getId() == R.id.makeSchedule) {
            navigationView.setCheckedItem(R.id.nav_schedules);
            getSupportFragmentManager().beginTransaction().replace(R.id.container, new ScheduleOverviewRecycler()).addToBackStack(null).commit();
        } else if (v.getId() == R.id.showAllVisually) {
            getSupportFragmentManager().beginTransaction().replace(R.id.container, new ScheduleVisualList()).addToBackStack(null).commit();
        }
    }
    public void showCategoryChooser() {
        final List<String> stringList=new ArrayList<>();

        stringList.addAll(Model.CATEGORY);
        new MaterialDialog.Builder(this)
                .title("Class Chooser")
                .items(stringList)
                .itemsCallbackSingleChoice(0, new MaterialDialog.ListCallbackSingleChoice() {
                    @Override
                    public boolean onSelection(MaterialDialog dialog, View view, int which, CharSequence text) {
                        showClassChooser(stringList.get(which));
                        return true;
                    }
                })
                .positiveText("Next")
                .negativeText("Cancel")
                .show();

        /*final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.category_dialog);

        final RadioGroup rg = (RadioGroup) dialog.findViewById(R.id.radio_group);

        for(int i=0;i<stringList.size();i++) {
            RadioButton rb = new RadioButton(this); // dynamically creating RadioButton and adding to RadioGroup.
            rb.setText(stringList.get(i));
            rg.addView(rb);
        }
        dialog.getWindow().getAttributes().windowAnimations = R.style.dialogAnimation;
        dialog.show();*/
        /*Button cancel = (Button) findViewById(R.id.cancelDialog);
        Button next = (Button) findViewById(R.id.nextDialog);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });*/
    }
    public void showClassChooser(String category) {
        final List<Course> courseList = new ArrayList<>();
        courseList.addAll(Model.list.get(category));
        new MaterialDialog.Builder(this)
                .title("Category Chooser")
                .items(courseList)
                .itemsCallbackSingleChoice(0, new MaterialDialog.ListCallbackSingleChoice() {
                    @Override
                    public boolean onSelection(MaterialDialog dialog, View view, int which, CharSequence text) {
                        if (!student.getCoursesList().contains(courseList.get(which))) {
                            AddClass.adapter.add(courseList.get(which));
                            student.addCourse(courseList.get(which));
                        }
                        return true;
                    }
                })
                .positiveText("Add")
                .negativeText("Cancel")
                .show();
      /*  Button cancel = (Button) findViewById(R.id.cancelDialog);
        Button next = (Button) findViewById(R.id.nextDialog);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int index = rg.getCheckedRadioButtonId();
                stringList.get(index);
                //do adding the class to backend logics
                dialog.dismiss();
            }
        });*/
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
