package com.hmmelton.workouttracker;

import android.database.sqlite.SQLiteDatabase;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.hmmelton.workouttracker.data.DatabaseUtil;
import com.hmmelton.workouttracker.data.ExerciseDbHelper;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @SuppressWarnings("unused")
    private final String TAG = getClass().getSimpleName();
    private ExerciseListAdapter mAdapter;
    private SQLiteDatabase mDatabase;

    // Views
    @BindView(R.id.main_tab_layout)
    TabLayout mTabLayout;
    @BindView(R.id.main_recycler_view)
    RecyclerView mRecyclerView;
    @BindView(R.id.empty_text_view)
    TextView mEmptyTextView;

    // Strings
    @BindString(R.string.history)
    String mHistory;
    @BindString(R.string.workouts)
    String mWorkouts;
    @BindString(R.string.exercises)
    String mExercises;
    @BindString(R.string.unexpected_error)
    String mUnexpectedError;

    // OnClick listeners
    @OnClick(R.id.main_fab)
    void onFabClick() {
        int currentTab = mTabLayout.getSelectedTabPosition();
        // Action depends on tab position, so check what it is
        // TODO: fill this in
        switch (currentTab) {
            case 0:
                break;
            case 1:
                break;
            case 2:
                break;
            default:
                // Let user know there has been an unexpected error
                Toast.makeText(MainActivity.this, mUnexpectedError, Toast.LENGTH_LONG).show();
                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Set up Butter Knife
        ButterKnife.bind(this);
        // Create and display tabs
        setUpTabs();
        // Get database
        getDatabase();
        // Set up RecyclerView
        setUpRecyclerView();
    }

    /**
     * This method creates tabs and adds them to the layout's TabLayout.
     */
    private void setUpTabs() {
        // Add tabs to tab layout
        mTabLayout.addTab(mTabLayout.newTab().setText(mHistory));
        mTabLayout.addTab(mTabLayout.newTab().setText(mWorkouts));
        mTabLayout.addTab(mTabLayout.newTab().setText(mExercises));
        // Add listener for tab selection
        mTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                // Update the adapter's data
                mAdapter.setDataType(mTabLayout.getSelectedTabPosition());
                checkAdapterCount();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {}

            @Override
            public void onTabReselected(TabLayout.Tab tab) {}
        });
    }

    /**
     * This method sets up {@link #mRecyclerView}
     */
    private void setUpRecyclerView() {
        // Set layout manager
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        // Create and set adapter
        mAdapter = new ExerciseListAdapter(DatabaseUtil.getAllHistory(mDatabase), this,
                mTabLayout.getSelectedTabPosition());
        mRecyclerView.setAdapter(mAdapter);

        checkAdapterCount();
    }

    /**
     * This method hides the screen's 'nothing to display' TextView, if the RecyclerView's adapter
     * contains items.
     */
    private void checkAdapterCount() {
        if (mAdapter.getItemCount() > 0) {
            mEmptyTextView.setVisibility(View.GONE);
        }
    }

    /**
     * This method prepares the database for CRUD requests.
     */
    private void getDatabase() {
        ExerciseDbHelper dbHelper = new ExerciseDbHelper(this);
        mDatabase = dbHelper.getWritableDatabase();
    }
}
