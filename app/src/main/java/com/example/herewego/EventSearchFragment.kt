package com.example.herewego

//import com.sun.xml.internal.fastinfoset.alphabet.BuiltInRestrictedAlphabets.table
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.squareup.okhttp.MediaType
import com.squareup.okhttp.OkHttpClient
import com.squareup.okhttp.Request
import com.squareup.okhttp.RequestBody
import kotlinx.android.synthetic.main.eventsearch_fragment.*
import org.json.JSONArray


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
        val url = "https://6b322f37.ngrok.io/apisearchevent"
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


