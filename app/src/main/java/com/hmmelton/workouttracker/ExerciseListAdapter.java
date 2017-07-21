package com.hmmelton.workouttracker;

import android.content.Context;
import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hmmelton.workouttracker.data.ExerciseContract;

/**
 * Created by harrisonmelton on 7/20/17.
 * This is an adapter class for {@link MainActivity#mRecyclerView}.
 */

public class ExerciseListAdapter extends RecyclerView.Adapter<ExerciseListAdapter.ViewHolder> {

    // Cursor containing data
    private Cursor mCursor;
    private Context mContext;
    // Index of MainActivity tab - 0 for history, 1 for workout, 2 for exercise
    private int mDataType;

    // Constructor
    public ExerciseListAdapter(Cursor cursor, Context context, int dataType) {
        mCursor = cursor;
        mContext = context;
        mDataType = dataType;
    }

    @Override
    public ExerciseListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // Inflate new row layout
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(android.R.layout.simple_list_item_1, parent, false);
        // Return new ViewHolder with attached layout
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ExerciseListAdapter.ViewHolder holder, int position) {
        // Move cursor to position of item
        if (!mCursor.moveToPosition(position))
            return; // Exit if cursor cannot move to position

        // Name to be displayed in list
        String name = "";
        // ID to use to fetch individual items, if the item's row is selected
        long id = -1;
        switch (mDataType) {
            case 0:
                name = mCursor.getString(mCursor.getColumnIndex(
                        ExerciseContract.HistoryEntry.COLUMN_NAME));
                id = mCursor.getLong(mCursor.getColumnIndex(ExerciseContract.HistoryEntry._ID));
                break;
            case 1:
                name = mCursor.getString(mCursor.getColumnIndex(
                        ExerciseContract.WorkoutEntry.COLUMN_NAME));
                id = mCursor.getLong(mCursor.getColumnIndex(ExerciseContract.WorkoutEntry._ID));
                break;
            case 2:
                name = mCursor.getString(mCursor.getColumnIndex(
                        ExerciseContract.ExerciseEntry.COLUMN_NAME));
                id = mCursor.getLong(mCursor.getColumnIndex(ExerciseContract.ExerciseEntry._ID));
                break;
            default: break;
        }

        // Set name to view holder's TextView
        holder.textView.setText(name);
        // Set ID as view holder's TextView's tag
        holder.textView.setTag(id);
    }

    @Override
    public int getItemCount() {
        return mCursor.getCount();
    }

    /**
     * This method sets the data type, and reloads the adapter's data
     * @param dataType new data type
     */
    public void setDataType(int dataType) {
        mDataType = dataType;
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        public ViewHolder(View itemView) {
            super(itemView);
            // TextView taken from generic Android row layout
            textView = (TextView) itemView.findViewById(android.R.id.text1);
        }
    }
}
