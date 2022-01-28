package com.example.hotelreservationchatbot

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import com.example.hotelreservationchatbot.databinding.ActivityAccountInfoBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.*
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_account_info.*
import kotlin.properties.Delegates

class AccountInfo : AppCompatActivity() {
    private lateinit var binding: ActivityAccountInfoBinding
    private lateinit var database: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityAccountInfoBinding.inflate(layoutInflater)
        setContentView(binding.root)
        var user22:String=""
        supportActionBar?.hide()
        val imageView = findViewById<ImageView>(R.id.acp)
        imageView.alpha = 0.25f
        var auth: FirebaseAuth = Firebase.auth
        var fullnm = findViewById<EditText>(R.id.fullnm)
        var email = findViewById<EditText>(R.id.email_ai)
        var cnic = findViewById<EditText>(R.id.cnic)
        var phone = findViewById<EditText>(R.id.phone_ai)

        val database = Firebase.database
        val user = auth.currentUser
        val uid: String = user?.uid.toString()
        val db = database.getReference(uid)
        db.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {

                for (obj in snapshot.children) {
                    var temp = obj.getValue<Profile>()
                    fullnm.setText(temp?.fullname)
                    cnic.setText(temp?.cnic)
                    phone.setText(temp?.phone)
                    email.setText(user?.email)
                    user22= temp?.user.toString()
                    break
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@AccountInfo, "Data cancelled", Toast.LENGTH_LONG).show()
            }

        })


        email_ai.keyListener=null


        binding.save.setOnClickListener {

            val fnm = fullnm.text.toString()
            val cn = cnic.text.toString()
            val phn = phone.text.toString()
            if (cn.length < 13 && cn.length > 13) {
                Toast.makeText(this, "Invalid CNIC", Toast.LENGTH_LONG).show()
            } else if (fnm.length < 3) {
                Toast.makeText(this, "Invalid Full Name", Toast.LENGTH_LONG).show()
            } else if (phn.length < 11 && phn.length > 11) {
                Toast.makeText(this, "Invalid Phone Number", Toast.LENGTH_LONG).show()
            } else {
                val alertDialog: AlertDialog
                val builder = AlertDialog.Builder(this)
                builder.setTitle("My Account Info")
                builder.setMessage("Are you sure, you want to change the data?")
                builder.apply {
                    setPositiveButton("Yes",
                        DialogInterface.OnClickListener { dialog, id ->
                            updateData(fnm, cn, phn, uid)
                            if (user22 == "false") {
                                startActivity(Intent(this@AccountInfo, Traveler::class.java))
                                finish()
                            } else {
                                startActivity(Intent(this@AccountInfo, HotelStaff::class.java))
                                finish()
                            }
                        })
                    setNegativeButton("Go back",
                        DialogInterface.OnClickListener { dialog, id ->
                            if (user22 == "false") {
                                startActivity(Intent(this@AccountInfo, Traveler::class.java))
                                finish()
                            } else {
                                startActivity(Intent(this@AccountInfo, HotelStaff::class.java))
                                finish()
                            }
                        })
                }
                builder.create()
                builder.show()


            }
        }

    }

    private fun updateData(fnm: String, cn: String, phn: String, uid: String) {
//        var auth: FirebaseAuth = Firebase.auth
//        val user11 = auth.currentUser
//        val uid: String = user11?.uid.toString()
        database = FirebaseDatabase.getInstance().getReference(uid)
        val user = mapOf<String, String>(
            "fullname" to fnm,
            "cnic" to cn,
            "phone" to phn
        )

        database.child("Profile").updateChildren(user).addOnSuccessListener {

            Toast.makeText(this, "Successfully Updated", Toast.LENGTH_SHORT).show()


        }.addOnFailureListener {

            Toast.makeText(this, "Failed to Update", Toast.LENGTH_SHORT).show()

        }
    }


    }
