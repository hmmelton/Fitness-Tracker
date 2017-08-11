package com.hmmelton.workouttracker.data

import android.provider.BaseColumns

/**
 * Created by harrisonmelton on 7/18/17.
 * This is a contract for saving exercises to a local SQLite database.
 */

object ExerciseContract {

    /**
     * This class represents an entry in the Exercises table.
     */
    object ExerciseEntry : BaseColumns {
        val TABLE_NAME = "exercises"
        val COLUMN_NAME = "exerciseName"
        val COLUMN_SET_TYPE = "setType"
        val COLUMN_REP_COUNT = "repCount"
        val COLUMN_REP_INTENSITY = "repIntensity"
        val COLUMN_TIMESTAMP = "timestamp"
    }
}
