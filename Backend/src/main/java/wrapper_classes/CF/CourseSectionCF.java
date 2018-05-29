package wrapper_classes.CF;

import java.util.List;

/**
 * Created by Kevin on 5/28/2018.
 */
public class CourseSectionCF {
    private int call_number;
    private int credits;
    private String ident;
    private InstructorCF instructor;
    private List<TimeSlotCF> timeslots;

    public CourseSectionCF() {

    }

    public int getCall_number() {
        return call_number;
    }

    public int getCredits() {
        return credits;
    }

    public String getIdent() {
        return ident;
    }

    public InstructorCF getInstructor() {
        return instructor;
    }

    public List<TimeSlotCF> getTimeslots() {
        return timeslots;
    }
}
