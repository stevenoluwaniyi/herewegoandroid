package com.example.herewego

import androidx.fragment.app.Fragment
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity;
import android.text.Editable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.squareup.okhttp.MediaType
import kotlinx.android.synthetic.main.eventsearch_fragment.*
import kotlinx.android.synthetic.main.list_events.view.*
import kotlinx.android.synthetic.main.list_events.*
import kotlinx.android.synthetic.main.eventsearch_fragment.view.list_recycler_view
import kotlinx.android.synthetic.main.activity_main.*
import com.squareup.okhttp.OkHttpClient
import com.squareup.okhttp.Request
import com.squareup.okhttp.RequestBody
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.activityUiThread
import org.jetbrains.anko.doAsync
import org.json.JSONArray
import org.json.JSONObject
import java.lang.Exception
import android.R
import android.R.attr.colorAccent
import android.graphics.Color
import androidx.core.content.ContextCompat
import android.graphics.Typeface
import android.system.Os.bind
//import com.sun.xml.internal.fastinfoset.alphabet.BuiltInRestrictedAlphabets.table
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TableRow.LayoutParams;
import android.widget.TextView;
import android.widget.Toast;
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.search_fragment.*
import kotlin.properties.Delegates


class EventSearchFragment(val event : String?) : Fragment() {

    var name : String?
    init{
        name = event
    }
    //val event_name = search_event.text?.toString()
    val event_list = fetchevents(name)
    val event_arrays = "[" + event_list + "]"
    val event_array = JSONArray(event_arrays)

    private fun populateData() : List<Event>{
        var value : Event = Event("","","","","","","")
        for(i in 0..(event_array.length() - 1 )){
            val events = event_array.getJSONObject(i)
            value = (Event(events.get("eventname").toString(),events.get("eventtype").toString(),events.get("eventcity").toString(),events.get("eventstart").toString(),
                events.get("eventend").toString(),events.get("venuename").toString(),events.get("venueaddress").toString()))

        }

        return listOf(value)
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
           val view = inflater.inflate(com.example.herewego.R.layout.eventsearch_fragment, container, false)
       return view
   }



    // populate the views now that the layout has been inflated
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

            try{
            super.onViewCreated(view, savedInstanceState)
            // RecyclerView node initialized here
            list_recycler_view.apply {
              layoutManager =  LinearLayoutManager(activity)
               adapter = ListAdapter(events_list)
            }
            }
            catch (e:Exception){
                println(e)
            }


    }



   private fun fetchevents(event_name: String?): String? {
        val url = "https://herewegoadi.appspot.com/apisearchevent"
        val json = """
    {
        "event_name":"${event_name}"
    }
    """.trimIndent()

        val client = OkHttpClient()
        val body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), json)
        val request = Request.Builder()
            .url(url)
            .post(body)
            .header("User-Agent", "Android")
            .build()
        val response = client.newCall(request).execute()
        val bodystr = response.body().string() // this can be consumed only once
        return bodystr
    }

}


