package com.example.socialsparks

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    // Tag for Logcat
    val tag = "SocialSparksApp"

    // View declarations
    lateinit var rgTime: RadioGroup
    lateinit var rbMorning: RadioButton
    lateinit var rbMidMorning: RadioButton
    lateinit var rbAfternoon: RadioButton
    lateinit var rbSnackTime: RadioButton
    lateinit var rbDinner: RadioButton
    lateinit var rbAfterDinner: RadioButton
    lateinit var btnSuggest: Button
    lateinit var btnReset: Button
    lateinit var tvOutput: TextView
    lateinit var tvError: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.i(tag, "App started")

        // Link views to XML
        rgTime = findViewById(R.id.rgTime)
        rbMorning = findViewById(R.id.rbMorning)
        rbMidMorning = findViewById(R.id.rbMidMorning)
        rbAfternoon = findViewById(R.id.rbAfternoon)
        rbSnackTime = findViewById(R.id.rbSnackTime)
        rbDinner = findViewById(R.id.rbDinner)
        rbAfterDinner = findViewById(R.id.rbAfterDinner)
        btnSuggest = findViewById(R.id.btnSuggest)
        btnReset = findViewById(R.id.btnReset)
        tvOutput = findViewById(R.id.tvOutput)
        tvError = findViewById(R.id.tvError)

        // Get spark button clicked
        btnSuggest.setOnClickListener {
            Log.d(tag, "Suggest button clicked")

            // Check if user selected a time
            if (rgTime.checkedRadioButtonId == -1) {
                Log.w(tag, "No time selected")
                tvError.visibility = TextView.VISIBLE
                tvError.text = "Please select a time of day before getting a spark!"
                tvOutput.text = ""
                return@setOnClickListener
            }

            // Clear error
            tvError.visibility = TextView.GONE
            tvError.text = ""

            // Get selected time label
            val selectedTime = when (rgTime.checkedRadioButtonId) {
                R.id.rbMorning -> "Morning"
                R.id.rbMidMorning -> "Mid-Morning"
                R.id.rbAfternoon -> "Afternoon"
                R.id.rbSnackTime -> "Afternoon Snack Time"
                R.id.rbDinner -> "Dinner"
                R.id.rbAfterDinner -> "After Dinner / Night"
                else -> "Unknown"
            }
            Log.d(tag, "Selected time: $selectedTime")

            // Handle unknown selection
            if (selectedTime == "Unknown") {
                Log.e(tag, "Unknown time selected")
                tvError.visibility = TextView.VISIBLE
                tvError.text = "Something went wrong. Please try again!"
                tvOutput.text = ""
                return@setOnClickListener
            }

            // Match time to suggestion
            val suggestion = when (rgTime.checkedRadioButtonId) {
                R.id.rbMorning -> "Send a 'Good Morning' text to a family member!"
                R.id.rbMidMorning -> "Reach out to a friend with a quick 'Thank you'!"
                R.id.rbAfternoon -> "Share a funny meme or interesting link with a friend!"
                R.id.rbSnackTime -> "Send a quick 'Thinking of you' message to someone special!"
                R.id.rbDinner -> "Call a friend or relative for a 5-minute catch-up!"
                R.id.rbAfterDinner -> "Leave a thoughtful comment on a friend's post!"
                else -> "No suggestion available."
            }
            Log.d(tag, "Suggestion: $suggestion")

            // Build and display result
            val result = StringBuilder()
            result.append("Time of Day: ").append(selectedTime).append("\n\n")
            result.append("Your Social Spark:\n")
            result.append(suggestion)

            tvOutput.text = result.toString()
            Log.i(tag, "Result displayed")
        }

        // Reset button - clears everything
        btnReset.setOnClickListener {
            Log.d(tag, "Reset button clicked")
            rgTime.clearCheck()
            tvOutput.text = ""
            tvError.visibility = TextView.GONE
            tvError.text = ""
        }
    }
}