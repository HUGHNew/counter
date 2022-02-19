package com.hugh.counter

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.io.OutputStreamWriter

class MainActivity : AppCompatActivity() {
    private val countersList = ArrayList<CounterBase>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        loadCounters()
        // region counter_list initialization
        val counters:RecyclerView = findViewById(R.id.counter_recycler)
        counters.layoutManager = LinearLayoutManager(this)
        counters.adapter = CounterAdapter(countersList)
        // endregion
    }
    private fun loadCounters(){
        OutputStreamWriter(openFileOutput("1", Context.MODE_PRIVATE)).use {
            for (i in 1..3)
            it.appendLine(i.toString())
        }
        OutputStreamWriter(openFileOutput("2", Context.MODE_PRIVATE)).use {
            for (i in 1..30)
                it.appendLine(i.toString())
        }
        OutputStreamWriter(openFileOutput("3", Context.MODE_PRIVATE)).use {
            for (i in 5..7)
                it.appendLine(i.toString())
        }
        OutputStreamWriter(openFileOutput("4", Context.MODE_PRIVATE)).use {
            for (i in 1..300)
                it.appendLine(i.toString())
        }
        OutputStreamWriter(openFileOutput("5", Context.MODE_PRIVATE)).use {
            for (i in 1..3)
                it.appendLine(i.toString())
        }
        countersList.add(CounterBase("123","1"))
        countersList.add(CounterBase("456","2"))
        countersList.add(CounterBase("123","3"))
        countersList.add(CounterBase("456","4"))
        countersList.add(CounterBase("789","5"))
    }
}