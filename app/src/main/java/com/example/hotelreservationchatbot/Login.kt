package com.example.hotelreservationchatbot

import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.*
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase
import org.w3c.dom.Text

class Login : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        supportActionBar?.hide()
        val imageView = findViewById<ImageView>(R.id.loginp)
        imageView.alpha = 0.25f
        var auth: FirebaseAuth= Firebase.auth
        var logbt=findViewById<Button>(R.id.loginbtn)
        var forgot=findViewById<TextView>(R.id.forgot)
        var register=findViewById<TextView>(R.id.register)
        var user22=""

        forgot.setOnClickListener {
            startActivity(Intent(this,Forgot::class.java))
        }
        register.setOnClickListener {
            var intent = Intent(this, Register::class.java)
            startActivity(intent)
        }

        val currentUser = auth.currentUser
        if(currentUser == null) {
//        Toast.makeText(this,"Logged in",Toast.LENGTH_LONG).show()
            val database=Firebase.database
            val user = auth.currentUser
            val uid: String = user?.uid.toString()
            val db=database.getReference(uid)
            db.addValueEventListener(object: ValueEventListener{
                override fun onDataChange(snapshot: DataSnapshot) {

                    for(obj in snapshot.children)
                    {
                        var temp=obj.getValue<Profile>()
                        if(temp?.user == "false")
                        {
                            user22="false"

                        }
                        else
                        {
                           user22="true"

                        }
                    }



                }

                override fun onCancelled(error: DatabaseError) {
                    Toast.makeText(this@Login,"Data cancelled",Toast.LENGTH_LONG).show()
                }

            })


            logbt.setOnClickListener {
                var email = findViewById<EditText>(R.id.email).text.toString()
                var password = findViewById<EditText>(R.id.pass).text.toString()
//                Toast.makeText(baseContext, email + password, Toast.LENGTH_SHORT).show()
                auth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this) { task ->
                        if (task.isSuccessful) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithEmail:success")
                            val user = auth.currentUser
                            Toast.makeText(
                                baseContext, "Successful login",
                                Toast.LENGTH_SHORT
                            ).show()
                            if(user22=="true")
                            {
                                startActivity(Intent(this@Login,HotelStaff::class.java))
                                finish()
                            }
                            else
                            {
                                startActivity(Intent(this@Login,Traveler::class.java))
                                finish()
                            }


                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithEmail:failure", task.exception)
                            Toast.makeText(
                                baseContext, "Authentication failed.",
                                Toast.LENGTH_SHORT
                            ).show()

                        }
                    }
            }
        }
        else
        {

            val database=Firebase.database
            val user = auth.currentUser
            val uid: String = user?.uid.toString()
            val db=database.getReference(uid)
            db.addValueEventListener(object: ValueEventListener{
                override fun onDataChange(snapshot: DataSnapshot) {

                    for(obj in snapshot.children)
                    {
                        var temp=obj.getValue<Profile>()
                        if(temp?.user == "false")
                        {
                            startActivity(Intent(this@Login,Traveler::class.java))
                            finish()

                        }
                        else
                        {
                            startActivity(Intent(this@Login,HotelStaff::class.java))
                            finish()

                        }
                    }



                }

                override fun onCancelled(error: DatabaseError) {
                    Toast.makeText(this@Login,"Data cancelled",Toast.LENGTH_LONG).show()
                }

            })
        }

    }
}