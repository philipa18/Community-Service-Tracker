package com.example.communityservicetracker.orgworkflow

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.communityservicetracker.R

// Where we will be able to
class ViewOpportunitiesActivity : Activity() {

    private lateinit var oppListAdapter: OpportunityListAdapter
    private lateinit var oppRecyclerView: RecyclerView

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


        // Adapter linkage
        oppListAdapter = OpportunityListAdapter()
        oppRecyclerView.adapter = oppListAdapter



    }

    /*
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
    }
    */

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
