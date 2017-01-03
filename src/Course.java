/**
 * Created by Kevin on 1/3/2017.
 */
public class Course implements Comparable<Course> {
    private String name;
    private Section[] sections;

    public Course(String name, Section[] sections) {
        this.name = name;
        this.sections = sections;
    }

    public String getName() {
        return name;
    }

    public Section[] getSections() {
        return sections;
    }

    @Override
    public int compareTo(Course o) {
        return sections.length - o.sections.length;
    }
}
