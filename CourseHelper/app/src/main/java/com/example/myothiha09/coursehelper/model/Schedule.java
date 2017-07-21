package com.example.myothiha09.coursehelper.model;

import java.util.ArrayList;

/**
 * Created by Kevin on 7/20/2017.
 */

public class Schedule {
    private ArrayList<Section> schedule;

    public Schedule() {
        schedule = new ArrayList<>();
    }

    public Schedule(ArrayList<Section> schedule) {
        this.schedule = schedule;
    }

    public ArrayList<Section> getSchedule() {
        return schedule;
    }

    public void setSchedule(ArrayList<Section> schedule) {
        this.schedule = schedule;
    }
}