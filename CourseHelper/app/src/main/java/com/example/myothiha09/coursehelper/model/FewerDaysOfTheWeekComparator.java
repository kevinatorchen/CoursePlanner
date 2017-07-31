package com.example.myothiha09.coursehelper.model;

import com.example.myothiha09.coursehelper.model.GenericComparator;

/**
 * Created by KChen10 on 7/31/2017.
 */
public class FewerDaysOfTheWeekComparator extends GenericComparator {

    public FewerDaysOfTheWeekComparator(int totalGaps, int morningMinutes, int daysOfWeek, int noMealTimes, int wrongProfessors, int missingCommitments) {
        super(0, 0, 1, 0, 100, 100);
    }
}
