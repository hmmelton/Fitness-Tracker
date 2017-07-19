package com.hmmelton.workouttracker.data;

import android.provider.BaseColumns;

/**
 * Created by harrisonmelton on 7/18/17.
 * This is a contract for saving exercises to a local SQLite database.
 */

public final class ExerciseContract {

    private ExerciseContract() {}

    /**
     * This class represents an entry in the Exercises table.
     */
    public static final class ExerciseEntry implements BaseColumns {
        public static final String TABLE_NAME = "exercises";
        public static final String COLUMN_EXERCISE_NAME = "exerciseName";
        public static final String COLUMN_SET_TYPE = "setType";
        public static final String COLUMN_REP_COUNT = "repCount";
        public static final String COLUMN_REP_INTENSITY = "repIntensity";
        public static final String COLUMN_TIMESTAMP = "timestamp";
    }

    /**
     * This class represents an entry in the Workouts table.
     */
    public static final class WorkoutEntry implements BaseColumns {
        public static final String TABLE_NAME = "workouts";
        public static final String COLUMN_WORKOUT_NAME = "workoutName";
        public static final String COLUMN_TIMESTAMP = "timestamp";
    }

    /**
     * This class represents an entry in the History table.
     */
    public static final class HistoryEntry implements BaseColumns {
        public static final String TABLE_NAME = "history";
        public static final String COLUMN_DATE = "date";
        public static final String COLUMN_TIMESTAMP = "timestamp";
    }

    /**
     * This class represents an entry in the Exercise/Workout junction table.
     */
    public static final class ExerciseWorkoutJunctionEntry implements BaseColumns {
        public static final String TABLE_NAME = "workoutsExercises";
        public static final String COLUMN_WORKOUT_ID = "workoutId";
        public static final String COLUMN_EXERCISE_ID = "exerciseId";
        public static final String COLUMN_TIMESTAMP = "timestamp";
    }

    /**
     * This class represents an entry in the History/Exercise junction table.
     */
    public static final class HistoryExerciseJunctionEntry implements BaseColumns {
        public static final String TABLE_NAME = "historyExercises";
        public static final String COLUMN_HISTORY_ID = "historyId";
        public static final String COLUMN_EXERCISE_ID = "exerciseId";
        public static final String COLUMN_TIMESTAMP = "timestamp";
    }
}
