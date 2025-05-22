package com.example.travelmate

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.google.mlkit.nl.translate.*
import com.google.mlkit.common.model.DownloadConditions

class TranslatorActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_translator)

        val editText = findViewById<EditText>(R.id.editInput)
        val output = findViewById<TextView>(R.id.txtOutput)
        val btnTranslate = findViewById<ImageButton>(R.id.btnSwap)
        val btnPlayInput = findViewById<ImageButton>(R.id.btnPlayInput)
        val btnPlayOutput = findViewById<ImageButton>(R.id.btnPlayOutput)
        val spinnerSource = findViewById<Spinner>(R.id.spinnerSource)
        val spinnerTarget = findViewById<Spinner>(R.id.spinnerTarget)

        // Define supported languages
        val languages = mapOf(
            "English" to TranslateLanguage.ENGLISH,
            "Spanish" to TranslateLanguage.SPANISH,
            "French" to TranslateLanguage.FRENCH,
            "German" to TranslateLanguage.GERMAN,
            "Hindi" to TranslateLanguage.HINDI,
            "Chinese" to TranslateLanguage.CHINESE,
            "Japanese" to TranslateLanguage.JAPANESE
        )

        // Setup of spinners
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, languages.keys.toList())
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerSource.adapter = adapter
        spinnerTarget.adapter = adapter


        spinnerSource.setSelection(adapter.getPosition("English"))
        spinnerTarget.setSelection(adapter.getPosition("Spanish"))

        btnTranslate.setOnClickListener {
            val inputText = editText.text.toString()
            val sourceLang = languages[spinnerSource.selectedItem.toString()] ?: TranslateLanguage.ENGLISH
            val targetLang = languages[spinnerTarget.selectedItem.toString()] ?: TranslateLanguage.SPANISH

            if (inputText.isNotEmpty()) {
                val options = TranslatorOptions.Builder()
                    .setSourceLanguage(sourceLang)
                    .setTargetLanguage(targetLang)
                    .build()

                val translator = Translation.getClient(options)
                val conditions = DownloadConditions.Builder().build()

                translator.downloadModelIfNeeded(conditions)
                    .addOnSuccessListener {
                        translator.translate(inputText)
                            .addOnSuccessListener { translatedText ->
                                output.text = translatedText
                            }
                            .addOnFailureListener {
                                Toast.makeText(this, "Translation failed: ${it.message}", Toast.LENGTH_LONG).show()
                            }
                    }
                    .addOnFailureListener {
                        Toast.makeText(this, " Download failed: ${it.message}", Toast.LENGTH_LONG).show()
                    }
            } else {
                Toast.makeText(this, "Enter text to translate", Toast.LENGTH_SHORT).show()
            }
        }

        btnPlayInput.setOnClickListener {
            Toast.makeText(this, "Playing input (TTS not implemented)", Toast.LENGTH_SHORT).show()
        }

        btnPlayOutput.setOnClickListener {
            Toast.makeText(this, "Playing output (TTS not implemented)", Toast.LENGTH_SHORT).show()
        }
    }
}
