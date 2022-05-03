package com.example.communityservicetracker.orgworkflow

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.communityservicetracker.R

// Class where organization's workflow will start
class DummyOrgActivity : AppCompatActivity() {

    // Buttons at bottom of page
    private lateinit var createOpp: Button
    private lateinit var viewOpps: Button
    private lateinit var profilePage: Button



    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dummy_org)

        // Set up buttons with their respective listeners
        createOpp = findViewById(R.id.createOpp)
        viewOpps = findViewById(R.id.viewOpps)
        profilePage = findViewById(R.id.profilePage)

        // WILO: Putting listeners here
        createOpp.setOnClickListener(){



        }




    }




}