package com.example.news_domain.mapper

import com.example.news_data.dto.ArticleResponse
import com.example.news_data.dto.BrandRelatedArticleResponse
import com.example.news_data.dto.SourceResponse
import com.example.news_domain.model.Article
import com.example.news_domain.model.Articles

fun BrandRelatedArticleResponse.toModel() : Articles = Articles(articles.toModel())
fun List<ArticleResponse>.toModel() = map { it.toModel(it.source) }
fun ArticleResponse.toModel(source : SourceResponse) = Article(source.id, source.name, author, title, description, url, urlToImage, publishedAt)