package com.example.mystartkotlin

import java.util.*

object Cash {

    private lateinit var events: ArrayList<Event>

    fun getEvents(): ArrayList<Event> {
        return events
    }

    private fun getEvents(position: Int): Event {
        return events[position]
    }

    fun addEvent(numberEvent: String, descriptionEvent: String, timeEvent: String) {
        var validate = true
        for (i in 0 until events.size) {
            if (getEvents(i)._description == descriptionEvent &&
                getEvents(i)._number == numberEvent
            ) {
                validate = false
                break
            }
        }
        if (validate) {
            events.add(Event(numberEvent, descriptionEvent, timeEvent))
        }
    }

}
