package com.hmmelton.workouttracker.data

import android.content.ContentValues
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.provider.BaseColumns

/**
 * Created by harrisonmelton on 7/20/17.
 * This is a utility file for database functions. The methods here handle the CRUD functions of the
 * database set up using the [ExerciseContract].
 */

object DatabaseUtil {

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
     * This method returns the exercise tied to the specific ID passed
     * @param exerciseId ID of exercise to be queried
     * *
     * @param database SQLiteDatabase instance on which to perform the query
     * *
     * @return Cursor containing retrieved exercise data
     */
    fun getExerciseById(exerciseId: Int, database: SQLiteDatabase): Cursor {
        // WHERE clause for IDs
        val whereClause = "${BaseColumns._ID}=?"
        // Specific arg is going to be passed ID
        val whereArgs = arrayOf("$exerciseId")

        // Query and return Cursor of retrieved data
        return database.query(
                ExerciseContract.ExerciseEntry.TABLE_NAME,
                null,
                whereClause,
                whereArgs,
                null,
                null,
                null,
                "LIMIT 1" // Each entry should have its own ID, so only 1 entry should be returned
        )
    }

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

}
