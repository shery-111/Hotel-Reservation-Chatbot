package com.example.hotelreservationchatbot

import android.content.Context
import android.content.Intent
import android.telecom.Call.Details
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import kotlinx.android.synthetic.main.bookingslistview.view.*
import java.security.AccessController.getContext


class CustomAdapter(var con:Context, var books:Bookings) : BaseAdapter () {
    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
        var inflater:LayoutInflater=con.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        var item: View =inflater.inflate(R.layout.bookingslistview,null)


        item.hname.text=books.hname.get(p0)
        item.Date.text=books.checkin.get(p0)
        item.loc.text=books.loc.get(p0)
        item.btnview.setOnClickListener {
            val i = Intent(con, Traveler::class.java)
            i.flags = Intent.FLAG_ACTIVITY_NEW_TASK //add this line
            con.startActivity(i)
        }

        return item
    }

    override fun getCount(): Int {
        return books.hname.size
    }

    override fun getItem(p0: Int): Any {
        return p0
    }

    override fun getItemId(p0: Int): Long {
       return p0.toLong()
    }


}