package com.example.hotelreservationchatbot

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.navigation.NavigationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_hotel_staff.*
import kotlinx.android.synthetic.main.activity_hotel_staff.view.*
import kotlinx.android.synthetic.main.content_main.*

class HotelStaff : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
//    private lateinit var database: DatabaseReference
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

        nav_menue.setNavigationItemSelectedListener(this)


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

    var pro=Profile()
    var auth: FirebaseAuth = Firebase.auth
    val database = Firebase.database
    val user = auth.currentUser
    val uid: String = user?.uid.toString()
    val db = database.getReference(uid)

    db.addValueEventListener(object : ValueEventListener {
        override fun onDataChange(snapshot: DataSnapshot) {

            for (obj in snapshot.children) {
                var temp = obj.getValue<Profile>()
                pro.fullname=temp?.fullname
                pro.phone=temp?.phone
                val navigationMenu: Menu = nav_menue.getMenu()
                val navigationFirstMenuItem = navigationMenu.getItem(0)
                val navigationSecondMenuItem = navigationMenu.getItem(1)
                navigationFirstMenuItem.setTitle(pro.fullname.toString())
                navigationSecondMenuItem.setTitle(pro.phone)
                break
            }
        }

        override fun onCancelled(error: DatabaseError) {
            Toast.makeText(this@HotelStaff, "Data cancelled", Toast.LENGTH_LONG).show()
        }

    })



    }


    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        if(item.itemId==R.id.signout)
        {
            Firebase.auth.signOut()
            startActivity(Intent(this,Login::class.java))
            finish()
        }
        if(item.itemId==R.id.menu_email)
        {
            Toast.makeText(this@HotelStaff,"Phone Number",Toast.LENGTH_LONG).show()
        }
        if(item.itemId==R.id.menu_name)
        {
            Toast.makeText(this@HotelStaff,"Full Name",Toast.LENGTH_LONG).show()
        }
        return true
    }
}