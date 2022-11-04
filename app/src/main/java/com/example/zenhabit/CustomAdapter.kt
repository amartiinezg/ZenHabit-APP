package com.example.zenhabit

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.zenhabit.classes.TaskCard

class CustomAdapter(private val dataSet: Array<TaskCard>) :
    RecyclerView.Adapter<CustomAdapter.TasksViewHolder>() {

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): TasksViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.card_tasques, viewGroup, false)

        return TasksViewHolder(view)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(viewHolder: TasksViewHolder, position: Int) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element

        viewHolder.taskNameTextView.text = dataSet[position].taskName
        viewHolder.taskTimeTextView.text = dataSet[position].taskTime

    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = dataSet.size

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     */
    class TasksViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val taskNameTextView: TextView
        val taskTimeTextView: TextView

        init {
            // Define click listener for the ViewHolder's View.
            taskNameTextView = view.findViewById(R.id.lbl_TaskName)
            taskTimeTextView = view.findViewById(R.id.lbl_TaskTime)

        }
    }
}
