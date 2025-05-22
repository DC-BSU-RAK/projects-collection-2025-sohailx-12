package com.example.travelmate

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity

class FlightsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_flights)

        val slots = listOf(
            R.id.slot1, R.id.slot2, R.id.slot3,
            R.id.slot4, R.id.slot5, R.id.slot6
        )

        slots.forEach { id ->
            findViewById<View>(id).setOnClickListener {
                startActivity(Intent(this, ComingSoonActivity::class.java))
            }
        }
    }
}
