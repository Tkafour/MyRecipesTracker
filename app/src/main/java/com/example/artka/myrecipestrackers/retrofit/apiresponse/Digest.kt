package com.example.artka.myrecipestrackers.retrofit.apiresponse

data class Digest(
        val daily: Double,
        val hasRDI: Boolean,
        val label: String,
        val schemaOrgTag: Any,
        val sub: List<Sub>,
        val tag: String,
        val total: Double,
        val unit: String
)