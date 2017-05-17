/**
 * Created by Kevin on 1/3/2017.
 */
public abstract class Course implements Comparable<Course> {
    private String name;

    public Course(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public abstract Section[] getSections();

    @Override
    public abstract int compareTo(Course other);

    public abstract int numberOfSections();
}
