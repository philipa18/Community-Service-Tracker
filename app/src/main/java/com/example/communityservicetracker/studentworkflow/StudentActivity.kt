package com.example.communityservicetracker.studentworkflow

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.TextView
import com.example.communityservicetracker.R
import com.example.communityservicetracker.orgworkflow.CreateOpportunityActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase


// Ines
class StudentActivity : Activity() {
    private var hoursEarned = 0
    private lateinit var hoursEarnedView: TextView
    private lateinit var name: TextView
    private lateinit var grade: TextView
    private lateinit var hourGoal: TextView
    private lateinit var progress: ProgressBar
    private lateinit var viewAcceptedButton: Button
    private lateinit var viewAvailableButton: Button
    private var mAuth: FirebaseAuth? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_student)
        mAuth = FirebaseAuth.getInstance()

        //username = findViewById(R.id.username)
        name = findViewById(R.id.name)
        grade = findViewById(R.id.grade)
        hourGoal = findViewById(R.id.numSSLHours)
        progress = findViewById(R.id.progressBar)
        viewAcceptedButton = findViewById(R.id.viewAcceptedButton)
        viewAvailableButton = findViewById(R.id.viewOpportunitiesButton)
        hoursEarnedView = findViewById(R.id.numSSLHoursSoFar)

        val uid = mAuth!!.currentUser!!.uid
        val mDB = Firebase.database.reference
        // retrieve data for each field


        hoursEarnedView.text=hoursEarned.toString()
        mDB.child("users").child(uid).child("name").get()
            .addOnSuccessListener {
                val usernameType = it.value.toString()
                name.text = usernameType
            }
        mDB.child("users").child(uid).child("currHours").get()
            .addOnSuccessListener {
                val usernameType = it.value.toString()
                hoursEarnedView.text = usernameType
                progress.progress = it.value.toString().toInt()
            }


        mDB.child("users").child(uid).child("hour_goal").get()
            .addOnSuccessListener {
                val usernameType = it.value.toString()
                hourGoal.text = usernameType
               // progress.max(it.value.toString().toInt())
            }

        mDB.child("users").child(uid).child("grade").get()
            .addOnSuccessListener {
                val usernameType = it.value.toString()
                grade.text = usernameType
                // progress.max(it.value.toString().toInt())
            }

        viewAvailableButton.setOnClickListener(){

            val intent = Intent(this@StudentActivity, ViewOpportunitiesStudentView::class.java)
            startActivity(intent)

        }

        viewAcceptedButton.setOnClickListener(){

            val intent = Intent(this@StudentActivity, ViewAcceptedActivities::class.java)
            startActivity(intent)

        }
    }



}