package com.example.mystartkotlin

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.mystartkotlin.EventAdapter.EventViewHolder
import java.util.*

internal class EventAdapter(
    context: Context?,  /*ArrayList<Event> events,*/
    RemoveClickListener: RemoveClickListener
) : RecyclerView.Adapter<EventViewHolder>() {
    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private val events = ArrayList<Event>()
    private val removeEventClickListener: RemoveClickListener = RemoveClickListener
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventViewHolder {
        val view = inflater.inflate(R.layout.event_item, parent, false)
        return EventViewHolder(view)
    }

    override fun onBindViewHolder(holder: EventViewHolder, position: Int) {
        val event = events[position]
        holder.number.text = event._number
        holder.description.text = event._description
        holder.textTimeDate.text = event._dateTime
        holder.deleteEvent.setOnClickListener {
            removeEventClickListener.removeEvent(position)
            notifyItemRangeChanged(position, itemCount)
        }
    }

    override fun getItemCount(): Int {
        return events.size
    }

    fun insertData(newEvents: ArrayList<Event>) {
        val diffResult = DiffUtil.calculateDiff(EventDiffUtil(events, newEvents))
        events.clear()
        events.addAll(newEvents)
        diffResult.dispatchUpdatesTo(this)
    }

    internal class EventViewHolder(itemView: View) : ViewHolder(itemView) {
        val deleteEvent: Button = itemView.findViewById(R.id.buttonDelete)
        val number: TextView = itemView.findViewById(R.id.number)
        val description: TextView = itemView.findViewById(R.id.description)
        val textTimeDate: TextView = itemView.findViewById(R.id.timeDate)
    }
}