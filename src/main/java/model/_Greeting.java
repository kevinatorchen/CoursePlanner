package model;

import java.util.List;

public class _Greeting {

    private int id;
    private String content;
    private List<_Name> names;

    public _Greeting() {

    }

    public _Greeting(int id, String content) {
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
        for (_Name name: names) {
            builder.append(name.getFirstName() + " " + name.getLastName() + "\n");
        }
        return builder.toString();
    }
}
