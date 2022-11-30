package com.example.zenhabit.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.zenhabit.R
import com.example.zenhabit.classes.ChallengeCard

class Adapter_ChallengeCard(val Frag : Fragment, private val dataSet: Array<ChallengeCard>) :
    RecyclerView.Adapter<Adapter_ChallengeCard.ChallengeViewHolder>() {

    override fun onCreateViewHolder(
        viewGroup: ViewGroup,
        viewType: Int
    ): Adapter_ChallengeCard.ChallengeViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.card_challenge, viewGroup, false)

        return Adapter_ChallengeCard.ChallengeViewHolder(view)

    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(
        viewHolder: Adapter_ChallengeCard.ChallengeViewHolder,
        position: Int
    ) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        viewHolder.challName.setText(dataSet[position].challengeName)
        viewHolder.challCompleted.isChecked = dataSet[position].challengeCompleted
    }

    override fun getItemCount() = dataSet.size

    class ChallengeViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val challName: TextView
        val challCompleted: CheckBox

        init {
            // Define click listener for the ViewHolder's View.
            challName = view.findViewById(R.id.lbl_Challenge)
            challCompleted = view.findViewById(R.id.check_challenge)

        }
    }
}