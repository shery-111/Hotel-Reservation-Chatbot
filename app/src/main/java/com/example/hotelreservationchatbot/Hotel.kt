package com.example.hotelreservationchatbot

class Hotel {

    var name:String?=null
    var address:String?=null
    var city:String?=null
    var phone:String?=null
    var economic:Int=0
    var luxury:Int=0

    fun initializeobject(nm:String,add:String,ph:String, ct:String,eco:Int,lux:Int)
    {
        name=nm
        address=add
        phone=ph
        city=ct
        economic=eco
        luxury=lux
    }
}