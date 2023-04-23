package com.example.news_domain.news

import com.example.news_data.NewsService
import com.example.news_data.dto.BrandRelatedArticleResponse
import com.example.news_domain.NewsRepository
import com.example.news_domain.di.mapper.toModel
import com.example.news_domain.model.Articles
import io.reactivex.Single

open class NewsDataRepository(val newsService : NewsService) : NewsRepository {
    override fun loadEveryArticle(): Single<Articles> = newsService.getEverything()
        .map(BrandRelatedArticleResponse::toModel)
}