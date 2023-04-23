package com.example.news_domain

import com.example.news_domain.model.Articles
import io.reactivex.Single

interface NewsRepository {
    fun loadEveryArticle() : Single<Articles>
}