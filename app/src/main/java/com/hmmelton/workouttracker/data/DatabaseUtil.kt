package com.hmmelton.workouttracker.data

import android.content.ContentValues
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase

/**
 * Created by harrisonmelton on 7/20/17.
 * This is a utility file for database functions. The methods here handle the CRUD functions of the
 * database set up using the [ExerciseContract].
 */

object DatabaseUtil {

    // region Create Functions

    /**
     * This method adds a new exercise to the database.
     * @param exerciseName name of exercise to be added
     * *
     * @param setType set type of exercise to be added (reps, minutes, etc.)
     * *
     * @param repCount rep count of exercise to be added
     * *
     * @param repIntensity rep intensity of exercise to be added (25lbs, 30 minutes, etc.)
     * *
     * @param database SQLiteDatabase instance on which the insertion is performed
     * *
     * @return boolean representing whether or not the insertion was successful
     */
    fun addNewExercise(exerciseName: String, setType: String, repCount: Int,
                       repIntensity: Double, database: SQLiteDatabase): Boolean {
        // Add all key value pairs to ContentValues object
        val cv = ContentValues()
        cv.put(ExerciseContract.ExerciseEntry.COLUMN_NAME, exerciseName)
        cv.put(ExerciseContract.ExerciseEntry.COLUMN_SET_TYPE, setType)
        cv.put(ExerciseContract.ExerciseEntry.COLUMN_REP_COUNT, repCount)
        cv.put(ExerciseContract.ExerciseEntry.COLUMN_REP_INTENSITY, repIntensity)
        // Insert into database and return whether or not it was successful
        return database.insert(ExerciseContract.ExerciseEntry.TABLE_NAME,
                null, cv) > 0
    }

    /**
     * This method adds a new workout to the database.
     * @param workoutName name of workout to be added
     * *
     * @param database SQLiteDatabase instance into which the workout is inserted
     * *
     * @return boolean representing whether or not the insertion was successful
     */
    fun addNewWorkout(workoutName: String, database: SQLiteDatabase): Boolean {
        // Add key/value pair to ContentValues
        val cv = ContentValues()
        cv.put(ExerciseContract.WorkoutEntry.COLUMN_NAME, workoutName)
        // Insert into database and return whether or not it was successful
        return database.insert(ExerciseContract.WorkoutEntry.TABLE_NAME, null, cv) > 0
    }

    /**
     * This method adds a new workout history to the database.
     * @param date date that workout took place
     * *
     * @param database SQLiteDatabase instance into which the workout is inserted
     * *
     * @return boolean representing whether or not the insertion was successful
     */
    fun addNewHistory(date: String, database: SQLiteDatabase): Boolean {
        // Add key/value pair to ContentValues
        val cv = ContentValues()
        cv.put(ExerciseContract.HistoryEntry.COLUMN_NAME, date)
        // Insert into database and return whether or not it was successful
        return database.insert(ExerciseContract.HistoryEntry.TABLE_NAME, null, cv) > 0
    }

    // endregion

    // region Read Functions

    /**
     * This method returns all the exercises in the database.
     * @param database SQLiteDatabase instance on which the query is performed
     * *
     * @return Cursor containing all exercises in database
     */
    fun getAllExercises(database: SQLiteDatabase): Cursor {
        return database.query(
                ExerciseContract.ExerciseEntry.TABLE_NAME, null, null, null, null, null,
                // Sort the returned values chronologically
                ExerciseContract.ExerciseEntry.COLUMN_TIMESTAMP
        )
    }

    /**
     * This method returns all the workouts in the database.
     * @param database SQLiteDatabase instance on which the query is performed
     * *
     * @return Cursor containing all workouts in database
     */
    fun getAllWorkouts(database: SQLiteDatabase): Cursor {
        return database.query(
                ExerciseContract.WorkoutEntry.TABLE_NAME, null, null, null, null, null,
                // Sort the returned values chronologically
                ExerciseContract.WorkoutEntry.COLUMN_TIMESTAMP
        )
    }

    /**
     * This method returns all the history items in the database.
     * @param database SQLiteDatabase instance on which the query is performed
     * *
     * @return Cursor containing all history items in database
     */
    fun getAllHistory(database: SQLiteDatabase): Cursor {
        return database.query(
                ExerciseContract.HistoryEntry.TABLE_NAME, null, null, null, null, null,
                // Sort the returned values chronologically
                ExerciseContract.HistoryEntry.COLUMN_TIMESTAMP
        )
    }

    // endregion

}
