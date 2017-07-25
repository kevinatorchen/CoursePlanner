/**
 * Created by Kevin on 1/3/2017.
 */
public abstract class Course extends Commitment {
    private String longName;

    public Course(String name) {
        super(name);
    }

    public String getLongName() {
        return longName;
    }

    public void setLongName(String longName) {
        this.longName = longName;
    }


    public boolean contains(String[] arr, String professor) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i].equals(professor)) {
                return true;
            }
        }
        return false;
    }

}
