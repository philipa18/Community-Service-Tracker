package com.example.communityservicetracker.accountentry

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.communityservicetracker.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class RegistrationActivity : AppCompatActivity() {

    private lateinit var header: TextView
    private lateinit var userName: EditText
    private lateinit var userEmail: EditText
    private lateinit var userPassword: EditText
    private lateinit var studentGrade: EditText
    private lateinit var studentGoal: EditText
    private lateinit var regBtn: Button
    private var mAuth: FirebaseAuth? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)

        mAuth = FirebaseAuth.getInstance()

        header = findViewById(R.id.regHeader)
        userName = findViewById(R.id.nameReg)
        userEmail = findViewById(R.id.emailReg)
        userPassword = findViewById(R.id.passwordReg)
        studentGrade = findViewById(R.id.gradeReg)
        studentGoal = findViewById(R.id.goalReg)
        regBtn = findViewById(R.id.regButton)

        val accountType = intent.getIntExtra("account_type",0)
        if (accountType == 0)
            header.text = "Register a new student account"
//            studentGrade.visibility = View.VISIBLE
//            studentGoal.visibility = View.VISIBLE
        else {
            header.text = "Register a new organization account"
            studentGrade.visibility = View.GONE
            studentGoal.visibility = View.GONE
        }


        regBtn.setOnClickListener { registerNewUser(accountType) }
    }

    private fun registerNewUser(accountType: Int) {
        val email: String = userEmail.text.toString()
        val password: String = userPassword.text.toString()
        val name: String = userName.text.toString()

//        if (!validator.validEmail(email)) {
//            Toast.makeText(applicationContext, "Please enter valid email...", Toast.LENGTH_LONG).show()
//            return
//        }
//        if (!validator.validPassword(password)) {
//            Toast.makeText(applicationContext, "Password must contain 8 characters with one letter and one number!", Toast.LENGTH_LONG).show()
//            return
//        }

        val x = mAuth!!.createUserWithEmailAndPassword(email, password)

        x.addOnCompleteListener { task ->
            if (task.isSuccessful) {
                val mDB = Firebase.database.reference
                val uid = task.result.user!!.uid
                mDB.child("users").child(uid).child("account_type").setValue(accountType)
                mDB.child("users").child(uid).child("name").setValue(name)

                val accountType = intent.getIntExtra("account_type",0)
                if (accountType == 0) {
                    mDB.child("users")
                        .child(uid)
                        .child("currHours")
                        .setValue(0)
                    mDB.child("users")
                        .child(uid)
                        .child("grade")
                        .setValue(studentGrade.text.toString())
                    mDB.child("users")
                        .child(uid)
                        .child("hour_goal")
                        .setValue(studentGoal.text.toString())
                }

                Toast.makeText(applicationContext, "Registration successful!", Toast.LENGTH_LONG).show()
                startActivity(Intent(this@RegistrationActivity, SignInActivity::class.java))
            } else {
                Toast.makeText(applicationContext, "Registration failed", Toast.LENGTH_LONG).show()
            }
        }

    }
}