package com.example.news_data.dto

import com.squareup.moshi.Json

data class ArticleResponse (
    @field:Json(name = "source")
    var source:  SourceResponse = SourceResponse(),
    @field:Json(name = "author")
    var author :String? ="",
    @field:Json(name = "title")
    var title :String ="",
    @field:Json(name = "description")
    var description :String ="",
    @field:Json(name = "url")
    var url :String ="",

    @field:Json(name = "urlToImage")
    var urlToImage :String? = "",
    @field:Json(name = "publishedAt")
    var publishedAt :String ="",
    @field:Json(name = "content")
    var content :String ="",
)