package com.example.artka.myrecipestrackers.utils

import android.util.Log
import com.example.artka.myrecipestrackers.BuildConfig

fun Any.debugLog(message : String) {
    if (BuildConfig.DEBUG) {
        Log.d(this.javaClass.simpleName, message)
    }
}