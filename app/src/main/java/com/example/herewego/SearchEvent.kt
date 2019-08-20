package com.example.herewego

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.squareup.okhttp.MediaType
import com.squareup.okhttp.OkHttpClient
import com.squareup.okhttp.Request
import com.squareup.okhttp.RequestBody
import kotlinx.android.synthetic.main.search_fragment.*
import kotlinx.android.synthetic.main.search_fragment.view.*
import kotlinx.android.synthetic.main.search_fragment.view.search_event
import org.jetbrains.anko.doAsync
import org.json.JSONObject
import java.lang.Exception

class SearchEvent : Fragment() {

    override fun onCreateView(

        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.search_fragment, container, false)



        view.search_button.setOnClickListener({
            doAsync {
                try {

                    val event_name : String? = search_event.text.toString()
                    val search_event_name = fetchevents(event_name)
                    val event_json = JSONObject(search_event_name)
                    if(event_json.get("event_found") == "True"){
                        //search_event_text.error = null
                        (activity as NavigationHost).navigateTo(EventSearchFragment(event_name), true)
                    }
                    else {
                        getActivity()?.runOnUiThread() {
                            search_event_text.error = getString(R.string.error_search)
                        }
                    }
                } catch (e: Exception){
                    println(e)
                }
            }
        })



        view.search_event.setOnKeyListener({ _, _, _ ->
            val event_check = search_event.text.toString()
            val search_check = fetchevents(event_check)
            val event_json = JSONObject(search_check)
            if (event_json.get("event_found") == "True") {
                // Clear the error.
                getActivity()?.runOnUiThread() {
                    search_event_text.error = null
                }

            }
            false
        })

        return view
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