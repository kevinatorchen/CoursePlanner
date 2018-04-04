package com.example.myothiha09.coursehelper.temp;

/**
 * Created by Myo Thiha on 4/3/2018.
 */

public class Instructor {
    public String fname;
    public String lname;

    @Override
    public String toString() {
        if (fname == null && lname == null) {
            return "No prof assigned";
        }
        if (fname == null) {
            return lname;
        }
        if (lname == null) {
            return fname;
        }
        return lname + ", " + fname;
    }
}