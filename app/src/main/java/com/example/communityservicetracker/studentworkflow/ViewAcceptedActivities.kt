package com.example.communityservicetracker.studentworkflow

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.communityservicetracker.R
import com.example.communityservicetracker.orgworkflow.Opportunity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class ViewAcceptedActivities : AppCompatActivity() {
    private lateinit var oppListAdapter: ViewAcceptedAdapter
    private lateinit var oppRecyclerView: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.view_accepted_activities)
        //Set up RecyclerView
        oppRecyclerView = findViewById<RecyclerView>(R.id.studentRecyclerView)

        //Set the layout manager
        oppRecyclerView.layoutManager = LinearLayoutManager(this)

        getOpportunities()
    }


    // get list of opportunities
    private fun getOpportunities(){

        Log.i("CST", "inside getOpportunities before reference to table ")
        val mAuth = FirebaseAuth.getInstance()
        val uid = mAuth!!.currentUser!!.uid
        val mDB = Firebase.database.reference;
       val acceptedTable= mDB.child("users").child(uid).child("accepted").ref
        val oppTable = FirebaseDatabase.getInstance().getReference("users/$uid/accepted")


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
                    oppListAdapter = ViewAcceptedAdapter( listOpps)
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