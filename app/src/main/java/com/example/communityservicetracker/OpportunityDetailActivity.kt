package com.example.communityservicetracker

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class OpportunityDetailActivity : AppCompatActivity() {
    private lateinit var title: TextView
    private lateinit var description: TextView
    private lateinit var participants: TextView
    private lateinit var timeCommitment: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_opportunity_detail)

        title = findViewById(R.id.opportunityTitle)
        description = findViewById(R.id.opportunityDescription)
        participants = findViewById(R.id.opportunityParticipants)
        timeCommitment = findViewById(R.id.opportunityCommitment)

        title.text = intent.getStringExtra("title")
        description.text = intent.getStringExtra("description")
        participants.text = "Participants: " + intent.getStringExtra("participants")
        timeCommitment.text = "Time Commitment: " + intent.getStringExtra("timeCommitment")
    }
}