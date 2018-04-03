package com.example.myothiha09.coursehelper.temp;

import java.util.List;

/**
 * Created by Myo Thiha on 3/29/2018.
 */

public class TempCourse {
    public String ident;
    public String name;
    public List<TempPeriod> periods;

    @Override
    public String toString() {
        return ident + " : " + name;
    }
}
