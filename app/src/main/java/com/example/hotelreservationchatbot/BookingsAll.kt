package com.example.hotelreservationchatbot

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.ListView

class BookingsAll : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bookings_all)
        val imageView = findViewById<ImageView>(R.id.fgp)
        imageView.alpha = 0.25f
        supportActionBar?.hide()
        var bk=Bookings()
        bk.checkin.add("12/1/2022")
        bk.hname.add("Lahore Hotel")
        bk.loc.add("Lahore")
        bk.checkout.add("13/1/2022")
        bk.roomtype.add("Luxury")
        bk.trooms.add("1")
        bk.price.add(5000)
        bk.checkin.add("11/1/2022")
        bk.hname.add("Hotel")
        bk.loc.add("FSD")
        bk.checkout.add("13/1/2022")
        bk.roomtype.add("Luxury")
        bk.trooms.add("1")
        bk.price.add(2000)

        var mylis=findViewById<ListView>(R.id.mylist)
        mylis.adapter=CustomAdapter(this,bk)

    }
}