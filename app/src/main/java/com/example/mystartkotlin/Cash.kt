package com.example.mystartkotlin

object Cash {

    private val events: ArrayList<Event> = ArrayList()

    fun getEvents(): ArrayList<Event> {
        return events
    }

    private fun getEvents(position: Int): Event {
        return events[position]
    }

    private fun validateEvent(numberEvent: String, descriptionEvent: String): Boolean{
        var validate = true
        for (i in 0 until events.size) {
            if (getEvents(i)._description == descriptionEvent &&
                    getEvents(i)._number == numberEvent
            ) {
                validate = false
                break
            }
        }
        return validate
    }

    fun addEvent(numberEvent: String, descriptionEvent: String, timeEvent: String) {
        if (validateEvent(numberEvent, descriptionEvent)) {
            events.add(Event(numberEvent, descriptionEvent, timeEvent))
        }
    }

}
