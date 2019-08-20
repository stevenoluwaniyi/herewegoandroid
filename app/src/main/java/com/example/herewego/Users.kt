package com.example.herewego

object Users {

    private var username = ""
    private var resp = ""

    fun setUsername(username:String){
        this.username = username
    }
    fun setResponse(resp:String){
        this.resp =resp
    }
    fun getUsername():String{
        return this.username
    }
    fun getResponse():String{
        return this.resp
    }
}