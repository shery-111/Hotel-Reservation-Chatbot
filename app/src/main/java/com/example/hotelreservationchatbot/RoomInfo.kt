package com.example.hotelreservationchatbot

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
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
import kotlinx.android.synthetic.main.activity_hotel_staff.*
import kotlinx.android.synthetic.main.activity_room_info.*

class RoomInfo : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_room_info)
        supportActionBar?.hide()
        val imageView = findViewById<ImageView>(R.id.regp)
        imageView.alpha = 0.25f


        var btn=findViewById<Button>(R.id.reg_btn)
        var auth: FirebaseAuth = Firebase.auth
        val database = Firebase.database
        val user = auth.currentUser
        val uid: String = user?.uid.toString()
        var room=Rooms()
        val db = database.getReference("Rooms")
        db.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {

                for (obj in snapshot.children) {
                    var temp = obj.getValue<Rooms>()
                    if(temp?.uid==uid)
                    {
                        room.economic= temp?.economic!!
                        room.luxury= temp?.luxury!!
                        ecorooms.setText(room.economic.toString())
                        luxrooms.setText(room.luxury.toString())
                        break
                    }
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@RoomInfo, "Data cancelled", Toast.LENGTH_LONG).show()
            }

        })

        btn.setOnClickListener {
            var noeco=findViewById<EditText>(R.id.ecorooms).text.toString().toInt()
            var nolux=findViewById<EditText>(R.id.luxrooms).text.toString().toInt()


            var rooms = Rooms()
            rooms.initializeobject(noeco, nolux,uid)
            val myRef = database.getReference("Rooms")
            myRef.child(uid).setValue(rooms)
            Toast.makeText(this,"Successfully saved Hotel Rooms Info",Toast.LENGTH_LONG).show()
            startActivity(Intent(this,HotelStaff::class.java))
            finish()



        }

    }
//    override fun onBackPressed() {
//
//        startActivity(Intent(this,HotelStaff::class.java))
//        finish()
//
//    }
}


