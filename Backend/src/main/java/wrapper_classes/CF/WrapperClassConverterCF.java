package wrapper_classes.CF;

import model.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Kevin on 5/28/2018.
 */
public class WrapperClassConverterCF {

    public static Map<String, Integer> dayToShift;

    private static int alreadyExistingMeetingTime(List<MeetingTime> meetingTimes, TimeSlotCF timeSlotCF) {
        for (int i = 0; i < meetingTimes.size(); i++) {
            MeetingTime meetingTime = meetingTimes.get(i);
            Time timeSlotCFStartTime = new Time(timeSlotCF.getStart_time() / 60, timeSlotCF.getStart_time() % 60);
            Time timeSlotCFEndTime = new Time(timeSlotCF.getEnd_time() / 60, timeSlotCF.getEnd_time() % 60);
            if (meetingTime.getStartTime().equals(timeSlotCFStartTime)
                    && meetingTime.getEndTime().equals(timeSlotCFEndTime)) {
                return i;
            }
        }
        return -1;
    }

    private static MeetingTime[] convertTimeslotsToMeetingTimes(List<TimeSlotCF> timeSlotsCF) {
        List<MeetingTime> meetingTimes = new ArrayList<>();
        for (TimeSlotCF timeSlotCF: timeSlotsCF) {
            int existingMeetingTime = alreadyExistingMeetingTime(meetingTimes, timeSlotCF);
            if (existingMeetingTime >= 0) {
                meetingTimes.get(existingMeetingTime).addDay(dayToShift.get(timeSlotCF.getDay()));
            } else {
                meetingTimes.add(new MeetingTime(1 << dayToShift.get(timeSlotCF.getDay()),
                        new Interval(new Time(timeSlotCF.getStart_time() / 60, timeSlotCF.getStart_time() % 60),
                                new Time(timeSlotCF.getEnd_time() / 60, timeSlotCF.getEnd_time() % 60))));
            }
        }
        MeetingTime[] arrayMeetingTimes = new MeetingTime[meetingTimes.size()];
        for (int i = 0; i < meetingTimes.size(); i++) {
            arrayMeetingTimes[i] = meetingTimes.get(i);
        }
        return arrayMeetingTimes;
    }

    public static List<CommitmentRequest> convertRequest(RequestWrapperCF request) {
        if (dayToShift == null) {
            dayToShift.put("M", 0);
            dayToShift.put("T", 1);
            dayToShift.put("W", 2);
            dayToShift.put("R", 3);
            dayToShift.put("F", 4);
        }

        List<CommitmentRequest> commitmentRequests = new ArrayList<>();
        for (CourseCF requestedCourseCF: request.getCourseRequests()) {
            CourseSection[] courseSections = new CourseSection[requestedCourseCF.getSectionData().size()];
            List<CourseSectionCF> sectionDataCF = requestedCourseCF.getSectionData();
            for (int i = 0; i < courseSections.length; i++) {
                CourseSectionCF currentCourseSectionCF = sectionDataCF.get(i);
                courseSections[i] = new CourseSection(
                        currentCourseSectionCF.getIdent(),
                        convertTimeslotsToMeetingTimes(currentCourseSectionCF.getTimeslots()),
                        currentCourseSectionCF.getCall_number(),
                        currentCourseSectionCF.getInstructor().toString());
            }


            CommitmentRequest currentRequest = new CommitmentRequest(
                    new SingleCourse(requestedCourseCF.getCourseName(), courseSections));
            commitmentRequests.add(currentRequest);

        }
        return commitmentRequests;
    }
}
