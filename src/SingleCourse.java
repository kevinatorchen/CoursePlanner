import java.util.ArrayList;

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

    @Override
    public Section[] getSections(String[] professors) {
        if (professors == null || professors.equals("")) {
            return getSections();
        }

        ArrayList<Section> matchingSections = new ArrayList<>();
        for (Section current: sections) {
            if (contains(professors, current.getProf())) {
                matchingSections.add(current);
            }
        }
        return matchingSections.toArray(new Section[matchingSections.size()]);
    }

    public int numberOfSections() {
        return sections.length;
    }

    @Override
    public int compareTo(Course o) {
        return numberOfSections() - o.numberOfSections();
    }
}
