package com.example.news_domain.di

import com.example.news_domain.NewsRepository
import com.example.news_domain.news.NewsDataRepository
import org.koin.dsl.module


val newsModule = module {
    factory <NewsRepository>{ NewsDataRepository()}
}