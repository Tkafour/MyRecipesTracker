package com.example.artka.myrecipestrackers.database

import androidx.room.TypeConverter
import com.example.artka.myrecipestrackers.retrofit.apiresponse.Digest
import com.example.artka.myrecipestrackers.retrofit.apiresponse.Ingredient
import com.example.artka.myrecipestrackers.utils.fromJson
import com.google.gson.Gson
import java.util.*

object RoomObjectConverters {

    var gson = Gson()

    @TypeConverter
    @JvmStatic
    fun stringToObjectList(data : String) : List<Any> {
        if (data == null) {
            return Collections.emptyList()
        }

        val list = gson.fromJson<List<Any>>(data)
        return list
    }

    @TypeConverter
    @JvmStatic
    fun objectListToString(objects : List<Any>) : String {
        return gson.toJson(objects)
    }

    @TypeConverter
    @JvmStatic
    fun stringToStringList(data : String) : List<String> {
        if (data == null) {
            return Collections.emptyList()
        }

        val list = gson.fromJson<List<String>>(data)
        return list
    }

    @TypeConverter
    @JvmStatic
    fun stringListToString(objects : List<String>) : String {
        return gson.toJson(objects)
    }

    @TypeConverter
    @JvmStatic
    fun stringToDigestList(data : String) : List<Digest> {
        if (data == null) {
            return Collections.emptyList()
        }

        val list = gson.fromJson<List<Digest>>(data)
        return list
    }

    @TypeConverter
    @JvmStatic
    fun digestListToString(objects : List<Digest>) : String {
        return gson.toJson(objects)
    }

    @TypeConverter
    @JvmStatic
    fun stringToIngredientsList(data : String) : List<Ingredient> {
        if (data == null) {
            return Collections.emptyList()
        }

        val list = gson.fromJson<List<Ingredient>>(data)
        return list
    }

    @TypeConverter
    @JvmStatic
    fun ingredientsListToString(objects : List<Ingredient>) : String {
        return gson.toJson(objects)
    }
}