package com.example.nan

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var imgFruits: ImageView
    private lateinit var imgVeggies: ImageView
    private lateinit var imgProtein: ImageView
    private lateinit var imgGrains: ImageView

    private lateinit var btnCheck: Button
    private lateinit var tipOutput: TextView
    private lateinit var emojiStatus: TextView
    private lateinit var rotatingTip: TextView

    private var fruitsSelected = false
    private var veggiesSelected = false
    private var proteinSelected = false
    private var grainsSelected = false

    private val tips = listOf(
        "Drink water regularly to stay hydrated 💧",
        "Colorful plates = balanced meals 🥗",
        "Take a deep breath and stretch 🧘",
        "Add leafy greens to your day 🌿",
        "Avoid skipping meals – nourish your body 🍽️",
        "Walk for 10 minutes after meals 🚶",
        "Enjoy your food mindfully 🍓",
        "Sleep well, stay well 😴",
        "Gratitude fuels wellness 🌟",
        "Eat fruits, not just snacks 🍎"
    )
    private var tipIndex = 0
    private val tipHandler = Handler(Looper.getMainLooper())
    private val rotateTips = object : Runnable {
        override fun run() {
            rotatingTip.text = tips[tipIndex % tips.size]
            tipIndex++
            tipHandler.postDelayed(this, 5000)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Bind views
        imgFruits = findViewById(R.id.imgFruits)
        imgVeggies = findViewById(R.id.imgVeggies)
        imgProtein = findViewById(R.id.imgProtein)
        imgGrains = findViewById(R.id.imgGrains)
        btnCheck = findViewById(R.id.btnCheck)
        emojiStatus = findViewById(R.id.emojiStatus)
        rotatingTip = findViewById(R.id.rotatingTip)

        // Toggle selection behavior for each food group
        imgFruits.setOnClickListener {
            fruitsSelected = !fruitsSelected
            updateSelectionUI(imgFruits, fruitsSelected)
        }
        imgVeggies.setOnClickListener {
            veggiesSelected = !veggiesSelected
            updateSelectionUI(imgVeggies, veggiesSelected)
        }
        imgProtein.setOnClickListener {
            proteinSelected = !proteinSelected
            updateSelectionUI(imgProtein, proteinSelected)
        }
        imgGrains.setOnClickListener {
            grainsSelected = !grainsSelected
            updateSelectionUI(imgGrains, grainsSelected)
        }

        // Evaluate button logic with popup dialog
        btnCheck.setOnClickListener {
            val selectedCount = listOf(fruitsSelected, veggiesSelected, proteinSelected, grainsSelected).count { it }

            val dailyTip = when (selectedCount) {
                4 -> "Great! You covered all food groups today!"
                3 -> "Almost there! Try to add what’s missing tomorrow."
                2 -> "Let’s aim for more variety tomorrow!"
                1 -> "Try including more groups for a balanced day."
                else -> "Oops! A colorful plate keeps you healthy. Try again tomorrow!"
            }

            val emoji = when (selectedCount) {
                4 -> "😄"
                3 -> "🙂"
                2 -> "😐"
                1 -> "☹️"
                else -> "😞"
            }

            val message = "$dailyTip\n\n$emoji"

            AlertDialog.Builder(this)
                .setTitle("Daily Nutrition Check")
                .setMessage(message)
                .setPositiveButton("OK", null)
                .show()
        }

        // Start rotating wellness tips
        tipHandler.post(rotateTips)
    }

    private fun updateSelectionUI(imageView: ImageView, selected: Boolean) {
        if (selected) {
            imageView.setBackgroundResource(R.drawable.image_selected_bg)
        } else {
            imageView.setBackgroundResource(R.drawable.image_unselected_bg)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        tipHandler.removeCallbacks(rotateTips)
    }
}