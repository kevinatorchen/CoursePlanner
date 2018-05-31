package wrapper_classes.CF;

import model.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.jar.Pack200;

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
                    && meetingTime.getEndTime().equals(timeSlotCFEndTime)
                    && meetingTime.getLocation().equals(timeSlotCF.getLocation())) {
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
                                new Time(timeSlotCF.getEnd_time() / 60, timeSlotCF.getEnd_time() % 60)),
                        timeSlotCF.getLocation()));
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
            dayToShift = new HashMap<>();
            dayToShift.put("M", 0);
            dayToShift.put("T", 1);
            dayToShift.put("W", 2);
            dayToShift.put("R", 3);
            dayToShift.put("F", 4);
        }

        //Null checks.
        for (CourseCF requestedCourseCF: request.getCourseRequests()) {
            if (requestedCourseCF.getCourseName() == null ||
                    requestedCourseCF.getDesiredProfessors() == null ||
                    requestedCourseCF.getSectionData() == null) {
                throw new IllegalArgumentException("Request missing one or more top level fields");
            }
            List<CourseSectionCF> sectionDataCF = requestedCourseCF.getSectionData();
            for (int i = 0; i < sectionDataCF.size(); i++) {
                CourseSectionCF currentCourseSectionCF = sectionDataCF.get(i);

                if (currentCourseSectionCF.getInstructor() == null) {
                    currentCourseSectionCF.setInstructor(new InstructorCF("To be ", "determined"));
                }
                if (currentCourseSectionCF.getCall_number() == 0 ||
                        currentCourseSectionCF.getCredits() == 0 ||
                        currentCourseSectionCF.getIdent() == null ||
                        currentCourseSectionCF.getTimeslots() == null) {
                    System.out.println("Request missing a necessary field for section data. Discarding section");
                    sectionDataCF.remove(i);
                    i--;
                    continue;
                }
                for (TimeSlotCF timeSlotCF: currentCourseSectionCF.getTimeslots()) {
                    if (timeSlotCF.getDay() == null ||
                            timeSlotCF.getStart_time() == 0 ||
                            timeSlotCF.getEnd_time() == 0) {
                        System.out.println("Request missing a necessary field for time slot. Discarding entire section");
                        sectionDataCF.remove(i);
                        i--;
                        break;

                    }
                    if (timeSlotCF.getLocation() == null) {
                        timeSlotCF.setLocation("TBA");
                    }
                }

            }
        }

        for (ActivityCF activityCF: request.getActivityRequests()) {
            if (activityCF.getName() == null || activityCF.getMeetingTimes() == null) {
                throw new IllegalArgumentException("Activity request has null name or meeting times");
            }
            for (TimeSlotCF timeSlotCF: activityCF.getMeetingTimes()) {
                if (timeSlotCF.getDay() == null) {
                    throw new IllegalArgumentException("Timeslot does not have day.");
                }
                if (timeSlotCF.getEnd_time() <= timeSlotCF.getStart_time()) {
                    throw new IllegalArgumentException("Timeslot end time must be after start time");
                }
            }
        }

        //Actual conversions.
        List<CommitmentRequest> commitmentRequests = new ArrayList<>();
        for (CourseCF requestedCourseCF: request.getCourseRequests()) {
            List<CourseSection> courseSections = new ArrayList<>();
            List<CourseSectionCF> sectionDataCF = requestedCourseCF.getSectionData();
            for (CourseSectionCF currentCourseSectionCF : sectionDataCF) {
                courseSections.add(new CourseSection(
                        currentCourseSectionCF.getIdent(),
                        convertTimeslotsToMeetingTimes(currentCourseSectionCF.getTimeslots()),
                        currentCourseSectionCF.getCall_number(),
                        currentCourseSectionCF.getInstructor().toString()));
            }
            CourseSection[] courseSectionArray = new CourseSection[courseSections.size()];
            courseSectionArray = courseSections.toArray(courseSectionArray);

            CommitmentRequest currentRequest = new CommitmentRequest(
                    new SingleCourse(requestedCourseCF.getCourseName(), courseSectionArray));
            String[] prof = new String[requestedCourseCF.getDesiredProfessors().size()];
            for (int i = 0; i < prof.length; i++) {
                prof[i] = requestedCourseCF.getDesiredProfessors().get(i).toString();
            }
            currentRequest.setProf(prof);
            for (Section section: currentRequest.getCommitment().getSections()) {
                section.setCommitment(currentRequest.getCommitment());
            }
            commitmentRequests.add(currentRequest);

        }

        for (ActivityCF activityCF: request.getActivityRequests()) {
            StudentActivitySection[] studentActivitySections = new StudentActivitySection[1];
            StudentActivitySection section = new StudentActivitySection(
                    "N/A", convertTimeslotsToMeetingTimes(activityCF.getMeetingTimes()));
            studentActivitySections[0] = section;
            CommitmentRequest currentRequest = new CommitmentRequest(
                    new StudentActivity(activityCF.getName(), studentActivitySections));
            currentRequest.getCommitment().getSections()[0].setCommitment(currentRequest.getCommitment());
            commitmentRequests.add(currentRequest);
        }

        return commitmentRequests;
    }
}
