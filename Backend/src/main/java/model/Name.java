package model;

/**
 * Created by Kevin on 5/27/2018.
 */
public class Name {
    private String firstName;
    private String lastName;

    public Name() {

    }

    public Name(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }
}
