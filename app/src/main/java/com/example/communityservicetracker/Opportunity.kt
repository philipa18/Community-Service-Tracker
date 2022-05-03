package com.example.communityservicetracker

import java.io.FileDescriptor

// Represents opportunity

data class Opportunity(val organizationName : String,
                       val description : String,
                       val title : String,
                       val timeCommitment : Int,
                       val numberOfApplicants : Int,
                       val listOfVolunteers : List<String>) {

    // List of volunteers stores ids (can be represented as strings)


    // toString method that provides description of opportunity
    override fun toString() : String{

        return ""


    }



}