package com.example.travelmate

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class SignupActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        val etUsername = findViewById<EditText>(R.id.etUsername)

        findViewById<Button>(R.id.btnSignup).setOnClickListener {
            val username = etUsername.text.toString().trim()
            val sharedPref = getSharedPreferences("user_prefs", MODE_PRIVATE)
            sharedPref.edit().putString("username", username).apply()

            startActivity(Intent(this, DashboardActivity::class.java))
            finish()
        }
    }
}
