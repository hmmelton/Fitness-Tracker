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

    /**
     * This class represents an entry in the Workouts table.
     */
    class WorkoutEntry : BaseColumns {
        companion object {
            val TABLE_NAME = "workouts"
            val COLUMN_NAME = "workoutName"
            val COLUMN_TIMESTAMP = "timestamp"
        }
    }

    /**
     * This class represents an entry in the History table.
     */
    class HistoryEntry : BaseColumns {
        companion object {
            val TABLE_NAME = "history"
            val COLUMN_NAME = "date"
            val COLUMN_TIMESTAMP = "timestamp"
        }
    }

    /**
     * This class represents an entry in the Exercise/Workout junction table.
     */
    class EWJuncEntry : BaseColumns {
        companion object {
            val TABLE_NAME = "workoutsExercises"
            val COLUMN_WORKOUT_ID = "workoutId"
            val COLUMN_EXERCISE_ID = "exerciseId"
            val COLUMN_TIMESTAMP = "timestamp"
        }
    }

    /**
     * This class represents an entry in the History/Exercise junction table.
     */
    class HEJuncEntry : BaseColumns {
        companion object {
            val TABLE_NAME = "historyExercises"
            val COLUMN_HISTORY_ID = "historyId"
            val COLUMN_EXERCISE_ID = "exerciseId"
            val COLUMN_TIMESTAMP = "timestamp"
        }
    }
}
