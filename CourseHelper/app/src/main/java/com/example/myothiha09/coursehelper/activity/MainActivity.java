package com.example.myothiha09.coursehelper.activity;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import com.example.myothiha09.coursehelper.R;
import com.example.myothiha09.coursehelper.fragment.AddClassFragment;
import com.example.myothiha09.coursehelper.fragment.ScheduleOverviewFragment;
import com.example.myothiha09.coursehelper.model.Model;
import com.example.myothiha09.coursehelper.model.Student;

public class MainActivity extends AppCompatActivity
    implements NavigationView.OnNavigationItemSelectedListener {
  public static Context context;
  int index = 0;
  int count = 0;
  private Student student;
  private NavigationView navigationView;

  //TODO: animations to make it very satisfying to use.

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);
    Model.getInstance(); //to intialize lists and student
    index = Model.student.getCourseRequests().size();
    student = Model.student;
    DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
    ActionBarDrawerToggle toggle =
        new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open,
            R.string.navigation_drawer_close);
    drawer.setDrawerListener(toggle);
    toggle.syncState();

    navigationView = (NavigationView) findViewById(R.id.nav_view);
    navigationView.setNavigationItemSelectedListener(this);
    View layout = navigationView.getHeaderView(0);
    ((TextView) layout.findViewById(R.id.headerName)).
        setText(student.getName());
    ((TextView) layout.findViewById(R.id.headerUni)).
        setText(student.getUniversity());
    getSupportFragmentManager().beginTransaction()
        .replace(R.id.container, new AddClassFragment())
        .commit();
  }

  @Override public void onBackPressed() {
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
        Toast.makeText(getApplicationContext(), "Press back again to exit", Toast.LENGTH_SHORT)
            .show();
        new Handler().postDelayed(new Runnable() {
          @Override public void run() {
            count = 0;
          }
        }, 2000);
      }
    }
  }

  @Override public boolean onCreateOptionsMenu(Menu menu) {
    // Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater().inflate(R.menu.main, menu);
    return true;
  }

  @Override public boolean onOptionsItemSelected(MenuItem item) {
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

  @SuppressWarnings("StatementWithEmptyBody") @Override
  public boolean onNavigationItemSelected(MenuItem item) {
    // Handle navigation view item clicks here.
    int id = item.getItemId();

    if (id == R.id.nav_select_classes) {
      getSupportFragmentManager().beginTransaction()
          .setCustomAnimations(R.anim.pop_enter, R.anim.pop_exit)
          .replace(R.id.container, new AddClassFragment())
          .addToBackStack(null)
          .commit();
    } else if (id == R.id.nav_schedules) {
      getSupportFragmentManager().beginTransaction()
          .setCustomAnimations(R.anim.pop_enter, R.anim.pop_exit)
          .replace(R.id.container, new ScheduleOverviewFragment())
          .addToBackStack(null)
          .commit();
    }
    DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
    drawer.closeDrawer(GravityCompat.START);
    return true;
  }

  /**
   * Creates the dialog box for alert function. It also tells permission information.
   *
   * @return nothing. Just prompts a dialog box.
   */
  private android.app.AlertDialog createDialogBox() {
    android.app.AlertDialog.Builder myDialogBuilder = new android.app.AlertDialog.Builder(this);
    myDialogBuilder.setTitle("Are you sure?");
    myDialogBuilder.setMessage("You will lose all your data!!!");
    myDialogBuilder.setIcon(R.mipmap.ic_launcher);

    myDialogBuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {
      @Override public void onClick(DialogInterface dialog, int which) {
        dialog.dismiss();
      }
    });
    myDialogBuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
      @Override public void onClick(DialogInterface dialog, int which) {
        RegisterActivity.prefs.edit().clear().commit();
        finishAffinity();
        System.exit(1);
      }
    });
    return myDialogBuilder.create();
  }

  public void onClickMain(View v) {
    if (v.getId() == R.id.makeSchedule) {
      navigationView.setCheckedItem(R.id.nav_schedules);
      getSupportFragmentManager().beginTransaction()
          .setCustomAnimations(R.anim.pop_enter, R.anim.pop_exit)
          .replace(R.id.container, new ScheduleOverviewFragment())
          .addToBackStack(null)
          .commit();
    }
  }

  @Override protected void onDestroy() {
    super.onDestroy();
  }
}
