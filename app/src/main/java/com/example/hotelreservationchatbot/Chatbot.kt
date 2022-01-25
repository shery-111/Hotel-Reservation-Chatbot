package com.example.hotelreservationchatbot

import android.R
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.hotelreservationchatbot.databinding.ActivityChatbotBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class Chatbot : AppCompatActivity() {
    var city= listOf<String>("lahore","faisalabad","murree","abottabad","karachi","skardu","hunza","quetta")
    var months= listOf<String>("jan","feb","mar","apr","jun","jul","aug","sep","oct","nov","dec")
    private lateinit var mainActivity:ActivityChatbotBinding
    private lateinit var messageList:ArrayList<MessageClass>
    private lateinit var database: DatabaseReference
    private val USER = 0
    private val BOT = 1
    private lateinit var adapter: MessageAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainActivity = ActivityChatbotBinding.inflate(layoutInflater)
        setContentView(mainActivity.root)
        supportActionBar?.hide()
        messageList = ArrayList<MessageClass>()
        val linearLayoutManager = LinearLayoutManager(this)
        linearLayoutManager.orientation = LinearLayoutManager.VERTICAL
        mainActivity.messageList.layoutManager = linearLayoutManager
        adapter = MessageAdapter(this,messageList)
        adapter.setHasStableIds(true)
        mainActivity.messageList.adapter = adapter
        mainActivity.sendBtn.setOnClickListener{
            val msg = mainActivity.messageBox.text.toString()
            sendMessage(msg)
//            Toast.makeText(this, "Message sent", Toast.LENGTH_SHORT).show()
            mainActivity.messageBox.setText("")
        }
    }
    fun sendMessage(message:String){
        var userMessage = MessageClass()
        if(message.isEmpty()){
            Toast.makeText(this,"Please type your message",Toast.LENGTH_SHORT).show()
        }
        else{
            userMessage = MessageClass(message,USER)
            messageList.add(userMessage)
            adapter.notifyDataSetChanged()
        }
        val okHttpClient = OkHttpClient()
        val retrofit = Retrofit.Builder().baseUrl("https://278f-72-255-5-143.ngrok.io/webhooks/rest/").client(okHttpClient).addConverterFactory(
            GsonConverterFactory.create()).build()
        val messagerSender = retrofit.create(MessageSender::class.java)
        val response = messagerSender.messageSender(userMessage)
        response.enqueue(object: Callback<ArrayList<BotResponse>> {
            override fun onResponse(
                call: Call<ArrayList<BotResponse>>,
                response: Response<ArrayList<BotResponse>>
            ) {
                if(response.body() != null || response.body()?.size != 0){
                    val message = response.body()!![0]
                    if(message.text.contains("Ap ne ye maloomat di hain, tasdeeq krein!",ignoreCase = true))
                    {
                        var str:String=message.text
                        var lines=str.lines()
                        var sub1=lines[1].substringAfter(':')
                        var sub2=lines[2].substringAfter(':')
                        sub2=sub2.replace("[^0-9]".toRegex(), "")
                        var sub3=lines[3].substringAfter(':')
                        sub3=sub3.replace("[^0-9]".toRegex(), "")
                        var sub4=lines[4].substringAfter(':')
                        for(obj in city)
                        {
                            if(sub1.contains(obj,ignoreCase = true))
                            {
                                sub1=obj
                            }
                        }
                        for(obj in months)
                        {
                            if(sub4.contains(obj,ignoreCase = true))
                            {
                                sub4=sub4.replace("[^0-9]".toRegex(), "")
                                sub4=sub4+" "+obj
                            }
                        }
//                        Toast.makeText(this@Chatbot,sub1+sub2+"\n"+sub3+"\n"+sub4,Toast.LENGTH_LONG).show()
                        var bk=Bookings()
                        var auth: FirebaseAuth = Firebase.auth
                        val database = Firebase.database
                        val user = auth.currentUser
                        val uid: String = user?.uid.toString()
                        val db = database.getReference("Hotels")

                        var bok=Book()
                        db.addValueEventListener(object: ValueEventListener {
                            override fun onDataChange(snapshot: DataSnapshot) {
                                var i=0
                                for(obj in snapshot.children)
                                {
                                    var temp=obj.getValue<Hotel>()
                                    if (temp != null) {
                                        if(sub1.contains(temp?.city.toString()) && (sub3>= temp?.economic.toString() || sub3>= temp?.luxury.toString()))
                                        {

                                            bk.hname.add(temp?.name.toString())
                                            bk.loc.add(temp?.city.toString())
                                            bk.price.add(temp?.economic.toString().toInt())
                                            bk.checkin.add(sub4)
//                                            Toast.makeText(this@Chatbot,bk[i].hname,Toast.LENGTH_SHORT).show()
                                            if(sub3>= temp?.economic.toString())
                                            {
                                                bk.roomtype.add("economic")
                                            }
                                            else
                                            {
                                                bk.roomtype.add("luxury")
                                            }
                                            i++
                                        }

                                    }
                                }
                                if(i>0)
                                {
                                    bk.price.sort()
                                    bok.hname=bk.hname[0]
                                    bok.loc=bk.loc[0]
                                    bok.price=bk.price[0]
                                    bok.checkin=bk.checkin[0]
                                    bok.uid=uid
                                    bok.roomtype=bk.roomtype[0]
                                    bok.trooms=1
                                    bok.checkout=""
                                    bok.rate=false
                                    val myRef = database.getReference("Bookings")
                                    myRef.child(uid).child("Book"+System.currentTimeMillis()).setValue(bok)
                                }
                                var not=NotificationCompat.Builder(this@Chatbot)
                                not.setContentTitle("Congrats!")
                                not.setContentText("The desired Hotel has been booked!")
                                not.setSmallIcon(R.drawable.ic_notification_overlay)
                                var fin=not.build()
                                var notManager=getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
                                notManager.notify(1,fin)
                                Toast.makeText(this@Chatbot,"You will be notified shortly!",Toast.LENGTH_LONG).show()
                                startActivity(Intent(this@Chatbot,Traveler::class.java))
                                finish()

                            }

                            override fun onCancelled(error: DatabaseError) {
                                Toast.makeText(this@Chatbot,"Data cancelled",Toast.LENGTH_LONG).show()
                            }

                        })

                    }
                    messageList.add(MessageClass(message.text,BOT))
                    adapter.notifyDataSetChanged()
                }
            }

            override fun onFailure(call: Call<ArrayList<BotResponse>>, t: Throwable) {
                val message = "Check your connection"
                messageList.add(MessageClass(message,BOT))
            }

        })
    }

}