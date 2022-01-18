package com.example.hotelreservationchatbot

import android.content.Context
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

class HotelInfo : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hotel_info)
        val imageView = findViewById<ImageView>(R.id.infop)
        imageView.alpha = 0.25f
        supportActionBar?.hide()
        var auth: FirebaseAuth = Firebase.auth

        var btn=findViewById<Button>(R.id.save_btn)
        val database="mydb"
        val mysharedpref=baseContext.getSharedPreferences(database, Context.MODE_PRIVATE)
        val editor=mysharedpref.edit()
        var bool:Boolean
        bool=mysharedpref.getBoolean("hotelinfo",true)
        if(bool) {
            btn.setOnClickListener {
                var hname = findViewById<EditText>(R.id.hname).text.toString()
                var address = findViewById<EditText>(R.id.address).text.toString()
                var city = findViewById<EditText>(R.id.city).text.toString()
                var phone = findViewById<EditText>(R.id.phn_hotel).text.toString()
                var eco = findViewById<EditText>(R.id.economic).text.toString().toInt()
                var lux = findViewById<EditText>(R.id.luxury).text.toString().toInt()

                if (hname.length < 5) {
                    Toast.makeText(this, "Enter correct Hotel Name", Toast.LENGTH_LONG).show()
                } else if (address.length < 8) {
                    Toast.makeText(this, "Enter correct Address", Toast.LENGTH_LONG).show()
                } else if (city.length < 5) {
                    Toast.makeText(this, "Enter correct City", Toast.LENGTH_LONG).show()
                } else if (phone.length != 11) {
                    Toast.makeText(this, "Enter correct Phone", Toast.LENGTH_LONG).show()
                } else if (lux < 500) {
                    Toast.makeText(this, "Enter correct Luxury room price", Toast.LENGTH_LONG)
                        .show()
                } else {
                    var hotel = Hotel()
                    hotel.initializeobject(hname, address, phone, city, eco, lux)
                    val user = auth.currentUser
                    val uid: String = user?.uid.toString()
                    val database = Firebase.database
                    val myRef = database.getReference("Hotels")
                    myRef.child(uid).setValue(hotel)
                    bool=false
                    editor.putBoolean("hotelinfo",bool)
                    editor.apply()
                    editor.commit()

                }
            }
        }
        else
        {
            startActivity(Intent(this,HotelStaff::class.java))
            finish()
        }
    }
}