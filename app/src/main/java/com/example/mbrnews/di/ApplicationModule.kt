package com.example.mbrnews.di

import com.example.mbrnews.display.news.NewsViewModel
import org.koin.dsl.module

val applicationModule = module {
    factory<NewsViewModel> { NewsViewModel(get()) }
}