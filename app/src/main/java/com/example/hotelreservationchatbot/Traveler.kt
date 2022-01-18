package com.example.hotelreservationchatbot

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.ActionBarDrawerToggle
import kotlinx.android.synthetic.main.activity_hotel_staff.*
import kotlinx.android.synthetic.main.content_main.*

class Traveler : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_traveler)
        setSupportActionBar(toolbar)

        val toggle = ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.open,R.string.close)
        toggle.isDrawerIndicatorEnabled=true
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
        var book=findViewById<Button>(R.id.tr1)
        var recm=findViewById<Button>(R.id.tr6)
        var chat=findViewById<Button>(R.id.tr5)
        var ac=findViewById<Button>(R.id.tr4)

        book.setOnClickListener {

        }
        recm.setOnClickListener {

        }

        chat.setOnClickListener {
            startActivity(Intent(this,Chatbot::class.java))

        }
        ac.setOnClickListener {
            startActivity(Intent(this,AccountInfo::class.java))

        }
    }
}