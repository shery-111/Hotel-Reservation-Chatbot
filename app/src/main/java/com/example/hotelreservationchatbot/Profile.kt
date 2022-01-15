package com.example.hotelreservationchatbot

class Profile {

    var fullname:String?=null
    var cnic:String?=null
    var phone:String?=null
    var user:Boolean?=null

    fun initializeobject(fn:String,cn:String,ph:String, us:Boolean)
    {
        fullname=fn
        cnic=cn
        phone=ph
        user=us
    }

}