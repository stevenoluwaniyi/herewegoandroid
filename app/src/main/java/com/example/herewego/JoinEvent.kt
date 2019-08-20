package com.example.herewego

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.squareup.okhttp.OkHttpClient
import com.squareup.okhttp.Request
import kotlinx.android.synthetic.main.eventsearch_fragment.*
import kotlinx.android.synthetic.main.join_events.*
import kotlinx.android.synthetic.main.join_events.view.*
import kotlinx.android.synthetic.main.login_fragment.view.*
import kotlinx.android.synthetic.main.show_events.*
import kotlinx.android.synthetic.main.show_events.view.*
import org.jetbrains.anko.doAsync
import org.json.JSONArray
import org.json.JSONObject

class JoinEvent : Fragment() {

    //val event_name = search_event.text?.toString()
    val event_list = fetchevents()
    val event_arrays =  event_list
    val event_array = JSONArray(event_arrays)

    private fun populateData() : List<ShowEvent>{
        var value : ShowEvent = ShowEvent("","","","","","","","")
        var myList: List<ShowEvent> = listOf()
        for(i in 0..(event_array.length() - 1 )){
            val events = event_array.getJSONObject(i)
            value = (ShowEvent(events.get("event_id").toString(),events.get("event_name").toString(),events.get("event_type").toString(),events.get("event_city").toString(),events.get("event_start").toString(),
                events.get("event_end").toString(),events.get("venue_name").toString(),events.get("event_description").toString()))
            myList += value

        }

        return myList
    }

    private val events_list = populateData()

    override fun onCreate(savedInstanceState: Bundle?) {

        try {
            super.onCreate(savedInstanceState)
            retainInstance = true
        }
        catch (e:Exception){
            println(e)
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(com.example.herewego.R.layout.join_events, container, false)
        view.join_event_button.setOnClickListener {
            doAsync {
                try {
                    val username = Users.getUsername()
                    val event_id = event_id_text.text.toString().toInt()
                    val response = joinevent(event_id, username)
                    val check_user = JSONObject(response)
                    if (check_user.get("event_join").toString() == "True") {
                        (activity as NavigationHost).navigateTo(NavigateFragment(), true)
                    } else if (check_user.get("event_join").toString() == "capacity") {

                        getActivity()?.runOnUiThread() {
                            join_event_id.error = getString(R.string.event_capacity)
                        }

                    } else {
                        getActivity()?.runOnUiThread() {
                            join_event_id.error = getString(R.string.already_registered)
                        }

                    }
                } catch (e: java.lang.Exception) {
                    println(e)
                }
            }
        }
        return view
    }



    // populate the views now that the layout has been inflated
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        try{
            super.onViewCreated(view, savedInstanceState)
            // RecyclerView node initialized here
            show_events_view.apply {
                layoutManager =  LinearLayoutManager(activity)
                adapter = ShowEventAdapter(events_list)
            }
        }
        catch (e:Exception){
            println(e)
        }


    }



    private fun fetchevents(): String? {
        val url = "https://6b322f37.ngrok.io/apishowevents"
        val client = OkHttpClient()
        val request = Request.Builder()
            .url(url)
            .header("User-Agent", "Android")
            .build()
        val response = client.newCall(request).execute()
        val bodystr = response.body().string() // this can be consumed only once
        return bodystr
    }

    private fun joinevent(event_id : Int?,username:String): String? {
        val url = "https://6b322f37.ngrok.io/apijoinevent/" + event_id + "/" + username
        val client = OkHttpClient()
        val request = Request.Builder()
            .url(url)
            .header("User-Agent", "Android")
            .build()
        val response = client.newCall(request).execute()
        val bodystr = response.body().string() // this can be consumed only once
        return bodystr
    }

}