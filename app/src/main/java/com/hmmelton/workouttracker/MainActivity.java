package com.hmmelton.workouttracker;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.hmmelton.workouttracker.data.DatabaseUtil;
import com.hmmelton.workouttracker.data.ExerciseDbHelper;
import com.hmmelton.workouttracker.fragments.ExercisesFragment;
import com.hmmelton.workouttracker.fragments.HistoryFragment;
import com.hmmelton.workouttracker.fragments.WorkoutsFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @SuppressWarnings("unused")
    private final String TAG = getClass().getSimpleName();

    // Views
    @BindView(R.id.main_tab_layout)
    TabLayout mTabLayout;
    @BindView(R.id.main_view_pager)
    ViewPager mViewPager;

    // Strings
    @BindString(R.string.history)
    String mHistory;
    @BindString(R.string.workouts)
    String mWorkouts;
    @BindString(R.string.exercises)
    String mExercises;
    @BindString(R.string.unexpected_error)
    String mUnexpectedError;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Set up Butter Knife
        ButterKnife.bind(this);
        // Create and display tabs
        setUpTabs();
    }

    /**
     * This method adds tabs to the passed {@link #mViewPager}
     */
    private void setUpViewPager() {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        // Add fragments to the adapter
        adapter.addFragment(HistoryFragment.newInstance());
        adapter.addFragment(WorkoutsFragment.newInstance());
        adapter.addFragment(ExercisesFragment.newInstance());
    }

    /**
     * This method creates tabs and adds them to the layout's TabLayout.
     */
    private void setUpTabs() {
        mTabLayout.setupWithViewPager(mViewPager);

    }

    // Adapter class for view pager tabs
    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();

        ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        void addFragment(Fragment fragment) {
            mFragmentList.add(fragment);
        }
    }
}
