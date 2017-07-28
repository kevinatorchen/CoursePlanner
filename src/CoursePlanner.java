import java.util.*;

/**
 * Created by Kevin on 1/3/2017.
 */
public class CoursePlanner {
    private static final int MONDAY = 1;
    private static final int TUESDAY = 2;
    private static final int WEDNESDAY = 4;
    private static final int THURSDAY  = 8;
    private static final int FRIDAY = 16;

    public static void main(String[] args) {
        testCase2();
    }

    public static void testCase2() {
        MeetingTime[] APPH1040AsectionMTs = {new MeetingTime(FRIDAY, new Time(8, 5), new Time(9, 55))};
        CourseSection APPH1040A = new CourseSection("A", APPH1040AsectionMTs, 5234, "Nidhi", "Howey");
        MeetingTime[] APPH1040EsectionMTs = {new MeetingTime(MONDAY + WEDNESDAY, new Time(13, 5), new Time(13, 55))};
        CourseSection APPH1040E = new CourseSection("E", APPH1040EsectionMTs, 5235, "Kevin", "Scheller");
        MeetingTime[] APPH1040LsectionMTs = {new MeetingTime(FRIDAY, new Time(13, 5), new Time(14, 55))};
        CourseSection APPH1040L = new CourseSection("L", APPH1040LsectionMTs, 5236, "Thiha", "CULC");
        CourseSection[] APPH1040Sections = {APPH1040A, APPH1040E, APPH1040L};
        Course APPH1040 = new SingleCourse("APPH 1040", APPH1040Sections);
        for (CourseSection currentSection: APPH1040Sections) {
            currentSection.setCommitment(APPH1040);
        }

        MeetingTime[] ENGL1102ASectionMTs = {new MeetingTime(MONDAY + WEDNESDAY + FRIDAY, new Time(8, 5), new Time(8, 55))};
        MeetingTime[] ENGL1102BSectionMTs = {new MeetingTime(TUESDAY + THURSDAY, new Time(9, 35), new Time (10, 55))};
        MeetingTime[] ENGL1102CSectionMTs = {new MeetingTime(MONDAY + WEDNESDAY + FRIDAY, new Time(12, 5), new Time(12, 55))};
        CourseSection ENGL1102A = new CourseSection("A", ENGL1102ASectionMTs, 5512, "Kevin", "IC");
        CourseSection ENGL1102B = new CourseSection("B", ENGL1102BSectionMTs, 5513, "Kevin", "IC");
        CourseSection ENGL1102C = new CourseSection("C", ENGL1102CSectionMTs, 5514, "Thiha", "IC");
        CourseSection[] ENGL1102Sections = {ENGL1102A, ENGL1102B, ENGL1102C};
        Course ENGL1102 = new SingleCourse("ENGL 1102", ENGL1102Sections);
        for (CourseSection currentSection: ENGL1102Sections) {
            currentSection.setCommitment(ENGL1102);
        }

        MeetingTime[] PHYS2211ASectionMTs = {new MeetingTime(MONDAY + WEDNESDAY + FRIDAY, new Time(8, 5), new Time(8, 55))};
        MeetingTime[] PHYS2211A01SectionMTs = {new MeetingTime(MONDAY, new Time(12, 5), new Time(12, 55)),
                new MeetingTime(WEDNESDAY, new Time(10, 5), new Time(11, 55))};
        MeetingTime[] PHYS2211A02SectionMTs = {new MeetingTime(MONDAY, new Time(18, 5), new Time(18, 55)),
                new MeetingTime(WEDNESDAY, new Time(12, 5), new Time(13, 55))};
        CourseSection PHYS2211AMainSection = new CourseSection("A", PHYS2211ASectionMTs);
        CourseSection PHYS2211A01 = new CourseSection("A01", PHYS2211A01SectionMTs, 8921, "Nidhi", "Howey");
        CourseSection PHYS2211A02 = new CourseSection("A02", PHYS2211A02SectionMTs, 8922, "Nidhi", "Howey");
        CourseSection[] PHYS2211ASubsections = {PHYS2211A01, PHYS2211A02};
        DoubleSection PHYS2211A = new DoubleSection(PHYS2211AMainSection, PHYS2211ASubsections);
        MeetingTime[] PHYS2211BSectionMTs = {new MeetingTime(TUESDAY + THURSDAY, new Time(9, 5), new Time(10, 35))};
        MeetingTime[] PHYS2211B01SectionMTs = {new MeetingTime(MONDAY, new Time(12, 35), new Time(13, 25)),
                new MeetingTime(WEDNESDAY, new Time(12, 35), new Time(1, 25))};
        MeetingTime[] PHYS2211B02SectionMTs = {new MeetingTime(TUESDAY, new Time(11, 5), new Time(11, 55)),
                new MeetingTime(THURSDAY, new Time(1, 5), new Time(1, 55))};
        CourseSection PHYS2211BMainSection = new CourseSection("B", PHYS2211BSectionMTs);
        CourseSection PHYS2211B01 = new CourseSection("B01", PHYS2211B01SectionMTs, 8923, "Kevin", "Howey");
        CourseSection PHYS2211B02 = new CourseSection("B02", PHYS2211B02SectionMTs, 8924, "Kevin", "Howey");
        CourseSection[] PHYS2211BSubsections = {PHYS2211B01, PHYS2211B02};
        DoubleSection PHYS2211B = new DoubleSection(PHYS2211BMainSection, PHYS2211BSubsections);
        DoubleSection[] PHYS2211Sections = {PHYS2211A, PHYS2211B};
        DoubleCourse PHYS2211 = new DoubleCourse("PHYS 2211", PHYS2211Sections);
        for (DoubleSection currentDoubleSection: PHYS2211Sections) {
            currentDoubleSection.getMainSection().setCommitment(PHYS2211);
            for (CourseSection currentSubSection: currentDoubleSection.getSubSections()) {
                currentSubSection.setCommitment(PHYS2211);
            }
        }
        for (CourseSection currentSingleSection: PHYS2211.getSections()) {
            currentSingleSection.setCommitment(PHYS2211);
        }

        MeetingTime[] gitmadMTs = {new MeetingTime(MONDAY + TUESDAY, new Time(12, 35), new Time(13, 25))};
        ActivitySection[] gitMadSections = {new ActivitySection("GITMAD", gitmadMTs, "Klaus 2443")};
        Activity gitmad = new Activity("GITMAD", gitMadSections);

        List<CommitmentRequest> commitmentRequests = new ArrayList<>();
        String[] APPH1040Professors = {"Thiha", "Kevin"};
        commitmentRequests.add(new CommitmentRequest(APPH1040, APPH1040Professors));
        //String[] ENGL1102Professors = {"Kevin", "Nidhi"};
        String[] ENGL1102Professors = {"Nidhi"};
        commitmentRequests.add(new CommitmentRequest(ENGL1102, ENGL1102Professors));
        String[] PHYS2211Professors = {"Nidhi"};
        commitmentRequests.add(new CommitmentRequest(PHYS2211, PHYS2211Professors));

        planAlternatives(commitmentRequests, 1);

    }

    public static void testCase1() {

        MeetingTime[] APPH1040AsectionMTs = {new MeetingTime(5, new Time(8, 5), new Time(9, 55))};
        CourseSection APPH1040A = new CourseSection("A", APPH1040AsectionMTs);
        MeetingTime[] APPH1040BsectionMTs = {new MeetingTime(1, new Time(13, 5), new Time(13, 55)), new MeetingTime(3, new Time(13, 5), new Time(13, 55))};
        CourseSection APPH1040B = new CourseSection("B", APPH1040BsectionMTs);
        MeetingTime[] APPH1040CsectionMTs = {new MeetingTime(5, new Time(13, 5), new Time(14, 55))};
        CourseSection APPH1040C = new CourseSection("C", APPH1040CsectionMTs);
        MeetingTime[] APPH1040DsectionMTs = {new MeetingTime(1, new Time(14, 5), new Time(14, 55)), new MeetingTime(3, new Time(14, 5), new Time(14, 55))};
        CourseSection APPH1040D = new CourseSection("D", APPH1040DsectionMTs);
        CourseSection[] APPH1040Sections = {APPH1040A, APPH1040B, APPH1040C, APPH1040D};
        Course APPH1040 = new SingleCourse("APPH 1040", APPH1040Sections);
        for (CourseSection currentSection: APPH1040Sections) {
            currentSection.setCommitment(APPH1040);
        }

        MeetingTime[] ENGL1102ASectionMTs = {new MeetingTime(1, new Time(13, 5), new Time (13, 55)), new MeetingTime(3, new Time(13, 5), new Time (13, 55)), new MeetingTime(5, new Time(13, 5), new Time (13, 55))};
        CourseSection ENGL1102A = new CourseSection("A", ENGL1102ASectionMTs);
        MeetingTime[] ENGL1102BSectionMTs = {new MeetingTime(2, new Time(15, 35), new Time(15, 05)), new MeetingTime(16, new Time(16, 35), new Time(16, 5))};
        CourseSection ENGL1102B = new CourseSection("B", ENGL1102BSectionMTs);
        CourseSection[] ENGL1102Sections = {ENGL1102A, ENGL1102B};
        Course ENGL1102 = new SingleCourse("ENGL 1102", ENGL1102Sections);
        for (CourseSection currentSection: ENGL1102Sections) {
            currentSection.setCommitment(ENGL1102);
        }

        Course[] courses = {APPH1040, ENGL1102};

        //planCourses(courses);

    }

    public static void planCourses(List<CommitmentRequest> courseRequests) {
        planCourses(courseRequests, false);
    }

    public static void planCourses(List<CommitmentRequest> courseRequests, boolean ignoreProfessor) {
        Collections.sort(courseRequests);
        Schedule schedule = new Schedule();
        planCourses(courseRequests, 0, schedule, ignoreProfessor);
    }

    public static void planCourses(List<CommitmentRequest> courseRequests, int currentCourse, Schedule schedule, boolean ignoreProfessor) {
        if (currentCourse >= courseRequests.size()) {
            System.out.println(Arrays.toString(schedule.getSchedule().toArray()));
            schedule.generateComparatorValues();
            System.out.println(schedule.getComparatorValues());

        } else {
            CommitmentRequest currentCourseRequest = courseRequests.get(currentCourse);
            Section[] sections = (ignoreProfessor) ?
                    currentCourseRequest.getCommitment().getSections() :
                    currentCourseRequest.getCommitment().getSections(currentCourseRequest.getProf());
            for (Section currentSection: sections) {
                boolean conflicts = false;
                for (Section sectionInSchedule: schedule.getSchedule()) {
                    if (currentSection.conflictsWith(sectionInSchedule)) {
                        conflicts = true;
                        break;
                    }
                }
                if (!conflicts) {
                    schedule.getSchedule().add(currentSection);
                    planCourses(courseRequests, currentCourse + 1, schedule, ignoreProfessor);
                    schedule.getSchedule().remove(schedule.getSchedule().size() - 1);
                }
            }
        }
    }

    //Tries allowing all professors only.
    public static void planAltConservative(List<CommitmentRequest> courseRequests) {
        planAlternatives(courseRequests, 0);
    }

    //Does whatever it can to find alternatives.
    public static void planAltFull(List<CommitmentRequest> courseRequests) {
        planAlternatives(courseRequests, courseRequests.size());
    }

    //Tries allowing all professors and/or dropping "maxDrop" number of commitments.
    public static void planAlternatives(List<CommitmentRequest> courseRequests, int maxDrop) {
        List<ArrayList<CommitmentRequest>> alternativeRequests = CoursePlanner.<CommitmentRequest>powerSet(courseRequests, courseRequests.size() - maxDrop);
        for (ArrayList<CommitmentRequest> currentAlternative: alternativeRequests) {
            planCourses(currentAlternative);
            planCourses(currentAlternative, true);
        }
    }

    public static <T> List<ArrayList<T>> powerSet(List<T> elements, int minSize) {
        List<ArrayList<T>> result = new ArrayList<>();
        result.add(new ArrayList<T>());
        for (T current: elements) {
            int size = result.size();
            for (int i = 0; i < size; i++) {
                ArrayList<T> newSet = (ArrayList<T>) result.get(i).clone();
                newSet.add(current);
                result.add(newSet);
            }
        }
        List<ArrayList<T>> truncated = new ArrayList<>();
        for (ArrayList<T> current: result) {
            if (current.size() >= minSize) {
                truncated.add(current);
            }
        }
        return truncated;
    }
}
