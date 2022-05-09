package com.example.communityservicetracker.studentworkflow

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.communityservicetracker.OpportunityDetailActivity
import com.example.communityservicetracker.R
import com.example.communityservicetracker.orgworkflow.Opportunity

class ViewAcceptedAdapter (private var oppsList: ArrayList<Opportunity>):
    RecyclerView.Adapter<ViewAcceptedAdapter.ViewHolder>(){

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewAcceptedAdapter.ViewHolder{

        // Create, inflate, and return viewholder with view passed in
        val v = LayoutInflater.from(parent.context).inflate(R.layout.accepted_opportunity_item, parent, false)
        return ViewHolder(v)
    }

    // Function keeps iterating through the arrays
    override fun onBindViewHolder(holder: ViewAcceptedAdapter.ViewHolder, index: Int) {

        Log.i("CST", "in ononBindViewHolder")

        holder.itemTitle.text = oppsList[index].title

    }

    // Need to identify how many items are being passed into our viewholder
    override fun getItemCount(): Int {
        return oppsList.size
    }

    // ViewHolder class
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        // UI components
        var itemTitle : TextView
        var detailsButton : Button
        var doneCheckbox: CheckBox


        init {

            // Initialize respective UI components
            itemTitle = itemView.findViewById(R.id.accepted_item_title)
            doneCheckbox = itemView.findViewById(R.id.done_checkbox)
            detailsButton = itemView.findViewById(R.id.detailsButton)

            // Should lead to a different page that shows more about the opportunity
            detailsButton.setOnClickListener(){

                // Get index of opportunity in recycler view
                val pos : Int = adapterPosition


                // Create intent
                val i = Intent(itemView.context,
                    OpportunityDetailActivity::class.java)

                Log.i("CST", "In adapter: Participants is ${oppsList[pos].numberOfApplicants}")

                // Putting all data necessary to be able to show more details
                i.putExtra("title", oppsList[pos].title)
                i.putExtra("description", oppsList[pos].description)
                i.putExtra("participants", oppsList[pos].numberOfApplicants.toString())
                i.putExtra("timeCommitment", oppsList[pos].timeCommitment.toString())


                // Start new activity with necessary attributes of opportunity passed in
                ContextCompat.startActivity(itemView.context, i, null)

            }

            doneCheckbox.setOnClickListener(){

                //  If user is a student, lead to a page that allows them to enroll?
                // If user is an org, I guess show information about opportunity?


            }

        }


    }

}