package com.hmmelton.workouttracker;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.hmmelton.workouttracker.data.ExerciseDbHelper;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.lang.reflect.Field;

import static org.junit.Assert.*;
import static com.hmmelton.workouttracker.data.ExerciseContract.*;

/**
 * Created by harrisonmelton on 7/18/17.
 * Instrumentation test for database functionality.
 */

@RunWith(AndroidJUnit4.class)
public class DatabaseTest {

    // Context used to create ExerciseDbHelper and perform operations
    private final Context mContext = InstrumentationRegistry.getTargetContext();
    // Class reference to help load the constructor on runtime
    private final Class mDbHelperClass = ExerciseDbHelper.class;
    // DB helper used to fetch writable database instances
    private static SQLiteOpenHelper mDbHelper;

    // ID values used for junction table tests
    private final int EXERCISE_ID = 1;
    private final int WORKOUT_ID = 1;
    private final int HISTORY_ID = 1;

    @Before
    /**
     * @throws Exception in case the constructor hasn't been implemented yet
     */
    public void setUp() throws Exception {
        deleteTheDatabase();
        mDbHelper = (SQLiteOpenHelper) mDbHelperClass.getConstructor(Context.class)
                .newInstance(mContext);
    }

    /**
     * This method tests that our database contains all of the tables that we think it should
     * contain.
     */
    @Test
    public void create_database_test() {

        /* Use WaitlistDbHelper to get access to a writable database */
        SQLiteDatabase database = mDbHelper.getWritableDatabase();


        /* We think the database is open, let's verify that here */
        String databaseIsNotOpen = "The database should be open and isn't";
        assertEquals(databaseIsNotOpen,
                true,
                database.isOpen());
    }

    /**
     * This method tests inserting a single Exercise record into an empty table from a brand new
     * database. The purpose is to test that the database is working as expected
     */
    @Test
    public void insert_single_exercise_record_test() {

        /* Use WaitlistDbHelper to get access to a writable database */
        SQLiteDatabase database = mDbHelper.getWritableDatabase();

        ContentValues testValues = new ContentValues();
        testValues.put(ExerciseEntry.COLUMN_EXERCISE_NAME, "Cable Crunches");
        testValues.put(ExerciseEntry.COLUMN_SET_TYPE, "lbs");
        testValues.put(ExerciseEntry.COLUMN_REP_COUNT, 10);
        testValues.put(ExerciseEntry.COLUMN_REP_INTENSITY, 87.5);

        /* Insert ContentValues into database and get first row ID back */
        long firstRowId = database.insert(
                ExerciseEntry.TABLE_NAME,
                null,
                testValues);

        /* If the insert fails, database.insert returns -1 */
        assertNotEquals("Unable to insert into the database", -1, firstRowId);

        /*
         * Query the database and receive a Cursor. A Cursor is the primary way to interact with
         * a database in Android.
         */
        Cursor wCursor = database.query(
                /* Name of table on which to perform the query */
                ExerciseEntry.TABLE_NAME,
                /* Columns; leaving this null returns every column in the table */
                null,
                /* Optional specification for columns in the "where" clause above */
                null,
                /* Values for "where" clause */
                null,
                /* Columns to group by */
                null,
                /* Columns to filter by row groups */
                null,
                /* Sort order to return in Cursor */
                null);

        /* Cursor.moveToFirst will return false if there are no records returned from your query */
        String emptyQueryError = "Error: No Records returned from exercise query";
        assertTrue(emptyQueryError, wCursor.moveToFirst());

        /* Close cursor and database */
        wCursor.close();
        mDbHelper.close();
    }

    /**
     * This method tests inserting a single Workout record into an empty table from a brand new
     * database. The purpose is to test that the database is working as expected
     */
    @Test
    public void insert_single_workout_record_test() {

        /* Use WaitlistDbHelper to get access to a writable database */
        SQLiteDatabase database = mDbHelper.getWritableDatabase();

        ContentValues testValues = new ContentValues();
        testValues.put(WorkoutEntry.COLUMN_WORKOUT_NAME, "Abs Routine");

        /* Insert ContentValues into database and get first row ID back */
        long firstRowId = database.insert(
                WorkoutEntry.TABLE_NAME,
                null,
                testValues);

        /* If the insert fails, database.insert returns -1 */
        assertNotEquals("Unable to insert into the database", -1, firstRowId);

        /*
         * Query the database and receive a Cursor. A Cursor is the primary way to interact with
         * a database in Android.
         */
        Cursor wCursor = database.query(
                /* Name of table on which to perform the query */
                WorkoutEntry.TABLE_NAME,
                /* Columns; leaving this null returns every column in the table */
                null,
                /* Optional specification for columns in the "where" clause above */
                null,
                /* Values for "where" clause */
                null,
                /* Columns to group by */
                null,
                /* Columns to filter by row groups */
                null,
                /* Sort order to return in Cursor */
                null);

        /* Cursor.moveToFirst will return false if there are no records returned from your query */
        String emptyQueryError = "Error: No Records returned from workout query";
        assertTrue(emptyQueryError, wCursor.moveToFirst());

        /* Close cursor and database */
        wCursor.close();
        mDbHelper.close();
    }

    /**
     * This method tests inserting a single History record into an empty table from a brand new
     * database. The purpose is to test that the database is working as expected
     */
    @Test
    public void insert_single_history_record_test() {

        /* Use WaitlistDbHelper to get access to a writable database */
        SQLiteDatabase database = mDbHelper.getWritableDatabase();

        ContentValues testValues = new ContentValues();
        testValues.put(HistoryEntry.COLUMN_DATE, "19 July 2017");

        /* Insert ContentValues into database and get first row ID back */
        long firstRowId = database.insert(
                HistoryEntry.TABLE_NAME,
                null,
                testValues);

        /* If the insert fails, database.insert returns -1 */
        assertNotEquals("Unable to insert into the database", -1, firstRowId);

        /*
         * Query the database and receive a Cursor. A Cursor is the primary way to interact with
         * a database in Android.
         */
        Cursor wCursor = database.query(
                /* Name of table on which to perform the query */
                HistoryEntry.TABLE_NAME,
                /* Columns; leaving this null returns every column in the table */
                null,
                /* Optional specification for columns in the "where" clause above */
                null,
                /* Values for "where" clause */
                null,
                /* Columns to group by */
                null,
                /* Columns to filter by row groups */
                null,
                /* Sort order to return in Cursor */
                null);

        /* Cursor.moveToFirst will return false if there are no records returned from your query */
        String emptyQueryError = "Error: No Records returned from history query";
        assertTrue(emptyQueryError, wCursor.moveToFirst());

        /* Close cursor and database */
        wCursor.close();
        mDbHelper.close();
    }

    /**
     * This method tests inserting a single Exercise/Workout Junction record into an empty table
     * from a brand new database. The purpose is to test that the database is working as expected
     */
    @Test
    public void insert_single_ew_junction_record_test() {

        // Insert initial values
        insert_single_exercise_record_test();
        insert_single_workout_record_test();

        /* Use WaitlistDbHelper to get access to a writable database */
        SQLiteDatabase database = mDbHelper.getWritableDatabase();

        ContentValues testValues = new ContentValues();
        testValues.put(ExerciseWorkoutJunctionEntry.COLUMN_EXERCISE_ID, EXERCISE_ID);
        testValues.put(ExerciseWorkoutJunctionEntry.COLUMN_WORKOUT_ID, WORKOUT_ID);

        /* Insert ContentValues into database and get first row ID back */
        long firstRowId = database.insert(
                ExerciseWorkoutJunctionEntry.TABLE_NAME,
                null,
                testValues);

        /* If the insert fails, database.insert returns -1 */
        assertNotEquals("Unable to insert into the database", -1, firstRowId);

        /*
         * Query the database and receive a Cursor. A Cursor is the primary way to interact with
         * a database in Android.
         */
        Cursor wCursor = database.query(
                /* Name of table on which to perform the query */
                ExerciseWorkoutJunctionEntry.TABLE_NAME,
                /* Columns; leaving this null returns every column in the table */
                null,
                /* Optional specification for columns in the "where" clause above */
                null,
                /* Values for "where" clause */
                null,
                /* Columns to group by */
                null,
                /* Columns to filter by row groups */
                null,
                /* Sort order to return in Cursor */
                null);

        /* Cursor.moveToFirst will return false if there are no records returned from your query */
        String emptyQueryError = "Error: No Records returned from EW junction query";
        assertTrue(emptyQueryError, wCursor.moveToFirst());

        // Get ID's
        int exerciseId = wCursor.getInt(1);
        int workoutId = wCursor.getInt(2);
        String exerciseIdMismatchError = "Error: Exercise ID returned from query does not match what" +
                "was expected ";
        assertTrue(exerciseIdMismatchError + "1", exerciseId == EXERCISE_ID);
        assertTrue(exerciseIdMismatchError + "2", workoutId == WORKOUT_ID);

        wCursor.close();
        mDbHelper.close();
    }

    /**
     * This method tests inserting a single History/Exercise Junction record into an empty table
     * from a brand new database. The purpose is to test that the database is working as expected
     */
    @Test
    public void insert_single_he_junction_record_test() {

        // Insert initial values
        insert_single_exercise_record_test();
        insert_single_history_record_test();

        /* Use WaitlistDbHelper to get access to a writable database */
        SQLiteDatabase database = mDbHelper.getWritableDatabase();

        ContentValues testValues = new ContentValues();
        testValues.put(HistoryExerciseJunctionEntry.COLUMN_EXERCISE_ID, EXERCISE_ID);
        testValues.put(HistoryExerciseJunctionEntry.COLUMN_HISTORY_ID, HISTORY_ID);

        /* Insert ContentValues into database and get first row ID back */
        long firstRowId = database.insert(
                HistoryExerciseJunctionEntry.TABLE_NAME,
                null,
                testValues);

        /* If the insert fails, database.insert returns -1 */
        assertNotEquals("Unable to insert into the database", -1, firstRowId);

        /*
         * Query the database and receive a Cursor. A Cursor is the primary way to interact with
         * a database in Android.
         */
        Cursor wCursor = database.query(
                /* Name of table on which to perform the query */
                HistoryExerciseJunctionEntry.TABLE_NAME,
                /* Columns; leaving this null returns every column in the table */
                null,
                /* Optional specification for columns in the "where" clause above */
                null,
                /* Values for "where" clause */
                null,
                /* Columns to group by */
                null,
                /* Columns to filter by row groups */
                null,
                /* Sort order to return in Cursor */
                null);

        /* Cursor.moveToFirst will return false if there are no records returned from your query */
        String emptyQueryError = "Error: No Records returned from HE junction query";
        assertTrue(emptyQueryError, wCursor.moveToFirst());

        // Get ID's
        int historyId = wCursor.getInt(1);
        int exerciseId = wCursor.getInt(2);
        String idMismatchError = "Error: Exercise ID returned from query does not match what" +
                "was expected ";
        assertTrue(idMismatchError + "1", historyId == HISTORY_ID);
        assertTrue(idMismatchError + "2", exerciseId == EXERCISE_ID);

        wCursor.close();
        mDbHelper.close();
    }

    /**
     * Deletes the entire database.
     */
    void deleteTheDatabase(){
        try {
            /* Use reflection to get the database name from the db helper class */
            Field f = mDbHelperClass.getDeclaredField("DATABASE_NAME");
            f.setAccessible(true);
            mContext.deleteDatabase((String)f.get(null));
        }catch (NoSuchFieldException ex){
            fail("Make sure you have a member called DATABASE_NAME in the WaitlistDbHelper");
        }catch (Exception ex){
            fail(ex.getMessage());
        }

    }
}
