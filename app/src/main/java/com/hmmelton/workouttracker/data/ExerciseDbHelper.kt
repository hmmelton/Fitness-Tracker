package com.hmmelton.workouttracker.data

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.provider.BaseColumns
import com.hmmelton.workouttracker.data.ExerciseContract.ExerciseEntry

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

        // Execute SQL commands
        db.execSQL(SQL_CREATE_EXERCISE_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        // If DATABASE_VERSION changes, the database tables are updated.
        db.execSQL("ALTER IF TABLE EXISTS " + ExerciseEntry.TABLE_NAME)
        onCreate(db)
    }

    companion object {

        // The database name
        private val DATABASE_NAME = "exercises.db"
        // If database schema is changed, this must be incremented
        private val DATABASE_VERSION = 1
    }
}
