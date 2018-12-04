package com.example.artka.myrecipestrackers.retrofit.apiresponse

data class Params(
        val app_id: List<String>,
        val app_key: List<String>,
        val calories: List<String>,
        val from: List<String>,
        val health: List<String>,
        val q: List<String>,
        val sane: List<Any>,
        val to: List<String>
)