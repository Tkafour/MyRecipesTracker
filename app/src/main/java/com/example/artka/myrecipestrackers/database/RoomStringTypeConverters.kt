package com.example.artka.myrecipestrackers.database

import androidx.room.TypeConverter

object RoomStringTypeConverters {

    @TypeConverter
    @JvmStatic
    fun fromString(value : String) : List<String> {
        var stringsList : ArrayList<String> = ArrayList<String>()
        for (s in value.split(",")) {
            stringsList.add(s)
        }
        return stringsList.toList()
    }

    @TypeConverter
    @JvmStatic
    fun fromArray(strings : ArrayList<String>) : String {
        var string = ""
        for (s in strings) {
            string += ("$s,")
        }
        return string
    }


}