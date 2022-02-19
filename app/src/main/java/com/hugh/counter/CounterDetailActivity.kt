package com.hugh.counter

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.io.BufferedReader
import java.io.InputStreamReader

class CounterDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_counter_detail)

        val detail : RecyclerView = findViewById(R.id.counter_detail)
        detail.layoutManager = LinearLayoutManager(this)
        detail.adapter = intent.getStringExtra("file")?.let { loadDetail(it) }
            ?.let { CounterDetailAdapter(it) }
    }
    private fun loadDetail(file:String):List<String>{
        var lines :List<String>?
        BufferedReader(InputStreamReader(openFileInput(file))).use{
            lines = it.readLines()
        }
        return lines!!
    }
}