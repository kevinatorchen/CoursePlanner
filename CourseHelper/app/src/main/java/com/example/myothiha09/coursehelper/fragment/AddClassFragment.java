package com.example.myothiha09.coursehelper.fragment;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.example.myothiha09.coursehelper.R;
import com.example.myothiha09.coursehelper.adapter.TestAdapter;
import com.example.myothiha09.coursehelper.controller.Boast;
import com.example.myothiha09.coursehelper.dialog.ClassSearcherDialog;
import com.example.myothiha09.coursehelper.model.Course;
import com.example.myothiha09.coursehelper.model.CourseRequest;
import com.example.myothiha09.coursehelper.model.Model;
import com.example.myothiha09.coursehelper.model.Student;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Myo on 5/25/2017.
 */

public class AddClassFragment extends Fragment {
  public static RecyclerView.Adapter adapter;
  @BindView(R.id.recycler_view) RecyclerView recyclerView;
  private RecyclerView.LayoutManager layoutManager;
  private Student student;

  @Nullable @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.add_class_screen, container, false);
    ButterKnife.bind(this, view);
    student = Model.student;
    initRecycler();
    setUpItemTouchHelper();
    setUpAnimationDecoratorHelper();
    return view;
  }

  private void initRecycler() {
    recyclerView.setHasFixedSize(true);
    layoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
    recyclerView.setLayoutManager(layoutManager);
    adapter = new TestAdapter(Model.student.getCoursesList());
    recyclerView.setItemAnimator(new DefaultItemAnimator());
    recyclerView.setAdapter(adapter);
  }

  private void setUpItemTouchHelper() {

    ItemTouchHelper.SimpleCallback simpleItemTouchCallback =
        new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {

          // we want to cache these and not allocate anything repeatedly in the onChildDraw method
          Drawable background;
          Drawable xMark;
          int xMarkMargin;
          boolean initiated;

          private void init() {
            background = new ColorDrawable(Color.RED);
            xMark = getActivity().getResources().getDrawable(R.drawable.ic_clear_24dp);
            xMark.setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_ATOP);
            xMarkMargin = (int) getActivity().getResources().getDimension(R.dimen.ic_clear_margin);
            initiated = true;
          }

          // not important, we don't want drag & drop
          @Override public boolean onMove(RecyclerView recyclerView,
              RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
            return false;
          }

          @Override
          public int getSwipeDirs(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
            int position = viewHolder.getAdapterPosition();
            TestAdapter testAdapter = (TestAdapter) recyclerView.getAdapter();
            if (testAdapter.isUndoOn() && testAdapter.isPendingRemoval(position)) {
              return 0;
            }
            return super.getSwipeDirs(recyclerView, viewHolder);
          }

          @Override public void onSwiped(RecyclerView.ViewHolder viewHolder, int swipeDir) {
            int swipedPosition = viewHolder.getAdapterPosition();
            TestAdapter adapter = (TestAdapter) recyclerView.getAdapter();
            boolean undoOn = adapter.isUndoOn();
            if (undoOn) {
              adapter.pendingRemoval(swipedPosition);
            } else {
              adapter.remove(swipedPosition);
            }
          }

          @Override public void onChildDraw(Canvas c, RecyclerView recyclerView,
              RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState,
              boolean isCurrentlyActive) {
            View itemView = viewHolder.itemView;

            // not sure why, but this method get's called for viewholder that are already swiped away
            if (viewHolder.getAdapterPosition() == -1) {
              // not interested in those
              return;
            }

            if (!initiated) {
              init();
            }

            // draw red background
            background.setBounds(itemView.getRight() + (int) dX, itemView.getTop(),
                itemView.getRight(), itemView.getBottom());
            background.draw(c);

            // draw x mark
            int itemHeight = itemView.getBottom() - itemView.getTop();
            int intrinsicWidth = xMark.getIntrinsicWidth();
            int intrinsicHeight = xMark.getIntrinsicWidth();

            int xMarkLeft = itemView.getRight() - xMarkMargin - intrinsicWidth;
            int xMarkRight = itemView.getRight() - xMarkMargin;
            int xMarkTop = itemView.getTop() + (itemHeight - intrinsicHeight) / 2;
            int xMarkBottom = xMarkTop + intrinsicHeight;
            xMark.setBounds(xMarkLeft, xMarkTop, xMarkRight, xMarkBottom);

            xMark.draw(c);

            super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
          }
        };
    ItemTouchHelper mItemTouchHelper = new ItemTouchHelper(simpleItemTouchCallback);
    mItemTouchHelper.attachToRecyclerView(recyclerView);
  }

  /**
   * We're gonna setup another ItemDecorator that will draw the red background in the empty space
   * while the items are animating to thier new positions
   * after an item is removed.
   */
  private void setUpAnimationDecoratorHelper() {
    recyclerView.addItemDecoration(new RecyclerView.ItemDecoration() {

      // we want to cache this and not allocate anything repeatedly in the onDraw method
      Drawable background;
      boolean initiated;

      private void init() {
        background = new ColorDrawable(Color.RED);
        initiated = true;
      }

      @Override public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {

        if (!initiated) {
          init();
        }

        // only if animation is in progress
        if (parent.getItemAnimator().isRunning()) {

          // some items might be animating down and some items might be animating up to close the gap left by the removed item
          // this is not exclusive, both movement can be happening at the same time
          // to reproduce this leave just enough items so the first one and the last one would be just a little off screen
          // then remove one from the middle

          // find first child with translationY > 0
          // and last one with translationY < 0
          // we're after a rect that is not covered in recycler-view views at this point in time
          View lastViewComingDown = null;
          View firstViewComingUp = null;

          // this is fixed
          int left = 0;
          int right = parent.getWidth();

          // this we need to find out
          int top = 0;
          int bottom = 0;

          // find relevant translating views
          int childCount = parent.getLayoutManager().getChildCount();
          for (int i = 0; i < childCount; i++) {
            View child = parent.getLayoutManager().getChildAt(i);
            if (child.getTranslationY() < 0) {
              // view is coming down
              lastViewComingDown = child;
            } else if (child.getTranslationY() > 0) {
              // view is coming up
              if (firstViewComingUp == null) {
                firstViewComingUp = child;
              }
            }
          }

          if (lastViewComingDown != null && firstViewComingUp != null) {
            // views are coming down AND going up to fill the void
            top = lastViewComingDown.getBottom() + (int) lastViewComingDown.getTranslationY();
            bottom = firstViewComingUp.getTop() + (int) firstViewComingUp.getTranslationY();
          } else if (lastViewComingDown != null) {
            // views are going down to fill the void
            top = lastViewComingDown.getBottom() + (int) lastViewComingDown.getTranslationY();
            bottom = lastViewComingDown.getBottom();
          } else if (firstViewComingUp != null) {
            // views are coming up to fill the void
            top = firstViewComingUp.getTop();
            bottom = firstViewComingUp.getTop() + (int) firstViewComingUp.getTranslationY();
          }

          background.setBounds(left, top, right, bottom);
          background.draw(c);
        }
        super.onDraw(c, parent, state);
      }
    });
  }

  public void showCategoryChooser() {
    final List<String> stringList = new ArrayList<>();

    stringList.addAll(Model.CATEGORY);
    new MaterialDialog.Builder(getContext()).title("Category Chooser")
        .items(stringList)
        .itemsCallbackSingleChoice(0, new MaterialDialog.ListCallbackSingleChoice() {
          @Override public boolean onSelection(MaterialDialog dialog, View view, int which,
              CharSequence text) {
            showClassChooser(stringList.get(which));
            return true;
          }
        })
        .positiveText("Next")
        .neutralText("Search for Class")
        .onNeutral(new MaterialDialog.SingleButtonCallback() {
          @Override
          public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
            dialog.dismiss();
            initClassSearcher();
          }
        })
        .negativeText("Cancel")
        .show();
  }

  private void initClassSearcher() {
    ClassSearcherDialog dialog = new ClassSearcherDialog(getContext());
    dialog.show();
  }

  private void showClassChooser(String category) {

    final List<Course> courseList = new ArrayList<>();
    for (Course x : Model.list.get(category)) {
      if (!student.getCoursesList().contains(x)) {
        courseList.add(x);
      }
    }
    new MaterialDialog.Builder(getContext()).title("Class Chooser")
        .items(courseList)
        .itemsCallbackSingleChoice(0, new MaterialDialog.ListCallbackSingleChoice() {
          @Override public boolean onSelection(MaterialDialog dialog, View view, int which,
              CharSequence text) {
            if (!student.getCoursesList().contains(courseList.get(which))) {
              Course selectectedCourse = courseList.get(which);
              student.addCourse(selectectedCourse);
              student.addCourseRequest(new CourseRequest(selectectedCourse, null));
              adapter.notifyDataSetChanged();
            }
            return true;
          }
        })
        .positiveText("Add Class")
        .negativeText("Cancel")
        .neutralText("Choose Professor")
        .onNeutral(new MaterialDialog.SingleButtonCallback() {
          @Override
          public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
            if (!courseList.isEmpty() && !student.getCoursesList().contains(courseList.get(dialog.getSelectedIndex()))) {
              showProfessorChooser(courseList.get(dialog.getSelectedIndex()));
            }
          }
        })
        .show();
  }

  private void showProfessorChooser(final Course course) {
    Integer[] preSelected = new Integer[course.getProfessors().size()];
    for (int i = 0; i < course.getProfessors().size(); i++) {
      preSelected[i] = i;
    }
    final List<String> selectedProfList = new ArrayList<>();
    new MaterialDialog.Builder(getContext()).title("Class Chooser")
        .items(course.getProfessors())
        .itemsCallbackMultiChoice(preSelected, new MaterialDialog.ListCallbackMultiChoice() {
          @Override
          public boolean onSelection(MaterialDialog dialog, Integer[] which, CharSequence[] text) {
            if (which.length == 0) {
              Boast.makeText(getContext(),
                  "Course was not added because you did not select a professor", Toast.LENGTH_LONG)
                  .show();
              return false;
            }
            for (int x : which) {
              selectedProfList.add(course.getProfessors().get(x));
            }
            student.addCourse(course);
            student.addCourseRequest(new CourseRequest(course,
                selectedProfList.toArray(new String[selectedProfList.size()])));
            adapter.notifyDataSetChanged();
            return true;
          }
        })
        .positiveText("Add Class")
        .negativeText("Cancel")
        .show();
  }

  @OnClick(R.id.addClass) void onAddClassClicked() {
    if (student.getCoursesList().size() >= 10) {
      Boast.makeText(getContext(), "You cannot have more than 10 classes.", Toast.LENGTH_LONG)
          .show();
    } else {
      showCategoryChooser();
    }
  }

  @OnClick(R.id.addActivity) void onAddActivityClicked() {
    Boast.makeText(getContext(), "Clicked Add Activity.", Toast.LENGTH_SHORT).show();
  }
}



