package wrapper_classes.CF;

import java.util.List;

/**
 * Created by Kevin on 5/28/2018.
 */
public class CourseCF {
    private String courseName;
    private List<CourseSectionCF> sectionData;

    public CourseCF() {

    }

    public String getCourseName() {
        return courseName;
    }

    public List<CourseSectionCF> getSectionData() {
        return sectionData;
    }

}
