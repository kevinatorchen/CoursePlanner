package model;

/**
 * Created by KChen10 on 7/25/2017.
 */
public class ComparatorValues {
    private int totalGaps;
    private int morningMinutes; // 8 - 8:59 am is doubled, 9 - 9:59 am is normal.
    private int daysOfWeek;
    private int noMealTime; //Lunch = 11 am - 2 pm, Dinner = 5 am - 8 pm
    private int wrongProfessors; // Used for creating alternative schedules with different professors.
    private int missingCommitments; //Used for creating alternative schedules with certain dropped commitments.

    public ComparatorValues(int totalGaps, int morningMinutes, int daysOfWeek, int noMealTime, int wrongProfessors, int missingCommitments) {
        this.totalGaps = totalGaps;
        this.morningMinutes = morningMinutes;
        this.daysOfWeek = daysOfWeek;
        this.noMealTime = noMealTime;
        this.wrongProfessors = wrongProfessors;
        this.missingCommitments = missingCommitments;
    }

    public int getTotalGaps() {
        return totalGaps;
    }

    public void setTotalGaps(int totalGaps) {
        this.totalGaps = totalGaps;
    }

    public int getMorningMinutes() {
        return morningMinutes;
    }

    public void setMorningMinutes(int morningMinutes) {
        this.morningMinutes = morningMinutes;
    }

    public int getDaysOfWeek() {
        return daysOfWeek;
    }

    public void setDaysOfWeek(int daysOfWeek) {
        this.daysOfWeek = daysOfWeek;
    }

    public int getNoMealTime() {
        return noMealTime;
    }

    public void setNoMealTime(int noMealTime) {
        this.noMealTime = noMealTime;
    }

    public int getWrongProfessors() {
        return wrongProfessors;
    }

    public void setWrongProfessors(int wrongProfessors) {
        this.wrongProfessors = wrongProfessors;
    }

    public int getMissingCommitments() {
        return missingCommitments;
    }

    public void setMissingCommitments(int missingCommitments) {
        this.missingCommitments = missingCommitments;
    }

    @Override
    public String toString() {
        return "ComparatorValues{" +
                "totalGaps=" + totalGaps +
                ", morningMinutes=" + morningMinutes +
                ", daysOfWeek=" + daysOfWeek +
                ", noMealTime=" + noMealTime +
                ", wrongProfessors=" + wrongProfessors +
                ", missingCommitments=" + missingCommitments +
                '}';
    }

}

