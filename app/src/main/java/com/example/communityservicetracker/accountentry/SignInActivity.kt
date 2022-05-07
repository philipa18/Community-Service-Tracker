package com.example.communityservicetracker.accountentry

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.communityservicetracker.R
import com.example.communityservicetracker.orgworkflow.DummyOrgActivity
import com.example.communityservicetracker.studentworkflow.StudentActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class SignInActivity : AppCompatActivity() {
    private lateinit var userEmail: EditText
    private lateinit var userPassword: EditText
    private lateinit var loginBtn: Button
    private var mAuth: FirebaseAuth? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)

        mAuth = FirebaseAuth.getInstance()

        userEmail = findViewById(R.id.emailEntry)
        userPassword = findViewById(R.id.passwordEntry)
        loginBtn = findViewById(R.id.signIn)

        loginBtn.setOnClickListener { signInAccount() }

    }

    private fun signInAccount() {
        val email: String = userEmail.text.toString()
        val password: String = userPassword.text.toString()

        if (TextUtils.isEmpty(email)) {
            Toast.makeText(applicationContext, "Please enter email...", Toast.LENGTH_LONG).show()
            return
        }
        if (TextUtils.isEmpty(password)) {
            Toast.makeText(applicationContext, "Please enter password!", Toast.LENGTH_LONG).show()
            return
        }

        mAuth!!.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val uid = mAuth!!.currentUser!!.uid
                    val mDB = Firebase.database.reference;
                    Log.d("UUID", uid)
                    mDB.child("users").child(uid).child("account_type").get()
                        .addOnSuccessListener {
                            val mUserType = it.value.toString()
                            Log.d("account_type", mUserType)
                            if (mUserType == "0")
                                startActivity(Intent(this@SignInActivity, StudentActivity::class.java))
                            else
                                startActivity(Intent(this@SignInActivity, DummyOrgActivity::class.java))

                        }
                    Toast.makeText(applicationContext, "Login successful!", Toast.LENGTH_LONG)
                        .show()


                } else {
                    Toast.makeText(
                        applicationContext,
                        "Login failed! Please try again later",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }

    }
}