package com.example.myothiha09.coursehelper.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.TreeMap;

/**
 * Created by KChen10 on 7/26/2017.
 */
public class ScheduleSorter {
    public static void sort(ArrayList<Schedule> schedules, GenericComparator comparator) {
        TreeMap<Long, Schedule> scoreToSchedule = new TreeMap<>(Collections.reverseOrder());
        for (Schedule current: schedules) {
            scoreToSchedule.put(comparator.computeScheduleValue(current), current);
        }
        schedules.clear();
        for (Schedule current: scoreToSchedule.values()) {
            schedules.add(current);
        }
    }
}

