package com.hmmelton.workouttracker.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.hmmelton.workouttracker.data.ExerciseContract.*;

/**
 * Created by harrisonmelton on 7/18/17.
 * This is a helper class for the Exercise database.
 */

public class ExerciseDbHelper extends SQLiteOpenHelper {

    // The database name
    private static final String DATABASE_NAME = "exercises.db";
    // If database schema is changed, this must be incremented
    private static final int DATABASE_VERSION = 1;

    // Constructor
    public ExerciseDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // String to create Exercise table
        final String SQL_CREATE_EXERCISE_TABLE = "CREATE TABLE " + ExerciseEntry.TABLE_NAME + " (" +
                ExerciseEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                ExerciseEntry.COLUMN_EXERCISE_NAME + " TEXT NOT NULL, " +
                ExerciseEntry.COLUMN_SET_TYPE + " TEXT NOT NULL, " +
                ExerciseEntry.COLUMN_REP_COUNT + " TEXT NOT NULL, " +
                ExerciseEntry.COLUMN_REP_INTENSITY + " REAL NOT NULL, " +
                ExerciseEntry.COLUMN_TIMESTAMP + " TIMESTAMP DEFAULT CURRENT_TIMESTAMP" +
                "); ";

        // String to create Workout table
        final String SQL_CREATE_WORKOUT_TABLE = "CREATE TABLE " + WorkoutEntry.TABLE_NAME + " (" +
                WorkoutEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                WorkoutEntry.COLUMN_WORKOUT_NAME + " TEXT NOT NULL, " +
                WorkoutEntry.COLUMN_TIMESTAMP + " TIMESTAMP DEFAULT CURRENT_TIMESTAMP" +
                "); ";

        // String to create History table
        final String SQL_CREATE_HISTORY_TABLE = "CREATE TABLE " + HistoryEntry.TABLE_NAME + " (" +
                HistoryEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                HistoryEntry.COLUMN_DATE + " TEXT NOT NULL, " +
                HistoryEntry.COLUMN_TIMESTAMP + " TIMESTAMP DEFAULT CURRENT_TIMESTAMP" +
                "); ";

        // String to create Exercise/Workout junction table
        final String SQL_CREATE_EXERCISE_WORKOUT_JUNCTION_TABLE = "CREATE TABLE " +
                ExerciseWorkoutJunctionEntry.TABLE_NAME + " (" +
                ExerciseWorkoutJunctionEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                ExerciseWorkoutJunctionEntry.COLUMN_EXERCISE_ID + " INTEGER NOT NULL, " +
                ExerciseWorkoutJunctionEntry.COLUMN_WORKOUT_ID + " INTEGER NOT NULL, " +
                ExerciseWorkoutJunctionEntry.COLUMN_TIMESTAMP +
                " TIMESTAMP DEFAULT CURRENT_TIMESTAMP" +
                "); ";

        // String to create History/Exercise junction table
        final String SQL_CREATE_HISTORY_EXERCISE_JUNCTION_TABLE = "CREATE TABLE " +
                HistoryExerciseJunctionEntry.TABLE_NAME + " (" +
                HistoryExerciseJunctionEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                HistoryExerciseJunctionEntry.COLUMN_HISTORY_ID + " INTEGER NOT NULL, " +
                HistoryExerciseJunctionEntry.COLUMN_EXERCISE_ID + " INTEGER NOT NULL, " +
                HistoryExerciseJunctionEntry.COLUMN_TIMESTAMP +
                " TIMESTAMP DEFAULT CURRENT_TIMESTAMP" +
                "); ";

        // Execute SQL commands
        db.execSQL(SQL_CREATE_EXERCISE_TABLE);
        db.execSQL(SQL_CREATE_WORKOUT_TABLE);
        db.execSQL(SQL_CREATE_HISTORY_TABLE);
        db.execSQL(SQL_CREATE_EXERCISE_WORKOUT_JUNCTION_TABLE);
        db.execSQL(SQL_CREATE_HISTORY_EXERCISE_JUNCTION_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // If DATABASE_VERSION changes, the database tables are updated.
        db.execSQL("ALTER IF TABLE EXISTS " + ExerciseEntry.TABLE_NAME);
        db.execSQL("ALTER IF TABLE EXISTS " + WorkoutEntry.TABLE_NAME);
        db.execSQL("ALTER IF TABLE EXISTS " + HistoryEntry.TABLE_NAME);
        db.execSQL("ALTER IF TABLE EXISTS " + ExerciseWorkoutJunctionEntry.TABLE_NAME);
        db.execSQL("ALTER IF TABLE EXISTS " + HistoryExerciseJunctionEntry.TABLE_NAME);
        onCreate(db);
    }
}
