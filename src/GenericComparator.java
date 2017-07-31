import java.util.Comparator;

/**
 * Created by KChen10 on 7/26/2017.
 */
public class GenericComparator {
    //Weights to factor in
    private static final int MAX_SCORE = 100000;

    private int totalGaps;
    private int morningMinutes;
    private int daysOfWeek;
    private int noMealTimes;
    private int wrongProfessors;
    private int missingCommitments;

    public GenericComparator(int totalGaps, int morningMinutes,  int daysOfWeek, int noMealTimes, int wrongProfessors, int missingCommitments) {
        if (Math.abs(totalGaps) > 100 || Math.abs(morningMinutes) > 100 || Math.abs(noMealTimes) > 100
                || Math.abs(daysOfWeek) > 100 || Math.abs(wrongProfessors) > 100 || Math.abs(missingCommitments) > 100) {
            throw new IllegalArgumentException("Weights must be between -100 and 100, inclusive");
        }
        this.totalGaps = totalGaps;
        this.morningMinutes = morningMinutes;
        this.daysOfWeek = daysOfWeek;
        this.noMealTimes = noMealTimes;
        this.wrongProfessors = wrongProfessors;
        this.missingCommitments = missingCommitments;
    }

    public long computeScheduleValue(Schedule schedule) {
        ComparatorValues values = schedule.getComparatorValues();
        long gapScore = limitScore(totalGaps * values.getTotalGaps());
        long morningScore = limitScore(morningMinutes * values.getMorningMinutes() * 3);
        long daysOfWeekScore = daysOfWeek * values.getDaysOfWeek() * 10000l;
        long mealScore = noMealTimes * values.getNoMealTime() * 10000l;
        long professorScore = wrongProfessors * values.getWrongProfessors() * 5000l;
        long commitmentScore = missingCommitments * values.getMissingCommitments() * 100000l;
        return gapScore + morningScore + mealScore + daysOfWeekScore + professorScore + commitmentScore;
    }

    public int limitScore(int value) {
        if (value > MAX_SCORE) {
            value = MAX_SCORE;
        }
        if (value < MAX_SCORE * -1) {
            value = MAX_SCORE * -1;
        }
        return value;
    }
}
