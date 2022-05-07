package com.example.communityservicetracker.studentworkflow

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.communityservicetracker.R
import com.example.communityservicetracker.orgworkflow.Opportunity
import java.util.*
import kotlin.collections.ArrayList

class ViewOpportunitiesStudentView : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.view_opportunities_student_view)

        //Set up RecyclerView
        val mRecyclerView = findViewById<RecyclerView>(R.id.list_of_opportunities_available)

        //Set the layout manager
        mRecyclerView.layoutManager = LinearLayoutManager(this)

        // Set up the adapter
        val opportunities = ArrayList<Opportunity>()
        // add opportunities to the list

        mRecyclerView.adapter = MyRecyclerViewAdapter(opportunities,R.layout.opportunity_item)

    }
}