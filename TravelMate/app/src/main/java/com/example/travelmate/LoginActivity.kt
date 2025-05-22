package com.example.travelmate

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val etUsername = findViewById<EditText>(R.id.etUsername)

        findViewById<Button>(R.id.btnLogin).setOnClickListener {
            val username = etUsername.text.toString().trim()

            // Save to SharedPreferences
            val sharedPref = getSharedPreferences("user_prefs", MODE_PRIVATE)
            sharedPref.edit().putString("username", username).apply()

            startActivity(Intent(this, DashboardActivity::class.java))
            finish()
        }

        findViewById<TextView>(R.id.txtSignup).setOnClickListener {
            startActivity(Intent(this, SignupActivity::class.java))
        }
    }
}
