package com.example.hotelreservationchatbot

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView

class Rating : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rating)
        supportActionBar?.hide()
        val imageView = findViewById<ImageView>(R.id.fgp)
        imageView.alpha = 0.25f



    }
}