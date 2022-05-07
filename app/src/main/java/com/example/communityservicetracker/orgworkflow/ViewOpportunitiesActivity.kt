package com.example.communityservicetracker.orgworkflow

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.communityservicetracker.R
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.*
import com.google.firebase.ktx.Firebase

// Where we will be able to
class ViewOpportunitiesActivity : AppCompatActivity() {

    private lateinit var oppListAdapter: OpportunityListAdapter
    private lateinit var oppRecyclerView: RecyclerView
    private lateinit var listOpps : ArrayList<Opportunity>  // User's list of opps


    // Typical three buttons at bottom of page
    private lateinit var createOpp: Button
    private lateinit var viewOpps: Button
    private lateinit var profilePage: Button


    // Reference
    private lateinit var mDB : DatabaseReference


    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_opportunities)

        setupButtons()

        // Reference to recycler view
        oppRecyclerView = findViewById<RecyclerView>(R.id.oppRecyclerView)

        // Vertical display
        oppRecyclerView.layoutManager = LinearLayoutManager(this)


        // Adapter linkage
        //oppListAdapter = OpportunityListAdapter()
        //oppRecyclerView.adapter = oppListAdapter

        Log.i("CST", "inside onCreate before (if intent equals null)")

        if (intent == null) {
            Log.i("CST", "Intent is empty")
        } else {
            intent?.let { workWithIntent(intent) }
        }

        Log.i("CST", "inside onCreate after (if intent equals null) ")


        // Where we populate the recycler view
        getOpportunities()



    }



    // get list of opportunities
    private fun getOpportunities(){

        var oppTable = FirebaseDatabase.getInstance().getReference("opportunities")

        Log.i("CST", "${oppTable.get()}")



    }



    // If intent was passed in, then we know we have to add to the adapter
    private fun workWithIntent(dataIntent: Intent){

        if (dataIntent == null) {
            return
        } else {

            Log.i("CST", "In else of workWithIntent function")

            // Create empty list which will be able to store list of volunteers in the future
            var listVolunteers = ArrayList<String>()

            try{

                // If it exists, then we declare and initialize data
                val newOpp = dataIntent?.let {

                    Log.i("CST", "In newOpp")


                    Opportunity(
                        "",
                        it.getStringExtra("desc").toString(),
                        it.getStringExtra("title").toString(),
                        it.getStringExtra("hours").toString().toInt(),
                        0,
                        listVolunteers
                    )

                }

                Log.i("CST", "Before trying to add opportunity")

                // Add opportunity to list
                //oppListAdapter.add(newOpp)

                Log.i("CST", "After trying to add opportunity")


            } catch (e: Exception) {
                Log.i("CST", e.localizedMessage)
            }


        }
    }




    // Sets up buttons and their respective listeners
    private fun setupButtons(){

        // Set up buttons with their respective listeners
        createOpp = findViewById(R.id.createOpp)
        viewOpps = findViewById(R.id.viewOpps)
        profilePage = findViewById(R.id.profilePage)


        // Listener to create opportunity
        createOpp.setOnClickListener(){

            val intent = Intent(this@ViewOpportunitiesActivity, CreateOpportunityActivity::class.java)
            startActivity(intent)

            // Will be empty because if already on the create opp page,
            //  then there is no use for this button

        }

        // Listener to view opportunities already listed
        viewOpps.setOnClickListener(){

            //val intent = Intent(this@ViewOpportunitiesActivity, ViewOpportunitiesActivity::class.java)
            //startActivity(intent)


        }

        // Listener for profile page
        // Already on profile page so no need to implement anything
        profilePage.setOnClickListener(){

            val intent = Intent(this@ViewOpportunitiesActivity, DummyOrgActivity::class.java)
            startActivity(intent)

        }

    }

}
