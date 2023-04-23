package com.example.news_domain

import com.example.news_domain.model.Articles
import io.reactivex.Single

interface NewsRepository {
    /**
     * Function used to retrieve default list of all articles can trigger a network call
     *
     * or db call depending on datasource and logic
     *
     * @see [NewsDataRepository]
     */
    fun loadEveryArticle() : Single<Articles>
}