/**
 * Created by Kevin on 5/17/2017.
 */
public class DoubleCourse extends Course {
    private Section[] mainSections;
    private Section[] subSections;

    public DoubleCourse(String name, Section[] mainSections, Section[] subSections);

    @Override
    public Section[] getSections() {
        return new Section[0];
    }

    @Override
    public int compareTo(Course other) {
        return 0;
    }

    @Override
    public int numberOfSections() {
        return 0;
    }
}
