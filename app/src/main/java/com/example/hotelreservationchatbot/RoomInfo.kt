package com.example.hotelreservationchatbot

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class RoomInfo : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_room_info)
        supportActionBar?.hide()
        val imageView = findViewById<ImageView>(R.id.regp)
        imageView.alpha = 0.25f
        var auth: FirebaseAuth = Firebase.auth

        var btn=findViewById<Button>(R.id.reg_btn)

        btn.setOnClickListener {
            var noeco=findViewById<EditText>(R.id.ecorooms).text.toString().toInt()
            var nolux=findViewById<EditText>(R.id.luxrooms).text.toString().toInt()

            val user = auth.currentUser
            val uid: String = user?.uid.toString()
            val database = Firebase.database
            var rooms = Rooms()
            rooms.initializeobject(noeco, nolux,uid)
            val myRef = database.getReference("Rooms")
            myRef.setValue(rooms)
            Toast.makeText(this,"Successfully saved Hotel Rooms Info",Toast.LENGTH_LONG).show()
            startActivity(Intent(this,HotelStaff::class.java))
            finish()

            onBackPressed()

        }

    }
    override fun onBackPressed() {

        startActivity(Intent(this,HotelStaff::class.java))
        finish()

    }
}


