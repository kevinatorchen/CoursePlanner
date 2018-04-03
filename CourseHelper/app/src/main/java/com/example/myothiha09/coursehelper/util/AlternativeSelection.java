package com.example.myothiha09.coursehelper.util;

/**
 * Created by Kevin on 8/1/2017.
 */

public enum AlternativeSelection {
    NONE(0),
    PROFONLY(1),
    SOMEALT(2),
    ANYALT(3);

    private final int selection;

    AlternativeSelection(int selection) {
        this.selection = selection;
    }
}
