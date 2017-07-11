import java.util.Arrays;

/**
 * Created by Kevin on 7/2/2017.
 */
public class DoubleSection {
    private Section mainSection;
    private Section[] subSections;

    public DoubleSection(Section mainSection, Section[] subSections) {
        this.mainSection = mainSection;
        this.subSections = subSections;
    }

    public Section getMainSection() {
        return mainSection;
    }

    public void setMainSection(Section mainSection) {
        this.mainSection = mainSection;
    }

    public Section[] getSubSections() {
        return subSections;
    }

    public void setSubSections(Section[] subSections) {
        this.subSections = subSections;
    }

    @Override
    public String toString() {
        return "DoubleSection{" +
                "mainSection=" + mainSection +
                ", subSections=" + Arrays.toString(subSections) +
                '}';
    }
}
