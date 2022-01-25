package com.example.hotelreservationchatbot

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.DatabaseReference
import kotlinx.android.synthetic.main.activity_bookdetails.*
import java.text.SimpleDateFormat
import java.util.*

class Bookdetails : AppCompatActivity() {
    private lateinit var database: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bookdetails)
        supportActionBar?.hide()
        val imageView = findViewById<ImageView>(R.id.fgp)
        imageView.alpha = 0.25f
        val database="mydb"
        val mysharedpref=this.getSharedPreferences(database, Context.MODE_PRIVATE)
        val editor=mysharedpref.edit()

        var hname=mysharedpref.getString("hname","Hotel name").toString()
        var loc=mysharedpref.getString("loc","Location").toString()
        var price=mysharedpref.getInt("price",0).toString()
        var checkin=mysharedpref.getString("checkin","checkin date").toString()
        var checkout=mysharedpref.getString("checkout","checkout date").toString()
        var trooms=mysharedpref.getInt("trooms",0).toString()
        var roomtype=mysharedpref.getString("roomtype","Room Type").toString()
        var rate1=mysharedpref.getBoolean("rate",false)

        rate.setOnClickListener {
            startActivity(Intent(this,Rating::class.java))
            finish()
        }


        hn.setText(hname)
        lc.setText(loc)
        prc.setText(price)
        chkin.setText(checkin)
        trms.setText(trooms)
        rmtype.setText(roomtype)


            ratebtn.setOnClickListener{
                if(checkout?.length>5)
                {
                    chkout.setText(checkout)
                    ratebtn.setOnClickListener {
                        startActivity(Intent(this,BookingsAll::class.java))
                        finish()
                    }
                }
                else
                {
                val date: String =
                    SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(Date())
                    ratebtn.setText("Back")
                chkout.setText(date)
                checkout=date
                Toast.makeText(this,"You have been checked out from Hotel! Checkout date added",Toast.LENGTH_LONG).show()
            }}



    }

//    private fun updateData(chkout:String, uid: String) {
////        var auth: FirebaseAuth = Firebase.auth
////        val user11 = auth.currentUser
////        val uid: String = user11?.uid.toString()
//        database = FirebaseDatabase.getInstance().getReference("Bookings")
//        val user = mapOf<String, String>(
//            "checkout" to chkout
//        )
//
//        database.updateChildren(user).addOnSuccessListener {
//
//            Toast.makeText(this, "Successfully Updated", Toast.LENGTH_SHORT).show()
//
//
//        }.addOnFailureListener {
//
//            Toast.makeText(this, "Failed to Update", Toast.LENGTH_SHORT).show()
//
//        }
//    }
}