package com.example.personalityquiz

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.os.SystemClock
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import java.util.*

class MainActivity : AppCompatActivity() {
    private lateinit var chronometer: Chronometer
    private lateinit var countDownTimer: CountDownTimer
    private var score = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val radioGroup = findViewById<RadioGroup>(R.id.radioGroup)
        val checkBox1 = findViewById<CheckBox>(R.id.checkBox1)
        val checkBox2 = findViewById<CheckBox>(R.id.checkBox2)
        val checkBox3 = findViewById<CheckBox>(R.id.checkBox3)
        val seekBar = findViewById<SeekBar>(R.id.seekBar)
        val spinner = findViewById<Spinner>(R.id.spinner)
        val dateButton = findViewById<Button>(R.id.dateButton)
        val timeButton = findViewById<Button>(R.id.timeButton)
        val endButton = findViewById<Button>(R.id.endButton)
        chronometer = findViewById(R.id.chronometer)

        // Start Chronometer
        chronometer.base = SystemClock.elapsedRealtime()
        chronometer.start()

        // Countdown Timer
        countDownTimer = object : CountDownTimer(120000, 1000) {
            override fun onTick(millisUntilFinished: Long) {}
            override fun onFinish() {
                Toast.makeText(this@MainActivity, "Czas minął!", Toast.LENGTH_SHORT).show()
                endButton.isEnabled = false
            }
        }.start()

        dateButton.setOnClickListener {
            val calendar = Calendar.getInstance()
            val datePicker = DatePickerDialog(this, { _, year, month, day ->
                dateButton.text = "$day/${month + 1}/$year"
            }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH))
            datePicker.show()
        }

        timeButton.setOnClickListener {
            val calendar = Calendar.getInstance()
            val timePicker = TimePickerDialog(this, { _, hour, minute ->
                timeButton.text = "$hour:$minute"
            }, calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), true)
            timePicker.show()
        }

        endButton.setOnClickListener {
            // Oblicz wynik na podstawie odpowiedzi
            score = 0
            when (radioGroup.checkedRadioButtonId) {
                R.id.radioButton1 -> score += 1
                R.id.radioButton2 -> score += 2
                R.id.radioButton3 -> score += 3
            }
            if (checkBox1.isChecked) score += 1
            if (checkBox2.isChecked) score += 2
            if (checkBox3.isChecked) score += 3
            score += seekBar.progress / 10

            val personality = when {
                score <= 5 -> "Introwertyk"
                score <= 10 -> "Ambiwertyk"
                else -> "Ekstrawertyk"
            }

            val intent = Intent(this, SummaryActivity::class.java)
            intent.putExtra("PERSONALITY", personality)
            startActivity(intent)
        }
    }
}