package com.example.artka.myrecipestrackers.retrofit.apiresponse

data class Sub(
        val daily: Double,
        val hasRDI: Boolean,
        val label: String,
        val schemaOrgTag: Any,
        val tag: String,
        val total: Double,
        val unit: String
)