package com.example.hotelreservationchatbot

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.animation.AnimationUtils
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

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()
        var auth: FirebaseAuth = Firebase.auth
        val imageView = findViewById<ImageView>(R.id.splash_pic)
        imageView.alpha = 0.25f
        var myanim= AnimationUtils.loadAnimation(this,R.anim.anim1)
        var myanim1= AnimationUtils.loadAnimation(this,R.anim.anim2)
//        var image=findViewById<ImageView>(R.id.imageView1)
        var image2=findViewById<ImageView>(R.id.imageView2)

//        image.startAnimation(myanim)
        image2.startAnimation(myanim1)


        var intent= Intent(this,who_r_u::class.java)
        Handler().postDelayed(object:Runnable{

            override fun run() {
                val database= Firebase.database
                val user = auth.currentUser
                val uid: String = user?.uid.toString()
                val db=database.getReference(uid)
                if(user!=null) {
                    db.addValueEventListener(object : ValueEventListener {
                        override fun onDataChange(snapshot: DataSnapshot) {

                            for (obj in snapshot.children) {
                                var temp = obj.getValue<Profile>()
                                if (temp?.user == "false") {
                                    startActivity(Intent(this@MainActivity, Traveler::class.java))
                                    finish()

                                } else {
                                    startActivity(Intent(this@MainActivity, HotelStaff::class.java))
                                    finish()

                                }
                            }


                        }

                        override fun onCancelled(error: DatabaseError) {
                            Toast.makeText(this@MainActivity, "Data cancelled", Toast.LENGTH_LONG)
                                .show()
                        }

                    })
                }
                else
                {
                    startActivity(intent)
                    finish()
                }
            }
        },3000)


    }
}