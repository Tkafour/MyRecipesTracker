package com.example.artka.myrecipestrackers.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.artka.myrecipestrackers.retrofit.apiresponse.RecipeModel

@Database(entities = [RecipeModel::class], version = 1)
@TypeConverters(RoomObjectConverters::class)
abstract class RecipeRoomDatabase : RoomDatabase() {
    abstract fun recipeDao() : RecipeDao

/*    companion object {
        private var INSTANCE: RecipeRoomDatabase? = null

        fun getInstance(context: Context): RecipeRoomDatabase? {
            if (INSTANCE == null) {
                synchronized(RecipeRoomDatabase::class) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            RecipeRoomDatabase::class.java, "recipe.db")
                            .build()
                }
            }
            return INSTANCE
        }

        fun destroyInstance() {
            INSTANCE = null
        }
    }*/
}