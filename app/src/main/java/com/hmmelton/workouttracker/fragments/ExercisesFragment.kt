package com.hmmelton.workouttracker.fragments

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.hmmelton.workouttracker.R

/**
 * A simple [Fragment] subclass.
 * Use the [ExercisesFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ExercisesFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater!!.inflate(R.layout.fragment_exercises, container, false)
    }

    companion object {

        /**
         * Use this factory method to create a new instance of
         * this fragment.
         * @return A new instance of fragment ExercisesFragment.
         */
        fun newInstance(): ExercisesFragment {
            return ExercisesFragment()
        }
    }
}// Required empty public constructor
