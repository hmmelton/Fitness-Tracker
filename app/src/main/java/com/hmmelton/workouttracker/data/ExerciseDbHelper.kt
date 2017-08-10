package com.hmmelton.workouttracker.data

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.provider.BaseColumns
import com.hmmelton.workouttracker.data.ExerciseContract.ExerciseEntry
import com.hmmelton.workouttracker.data.ExerciseContract.WorkoutEntry
import com.hmmelton.workouttracker.data.ExerciseContract.HistoryEntry
import com.hmmelton.workouttracker.data.ExerciseContract.EWJuncEntry
import com.hmmelton.workouttracker.data.ExerciseContract.HEJuncEntry

/**
 * Created by harrisonmelton on 7/18/17.
 * This is a helper class for the Exercise database.
 */

class ExerciseDbHelper// Constructor
(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    override fun onCreate(db: SQLiteDatabase) {
        // String to create Exercise table
        val SQL_CREATE_EXERCISE_TABLE = "CREATE TABLE " + ExerciseEntry.TABLE_NAME + " (" +
                BaseColumns._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                ExerciseEntry.COLUMN_NAME + " TEXT NOT NULL, " +
                ExerciseEntry.COLUMN_SET_TYPE + " TEXT NOT NULL, " +
                ExerciseEntry.COLUMN_REP_COUNT + " INTEGER NOT NULL, " +
                ExerciseEntry.COLUMN_REP_INTENSITY + " REAL NOT NULL, " +
                ExerciseEntry.COLUMN_TIMESTAMP + " TIMESTAMP DEFAULT CURRENT_TIMESTAMP" +
                "); "

        // String to create Workout table
        val SQL_CREATE_WORKOUT_TABLE = "CREATE TABLE " + WorkoutEntry.TABLE_NAME + " (" +
                BaseColumns._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                WorkoutEntry.COLUMN_NAME + " TEXT NOT NULL, " +
                WorkoutEntry.COLUMN_TIMESTAMP + " TIMESTAMP DEFAULT CURRENT_TIMESTAMP" +
                "); "

        // String to create History table
        val SQL_CREATE_HISTORY_TABLE = "CREATE TABLE " + HistoryEntry.TABLE_NAME + " (" +
                BaseColumns._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                HistoryEntry.COLUMN_NAME + " TEXT NOT NULL, " +
                HistoryEntry.COLUMN_TIMESTAMP + " TIMESTAMP DEFAULT CURRENT_TIMESTAMP" +
                "); "

        // String to create Exercise/Workout junction table
        val SQL_CREATE_EXERCISE_WORKOUT_JUNCTION_TABLE = "CREATE TABLE " +
                EWJuncEntry.TABLE_NAME + " (" +
                BaseColumns._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                EWJuncEntry.COLUMN_EXERCISE_ID + " INTEGER NOT NULL, " +
                EWJuncEntry.COLUMN_WORKOUT_ID + " INTEGER NOT NULL, " +
                EWJuncEntry.COLUMN_TIMESTAMP +
                " TIMESTAMP DEFAULT CURRENT_TIMESTAMP, " +
                "FOREIGN KEY (" + EWJuncEntry.COLUMN_EXERCISE_ID +
                ") REFERENCES " + ExerciseEntry.TABLE_NAME + "(" + BaseColumns._ID + "), " +
                "FOREIGN KEY (" + EWJuncEntry.COLUMN_WORKOUT_ID +
                ") REFERENCES " + WorkoutEntry.TABLE_NAME + "(" + BaseColumns._ID + "));"

        // String to create History/Exercise junction table
        val SQL_CREATE_HISTORY_EXERCISE_JUNCTION_TABLE = "CREATE TABLE " +
                HEJuncEntry.TABLE_NAME + " (" +
                BaseColumns._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                HEJuncEntry.COLUMN_HISTORY_ID + " INTEGER NOT NULL, " +
                HEJuncEntry.COLUMN_EXERCISE_ID + " INTEGER NOT NULL, " +
                HEJuncEntry.COLUMN_TIMESTAMP +
                " TIMESTAMP DEFAULT CURRENT_TIMESTAMP, " +
                "FOREIGN KEY (" + HEJuncEntry.COLUMN_HISTORY_ID +
                ") REFERENCES " + HistoryEntry.TABLE_NAME + "(" + BaseColumns._ID + "), " +
                "FOREIGN KEY (" + HEJuncEntry.COLUMN_EXERCISE_ID +
                ") REFERENCES " + ExerciseEntry.TABLE_NAME + "(" + BaseColumns._ID + "));"

        // Execute SQL commands
        db.execSQL(SQL_CREATE_EXERCISE_TABLE)
        db.execSQL(SQL_CREATE_WORKOUT_TABLE)
        db.execSQL(SQL_CREATE_HISTORY_TABLE)
        db.execSQL(SQL_CREATE_EXERCISE_WORKOUT_JUNCTION_TABLE)
        db.execSQL(SQL_CREATE_HISTORY_EXERCISE_JUNCTION_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        // If DATABASE_VERSION changes, the database tables are updated.
        db.execSQL("ALTER IF TABLE EXISTS " + ExerciseEntry.TABLE_NAME)
        db.execSQL("ALTER IF TABLE EXISTS " + WorkoutEntry.TABLE_NAME)
        db.execSQL("ALTER IF TABLE EXISTS " + HistoryEntry.TABLE_NAME)
        db.execSQL("ALTER IF TABLE EXISTS " + EWJuncEntry.TABLE_NAME)
        db.execSQL("ALTER IF TABLE EXISTS " + HEJuncEntry.TABLE_NAME)
        onCreate(db)
    }

    companion object {

        // The database name
        private val DATABASE_NAME = "exercises.db"
        // If database schema is changed, this must be incremented
        private val DATABASE_VERSION = 1
    }
}
