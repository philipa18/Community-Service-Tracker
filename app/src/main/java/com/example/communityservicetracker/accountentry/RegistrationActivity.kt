package com.example.communityservicetracker.accountentry

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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
    private lateinit var userEmail: EditText
    private lateinit var userPassword: EditText
    private lateinit var regBtn: Button
    private var mAuth: FirebaseAuth? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)

        mAuth = FirebaseAuth.getInstance()

        header = findViewById(R.id.orgRegHeader)
        userEmail = findViewById(R.id.orgEmailReg)
        userPassword = findViewById(R.id.orgPasswordReg)
        regBtn = findViewById(R.id.orgRegButton)

        val accountType = intent.getIntExtra("account_type",0)
        if (accountType == 0)
            header.text = "Register a new student account"
        else
            header.text = "Register a new organization account"


        regBtn.setOnClickListener { registerNewUser(accountType) }
    }

    private fun registerNewUser(accountType: Int) {
        val email: String = userEmail.text.toString()
        val password: String = userPassword.text.toString()

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
                val mDB = Firebase.database.reference;
                mDB.child("users").child(task.result.user!!.uid).child("account_type").setValue(accountType)
                Toast.makeText(applicationContext, "Registration successful!", Toast.LENGTH_LONG).show()
                startActivity(Intent(this@RegistrationActivity, SignInActivity::class.java))
            } else {
                Toast.makeText(applicationContext, "Registration failed", Toast.LENGTH_LONG).show()
            }
        }

    }
}