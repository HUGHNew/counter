package com.hugh.counter

import android.content.Context
import android.content.Intent
import android.os.Build
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.io.*
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class CounterAdapter(private val counters:List<CounterBase>):RecyclerView.Adapter<CounterAdapter.ViewHolder>() {
    private val tag = "CounterAdapter"
    inner class ViewHolder(view: View):RecyclerView.ViewHolder(view){
        val descText : TextView = view.findViewById(R.id.counter_desc)
        val freqBtn : Button = view.findViewById(R.id.counter_time)
        lateinit var storage : String
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_counter,parent,false)
        val viewHolder = ViewHolder(view)
        // region action settings
        viewHolder.descText.setOnClickListener {
            val intent = Intent(parent.context,CounterDetailActivity::class.java)
            intent.putExtra("file",viewHolder.storage)
            parent.context.startActivity(intent)
        }
        viewHolder.freqBtn.setOnClickListener {
            viewHolder.freqBtn.text = (viewHolder.freqBtn.text.toString().toInt() + 1).toString()
            Log.i(tag,"click freq. To save timestamp now")
            val stream = parent.context.openFileOutput(viewHolder.storage, Context.MODE_APPEND)
            BufferedWriter(OutputStreamWriter(stream)).use {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    it.append(DateTimeFormatter.ofPattern("yy/M/dd|HH:mm:ss").format(LocalDateTime.now()))
                }else{
                    it.append("Unix timestamp ${(System.currentTimeMillis()/1000L)}")
                }
                it.newLine()
            }
            Log.i(tag,"click freq. Timestamp saved")
        }
        // endregion
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(counters[position]){
            holder.descText.text = desc
            holder.freqBtn.text = times.toString()
            holder.storage = storage
        }
        Log.i(tag,"set storage:${holder.storage}")
    }

    override fun getItemCount(): Int = counters.size
}