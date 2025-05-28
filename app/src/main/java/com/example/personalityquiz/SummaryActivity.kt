package com.example.personalityquiz

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class SummaryActivity : AppCompatActivity() {
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_summary)

        val personality = intent.getStringExtra("PERSONALITY") ?: "Brak danych"

        val textView = findViewById<TextView>(R.id.personalityText)
        val imageView = findViewById<ImageView>(R.id.personalityImage)
        val ratingBar = findViewById<RatingBar>(R.id.ratingBar)

        textView.text = "Twój typ osobowości: $personality"

        when (personality) {
            "Introwertyk" -> imageView.setImageResource(R.drawable.introvert)
            "Ambiwertyk" -> imageView.setImageResource(R.drawable.ambivert)
            "Ekstrawertyk" -> imageView.setImageResource(R.drawable.extrovert)
        }

        ratingBar.setOnRatingBarChangeListener { _, rating, _ ->
            Toast.makeText(this, "Ocena: $rating", Toast.LENGTH_SHORT).show()
        }
    }
}
