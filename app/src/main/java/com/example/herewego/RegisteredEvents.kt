package com.example.herewego

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.registed_events.view.*
import kotlinx.android.synthetic.main.registed_events.view.*
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException
import java.util.ArrayList


class RegisteredEvents : Fragment() {
    val x = LoginFragment()

    private val jsoncode = 1
    private var response: String? = null
    //**private var userlist: ListView? = null
    private var eventslist : ListView? =null
    //**private var userArrayList: ArrayList<String>? = null
    private var eventsArrayList: ArrayList<String>? = null
    //**private var userModelArrayList: ArrayList<User_Model>? = null
    private var eventsModelArrayList: ArrayList<ListEvents_Model>? = null
    //**private var customAdapter: CustomAdapter? = null
    private var listeventsAdapter: ListEventsAdapter? =null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.registed_events, container, false)

        eventslist = view.events_list
        println("print event_list")
        println(eventslist)
        println("Printing inside 3rd fragment")
        println("user in 3rd fragment is")
        println(Users.getUsername())


        var gotresponse2 = Users.getResponse()
        println("Printing response")
        val jsonarray2 = JSONArray(gotresponse2)
        println(gotresponse2)
        println("Printing json data")
        println(jsonarray2)
        //eventsModelArrayList = getInfo(gotresponse2.toString())
        eventsModelArrayList =getInfo(gotresponse2!!)
        listeventsAdapter = ListEventsAdapter(view.context, eventsModelArrayList!!)
        // set the custom adapter for the userlist viewing
        eventslist!!.adapter = listeventsAdapter


        for (i in 0..(jsonarray2.length() - 1)){
            println("printing inside last fragment loop")
            val data= jsonarray2.getJSONObject(i)
            println(data.get("event_id"))
            println(data.get("event_name"))
            println(data.get("venue_name"))
            println(data.get("event_start"))
            println(data.get("event_end"))
        }


        return view;

    }
}


fun getInfo(response: String): ArrayList<ListEvents_Model> {
    val userModelArrayList = ArrayList<ListEvents_Model>()
    try {
        val dataArray = JSONArray(response)
        for (i in 0 until dataArray.length()) {
            val eventsModel = ListEvents_Model()
            val dataobj = dataArray.getJSONObject(i)
            eventsModel.setEventid(dataobj.getInt("event_id"))
            eventsModel.setEventName(dataobj.getString("event_name"))
            eventsModel.setVenuename(dataobj.getString("venue_name"))
            eventsModel.seteventStart(dataobj.getString("event_start"))
            eventsModel.seteventEnd(dataobj.getString("event_end"))
            userModelArrayList.add(eventsModel)
        }
    } catch (e: JSONException) {
        e.printStackTrace()
    }

    return userModelArrayList
}


fun getStrings(response: String): ArrayList<String> {
    val userArrayList = ArrayList<String>()
    try {
        val dataArray = JSONArray(response)
        for (i in 0 until dataArray.length()) {
            val dataobj = dataArray.getJSONObject(i)
            userArrayList.add(dataobj.toString())
        }
    } catch (e: JSONException) {
        e.printStackTrace()
    }

    return userArrayList
}