package com.example.herewego

//import com.sun.xml.internal.fastinfoset.alphabet.BuiltInRestrictedAlphabets.table
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView


class ShowEventAdapter(private val list: List<ShowEvent>)
    : RecyclerView.Adapter<ShowEventHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShowEventHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ShowEventHolder(inflater, parent)
    }

    override fun onBindViewHolder(holder: ShowEventHolder, position: Int) {
        val event: ShowEvent = list[position]
        holder.bind(event)
    }

    override fun getItemCount(): Int = list.size

}