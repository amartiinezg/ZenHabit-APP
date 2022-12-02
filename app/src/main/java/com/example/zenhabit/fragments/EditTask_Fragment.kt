package com.example.zenhabit.fragments

import android.app.TimePickerDialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.core.view.get
import androidx.fragment.app.Fragment
import androidx.navigation.NavArgs
import androidx.navigation.fragment.navArgs
import com.example.zenhabit.R
import com.example.zenhabit.classes.DataBase.usersclass.UsersClass
import com.example.zenhabit.databinding.FragmentEditTaskBinding
import com.example.zenhabit.utilities.DataBaseUtils
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import java.util.*
import kotlin.time.Duration.Companion.seconds

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"
private lateinit var bin: FragmentEditTaskBinding

/**
 * A simple [Fragment] subclass.
 * Use the [EditTask_Fragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class EditTask_Fragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    val args: EditTask_FragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        bin = FragmentEditTaskBinding.inflate(layoutInflater)
        var initializeUserDataList : List<UsersClass>
        val coloredSpinner: Spinner = bin.slctorCategoryTask
        var adapter: ArrayAdapter<*> = ArrayAdapter.createFromResource(
            activity!!,
            R.array.categoria,
            R.layout.spinner_custom_layout
        )
        adapter.setDropDownViewResource(R.layout.spinner_dropdown_layout)
        coloredSpinner.adapter = adapter

        return bin.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val tasca = args.tasca

        bin.taskIdLabelEditTask.setText(tasca.tascaNom)
        var categoria = tasca.tascaCategoria
        if (!tasca.tascaDescripcio.isNullOrBlank()) {
            bin.editTextDescriptionName.setText(tasca.tascaDescripcio)
        }

        val cal = Calendar.getInstance()

        when (categoria) {
            "Aprenentatge" -> bin.slctorCategoryTask.setSelection(0)
            "Productivitat" -> bin.slctorCategoryTask.setSelection(1)
            "Salut" -> bin.slctorCategoryTask.setSelection(2)
        }
        bin.lblTimerTask.setText(tasca.tascaTemps)

        val listenerHora = TimePickerDialog.OnTimeSetListener { view, hora, minutos ->
            bin.lblTimerTask.setText("$hora:${minutos}")
        }
        bin.iconDate.setOnClickListener {
            TimePickerDialog(
                activity!!,
                listenerHora,
                cal.get(Calendar.HOUR_OF_DAY),
                cal.get(Calendar.MINUTE),
                true
            ).show()
        }

        bin.btnSaveEditTask.setOnClickListener {

            var tascaNom = bin.taskIdLabelEditTask.text.toString()
            var tascaDescripcio = bin.editTextDescriptionName.text.toString()
            var tascaCategoria = getResources().getStringArray(R.array.categoria)[bin.slctorCategoryTask.selectedItemPosition].toString();
            var indexTascaCategoria =bin.slctorCategoryTask.selectedItemPosition
            var tascaTemps = bin.lblTimerTask.text.toString()


            Log.d("EditTask_Fragment", tascaCategoria.toString())

            if (!tasca.edicio) {
                DataBaseUtils.loadNewUserTask(
                    true,
                    tascaTemps,
                    tascaNom,
                    tascaDescripcio,
                    tascaCategoria,
                    indexTascaCategoria
                )
            }
            else {
                //EDICIÓ
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
         * @return A new instance of fragment EditTask_Fragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            EditTask_Fragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}