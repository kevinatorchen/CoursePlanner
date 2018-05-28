package model;

public class Greeting {

    private int id;
    private String content;
    private Name name;

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

    public String getName() {
        return name.getFirstName() + " " + name.getLastName();
    }
}
