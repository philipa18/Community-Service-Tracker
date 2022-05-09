package com.example.communityservicetracker.studentworkflow

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.communityservicetracker.R
import com.example.communityservicetracker.orgworkflow.Opportunity
import com.example.communityservicetracker.studentworkflow.StudentViewOpportunitiesAdapter
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import java.util.*
import kotlin.collections.ArrayList

class ViewOpportunitiesStudentView : AppCompatActivity() {
    private lateinit var oppListAdapter: StudentViewOpportunitiesAdapter
    private lateinit var oppRecyclerView: RecyclerView
    private lateinit var viewProfileButton: Button
    private lateinit var viewAcceptedButton: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.view_opportunities_student_view)

        viewProfileButton = findViewById(R.id.profilePage)
        viewAcceptedButton = findViewById(R.id.viewOpps)
        viewAcceptedButton.setOnClickListener(){

            val intent = Intent(this@ViewOpportunitiesStudentView, ViewAcceptedActivities::class.java)
            startActivity(intent)

        }

        viewProfileButton.setOnClickListener(){

            val intent = Intent(this@ViewOpportunitiesStudentView, StudentActivity::class.java)
            startActivity(intent)

        }
        //Set up RecyclerView
        oppRecyclerView = findViewById<RecyclerView>(R.id.oppRecyclerView)

        //Set the layout manager
        oppRecyclerView.layoutManager = LinearLayoutManager(this)

       getOpportunities()

    }


    // get list of opportunities
    private fun getOpportunities(){

        Log.i("CST", "inside getOpportunities before reference to table ")

        val oppTable = FirebaseDatabase.getInstance().getReference("opportunities")

        Log.i("CST", "inside getOpportunities after reference to table ")

        oppTable.addValueEventListener(object : ValueEventListener {



            override fun onDataChange(snapshot: DataSnapshot) {

                var listOpps = arrayListOf<Opportunity>()

                try {

                    //listOpps.clear()

                    Log.i("CST", "in try/catch block in onDataChange")

                    // Iterates through opportunities stored in db
                    for (dataSnapshot in snapshot.children) {

                        // Retrieve current opportunity
                        val currOpp : Opportunity? = dataSnapshot.getValue(Opportunity::class.java)

                        Log.i("CST", "${currOpp!!.organizationName}")

                        // Check if this opportunity has been posted by the current user (organization)
                        //if (currOpp!!.organizationName.toString() == Firebase.auth.currentUser?.displayName.toString()) {

                        // Add to list to be passed to adapter
                        listOpps.add(currOpp!!)
                        //}
                    }

                    // Adapter linkage
                    oppListAdapter = StudentViewOpportunitiesAdapter( listOpps)
                    //Log.i("CST", "AAAA")
                    oppRecyclerView.adapter = oppListAdapter
                    //Log.i("CST", "BBBB")
                    oppListAdapter.notifyDataSetChanged() // Necessary?``
                    //Log.i("CST", "CCCC")


                } catch (e: Exception) {
                    Log.i("CST", e.localizedMessage!!)
                }

            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }


        })


        Log.i("CST", "inside getOpportunities after adding value event listener")



    }
}