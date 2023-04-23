package com.example.news_data

import com.example.news_data.dto.ArticleResponse
import com.example.news_data.dto.BrandRelatedArticleResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query


interface NewsService {
    @GET("everything")
    fun getEverything(): Single<BrandRelatedArticleResponse>
}