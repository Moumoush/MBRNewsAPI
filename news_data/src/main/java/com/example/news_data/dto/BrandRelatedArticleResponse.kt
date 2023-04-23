package com.example.news_data.dto

import com.squareup.moshi.Json

data class BrandRelatedArticleResponse (
    @field:Json(name = "articles")
    var articles:  List<ArticleResponse> = listOf(),
)