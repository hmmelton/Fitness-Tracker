package com.hmmelton.workouttracker

import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.os.Bundle

import com.hmmelton.workouttracker.fragments.ExercisesFragment
import com.hmmelton.workouttracker.fragments.HistoryFragment

import java.util.ArrayList

import butterknife.BindString
import butterknife.BindView
import butterknife.ButterKnife

class MainActivity : AppCompatActivity() {

    @Suppress("unused")
    private val TAG = javaClass.simpleName

    // Views
    @BindView(R.id.main_tab_layout)
    internal var mTabLayout: TabLayout? = null
    @BindView(R.id.main_view_pager)
    internal var mViewPager: ViewPager? = null

    // Strings
    @BindString(R.string.history)
    internal var mHistory: String? = null
    @BindString(R.string.exercises)
    internal var mExercises: String? = null
    @BindString(R.string.unexpected_error)
    internal var mUnexpectedError: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // Set up Butter Knife
        ButterKnife.bind(this)
        // Set ViewPager fragments
        setUpViewPager()
        // Set tab titles
        setUpTabs()
    }

    /**
     * This method adds tabs to the passed [.mViewPager]
     */
    private fun setUpViewPager() {
        val adapter = ViewPagerAdapter(supportFragmentManager)
        // Add fragments to the adapter
        adapter.addFragment(HistoryFragment.newInstance())
        adapter.addFragment(ExercisesFragment.newInstance())
        mViewPager!!.adapter = adapter
    }

    /**
     * This method creates tabs and adds them to the layout's TabLayout.
     */
    private fun setUpTabs() {
        mTabLayout!!.setupWithViewPager(mViewPager)
        if (mTabLayout!!.tabCount == 2) {
            mTabLayout!!.getTabAt(0)!!.text = mHistory
            mTabLayout!!.getTabAt(1)!!.text = mExercises
        }
    }

    // Adapter class for view pager tabs
    internal inner class ViewPagerAdapter(manager: FragmentManager) : FragmentPagerAdapter(manager) {
        private val mFragmentList = ArrayList<Fragment>()

        override fun getItem(position: Int): Fragment {
            return mFragmentList[position]
        }

        override fun getCount(): Int {
            return mFragmentList.size
        }

        fun addFragment(fragment: Fragment) {
            mFragmentList.add(fragment)
        }
    }
}
