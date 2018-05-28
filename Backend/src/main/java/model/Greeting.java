package model;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Greeting {

    private int id;
    private String content;
    private List<Name> names;

    public Greeting() {

    }

    public Greeting(int id, String content) {
        this.id = id;
        this.content = content;
    }

    public int getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public String getNames() {
        StringBuilder builder = new StringBuilder();
        for (Name name: names) {
            builder.append(name.getFirstName() + " " + name.getLastName() + "\n");
        }
        return builder.toString();
    }
}
