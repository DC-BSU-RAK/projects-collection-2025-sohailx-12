package com.example.travelmate

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class DashboardActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        // Load username from SharedPreferences
        val sharedPref = getSharedPreferences("user_prefs", MODE_PRIVATE)
        val username = sharedPref.getString("username", "User")

        // welcome text
        val welcomeText = findViewById<TextView>(R.id.txtWelcome)
        welcomeText.text = "Welcome, $username!"

        findViewById<Button>(R.id.btnFlights).setOnClickListener {
            startActivity(Intent(this, FlightsActivity::class.java))
        }

        findViewById<Button>(R.id.btnHotels).setOnClickListener {
            startActivity(Intent(this, HotelsActivity::class.java))
        }

        findViewById<Button>(R.id.btnTranslator).setOnClickListener {
            startActivity(Intent(this, TranslatorActivity::class.java))
        }
    }
}
