package com.example.artka.myrecipestrackers.database

import androidx.room.TypeConverter
import com.example.artka.myrecipestrackers.retrofit.apiresponse.Digest
import com.example.artka.myrecipestrackers.retrofit.apiresponse.Ingredient
import com.example.artka.myrecipestrackers.retrofit.apiresponse.TotalDaily
import com.example.artka.myrecipestrackers.retrofit.apiresponse.TotalNutrients
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

        return gson.fromJson(data)
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

        return gson.fromJson(data)
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

        return gson.fromJson(data)
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

        return gson.fromJson(data)
    }

    @TypeConverter
    @JvmStatic
    fun ingredientsListToString(objects : List<Ingredient>) : String {
        return gson.toJson(objects)
    }

    @TypeConverter
    @JvmStatic
    fun stringToTotalDailyList(data : String) : List<TotalDaily> {
        if (data == null) {
            return Collections.emptyList()
        }

        return gson.fromJson(data)
    }

    @TypeConverter
    @JvmStatic
    fun totalDailyListToString(objects : List<TotalDaily>) : String {
        return gson.toJson(objects)
    }

    @TypeConverter
    @JvmStatic
    fun stringToTotalNutrientsList(data : String) : List<TotalNutrients> {
        if (data == null) {
            return Collections.emptyList()
        }

        return gson.fromJson(data)
    }

    @TypeConverter
    @JvmStatic
    fun totalNutrientsListToString(objects : List<TotalNutrients>) : String {
        return gson.toJson(objects)
    }
}