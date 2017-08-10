package com.hmmelton.workouttracker

import android.support.v7.app.AppCompatActivity
import android.os.Bundle

import butterknife.ButterKnife

class AddExerciseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_exercise)
        // Init Butter Knife
        ButterKnife.bind(this)
    }
}
