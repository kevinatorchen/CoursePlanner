package com.example.myothiha09.coursehelper.temp;

import java.util.ArrayList;

/**
 * Created by Myo Thiha on 3/29/2018.
 */

public class TempPeriod {
    int call_number;
    int credits;
    String ident;
    ArrayList<TimeSlot> timeslots;
    Instructor instructor = new Instructor();

    @Override
    public String toString() {
        String result = "Credit Hours " + credits + "\n" + "Instructor " + instructor.fname + "\n";
        if (timeslots.size() != 0) {
            result += "Timeslots:\n";
            result += timeslots.get(0).location + "\n";
            for (TimeSlot x: timeslots) {
                result += "Day " + x.day + ";" + x.start_time + " - " + x.end_time + "\n";
            }
        } else {
            result += "No timeslot";
        }
        return result;
    }
}
class Instructor {
    String fname;
    String lname;
}
class TimeSlot {
    public String location;
    public int start_time;
    public int end_time;
    public String day;
}