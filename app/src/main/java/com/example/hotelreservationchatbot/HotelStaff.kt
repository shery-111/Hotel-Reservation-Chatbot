package com.example.hotelreservationchatbot

import android.content.Intent
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Button
import androidx.appcompat.app.ActionBarDrawerToggle
import com.google.android.material.navigation.NavigationView
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_hotel_staff.*
import kotlinx.android.synthetic.main.content_main.*

class HotelStaff : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    private lateinit var user:FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hotel_staff)
        setSupportActionBar(toolbar)

        val toggle = ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.open,R.string.close)
        toggle.isDrawerIndicatorEnabled=true
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        var trvl3=findViewById<Button>(R.id.travler3)
        var updateroom=findViewById<Button>(R.id.travler)
        var edithotel=findViewById<Button>(R.id.travler2)

        trvl3.setOnClickListener {
            startActivity(Intent(this,AccountInfo::class.java))
            finish()
        }

        updateroom.setOnClickListener {
            startActivity(Intent(this,RoomInfo::class.java))
            finish()
        }

        edithotel.setOnClickListener {
            startActivity(Intent(this,HotelInfo::class.java))
            finish()
        }

    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        if(item.itemId==R.id.signout)
        {
            user.signOut()
            startActivity(Intent(this,Login::class.java))
            finish()
        }
        return true
    }
}