import java.util.ArrayList;
import java.util.List;

/**
 * Created by KChen10 on 7/31/2017.
 */
public class Filter {
    public static ArrayList<Schedule> filterMorningClasses(List<Schedule> schedule) {
        ArrayList<Schedule> filteredSchedules = new ArrayList<>();
        for (Schedule currentSchedule: schedule) {
            if (currentSchedule.getComparatorValues().getMorningMinutes() == 0) {
                filteredSchedules.add(currentSchedule);
            }
        }
        return filteredSchedules;
    }

    public static ArrayList<Schedule> filterNoMeals(List<Schedule> schedule) {
        ArrayList<Schedule> filteredSchedules = new ArrayList<>();
        for (Schedule currentSchedule: schedule) {
            if (currentSchedule.getComparatorValues().getNoMealTime() == 0) {
                filteredSchedules.add(currentSchedule);
            }
        }
        return filteredSchedules;
    }
}
