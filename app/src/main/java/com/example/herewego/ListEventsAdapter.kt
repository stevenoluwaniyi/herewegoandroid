package com.example.herewego

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import java.util.ArrayList

/**
 * Orignial author: Parsania Hardik on 03-Jan-17.
 * Modified by Ramesh Yerraballi on 8/12/19
 */
class ListEventsAdapter(private val context: Context, private val usersModelArrayList: ArrayList<ListEvents_Model>) :
    BaseAdapter() {

    override fun getViewTypeCount(): Int {
        return 1
    }

    override fun getItemViewType(position: Int): Int {

        return position
    }

    override fun getCount(): Int {
        return usersModelArrayList.size
    }

    override fun getItem(position: Int): Any {
        return usersModelArrayList[position]
    }

    override fun getItemId(position: Int): Long {
        return 0
    }

    @SuppressLint("SetTextI18n")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var convertView = convertView
        val holder: ViewHolder

        if (convertView == null) {
            holder = ViewHolder()
            val inflater = context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            convertView = inflater.inflate(R.layout.events, null, true)
            //storing values as text views
            holder.event_id = convertView!!.findViewById(R.id.event_id) as TextView
            holder.event_name = convertView.findViewById(R.id.event_name) as TextView
            holder.venue_name = convertView.findViewById(R.id.venue_name) as TextView
            holder.event_start = convertView.findViewById(R.id.event_start) as TextView
            holder.event_end = convertView.findViewById(R.id.event_end) as TextView



            convertView.tag = holder
        } else {
            // the getTag returns the viewHolder object set as a tag to the view
            holder = convertView.tag as ViewHolder
        }

        holder.event_id!!.text = "Event ID: " + usersModelArrayList[position].getEventid()
        holder.event_name!!.text = "Event Name: " + usersModelArrayList[position].getEventname()
        holder.venue_name!!.text = "Venue Name: " + usersModelArrayList[position].getVenuename()
        holder.event_start!!.text = "Event Start: " + usersModelArrayList[position].geteventStart()
        holder.event_end!!.text = "Event Start: " + usersModelArrayList[position].geteventEnd()



        return convertView
    }

    private inner class ViewHolder {

        var event_id: TextView? = null
        var event_name: TextView? = null
        var venue_name: TextView? = null
        var event_start: TextView? = null
        var event_end: TextView? = null
    }

}