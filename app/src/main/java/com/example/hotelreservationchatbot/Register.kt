package com.example.hotelreservationchatbot

import android.content.ContentValues.TAG
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class Register : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        supportActionBar?.hide()
        val database="mydb"
        val imageView = findViewById<ImageView>(R.id.regp)
        imageView.alpha = 0.25f
        var auth: FirebaseAuth= Firebase.auth
        var btn=findViewById<Button>(R.id.reg_btn)
        val mysharedpref=baseContext.getSharedPreferences(database, Context.MODE_PRIVATE)
        var user11=mysharedpref.getBoolean("user",false)
        var us:String="false"
        if(!user11)
        {
            us="false"
        }
        else
        {
            us="true"
        }


        btn.setOnClickListener {

            var email=findViewById<EditText>(R.id.e_mail).text.toString()
            var pass=findViewById<EditText>(R.id.passw).text.toString()
            var fullnm=findViewById<EditText>(R.id.full_name).text.toString()
            var cnic=findViewById<EditText>(R.id.cnic).text.toString()
            var phone=findViewById<EditText>(R.id.phone).text.toString()
            if(cnic.length!=13)
            {
                Toast.makeText(this, "Invalid CNIC", Toast.LENGTH_LONG).show()
            }
            else if(fullnm.length<3)
            {
                Toast.makeText(this, "Invalid Full Name", Toast.LENGTH_LONG).show()
            }
            else if(phone.length!=11)
            {
                Toast.makeText(this, "Invalid Phone Number", Toast.LENGTH_LONG).show()
            }
            else {
                var prof:Profile=Profile()
//                val user = Firebase.auth.currentUser
                prof.initializeobject(fullnm,cnic,phone,us)
                auth.createUserWithEmailAndPassword(email, pass)
                    .addOnCompleteListener(this) { task ->
                        if (task.isSuccessful) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "createUserWithEmail:success")

                            val user = auth.currentUser
                            val uid: String = user?.uid.toString()
                            val database = Firebase.database
                            val myRef = database.getReference(uid)
                            myRef.child("Profile").setValue(prof)
                            Toast.makeText(this, "Successful Registration", Toast.LENGTH_LONG).show()
                            if(!user11)
                            {
                                startActivity(Intent(this@Register,Traveler::class.java))
                                finish()
                            }
                            else
                            {
                                startActivity(Intent(this@Register,HotelStaff::class.java))
                                finish()
                            }

                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "createUserWithEmail:failure", task.exception)
                            Toast.makeText(
                                baseContext, "Authentication failed.",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
            }
        }
    }
}