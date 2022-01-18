package com.example.hotelreservationchatbot

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class Traveler : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_traveler)
        supportActionBar?.hide()
        var trav=findViewById<Button>(R.id.rec_hotels)
        var chat=findViewById<Button>(R.id.chatbot_btn)
        var ac=findViewById<Button>(R.id.accountinfo)

        trav.setOnClickListener {

        }

        chat.setOnClickListener {
            startActivity(Intent(this,Chatbot::class.java))

        }
        ac.setOnClickListener {
            startActivity(Intent(this,AccountInfo::class.java))

        }
    }
}