package com.example.myothiha09.coursehelper.model;

/**
 * Created by Myo on 5/18/2017.
 */

public class Time implements Comparable<Time> {
    private int hour;
    private int minute;

    public Time(int hour, int minute) {
        this.hour = hour;
        this.minute = minute;
    }

    public int getHour() {
        return hour;
    }
    public String toString() {
        if (minute/10 != 0)
        return hour + ": "+ minute;

        return hour + ": 0" + minute;
    }

    public int getMinute() {
        return minute;
    }

    @Override
    public int compareTo(Time other) {
        if (hour != other.hour) {
            return hour - other.hour;
        } else {
            return minute - other.minute;
        }
    }
}
