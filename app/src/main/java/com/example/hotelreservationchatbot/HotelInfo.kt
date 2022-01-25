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
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase

class HotelInfo : AppCompatActivity() {
    var i=0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hotel_info)
        val imageView = findViewById<ImageView>(R.id.infop)
        imageView.alpha = 0.25f
        supportActionBar?.hide()
        var auth: FirebaseAuth = Firebase.auth


        val database = Firebase.database
        val user = auth.currentUser
        val uid: String = user?.uid.toString()
        val db = database.getReference("Hotels")
        var btn=findViewById<Button>(R.id.save_btn)

        var ht=Hotel()
        db.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                var hname = findViewById<EditText>(R.id.hname)
                var address = findViewById<EditText>(R.id.address)
                var city = findViewById<EditText>(R.id.city)
                var phone = findViewById<EditText>(R.id.phn_hotel)
                var eco = findViewById<EditText>(R.id.economic)
                var lux = findViewById<EditText>(R.id.luxury)

                for (obj in snapshot.children) {
                    var temp = obj.getValue<Hotel>()
                    if(temp?.uid.toString()==uid)
                    {
                        hname.setText(temp?.name)
                        address.setText(temp?.address)
                        city.setText(temp?.city)
                        phone.setText(temp?.phone)
                        eco.setText(temp?.economic.toString())
                        lux.setText(temp?.luxury.toString())
//                        btn.setText("Save")
                        i=i+1
                        break


                    }

                }
//                Toast.makeText(this@HotelInfo,ht.name+ht.luxury,Toast.LENGTH_LONG).show()
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@HotelInfo, "Data cancelled", Toast.LENGTH_LONG).show()
            }

        })

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

                        val user = auth.currentUser
                        val uid: String = user?.uid.toString()
                        val database = Firebase.database
                        val myRef = database.getReference("Hotels")
                        var hotel = Hotel()
                        hotel.initializeobject(hname, address, phone, city, eco, lux, uid)
                        myRef.child(uid).setValue(hotel)
                        Toast.makeText(this, "Successfully saved Hotel Info", Toast.LENGTH_LONG)
                            .show()
                        startActivity(Intent(this, HotelStaff::class.java))
                        finish()

                    }


            }



//            onBackPressed()




    }
    override fun onBackPressed() {

        startActivity(Intent(this,HotelStaff::class.java))
        finish()

    }
}