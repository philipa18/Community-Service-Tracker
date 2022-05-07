package com.example.communityservicetracker.orgworkflow

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import com.example.communityservicetracker.R
import com.google.firebase.auth.ktx.auth

// Used to get data about an organization
import com.google.firebase.database.*
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

// This idea for this class if that once the user (organization) signs in, they will be
//  shown their credentials. This information will be retrieved from their entry in the database.
class OrgInfoActivity : Activity() {

    // Reference to database
    private lateinit var mDB: DatabaseReference




    // Buttons at bottom of page
    private lateinit var createOpp: Button
    private lateinit var viewOpps: Button
    private lateinit var profilePage: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_organization_information)

        // Set up buttons
        setupButtons()


        // Get reference to my database
        mDB = Firebase.database.reference

        // Retrieves user signed in
        val user = Firebase.auth.currentUser
        if (user != null) {
            // User is signed in

            val name = user.displayName
            Log.i("OrgInfoActivity","Name of user signed in: $name")


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

            val intent = Intent(this@OrgInfoActivity, CreateOpportunityActivity::class.java)
            startActivity(intent)

        }

        // Listener to view opportunities already listed
        viewOpps.setOnClickListener(){

            val intent = Intent(this@OrgInfoActivity, ViewOpportunitiesActivity::class.java)
            startActivity(intent)


        }

        // Listener for profile page
        profilePage.setOnClickListener(){

           //val intent = Intent(this@OrgInfoActivity, OrgInfoActivity::class.java)
            //startActivity(intent)

        }
    }


}