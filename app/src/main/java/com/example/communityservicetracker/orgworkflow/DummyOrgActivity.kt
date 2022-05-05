package com.example.communityservicetracker.orgworkflow

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import com.example.communityservicetracker.R
import com.google.firebase.auth.ktx.auth


// Used to get data about an organization
import com.google.firebase.database.*
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

// Class where organization's workflow will start
class DummyOrgActivity : AppCompatActivity() {

    // Buttons at bottom of page
    private lateinit var createOpp: Button
    private lateinit var viewOpps: Button
    private lateinit var profilePage: Button


    // Reference to database
    private lateinit var mDB: DatabaseReference


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dummy_org)

        // Sets buttons up and their respective listeners
        setupButtons()

        // Get reference to my database
        mDB = Firebase.database.reference

        // Retrieves user signed in
        val user = Firebase.auth.currentUser
        if (user != null) {
            // User is signed in

            val uid = user.uid
            Log.i("OrgInfoActivity","uid of user signed in: $uid")


        } else {
            // No user is signed in
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

            val intent = Intent(this@DummyOrgActivity, CreateOpportunityActivity::class.java)
            startActivity(intent)

        }

        // Listener to view opportunities already listed
        viewOpps.setOnClickListener(){

            val intent = Intent(this@DummyOrgActivity, ViewOpportunitiesActivity::class.java)
            startActivity(intent)


        }

        // Listener for profile page
        // Already on profile page so no need to implement anything
        profilePage.setOnClickListener(){

           // val intent = Intent(this@DummyOrgActivity, OrgInfoActivity::class.java)
            //startActivity(intent)

        }
    }




}