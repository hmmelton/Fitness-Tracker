package com.hmmelton.workouttracker;

import android.os.Handler;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.hmmelton.workouttracker.fragments.ExercisesFragment;
import com.hmmelton.workouttracker.fragments.HistoryFragment;
import com.hmmelton.workouttracker.fragments.WorkoutsFragment;

import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

import butterknife.BindColor;
import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @SuppressWarnings("unused")
    private final String TAG = getClass().getSimpleName();

    // Views
    @BindView(R.id.main_view_pager)
    ViewPager mViewPager;
    @BindView(R.id.main_tab_layout)
    TabLayout mTabLayout;

    // Strings
    @BindString(R.string.history)
    String mHistory;
    @BindString(R.string.workouts)
    String mWorkouts;
    @BindString(R.string.exercises)
    String mExercises;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Set up Butter Knife
        ButterKnife.bind(this);
        // Add fragments to ViewPager
        setUpViewPager();
        // Create and display tabs
        setUpTabs();
    }

    /**
     * This method creates tabs and adds them to the layout's TabLayout.
     */
    private void setUpTabs() {
        // Use in conjunction with ViewPager
        mTabLayout.setupWithViewPager(mViewPager);
        // Add tab titles
        if (mTabLayout.getTabCount() >= 3) {
            mTabLayout.getTabAt(0).setText(mHistory);
            mTabLayout.getTabAt(1).setText(mWorkouts);
            mTabLayout.getTabAt(2).setText(mExercises);
        }
    }

    /**
     * This method sets up the layout's ViewPager
     */
    private void setUpViewPager() {
        // Instantiate adapter
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());

        // Add fragments to adapter
        adapter.addFragment(HistoryFragment.newInstance());
        adapter.addFragment(WorkoutsFragment.newInstance());
        adapter.addFragment(ExercisesFragment.newInstance());
        mViewPager.setAdapter(adapter);
    }

    // Adapter class for view pager tabs
    private class ViewPagerAdapter extends FragmentPagerAdapter {
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
