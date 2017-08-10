package com.hmmelton.workouttracker

import android.content.Context
import android.database.Cursor
import android.provider.BaseColumns
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

import com.hmmelton.workouttracker.data.ExerciseContract

/**
 * Created by harrisonmelton on 7/20/17.
 * This is an adapter class for [MainActivity.mRecyclerView].
 */

class ExerciseListAdapter(
        // Cursor containing data
        private val mCursor: Cursor,
        // Index of MainActivity tab - 0 for history, 1 for workout, 2 for exercise
        private val mContext: Context,
        private var mDataType: Int) : RecyclerView.Adapter<ExerciseListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExerciseListAdapter.ViewHolder {
        // Inflate new row layout
        val inflater = LayoutInflater.from(mContext)
        val view = inflater.inflate(android.R.layout.simple_list_item_1, parent, false)
        // Return new ViewHolder with attached layout
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ExerciseListAdapter.ViewHolder, position: Int) {
        // Move cursor to position of item
        if (!mCursor.moveToPosition(position))
            return  // Exit if cursor cannot move to position

        // Name to be displayed in list
        var name = ""
        // ID to use to fetch individual items, if the item's row is selected
        var id: Long = -1
        when (mDataType) {
            0 -> {
                name = mCursor.getString(mCursor.getColumnIndex(
                        ExerciseContract.HistoryEntry.COLUMN_NAME))
                id = mCursor.getLong(mCursor.getColumnIndex(BaseColumns._ID))
            }
            1 -> {
                name = mCursor.getString(mCursor.getColumnIndex(
                        ExerciseContract.WorkoutEntry.COLUMN_NAME))
                id = mCursor.getLong(mCursor.getColumnIndex(BaseColumns._ID))
            }
            2 -> {
                name = mCursor.getString(mCursor.getColumnIndex(
                        ExerciseContract.ExerciseEntry.COLUMN_NAME))
                id = mCursor.getLong(mCursor.getColumnIndex(BaseColumns._ID))
            }
            else -> {
            }
        }

        // Set name to view holder's TextView
        holder.textView.text = name
        // Set ID as view holder's TextView's tag
        holder.textView.tag = id
    }

    override fun getItemCount(): Int {
        return mCursor.count
    }

    /**
     * This method sets the data type, and reloads the adapter's data
     * @param dataType new data type
     */
    fun setDataType(dataType: Int) {
        mDataType = dataType
        notifyDataSetChanged()
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var textView: TextView

        init {
            // TextView taken from generic Android row layout
            textView = itemView.findViewById<View>(android.R.id.text1) as TextView
        }
    }
}
