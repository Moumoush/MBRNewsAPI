package com.example.news_domain.news

import com.example.news_data.NewsService
import com.example.news_data.dto.BrandRelatedArticleResponse
import com.example.news_domain.NewsRepository
import com.example.news_domain.mapper.toModel
import com.example.news_domain.model.Articles
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

class NewsDataRepository(val newsService : NewsService) : NewsRepository {
    override fun loadEveryArticle(): Single<Articles> = newsService.getEverything()
        .map(BrandRelatedArticleResponse::toModel)
}