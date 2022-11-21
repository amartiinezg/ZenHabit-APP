package com.example.zenhabit.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.zenhabit.R
import com.example.zenhabit.adapters.Adapter_TaskCard
import com.example.zenhabit.classes.TaskCard
import com.example.zenhabit.databinding.FragmentTasksAndHabitsBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [TasksAndHabits_Fragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class TasksAndHabits_Fragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private lateinit var binding: FragmentTasksAndHabitsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
       binding = FragmentTasksAndHabitsBinding.inflate(layoutInflater)

        binding.floatButtonAddTask.setOnClickListener{


                findNavController().navigate(R.id.action_tasksAndHabits_Fragment_to_editTask_Fragment)

        }

        binding.reclyclerViewTasques
       return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val data = arrayOf(
            TaskCard(
                "Menjar Avena",
                "10:00"

            ),
            TaskCard(
                "Planxar la roba",
                "13:30"

            ),
            TaskCard(
                "Jugar al PayDay 2",
                "00:10"

            )
        )

        binding.reclyclerViewTasques.layoutManager = LinearLayoutManager(activity)
        binding.reclyclerViewTasques.adapter = Adapter_TaskCard(data)


    }


    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment TasksAndHabits_Fragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            TasksAndHabits_Fragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}