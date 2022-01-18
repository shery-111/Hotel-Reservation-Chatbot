package com.example.hotelreservationchatbot

import android.content.Intent
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.ActionBarDrawerToggle
import kotlinx.android.synthetic.main.activity_hotel_staff.*
import kotlinx.android.synthetic.main.content_main.*

class HotelStaff : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hotel_staff)
        setSupportActionBar(toolbar)

        val toggle = ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.open,R.string.close)
        toggle.isDrawerIndicatorEnabled=true
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        var trvl3=findViewById<Button>(R.id.travler3)

        trvl3.setOnClickListener {
            startActivity(Intent(this,AccountInfo::class.java))
            finish()
        }

    }
}