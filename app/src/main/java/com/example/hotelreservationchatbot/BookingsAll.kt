package com.example.hotelreservationchatbot

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.ListView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase

class BookingsAll : AppCompatActivity() {
    private lateinit var database: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bookings_all)
        val imageView = findViewById<ImageView>(R.id.fgp)
        var auth: FirebaseAuth = Firebase.auth
        imageView.alpha = 0.25f
        supportActionBar?.hide()
        var bk=Bookings()


        val database = Firebase.database
        val user = auth.currentUser
        val uid: String = user?.uid.toString()
        val db = database.getReference("Bookings")
        db.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {

                for (obj in snapshot.children) {
                    var temp = obj.getValue<Bookings>()
                    if(temp?.uid.toString()==uid)
                    {
                        bk.checkin.add(temp?.checkin.toString())
                        bk.hname.add(temp?.hname.toString())
                        bk.loc.add(temp?.loc.toString())
                        bk.checkout.add(temp?.checkout.toString())
                        bk.roomtype.add(temp?.roomtype.toString())
                        bk.trooms.add(temp?.trooms.toString().toInt())
                        bk.price.add(temp?.price.toString().toInt())
                    }

                }
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@BookingsAll, "Data cancelled", Toast.LENGTH_LONG).show()
            }

        })

        var mylis=findViewById<ListView>(R.id.mylist)
        mylis.adapter=CustomAdapter(this,bk)

    }
}