package com.example.zenhabit.adapters

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment.Companion.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.zenhabit.R
import com.example.zenhabit.classes.TaskCard
import com.example.zenhabit.fragments.TasksAndHabits_FragmentDirections
import com.example.zenhabit.utilities.DataBaseUtils

class Adapter_TaskCard(val Frag :Fragment,private val dataSet: ArrayList<TaskCard>) :
    RecyclerView.Adapter<Adapter_TaskCard.TasksViewHolder>() {

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): TasksViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.card_tasques, viewGroup, false)


        return TasksViewHolder(view)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(viewHolder: TasksViewHolder, @SuppressLint("RecyclerView") position: Int) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        var docRef = DataBaseUtils.db.collection("Users").document(DataBaseUtils.user!!.uid).collection(
            "Tasques").document(dataSet[position].tascaNom!!)


        viewHolder.taskNameTextView.text = dataSet[position].tascaNom
        viewHolder.taskTimeTextView.text = dataSet[position].tascaTemps
        viewHolder.btn_EditarTasca.setOnClickListener{
            dataSet[position].edicio = true
            val sendData = TasksAndHabits_FragmentDirections.actionTasksAndHabitsFragmentToEditTaskFragment(dataSet[position])
            findNavController(Frag).navigate(sendData)
        }
        val container = viewHolder.itemView as ViewGroup
        val slideOut = AnimationUtils.loadAnimation(viewHolder.itemView.context, android.R.anim.slide_out_right)

        slideOut.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationEnd(animation: Animation) {
                container.removeView(viewHolder.itemView)
                notifyItemRemoved(position) // notificar al adaptador sobre la eliminación del elemento
                notifyDataSetChanged() // notificar al adaptador sobre el cambio de tamaño del conjunto de datos
            }
            override fun onAnimationStart(p0: Animation?) {
            }

            override fun onAnimationRepeat(p0: Animation?) {
            }
        })

        viewHolder.btn_EliminarTasca.setOnClickListener{
            dataSet.removeAt(position)
            viewHolder.itemView.startAnimation(slideOut)
            docRef.delete().addOnSuccessListener {
                Log.i("FIRESTORE", "Documento eliminado correctamente")
            }
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
        val btn_EliminarTasca: Button

        init {
            // Define click listener for the ViewHolder's View.
            taskNameTextView = view.findViewById(R.id.lbl_TaskName)
            taskTimeTextView = view.findViewById(R.id.lbl_TaskTime)
            btn_EditarTasca = view.findViewById(R.id.button_EditTask)
            btn_EliminarTasca = view.findViewById(R.id.button_RemoveTask)

        }
    }
}
