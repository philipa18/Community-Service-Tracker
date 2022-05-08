package com.example.communityservicetracker.orgworkflow

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.communityservicetracker.OpportunityDetailActivity
import com.example.communityservicetracker.R


class OpportunityListAdapter(
    private var oppsList: ArrayList<Opportunity>) :
    RecyclerView.Adapter<OpportunityListAdapter.ViewHolder>() {

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

        Log.i("CST", "in onBindViewHolder")

        holder.itemTitle.text = oppsList[index].title
        holder.itemDesc.text = oppsList[index].description

    }


    // Need to identify how many items are being passed into our viewholder
    override fun getItemCount(): Int {
        return oppsList.size
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
            moreButton.setOnClickListener{

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
                startActivity(itemView.context, i, null)


            }

        }


    }


}
