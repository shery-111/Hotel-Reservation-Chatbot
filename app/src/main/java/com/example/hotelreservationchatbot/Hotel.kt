package com.example.hotelreservationchatbot

class Hotel {

    var name:String?=null
    var address:String?=null
    var city:String?=null
    var phone:String?=null

    fun initializeobject(nm:String,add:String,ph:String, ct:String)
    {
        name=nm
        address=add
        phone=ph
        city=ct
    }
}