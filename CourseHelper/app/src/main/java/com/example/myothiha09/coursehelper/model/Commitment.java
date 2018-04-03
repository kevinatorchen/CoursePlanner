package com.example.myothiha09.coursehelper.model;

/**
 * Created by KChen10 on 7/27/2017.
 */

public abstract class Commitment implements Comparable<Commitment>{
    private String name;

    public Commitment(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
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
