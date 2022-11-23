package com.example.zenhabit.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment.Companion.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.zenhabit.R
import com.example.zenhabit.classes.TaskCard
import com.example.zenhabit.fragments.TasksAndHabits_FragmentDirections

class Adapter_TaskCard(val Frag :Fragment,private val dataSet: Array<TaskCard>) :
    RecyclerView.Adapter<Adapter_TaskCard.TasksViewHolder>() {

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
        viewHolder.btn_EditarTasca.setOnClickListener{
            val sendData = TasksAndHabits_FragmentDirections.actionTasksAndHabitsFragmentToEditTaskFragment(dataSet[position].taskName.toString())
            findNavController(Frag).navigate(sendData)
        }
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
        val btn_EditarTasca: Button

        init {
            // Define click listener for the ViewHolder's View.
            taskNameTextView = view.findViewById(R.id.lbl_TaskName)
            taskTimeTextView = view.findViewById(R.id.lbl_TaskTime)
            btn_EditarTasca = view.findViewById(R.id.button_EditTask)

        }
    }
}
