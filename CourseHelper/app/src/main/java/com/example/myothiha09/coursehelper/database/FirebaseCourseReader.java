package com.example.myothiha09.coursehelper.database;

import android.util.Log;

import com.example.myothiha09.coursehelper.model.Course;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

/**
 * Created by Kevin on 3/29/2018.
 */



public class FirebaseCourseReader {
    public static void populateCourses(HashMap<String, ArrayList<Course>> allCourseData) {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("Courses/CS 1332");

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                HashMap value = dataSnapshot.getValue(HashMap.class);
                Log.d("Read", "Value is: " + Arrays.toString(value.keySet().toArray()));
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("Read Cancelled", "Failed to read value.", error.toException());
            }
        });
    }
}
