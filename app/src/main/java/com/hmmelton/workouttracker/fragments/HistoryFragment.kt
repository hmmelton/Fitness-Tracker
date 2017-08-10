package com.hmmelton.workouttracker.fragments

import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v4.app.Fragment
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

import com.hmmelton.workouttracker.R

import butterknife.BindView
import butterknife.ButterKnife
import butterknife.OnClick

/**
 * A simple [Fragment] subclass.
 * Use the [HistoryFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HistoryFragment : Fragment() {

    // Views
    @BindView(R.id.history_recycler_view)
    internal var mRecyclerView: RecyclerView? = null
    @BindView(R.id.history_text_view)
    internal var mEmptyListText: TextView? = null

    // OnClick handler for FloatingActionButton
    @OnClick(R.id.history_fab)
    internal fun onFabClick() {

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater!!.inflate(R.layout.fragment_history, container, false)
        // Initiate Butter Knife
        ButterKnife.bind(this, view)

        return view
    }

    companion object {

        /**
         * Use this factory method to create a new instance of
         * this fragment.

         * @return A new instance of fragment HistoryFragment.
         */
        fun newInstance(): HistoryFragment {
            return HistoryFragment()
        }
    }
}// Required empty public constructor
