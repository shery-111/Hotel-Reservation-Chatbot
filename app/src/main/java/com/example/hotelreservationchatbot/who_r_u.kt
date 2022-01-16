package com.example.hotelreservationchatbot

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class who_r_u : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_who_ru)
        supportActionBar?.hide()
        val database="mydb"
        val mysharedpref=baseContext.getSharedPreferences(database, Context.MODE_PRIVATE)
        val editor=mysharedpref.edit()
        var bool:Boolean
        bool=mysharedpref.getBoolean("firsttime",true)
        if(bool) {

            var button1 = findViewById<Button>(R.id.rec_hotels)
            var button2 = findViewById<Button>(R.id.edit_hotel)

            button1.setOnClickListener {
                var intent = Intent(this, Login::class.java)
                bool=false
                editor.putBoolean("firsttime",bool)
                editor.putBoolean("user",false) //false for Traveler
                editor.apply()
                editor.commit()
                startActivity(intent)
                finish()
            }
            button2.setOnClickListener {
                var intent = Intent(this, Login::class.java)
                bool=false
                editor.putBoolean("firsttime",bool)
                editor.putBoolean("user",true) //true for HotelStaff
                editor.apply()
                editor.commit()
                startActivity(intent)
                finish()
            }
        }
        else
        {
            startActivity(Intent(this,Login::class.java))
            finish()
        }
    }
}