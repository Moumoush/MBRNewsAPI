package com.example.news_domain.di

import com.example.news_data.di.newsDataModule
import com.example.news_domain.NewsRepository
import com.example.news_domain.news.NewsDataRepository
import org.koin.dsl.module


private val newsModule = module {
    factory<NewsRepository>{ NewsDataRepository(get())}
}


val newsDomainModules = listOf(newsModule, newsDataModule)