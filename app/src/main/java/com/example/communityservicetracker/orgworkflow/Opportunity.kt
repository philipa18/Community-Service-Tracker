package com.example.communityservicetracker.orgworkflow

import android.content.Intent
import java.text.ParseException
import java.util.*
import kotlin.collections.ArrayList


// Represents opportunity
class Opportunity() {

    var organizationName : String? = String()
    var description : String? = String()
    var title : String? = String()
    var timeCommitment : Int = 0    // will change to value passed in
    var numberOfApplicants : Int = 0
    lateinit var listOfVolunteers : ArrayList<String>

    // listOfVolunteers: List of volunteers stores ids from database (can be represented as strings)

    // Constructor with details passed in
    internal constructor (organizationName : String?,
                          description : String?,
                          title : String?,
                          timeCommitment : Int,
                          numberOfApplicants : Int,
                          listOfVolunteers : ArrayList<String>) : this() {

        this.organizationName = organizationName
        this.description = description
        this.title = title
        this.timeCommitment = timeCommitment
        this.numberOfApplicants = numberOfApplicants
        this.listOfVolunteers = listOfVolunteers

    }


    // For FireBase UI
    //internal constructor() : this() {}

    // toString method that provides description of opportunity
    //override fun toString() : String{
    //    return "" //}



}