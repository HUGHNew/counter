package com.hugh.counter

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log

class CounterSQLite(private val context: Context, name:String, version:Int)
    : SQLiteOpenHelper(context,name,null,version) {
    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(createCounters)
    }

    override fun onUpgrade(db: SQLiteDatabase, old: Int, new: Int) {
        Log.d(tag,"old version is $old")
        Log.d(tag,"new version is $new")
        for (idx in old until new){
            Log.d(tag,"executing : ${sqlList[idx]}")
            db.execSQL(sqlList[idx])
        }
    }

    companion object {
        const val createCounters = "create table counters(" +
                "id integer primary key autoincrement," +
                "name text not null," +
                "record text," +
                "frequency integer)"
        @Deprecated(
            "don't create table to save records use the file instead",
            ReplaceWith(""),
            level = DeprecationLevel.WARNING
        )
        // no data integrity constraints here
        val createCounterRecords = """
            |create table records(
            |id integer primary key,
            |date text,
            |time text
            |)
        """.trimMargin()
        val sqlList : List<String> = listOf(createCounters)
        const val tag = "SQLite upgrading : "
        const val version_0_1 = 1
    }
}