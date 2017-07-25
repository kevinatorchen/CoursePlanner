/**
 * Created by Kevin on 7/20/2017.
 */
public class CourseRequest implements Comparable<CourseRequest> {
    private Course course;
    private String prof[];

    public CourseRequest(Course course) {
        this(course, null);
    }

    public CourseRequest(Course course, String[] prof) {
        this.course = course;
        this.prof = prof;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public String[] getProf() {
        return prof;
    }

    public void setProf(String[] prof) {
        this.prof = prof;
    }

    @Override
    public int compareTo(CourseRequest o) {
        return this.getCourse().compareTo(o.getCourse());
    }
}
