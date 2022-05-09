package com.example.communityservicetracker.studentworkflow

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.communityservicetracker.OpportunityDetailActivity
import com.example.communityservicetracker.R
import com.example.communityservicetracker.orgworkflow.Opportunity
import com.example.communityservicetracker.orgworkflow.OpportunityListAdapter
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuth.*
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase


class StudentViewOpportunitiesAdapter  (private var oppsList: ArrayList<Opportunity>):
    RecyclerView.Adapter<StudentViewOpportunitiesAdapter.ViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): StudentViewOpportunitiesAdapter.ViewHolder{

        // Create, inflate, and return viewholder with view passed in
        val v = LayoutInflater.from(parent.context).inflate(R.layout.student_opportunity_item, parent, false)
        return ViewHolder(v)
    }

    // Function keeps iterating through the arrays
    override fun onBindViewHolder(holder: StudentViewOpportunitiesAdapter.ViewHolder, index: Int) {

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
        var moreButton : Button
        var addButton: Button


        init {

            // Initialize respective UI components
            itemTitle = itemView.findViewById(R.id.item_title)
            addButton = itemView.findViewById(R.id.add_button)
            moreButton = itemView.findViewById(R.id.moreButton)

            // Should lead to a different page that shows more about the opportunity
            moreButton.setOnClickListener(){

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

            addButton.setOnClickListener(){

                //  If user is a student, lead to a page that allows them to enroll?
                // If user is an org, I guess show information about opportunity?
                val pos : Int = adapterPosition
                var mAuth = FirebaseAuth.getInstance()
                val mDB = Firebase.database.reference
                val uid = mAuth!!.currentUser!!.uid
                Toast.makeText(
                    itemView.context,
                    "Opportunity  Added! See it on your profile",
                    Toast.LENGTH_LONG
                ).show()


            }

        }


    }


}