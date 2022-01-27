package com.example.hotelreservationchatbot

import android.os.Bundle
import android.widget.ImageView
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase

class RecHotels : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rec_hotels)
        val imageView = findViewById<ImageView>(R.id.fgp)
        imageView.alpha = 0.25f
        supportActionBar?.hide()
        var rt=RecommendedHotels()
        var auth: FirebaseAuth = Firebase.auth
        val database = Firebase.database
        val user = auth.currentUser
        val uid: String = user?.uid.toString()
        var db = database.getReference("Hotels")
        db.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                for (obj in snapshot.children) {
                    var temp = obj.getValue<Hotel>()

                        rt.hname.add(temp?.name.toString())
                        rt.city.add(temp?.city.toString())
                }

            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@RecHotels, "Data cancelled", Toast.LENGTH_LONG).show()
            }

        })

        var cities=ArrayList<String>()

        db = database.getReference("Ratings")
        db.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                var rec=RecommendedHotels()
                var p=0
                for(arr in rt.hname)
                {
                    var t=0
                    var sum:Float
                    var review=""
                    t=0
                    sum= 0F
                    var v=0
                    for (obj in snapshot.children) {
                        var temp = obj.getValue<Rate>()

                        if(arr==temp?.hname)
                        {
                            sum += temp?.rating.toFloat()
                            if(t==0)
                            {
                                review=review+(t+1).toString()+") "+temp?.review
                            }
                            else
                            {
                                review=review+"\n"+(t+1).toString()+") "+temp?.review
                            }
                            t++
                        }
                        if(t>0){
                            v=1
                        }


                    }
                    if(v==1){
                        rec.sum.add(sum)
                        rec.count.add(t)
                    rec.rating.add(sum/t)
                    rec.rev.add(review)
                    rec.hname.add(arr)
                    cities.add(rt.city[p])
                    p++}
                }
                for(arr in cities)
                {
                    rec.city.add(arr)
                }

                rec.rating.sort()


                var mylis=findViewById<ListView>(R.id.mylist1)
                mylis.adapter=CustomAdapterRec(this@RecHotels,rec)


            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@RecHotels, "Data cancelled", Toast.LENGTH_LONG).show()
            }

        })


    }
}