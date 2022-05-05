package com.example.communityservicetracker.orgworkflow

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.communityservicetracker.R


class OpportunityListAdapter :
    RecyclerView.Adapter<OpportunityListAdapter.ViewHolder>() {

    // Array would store data retrieved from the user's list of opportunities in db
    private var titlesOpportunities = arrayOf("Title 01", "Title 02", "Title 03")
    private var descriptionsOfOpportunities = arrayOf("Desc 01", "Desc 02", "Desc 03")
    /*
    The arrays above store temporary data to help you both understand what the
    page would look like
*   */



    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): OpportunityListAdapter.ViewHolder {

        // Create, inflate, and return viewholder with view passed in
        val v = LayoutInflater.from(parent.context).inflate(R.layout.opportunity_item, parent, false)
        return ViewHolder(v)
    }

    // Function keeps iterating through the arrays
    override fun onBindViewHolder(holder: OpportunityListAdapter.ViewHolder, index: Int) {

        holder.itemTitle.text = titlesOpportunities[index]
        holder.itemDesc.text = descriptionsOfOpportunities[index]


    }


    // Need to identify how many items are being passed into our viewholder
    override fun getItemCount(): Int {
        return titlesOpportunities.size
    }


    // ViewHolder class
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        // UI components
        var itemTitle : TextView
        var itemDesc : TextView
        var moreButton : Button


        init {

            // Initialize respective UI components
            itemTitle = itemView.findViewById(R.id.item_title)
            itemDesc = itemView.findViewById(R.id.item_description)
            moreButton = itemView.findViewById(R.id.moreButton)

            // Should lead to a different page that shows more about the opportunity
            moreButton.setOnClickListener(){

                // If user is a student, lead to a page that allows them to enroll?
                // If user is an org, I guess show information about opportunity?


            }

        }


    }


}
