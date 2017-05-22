package com.example.myothiha09.coursehelper.controller;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myothiha09.coursehelper.R;
import com.example.myothiha09.coursehelper.model.Section;

import java.util.ArrayList;

/**
 * Created by Myo on 5/22/2017.
 */

public class ScheduleOverviewRecycler extends Fragment{
    static ArrayList<Section> current = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.schedule_overview, container, false);
        return view;
    }

    public static ArrayList<Section> getCurrent() {
        return current;
    }
}
