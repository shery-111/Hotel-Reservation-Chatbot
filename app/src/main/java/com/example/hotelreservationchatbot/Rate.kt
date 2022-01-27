package com.example.hotelreservationchatbot

class Rate {
    var hname:String=""
    var city:String=""
    var rating:String=""
    var uid:String=""
    var review:String=""
    var checkin:String=""

    fun initializeobject(hn:String,ct:String, rat:String, ui:String,rv:String,chkin:String)
    {
         hname=hn
         city=ct
         rating=rat
         uid=ui
         review=rv
         checkin=chkin
    }
}