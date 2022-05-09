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
        val grade: String = studentGrade.text.toString()
        val goal: String = studentGoal.text.toString()

        val emailRegex = Regex("(?:[a-z0-9!#\$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#\$%&'" +
                "*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x" +
                "5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z" +
                "0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4" +
                "][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z" +
                "0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|" +
                "\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)])")

        if (email.isEmpty() || !emailRegex.matches(email)) {
            Toast.makeText(applicationContext, "Please enter valid email", Toast.LENGTH_LONG).show()
            return
        }

        val passwordRegex = Regex("^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}\$")

        if (password.isEmpty() || !passwordRegex.matches(password)) {
            Toast.makeText(applicationContext, "Password must contain 8 characters with one letter and one number", Toast.LENGTH_LONG).show()
            return
        }

        if (name.isEmpty()) {
            Toast.makeText(applicationContext, "Please enter a name", Toast.LENGTH_LONG).show()
            return
        }

        if (accountType == 0 && grade.isEmpty()) {
            Toast.makeText(applicationContext, "Please enter your grade", Toast.LENGTH_LONG).show()
            return
        }

        if (accountType == 0 && goal.isEmpty()) {
            Toast.makeText(applicationContext, "Please enter your hour goal", Toast.LENGTH_LONG).show()
            return
        }



        val x = mAuth!!.createUserWithEmailAndPassword(email, password)

        x.addOnCompleteListener { task ->
            if (task.isSuccessful) {
                val mDB = Firebase.database.reference
                val uid = task.result.user!!.uid
                mDB.child("users").child(uid).child("account_type").setValue(accountType)
                mDB.child("users").child(uid).child("name").setValue(name)

                if (accountType == 0) {
                    mDB.child("users")
                        .child(uid)
                        .child("currHours")
                        .setValue(0)
                    mDB.child("users")
                        .child(uid)
                        .child("grade")
                        .setValue(grade)
                    mDB.child("users")
                        .child(uid)
                        .child("hour_goal")
                        .setValue(goal)
                }

                Toast.makeText(applicationContext, "Registration successful!", Toast.LENGTH_LONG).show()
                startActivity(Intent(this@RegistrationActivity, SignInActivity::class.java))
            } else {
                Toast.makeText(applicationContext, "Registration failed", Toast.LENGTH_LONG).show()
            }
        }

    }
}