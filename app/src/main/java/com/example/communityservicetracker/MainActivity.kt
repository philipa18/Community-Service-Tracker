package com.example.communityservicetracker

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.communityservicetracker.accountentry.RegistrationActivity
import com.example.communityservicetracker.accountentry.SignInActivity

class MainActivity : AppCompatActivity() {

    private lateinit var loginBtn: Button
    private lateinit var studentReg: Button
    private lateinit var orgReg: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        loginBtn = findViewById(R.id.button)
        studentReg = findViewById(R.id.button2)
        orgReg = findViewById(R.id.button3)

        loginBtn.setOnClickListener {
            startActivity(Intent(this@MainActivity, SignInActivity::class.java))
        }

        studentReg.setOnClickListener {
            val intent = Intent(this@MainActivity, RegistrationActivity::class.java)
            intent.putExtra("account_type", 0)
            startActivity(intent)
        }

        orgReg.setOnClickListener {
            val intent = Intent(this@MainActivity, RegistrationActivity::class.java)
            intent.putExtra("account_type", 1)
            startActivity(intent)
        }
    }
}