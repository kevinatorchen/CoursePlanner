package com.example.myothiha09.coursehelper.temp;

/**
 * Created by Myo Thiha on 4/3/2018.
 */

public class Instructor {
    public String fname;
    public String lname;

    private boolean equalStrings(String a, String b) {
        return a == null && b == null || !(a == null || b == null) && a.equals(b);
    }

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof Instructor)) {
            return false;
        }
        Instructor otherInstructor = (Instructor) other;
        return equalStrings(this.fname, otherInstructor.fname) && equalStrings(this.lname, otherInstructor.lname);
    }

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