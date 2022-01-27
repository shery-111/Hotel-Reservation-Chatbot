package com.example.hotelreservationchatbot

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView

class HotelReviews : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hotel_reviews)
        val imageView = findViewById<ImageView>(R.id.fgp)
        imageView.alpha = 0.25f
        supportActionBar?.hide()

        var txt=findViewById<TextView>(R.id.revvv)
        val database="mydb"
        val mysharedpref=this.getSharedPreferences(database, Context.MODE_PRIVATE)

        var revv=mysharedpref.getString("reviews","NO REVIEWS")
        txt.setText(revv)


    }
}