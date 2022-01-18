package com.example.hotelreservationchatbot

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class Forgot : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forgot)
        supportActionBar?.hide()
        val imageView = findViewById<ImageView>(R.id.fgp)
        imageView.alpha = 0.25f
        var auth: FirebaseAuth = Firebase.auth
        var btn=findViewById<Button>(R.id.fg_btn)

        btn.setOnClickListener {
            var email=findViewById<EditText>(R.id.forgot_email).text.toString()
            if(email.length>6)
            {
                Firebase.auth.sendPasswordResetEmail(email)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            Toast.makeText(this,"Email Sent Successfully", Toast.LENGTH_LONG).show()
                            startActivity(Intent(this,Login::class.java))
                            finish()
                        }
                    }
            }
            else
            {
                Toast.makeText(this,"Enter correct Email Format", Toast.LENGTH_LONG).show()
            }
        }
    }
}