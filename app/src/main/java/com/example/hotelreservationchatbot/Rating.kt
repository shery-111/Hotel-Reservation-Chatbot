package com.example.hotelreservationchatbot

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_rating.*

class Rating : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rating)
        supportActionBar?.hide()
        val imageView = findViewById<ImageView>(R.id.fgp)
        imageView.alpha = 0.25f


        val rBar = findViewById<RatingBar>(R.id.ratingBar)

        val btn=findViewById<Button>(R.id.ratebtn)

        val database="mydb"
        val mysharedpref=this.getSharedPreferences(database, Context.MODE_PRIVATE)


        btn.setOnClickListener {
                val msg = rBar.rating.toString()
                Toast.makeText(this, "Rating is: "+msg, Toast.LENGTH_SHORT).show()
        }



    }
}