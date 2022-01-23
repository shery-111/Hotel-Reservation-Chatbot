package com.example.hotelreservationchatbot

class Rooms {
    var economic:Int=0
    var luxury:Int=0
    var uid:String=""

    fun initializeobject(eco:Int,lux:Int,ui:String)
    {
        economic=eco
        luxury=lux
        uid=ui
    }
}