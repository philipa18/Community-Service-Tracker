package com.example.communityservicetracker

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button

class MainActivity : AppCompatActivity() {

    /*
    Temporary buttons that lead to separate workflows. Plan to get rid of
    these once we all have our workflows ready.
    */
    private lateinit var nathanielButton: Button
    private lateinit var inesButton: Button
    private lateinit var philipButton: Button;


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        /*
        Set up buttons to lead to each group members' workflows.
        Should help with collaboration
         */
        philipButton = findViewById(R.id.PhilipButton)
        inesButton = findViewById(R.id.InesButton)
        nathanielButton = findViewById(R.id.NathanielButton)

        // Set onClickListener for each button
        philipButton.setOnClickListener(){
            processClick(it)
        }

        inesButton.setOnClickListener(){
            processClick(it)
        }

        nathanielButton.setOnClickListener(){
            processClick(it)
        }

    }

    // Depending on which button is clicked, we will be taken to that page
    fun processClick(v: View) {

        // First argument: current class
        // Second argument: the class that we will be directed to depending
        //      on the id of the button that we clicked

        var activityIntent = Intent(this@MainActivity,

            when ((v as Button).text)  {

                "Sign-in and account creation workflow" ->
                    SignInAccCreationActivity::class.java
                "Student Workflow" ->
                    StudentActivity::class.java
                else -> OrgActivity::class.java
            }

        )

        // Go to that class by displaying its contents on the screen
        startActivity(activityIntent)





    }
}