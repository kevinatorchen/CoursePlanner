/**
 * Created by Kevin on 5/17/2017.
 */
public class DoubleCourse extends Course {
    private DoubleSection[] sections;
    private Section[] individualSections;
    private int numberOfSections;

    public DoubleCourse(String name, DoubleSection[] sections) {
        super(name);
        this.sections = sections;
        generateNumberOfSections();
        generateIndividualSections();
    }

    private void generateIndividualSections() {
        individualSections = new Section[numberOfSections];
        for (DoubleSection currentDouble: sections) {
            for (Section currentSubsection: currentDouble.getSubSections()) {
                String name = currentDouble.getMainSection().getName() + " + " + currentSubsection.getName();
                MeetingTime[] meetingTimes = new MeetingTime[numberOfSections];
            }
        }
    }

    private void generateNumberOfSections() {
        int number = 0;
        for (DoubleSection current: sections) {
            number += current.getSubSections().length;
        }
        numberOfSections = number;
    }

    @Override
    public Section[] getSections() {
        return new Section[0];
    }

    @Override
    public int compareTo(Course other) {
        return this.numberOfSections() - other.numberOfSections();
    }

    @Override
    public int numberOfSections() {
        return numberOfSections;
    }
}
