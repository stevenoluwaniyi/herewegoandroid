package com.example.herewego

class ListEvents_Model {
    var event_id: Int? = null
    var event_name: String? = null
    var venue_name: String? = null
    var event_start: String? = null
    var event_end: String? =null

    fun getEventid(): String {
        return event_id.toString()
    }
    fun setEventid(name: Int) {
        this.event_id = name
    }
    fun getEventname(): String {
        return event_name.toString()
    }
    fun setEventName(name: String) {
        this.event_name = name
    }
    fun getVenuename(): String {
        return venue_name.toString()
    }
    fun setVenuename(name: String) {
        this.venue_name = name
    }
    fun geteventStart(): String {
        return event_start.toString()
    }
    fun seteventStart(name: String){
        this.event_start = name
    }
    fun geteventEnd(): String {
        return event_end.toString()
    }
    fun seteventEnd(name: String){
        this.event_end = name
    }



}