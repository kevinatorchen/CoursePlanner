package com.example.myothiha09.coursehelper.model;

/**
 * Created by KChen10 on 7/27/2017.
 */

public abstract class Commitment implements Comparable<Commitment>{
    private String category;
    private String name;

    public Commitment(String name, String category) {
        this.name = name;
        this.category = category;
    }

    public Commitment(String name) {
        this(name, null);
    }

    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public abstract Section[] getSections();

    public abstract Section[] getSections(String[] professors);

    @Override
    public int compareTo(Commitment other) {
        return other.numberOfSections() - this.numberOfSections();
    }

    public abstract int numberOfSections();


    @Override
    public String toString() {
        return name;
    }
}
