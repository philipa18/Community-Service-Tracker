package com.example.communityservicetracker.orgworkflow

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import com.example.communityservicetracker.R
import com.google.firebase.auth.ktx.auth


// Used to get data about an organization
import com.google.firebase.database.*
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

// Class where organization's workflow will start
class OrgInfoActivity : AppCompatActivity() {

    // Buttons at bottom of page
    private lateinit var createOpp: Button
    private lateinit var viewOpps: Button
    private lateinit var profilePage: Button


    // Reference to database
    private lateinit var mDB: DatabaseReference


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_organization_information)

        // Sets buttons up and their respective listeners
        setupButtons()

        // Get reference to my database
        mDB = Firebase.database.reference

        // Retrieves user signed in
        val user = Firebase.auth.currentUser

        // Access current organization
        mDB.child("users").child(user!!.uid).child("name").get().addOnSuccessListener{

            // Gets name
            val name = it.value.toString()

            // Change text field to organization username
            findViewById<TextView>(R.id.orgUsername).text = "Name: " + name

            // Name of user in action bar
            supportActionBar!!.title = "$name's Profile";

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
        // Already on profile page so no need to implement on set click listener

    }




}