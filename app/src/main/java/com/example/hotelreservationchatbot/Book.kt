package com.example.hotelreservationchatbot

class Book {
    var hname:String=""
    var loc:String=""
    var price:Int=0
    var checkin:String=""
    var checkout:String=""
    var roomtype:String=""
    var trooms:Int=0
    var rate:Boolean=false
    var uid:String=""

    fun initializeobject(hn:String,lc:String,pric:Int, chkin:String,chkout:String,rmt:String,trm:Int,rt:Boolean,ui:String)
    {
        hname=hn
        loc=lc
        price=pric
        checkin=chkin
        checkout=chkout
        roomtype=rmt
        uid=ui
        trooms=trm
        rate=rt
    }
}