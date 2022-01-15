package com.example.hotelreservationchatbot

import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import org.w3c.dom.Text

class Login : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        supportActionBar?.hide()
        var auth: FirebaseAuth= Firebase.auth
        var logbt=findViewById<Button>(R.id.loginbtn)
        var forgot=findViewById<TextView>(R.id.forgot)
        var register=findViewById<TextView>(R.id.register)

        val currentUser = auth.currentUser
        if(currentUser != null) {
        Toast.makeText(this,"Logged in",Toast.LENGTH_LONG).show()

            register.setOnClickListener {
                var intent = Intent(this, Register::class.java)
                startActivity(intent)
            }

            logbt.setOnClickListener {
                var email = findViewById<EditText>(R.id.email).text.toString()
                var password = findViewById<EditText>(R.id.pass).text.toString()
                Toast.makeText(baseContext, email + password, Toast.LENGTH_SHORT).show()
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

    }
}