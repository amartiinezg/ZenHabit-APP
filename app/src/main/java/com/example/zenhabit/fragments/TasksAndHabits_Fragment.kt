package com.example.zenhabit.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.zenhabit.adapters.Adapter_ChallengeCard
import com.example.zenhabit.adapters.Adapter_TaskCard
import com.example.zenhabit.classes.ChallengeCard
import com.example.zenhabit.classes.DataBase.usersclass.UsersClass
import com.example.zenhabit.classes.TaskCard
import com.example.zenhabit.databinding.FragmentTasksAndHabitsBinding
import com.example.zenhabit.utilities.DataBaseUtils
import com.google.firebase.firestore.QueryDocumentSnapshot

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
            val sendData = TasksAndHabits_FragmentDirections.actionTasksAndHabitsFragmentToEditTaskFragment(TaskCard("", "", "", "", false))
            NavHostFragment.findNavController(this).navigate(sendData)
        }



        binding.reclyclerViewTasques
       return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var docRef = DataBaseUtils.db.collection("Users").document(DataBaseUtils.user!!.uid).collection("Tasques").get()
        val tasques = ArrayList<TaskCard>()

        docRef.addOnSuccessListener { result ->
            for (document in result) {
                if(!document.data.get("nom").toString().equals("null")) {
                    var tasca = TaskCard(
                        document.data.get("nom").toString(),
                        document.data.get("descripcio").toString(),
                        document.data.get("categoria").toString(),
                        document.data.get("data").toString(),
                        false
                    )
                    tasques.add(tasca)
                    Log.d("Proves", "${document.id} => ${document.data}")
                }
            }
            val challenges = arrayOf(
                ChallengeCard(
                    "Anar a correr 5km",
                    "",
                    true
                ),
                ChallengeCard(
                    "Màxim 2 hores al mòvil",
                    "",
                    false
                ),
                ChallengeCard(
                    "Fer 2 tasques",
                    "",
                    true
                )
            )
            binding.reclyclerViewChallenges.layoutManager = LinearLayoutManager(activity)
            binding.reclyclerViewChallenges.adapter = Adapter_ChallengeCard(this, challenges)

            binding.reclyclerViewTasques.layoutManager = LinearLayoutManager(activity)
            binding.reclyclerViewTasques.adapter = Adapter_TaskCard(this, tasques)
        }
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