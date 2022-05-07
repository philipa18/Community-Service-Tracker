package com.example.communityservicetracker.orgworkflow

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.communityservicetracker.R
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase


// Page that allows organization to create a new opportunity to be posted
class CreateOpportunityActivity : AppCompatActivity() {

    // Typical three buttons at bottom of page
    private lateinit var createOpp: Button
    private lateinit var viewOpps: Button
    private lateinit var profilePage: Button

    // Two buttons (submit or cancel) above the three
    private lateinit var submitButton: Button
    private lateinit var cancelButton: Button

    // Text fields
    private lateinit var titleOpp : EditText
    private lateinit var descOpp : EditText
    private lateinit var hoursOpp : EditText



    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_opportunity)

        // Sets buttons up and their respective listeners
        setupButtons()

        // Set up text fields
        setUpTextFields()

        // Initialize getResult


    }

    // Sets up textfields and their listeners
    private fun setUpTextFields(){

        titleOpp = findViewById(R.id.titleOpp)
        descOpp = findViewById(R.id.descOpp)
        hoursOpp = findViewById(R.id.hoursOpp)
    }




    // Sets up buttons and their respective listeners
    private fun setupButtons(){

        // Set up buttons with their respective listeners
        createOpp = findViewById(R.id.createOpp)
        viewOpps = findViewById(R.id.viewOpps)
        profilePage = findViewById(R.id.profilePage)

        submitButton = findViewById(R.id.submitButton)
        cancelButton = findViewById(R.id.cancelButton)



        // Listener to create opportunity
        createOpp.setOnClickListener(){

            //val intent = Intent(this@DummyOrgActivity, CreateOpportunityActivity::class.java)
            //startActivity(intent)

            // Will be empty because if already on the create opp page,
            //  then there is no use for this button

        }

        // Listener to view opportunities already listed
        viewOpps.setOnClickListener(){

            val intent = Intent(this@CreateOpportunityActivity, ViewOpportunitiesActivity::class.java)
            startActivity(intent)


        }

        // Listener for profile page
        // Already on profile page so no need to implement anything
        profilePage.setOnClickListener(){

            val intent = Intent(this@CreateOpportunityActivity, DummyOrgActivity::class.java)
            startActivity(intent)

        }

        // For submit button, validate data in each text box
        submitButton.setOnClickListener(){

            // validate data in each text box
            // If invalid,
                // send toast to make user type of valid values
            // If valid,
                // pass the input into opportunity class
                // add the opportunity to list of opportunities in db?
                // Go to page with recycler view and display that the opportunity is available

            // validate data: check if any of the blocks are empty

            /* Everything in the commented block below will be used eventually.
                I only did it to prevent app from crashing.
            */

            if (titleOpp.text.toString().isEmpty() ||
                descOpp.text.toString().isEmpty() ||
                hoursOpp.text.toString().isEmpty()) {

                Toast.makeText(this,
                    "There exists an empty text box. Please try again",
                    Toast.LENGTH_LONG).show()

            } else {

                // Store info in variables to be passed into opportunity class
                val title: String = titleOpp.text.toString()
                val desc: String = descOpp.text.toString()
                val hours: Int = hoursOpp.text.toString().toInt()

                Log.i("CST", "Before creating the data intent")

                var dataIntent = Intent(this@CreateOpportunityActivity,
                    ViewOpportunitiesActivity::class.java)

                //dataIntent.

                dataIntent.putExtra("desc", desc)
                dataIntent.putExtra("title", title)
                dataIntent.putExtra("hours", hours.toString())

                Log.i("CST", "Before starting activity")


                // Add opportunity to database
                var dbOpportunities = FirebaseDatabase.getInstance().getReference("opportunities")


                var listVolunteers = ArrayList<String>()


                //val oppName = FirebaseDatabase.User

                // New opportunity
                val newOpp : Opportunity = Opportunity(
                    Firebase.auth.currentUser?.uid.toString(),
                    desc,
                    title,
                    hours.toInt(),
                    0,
                    listVolunteers
                )

                // Creates random key for new opportunity
                val opp_id : String? = dbOpportunities.push().getKey()

                // Adding an opportunity with the generated id to the table
                dbOpportunities.child(opp_id!!)
                    .setValue(newOpp)

                // Send data to activity
                startActivity(dataIntent)

                Log.i("CST", "After starting activity")





                //finish()

            }




        }


        // For cancel button, mimic the back button
        cancelButton.setOnClickListener(){

            setResult(Activity.RESULT_CANCELED)
            finish()

        }


    }




}