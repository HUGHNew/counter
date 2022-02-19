package com.hugh.counter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CounterDetailAdapter(private val counters:List<String>): RecyclerView.Adapter<CounterDetailAdapter.ViewHolder>()  {
    inner class ViewHolder(view: View):RecyclerView.ViewHolder(view){
        private val textView = view as TextView
        fun bind(data: String){
            textView.text = data
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(android.R.layout.simple_list_item_1, parent, false)
        return ViewHolder(view)
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(counters[position])
    }

    override fun getItemCount(): Int = counters.size
}