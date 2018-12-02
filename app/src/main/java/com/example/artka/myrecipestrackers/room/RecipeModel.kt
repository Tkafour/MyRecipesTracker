package com.example.artka.myrecipestrackers.room

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


@Entity
@Parcelize
data class RecipeModel(val publisher : String,
                       val f2f_url : String,
                       val title: String,
                       val source_url : String,
                       @field:PrimaryKey val recipe_id : String,
                       val image_url : String,
                       val social_rank : Double,
                       val publisher_url : String) : Parcelable