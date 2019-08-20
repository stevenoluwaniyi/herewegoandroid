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
import kotlinx.android.synthetic.main.eventsearch_fragment.view.*
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


class ListAdapter(private val list: List<Event>)
    : RecyclerView.Adapter<EventViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return EventViewHolder(inflater, parent)
    }

    override fun onBindViewHolder(holder: EventViewHolder, position: Int) {
        val event: Event = list[position]
        holder.bind(event)
    }

    override fun getItemCount(): Int = list.size

}
