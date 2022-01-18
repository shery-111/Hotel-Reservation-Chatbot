package com.example.hotelreservationchatbot

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.animation.AnimationUtils
import android.widget.ImageView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()
        val imageView = findViewById<ImageView>(R.id.splash_pic)
        imageView.alpha = 0.25f
        var myanim= AnimationUtils.loadAnimation(this,R.anim.anim1)
        var myanim1= AnimationUtils.loadAnimation(this,R.anim.anim2)
        var image=findViewById<ImageView>(R.id.imageView1)
        var image2=findViewById<ImageView>(R.id.imageView2)

        image.startAnimation(myanim)
        image2.startAnimation(myanim1)


        var intent= Intent(this,who_r_u::class.java)
        Handler().postDelayed(object:Runnable{

            override fun run() {
                startActivity(intent)
                finish()
            }
        },3000)


    }
}