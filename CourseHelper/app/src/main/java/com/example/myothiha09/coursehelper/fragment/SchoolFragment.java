package com.example.myothiha09.coursehelper.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myothiha09.coursehelper.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class SchoolFragment extends Fragment {



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.school_screen, container, false);
        return view;
    }

}
