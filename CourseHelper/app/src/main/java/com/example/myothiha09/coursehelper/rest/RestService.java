package com.example.myothiha09.coursehelper.rest;

/**
 * Created by Myo Thiha on 3/29/2018.
 */


import android.bluetooth.BluetoothClass;

import com.example.myothiha09.coursehelper.model.Course;
import com.example.myothiha09.coursehelper.temp.TempCourse;
import com.example.myothiha09.coursehelper.temp.TempMajor;
import com.example.myothiha09.coursehelper.temp.TempPeriod;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * A list of necessary endpoints
 */

public interface RestService {
    @GET("/gatech/terms/201808/majors") Call<List<TempMajor>> listMajors();
    @GET("/gatech/terms/201808/majors/{majors}/courses") Call<List<TempCourse>> listCoursesForMajor(@Path("majors") String major);
    @GET("/gatech/terms/201808/majors/{majors}/courses/{courseID}/sections") Call<List<TempPeriod>> listSectionsForCourse(@Path("majors") String major, @Path("courseID") String id);
}