package com.example.communityservicetracker.orgworkflow

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.communityservicetracker.R
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.*
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase


// Where we will be able to
class ViewOpportunitiesActivity : AppCompatActivity() {

    private lateinit var oppListAdapter: OpportunityListAdapter
    private lateinit var oppRecyclerView: RecyclerView
    //private lateinit var listOpps : ArrayList<Opportunity>  // User's list of opps


    // Typical three buttons at bottom of page
    private lateinit var createOpp: Button
    private lateinit var viewOpps: Button
    private lateinit var profilePage: Button

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_opportunities)

        setupButtons()

        // Reference to recycler view
        oppRecyclerView = findViewById<RecyclerView>(R.id.oppRecyclerView)

        // Vertical display
        oppRecyclerView.layoutManager = LinearLayoutManager(this)

        // Where we populate the recycler view
        getOpportunities()

        // Get reference to my database
        val mDB = Firebase.database.reference

        // Retrieves user signed in
        val user = Firebase.auth.currentUser

        // Access current organization
        mDB.child("users").child(user!!.uid).child("name").get().addOnSuccessListener{

            val name = it.value.toString()

            // Name of user
            supportActionBar!!.title = "View $name's Opportunities";

        }

    }



    // get list of opportunities
    private fun getOpportunities(){


        // Reference to opportunity table
        val oppTable = FirebaseDatabase.getInstance().getReference("opportunities")

        // Listener for when an opportunity object is added to the table
        oppTable.addValueEventListener(object : ValueEventListener{

            override fun onDataChange(snapshot: DataSnapshot) {

                // Will store opportunities from db
                var listOpps = arrayListOf<Opportunity>()

                try {

                    Log.i("CST", "in try/catch block in onDataChange")

                    // Iterates through opportunities stored in db
                    for (dataSnapshot in snapshot.children) {

                        // Retrieve current opportunity
                        val currOpp : Opportunity? = dataSnapshot.getValue(Opportunity::class.java)

                        Log.i("CST", "${currOpp!!.organizationName}")

                        // Check if this opportunity has been posted by the current user (organization)
                        if (currOpp!!.organizationName.toString() == Firebase.auth.currentUser?.uid.toString()) {

                            // Add to list to be passed to adapter
                            listOpps.add(currOpp!!)
                        }
                    }

                    // Adapter linkage
                    oppListAdapter = OpportunityListAdapter(listOpps)
                    oppRecyclerView.adapter = oppListAdapter
                    oppListAdapter.notifyDataSetChanged() // Necessary?



                } catch (e: Exception) {
                    Log.i("CST", e.localizedMessage!!)
                }

            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }


        })

    }





    // Sets up buttons and their respective listeners
    private fun setupButtons(){

        // Set up buttons with their respective listeners
        createOpp = findViewById(R.id.createOpp)
        viewOpps = findViewById(R.id.viewOpps)
        profilePage = findViewById(R.id.profilePage)


        // Listener to create opportunity
        createOpp.setOnClickListener(){

            val intent = Intent(this@ViewOpportunitiesActivity,
                CreateOpportunityActivity::class.java)
            startActivity(intent)

        }

        // Already on view opps page so no need to implement any listener

        // Listener for profile page
        profilePage.setOnClickListener(){

            val intent = Intent(this@ViewOpportunitiesActivity, OrgInfoActivity::class.java)
            startActivity(intent)

        }

    }

}
