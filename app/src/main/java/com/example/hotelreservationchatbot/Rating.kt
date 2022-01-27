package com.example.hotelreservationchatbot

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.View
import android.widget.*
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_hotel_staff.*
import kotlinx.android.synthetic.main.activity_rating.*

class Rating : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rating)
        supportActionBar?.hide()
        val imageView = findViewById<ImageView>(R.id.fgp)
        imageView.alpha = 0.25f
        var auth: FirebaseAuth = Firebase.auth

        val rBar = findViewById<RatingBar>(R.id.ratingBar)
        val datab="mydb"
        val mysharedpref=this.getSharedPreferences(datab, Context.MODE_PRIVATE)

        val btn=findViewById<Button>(R.id.ratebtn)
        var hname=mysharedpref.getString("hname","Hotel name").toString()
        var loc=mysharedpref.getString("loc","Location").toString()
        var checkin=mysharedpref.getString("checkin","checkin date").toString()

        val user = auth.currentUser
        val uid: String = user?.uid.toString()
        val database = Firebase.database
        val db = database.getReference("Ratings")
        db.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {

                for (obj in snapshot.children) {
                    var temp = obj.getValue<Rate>()
                    if(uid==temp?.uid && hname==temp?.hname && temp?.city==loc && temp?.checkin==checkin)
                    {
                        btn.visibility= View.INVISIBLE
                        review.setText(temp?.review)
                        rBar.rating=temp?.rating.toFloat()
                        rBar.setIsIndicator(true)
                        review.keyListener=null


                    }

                }
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@Rating, "Data cancelled", Toast.LENGTH_LONG).show()
            }

        })


        btn.setOnClickListener {
            var rew=findViewById<EditText>(R.id.review).text.toString()
            val msg = rBar.rating.toString()
            Toast.makeText(this, "Rating is: "+msg, Toast.LENGTH_SHORT).show()
            val user = auth.currentUser
            val uid: String = user?.uid.toString()
            val database = Firebase.database
            val myRef = database.getReference("Ratings")
            var rt = Rate()
            rt.initializeobject(hname,loc,msg,uid,rew,checkin)
            myRef.child(""+System.currentTimeMillis()).setValue(rt)
            startActivity(Intent(this,BookingsAll::class.java))
            finish()
        }



    }
}