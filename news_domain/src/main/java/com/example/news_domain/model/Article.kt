package com.example.news_domain.model

import com.example.news_data.dto.SourceResponse
import com.squareup.moshi.Json

class Article (
    var id : String="",
    var name : String="",
    var author :String ="",
    var title :String ="",
    var description :String ="",
    var url :String ="",
    var urlToImage :String ="",
    var publishedAt :String ="",
    var content :String ="",
)