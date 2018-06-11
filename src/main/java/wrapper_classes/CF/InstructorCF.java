package wrapper_classes.CF;

/**
 * Created by Kevin on 5/28/2018.
 */
public class InstructorCF {
    private String fname;
    private String lname;

    public InstructorCF() {

    }

    public InstructorCF(String fname, String lname) {
        this.fname = fname;
        this.lname = lname;
    }

    public String getFname() {
        return fname;
    }

    public String getLname() {
        return lname;
    }

    @Override
    public String toString() {
        return fname + lname;
    }
}
