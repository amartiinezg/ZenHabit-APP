package com.example.zenhabit.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.zenhabit.R
import com.example.zenhabit.classes.JardiCard

class Adapter_JardiCard(private val dataSet: Array<JardiCard>) :
    RecyclerView.Adapter<Adapter_JardiCard.JardiViewHolder>() {

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): JardiViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.card_jardi, viewGroup, false)

        return JardiViewHolder(view)

    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(viewHolder: JardiViewHolder, position: Int) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        if (dataSet[position].amountReward == 0) {
            viewHolder.jardiImgReward.setBackgroundResource(dataSet[position].imgReward)
            viewHolder.jardiTextName.text = dataSet[position].rewardName
            viewHolder.jardiTextAmount.text = "Sense desbloquejar"
            viewHolder.jardiTextStatusReward.setImageResource(R.drawable.padlock_closed)
            viewHolder.card.setCardBackgroundColor(viewHolder.card.context.resources.getColor(R.color.transparent_red))
        }
        else {
            viewHolder.jardiImgReward.setBackgroundResource(dataSet[position].imgReward)
            viewHolder.jardiTextName.text = dataSet[position].rewardName
            viewHolder.jardiTextName.setTextColor(viewHolder.card.context.resources.getColor(R.color.primary))
            viewHolder.jardiTextAmount.text = "Has obtingut " + dataSet[position].amountReward
            viewHolder.jardiTextAmount.setTextColor(viewHolder.card.context.resources.getColor(R.color.primary))
            viewHolder.jardiTextStatusReward.setImageResource(R.drawable.padlock_opened)
            viewHolder.card.setCardBackgroundColor(viewHolder.card.context.resources.getColor(R.color.transparent_green))
        }
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = dataSet.size

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     */
    class JardiViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val jardiImgReward: ImageView
        val jardiTextName: TextView
        val jardiTextAmount: TextView
        val jardiTextStatusReward: ImageView
        val card: CardView

        init {
            // Define click listener for the ViewHolder's View.
            jardiImgReward = view.findViewById(R.id.img_cardJardi)
            jardiTextName = view.findViewById(R.id.card_TreeOrPlant)
            jardiTextAmount = view.findViewById(R.id.card_amountReward)
            jardiTextStatusReward = view.findViewById(R.id.card_UnlockedOrLocked)
            card = view.findViewById(R.id.card_jardi)

        }
    }
}
