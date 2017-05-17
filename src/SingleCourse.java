/**
 * Created by Kevin on 5/17/2017.
 */
public class SingleCourse extends Course {
    private Section[] sections;

    public SingleCourse(String name, Section[] sections) {
        super(name);
        this.sections = sections;
    }

    public Section[] getSections() {
        return sections;
    }

    public int numberOfSections() {
        return sections.length;
    }

    @Override
    public int compareTo(Course o) {
        return numberOfSections() - o.numberOfSections();
    }
}
