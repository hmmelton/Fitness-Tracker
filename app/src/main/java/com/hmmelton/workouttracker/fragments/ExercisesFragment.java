package com.hmmelton.workouttracker.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hmmelton.workouttracker.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ExercisesFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ExercisesFragment extends Fragment {

    public ExercisesFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment.
     * @return A new instance of fragment ExercisesFragment.
     */
    public static ExercisesFragment newInstance() {
        return new ExercisesFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_exercises, container, false);
    }
}
