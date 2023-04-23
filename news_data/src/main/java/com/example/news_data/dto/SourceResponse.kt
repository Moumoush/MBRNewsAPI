package com.example.news_data.dto

import com.squareup.moshi.Json

data class SourceResponse(
    @field:Json(name = "id")
    var id : String="",
    @field:Json(name = "name")
    var name : String="",
)