package com.example.mystartkotlin

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private val requestTwoAct = 1

    private lateinit var recyclerView: RecyclerView
    private lateinit var eventAdapter: EventAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (Cash.getEvents().size == 0) {
            Cash.addEvent(resources.getString(R.string.defaultNumber),
                resources.getString(R.string.defaultTextDescription),
                resources.getString(R.string.defaultDateTime))
        }

        recyclerView = findViewById(R.id.eventList)
        recyclerView.layoutManager = LinearLayoutManager(this)
        //если знаем за ранее размер списка то true
        recyclerView.setHasFixedSize(false)
        eventAdapter = EventAdapter(this, object : RemoveClickListener {

            override fun removeEvent (positionEvent: Int){
                Cash.getEvents().removeAt(positionEvent)
                if (Cash.getEvents().size == 0) {
                    Cash.addEvent(resources.getString(R.string.defaultNumber),
                        resources.getString(R.string.defaultTextDescription),
                        resources.getString(R.string.defaultDateTime))
                }
                eventAdapter.insertData(Cash.getEvents())
            }
        })


        recyclerView.adapter = eventAdapter
        eventAdapter.insertData(Cash.getEvents())
    }

    // Метод обработки нажатия на кнопку
    fun goToAddEventActivity(view: View?) {
        // действия, совершаемые после нажатия на кнопку
        // Создаем объект Intent для вызова новой Activity
        val intent = Intent(this, AddEventActivity::class.java)
        startActivityForResult(intent, requestTwoAct)
    }

    //метод принимающий результат со второй активити()
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK) {

            eventAdapter.insertData(Cash.getEvents())
        }
    }
}