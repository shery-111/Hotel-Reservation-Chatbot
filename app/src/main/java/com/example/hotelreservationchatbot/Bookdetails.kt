package com.example.hotelreservationchatbot

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_bookdetails.*
import java.text.SimpleDateFormat
import java.util.*

class Bookdetails : AppCompatActivity() {
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


        hn.setText(hname)
        lc.setText(loc)
        prc.setText(price)
        chkin.setText(checkin)
        trms.setText(trooms)
        rmtype.setText(roomtype)
        if(checkout?.length>5)
        {
            chkout.setText(checkout)
            chkbtn.visibility= View.INVISIBLE
        }
        else
        {
            chkbtn.setOnClickListener{
                val date: String =
                    SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(Date())

                chkout.setText(date)
                Toast.makeText(this,"You have been checked out from Hotel! Checkout date added",Toast.LENGTH_LONG).show()

            }
        }


    }
}