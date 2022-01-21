package com.example.hotelreservationchatbot

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Button
import androidx.appcompat.app.ActionBarDrawerToggle
import com.google.android.material.navigation.NavigationView
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_hotel_staff.*
import kotlinx.android.synthetic.main.content_main.*

class Traveler : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    private lateinit var user: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_traveler)
        setSupportActionBar(toolbar)

        val toggle = ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.open,R.string.close)
        toggle.isDrawerIndicatorEnabled=true
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
        var book=findViewById<Button>(R.id.tr1)
        var recm=findViewById<Button>(R.id.tr6)
        var chat=findViewById<Button>(R.id.tr5)
        var ac=findViewById<Button>(R.id.tr4)

        book.setOnClickListener {

        }
        recm.setOnClickListener {

        }

        chat.setOnClickListener {
            startActivity(Intent(this,Chatbot::class.java))

        }
        ac.setOnClickListener {
            startActivity(Intent(this,AccountInfo::class.java))

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