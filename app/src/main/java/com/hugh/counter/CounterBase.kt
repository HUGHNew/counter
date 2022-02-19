package com.hugh.counter

data class CounterBase(val desc:String,val storage:String,var times: Int = 0,
                       val date_fmt:String="yyyy/MM/dd", val time_fmt:String="hh:mm:ss") {
    operator fun inc() : CounterBase=apply {
        ++times
    }
}