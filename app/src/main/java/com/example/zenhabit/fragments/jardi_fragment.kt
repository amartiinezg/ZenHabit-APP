package com.example.zenhabit.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.zenhabit.R
import com.example.zenhabit.adapters.Adapter_JardiCard
import com.example.zenhabit.classes.JardiCard
import com.example.zenhabit.classes.TaskCard
import com.example.zenhabit.databinding.FragmentJardiBinding
import com.example.zenhabit.utilities.DataBaseUtils

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [jardi.newInstance] factory method to
 * create an instance of this fragment.
 */
class jardi : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private lateinit var binding: FragmentJardiBinding
    private var Imatges = arrayOf(0, R.drawable.tree_1, R.drawable.pine_tree)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentJardiBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //var jardi: HashMap<String, HashMap<String, Int>> = DataBaseUtils.checkJardi()

        var documentRef = DataBaseUtils.db.collection("Users").document(DataBaseUtils.user!!.uid).collection("Jardi")
        var documentJardi = DataBaseUtils.db.collection("Jardi").get()
        var jardiExists = documentRef.get()
        var hashJardi: HashMap<String, HashMap<String, Int>> = HashMap()
        documentJardi.addOnSuccessListener { jardi ->
            for (recompensa in jardi) {
                var dadesHash: HashMap<String, Int> = HashMap()
                dadesHash.put("ID", recompensa.data.get("Icon").toString().toInt())
                dadesHash.put("Quantitat", 0)
                hashJardi.put(recompensa.data.get("Name").toString(), dadesHash)
            }
            jardiExists.addOnSuccessListener { result ->
                if (result.size() == 0) {
                    documentRef.document("Recompenses").set(hashJardi)
                } else {

                }
                var data :ArrayList<JardiCard> = ArrayList<JardiCard>();
                for (recompensa in hashJardi){
                    data.add(JardiCard(Imatges.get(1), recompensa.key.toString(), recompensa.value["Quantitat"].toString().toInt()))
                }

                binding.jardiRecyclerView.layoutManager = LinearLayoutManager(activity)
                binding.jardiRecyclerView.adapter = Adapter_JardiCard(data)
            }
        }

    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment jardi.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            jardi().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}