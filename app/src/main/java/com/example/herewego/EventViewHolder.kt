package com.example.herewego

import android.R
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class EventViewHolder(inflater: LayoutInflater, parent: ViewGroup) :
    RecyclerView.ViewHolder(inflater.inflate(com.example.herewego.R.layout.list_events, parent, false)) {
    private var e_eventname: TextView? = null
    private var e_eventtype: TextView? = null
    private var e_eventcity: TextView? = null
    private var e_eventstart: TextView? = null
    private var e_eventend: TextView? = null
    private var e_venuename: TextView? = null
    private var e_venueaddress: TextView? = null


    init {
        e_eventname =  itemView.findViewById(com.example.herewego.R.id.event_name)
        e_eventtype = itemView.findViewById(com.example.herewego.R.id.event_type)
        e_eventcity = itemView.findViewById(com.example.herewego.R.id.event_city)
        e_eventstart = itemView.findViewById(com.example.herewego.R.id.event_start)
        e_eventend = itemView.findViewById(com.example.herewego.R.id.event_end)
        e_venuename = itemView.findViewById(com.example.herewego.R.id.venue_name)
        e_venueaddress = itemView.findViewById(com.example.herewego.R.id.venue_address)

    }

    fun bind(event: Event) {
        e_eventname?.text = event.eventname
        e_eventtype?.text = event.eventtype
        e_eventcity?.text = event.eventcity
        e_eventstart?.text = event.eventstart
        e_eventend?.text = event.eventend
        e_eventend?.text = event.eventend
        e_venuename?.text = event.venuename
        e_venueaddress?.text = event.venueaddress
    }

}