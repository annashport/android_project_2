package com.example.myyyyapplication.data.source.local

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import org.json.JSONObject

@ProvidedTypeConverter
class WorkshopTimeConverter {

    @TypeConverter
    fun scheduledTimeToString(scheduledTime: Map<Int, String>?): String? {
        return scheduledTime?.map { it.key.toString() to it.value }?.toMap()?.let {
            JSONObject(it).toString()
        }
    }

    @TypeConverter
    fun stringToScheduledTime(scheduledTime: String?): Map<Int, String>? {
        return scheduledTime?.let {
            val map = mutableMapOf<Int,String>()
            val json = JSONObject(it)
            json.keys().forEach {
                map.put(it.toInt(), json.getString(it))
            }
            return map
        }
    }
}